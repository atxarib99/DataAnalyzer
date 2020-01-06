/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer;

import dataanalyzer.dialog.LambdaMapSettings;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;

/**
 * @author Morgan
 * @author Peter
 * @author Preston
 * @author Dante
 */
public class LambdaMapPanel extends javax.swing.JPanel {

    //Contains the table data and column/row headers 
    private DefaultTableModel table;

    //Contains the information from the char manager
    private CategoricalHashMap dataMap;

    //2D arrays that mirror the dataTableModel that store respective data sets
    private double[][] afrAvgTable;
    private double[][] afrMinTable;
    private double[][] afrMaxTable;
    private double[][] injectorTimingTable;

    //Decimal Formats for rendering floating point integers in the table
    private DecimalFormat afrFormat = new DecimalFormat("##.##");

    //Contains the amount of columns counting the row headers
    private final int columnSize = 25;

    //Containts the amount of rows not counting the column headers
    private final int rowSize = 25;

    //Setting values for JTable
    private int maxRPM;
    private double targetAFR;
    private double afrError;
    private int injectorTimeColorMap;

    //Contains the current lambda view (avg=0, max=1, min=2, injector=3)
    private int currentLambdaView;

    //Lambda constant values
    private static final double MIN_LAMBDA = 0.68;
    private static final double MAX_LAMBDA = 1.36;
    
    JFrame currentFrame;

    /**
     * Creates new form LambdaMap
     */
    public LambdaMapPanel(JFrame currentFrame) {
        //Sets default table setting values
        this.maxRPM = 12500;
        this.targetAFR = 14;
        this.afrError = 1.5;
        this.injectorTimeColorMap = 0;

        //Initializes table application
        initTableModel(maxRPM, 100);
        initComponents();
        
        //Sets current dataAnalyzer frame
        this.currentFrame = currentFrame;
    }

    /**
     * Creates new form LambdaMap with data from a catagoricalHashmap
     *
     * @param dataMap Log data for the car's ECU stored in a CategoricalHashMap
     */
    public LambdaMapPanel(JFrame currentFrame, CategoricalHashMap dataMap) {
        //Passes through table data
        this.dataMap = dataMap;

        //Sets default table setting values
        this.maxRPM = 12500;
        this.targetAFR = 14;
        this.afrError = 1.5;
        this.injectorTimeColorMap = 0;

        //Initializes table application
        initTableModel(maxRPM, 100);
        initComponents();

        //Populates table with passed in data
        updateTables();
        populateTable(afrAvgTable);
        
        //Sets current dataAnalyzer frame
        this.currentFrame = currentFrame;
    }

    /**
     * Creates new form LambdaMap with data from a catagoricalHashmap and sets a
     * new max RPM value, target AFR value and AFR error value.
     *
     * @param dataMap Log data for the car's ECU stored in a CategoricalHashMap
     * @param maxRPM The maximum RPM value in the tables column header
     * @param targetAFR The desired AFR value
     * @param afrError The tolerance values for target values
     */
    public LambdaMapPanel(CategoricalHashMap dataMap, int maxRPM, int targetAFR, int afrError) {
        //Passes through table data
        this.dataMap = dataMap;

        //Sets default table setting values
        this.maxRPM = maxRPM;
        this.targetAFR = targetAFR;
        this.afrError = afrError;
        this.injectorTimeColorMap = 0;

        //Initializes table application
        initTableModel(maxRPM, 100);
        initComponents();

        //Populates table with passed in data
        updateTables();
        populateTable(afrAvgTable);
    }

    /**
     * Initializes the classes DefaultTableModel attribute with row and column
     * headers based on parameters
     *
     * @param columnLimit The last/largest value in the column header sequence
     * @param rowLimit The last/largest value in the row header sequence
     */
    private void initTableModel(int columnLimit, int rowLimit) {
        //Creates 2D array for table model need for the JTable
        //Note: First column contains row headers, not table data
        Object[][] dataTable = new Object[rowSize][columnSize];

        //Creates array just for the column headers
        Object columnHeader[] = new Object[columnSize];

        //Initializes all table data to zero
        for (int row = 0; row < rowSize; row++) {
            for (int col = 1; col < columnSize; col++) {
                dataTable[row][col] = 0;
            }
        }

        //Initializes row headers based on the passed rowLimit value
        for (int i = 0; i < rowSize - 1; i++) {
            if (rowLimit % (rowSize - 1) == 0) {
                dataTable[rowSize - 1 - i][0] = (rowLimit / (rowSize + 1)) * (i + 1);
            } else {
                dataTable[rowSize - 1 - i][0] = (rowLimit / (rowSize)) * (i + 1);
            }
        }
        dataTable[0][0] = rowLimit;

        //Initializes column headers based on the passed colLimit value
        columnHeader[0] = "";
        for (int i = 1; i < columnSize - 1; i++) {
            if (columnLimit % (columnSize - 2) == 0) {
                columnHeader[i] = (columnLimit / (columnSize)) * i;
            } else {
                columnHeader[i] = (columnLimit / (columnSize - 1)) * i;
            }
        }
        columnHeader[columnSize - 1] = columnLimit;

        //Sets table equal to a new DefaultTableModel created from dataTable and columnHeader
        table = new DefaultTableModel(dataTable, columnHeader) {
            //Overrides isCellEditable to make all cells uneditable
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
    }

    /**
     * Squeezes a large range into a defined range
     */
    private int squeeze(double value, int min, int max, int floor, int ceil) {
        return (int) Math.floor(((ceil - floor) * (value - min) * 1.0) / (max - min) + floor);
    }

    /**
     * A linear function (The same as squeeze) to expand the data into its
     * proper range
     */
    private double afr(double lambda) {
        return ((20 - 10) * (lambda - MIN_LAMBDA) * 1.0) / (MAX_LAMBDA - MIN_LAMBDA) + 10;
    }

    /**
     * Runs through the RPM, TPS, Lambda, and FuelOpenTime to update the
     * afrTable and injectorTimingTable to the averaged value across the data
     * set for that rpm and tps
     */
    private void updateTables() {
        LinkedList<LogObject> list = dataMap.getList("Time,RPM");
        LinkedList<LogObject> list2 = dataMap.getList("Time,TPS");
        LinkedList<LogObject> list3 = dataMap.getList("Time,Lambda");
        LinkedList<LogObject> list4 = dataMap.getList("Time,FuelOpenTime");

        afrAvgTable = new double[table.getColumnCount()][table.getRowCount()];
        afrMinTable = new double[table.getColumnCount()][table.getRowCount()];

        for (int x = 0; x < afrMinTable.length; x++) {
            Arrays.fill(afrMinTable[x], Double.MAX_VALUE);
        }

        afrMaxTable = new double[table.getColumnCount()][table.getRowCount()];
        injectorTimingTable = new double[table.getColumnCount()][table.getRowCount()];

        int[][] avg = new int[table.getColumnCount()][table.getRowCount()];

        for (int i = 0; i < list.size(); i++) {
            double rpm = 0, tps = 0, lambda = 0, injectorTime = 0;

            //to make sure the LogObject has a value
            try {
                LogObject rpmObj = list.pop();
                list.addLast(rpmObj);
                rpm = ((SimpleLogObject) rpmObj).value;

                LogObject tpsObj = list2.pop();
                list2.addLast(tpsObj);
                tps = ((SimpleLogObject) tpsObj).value;

                LogObject lambdaObj = list3.pop();
                list3.addLast(lambdaObj);
                lambda = ((SimpleLogObject) lambdaObj).value;

                LogObject injectorObj = list4.pop();
                list4.addLast(injectorObj);
                injectorTime = ((SimpleLogObject) injectorObj).value;
            } catch (Exception e) {
                System.out.println(e);
            }

            if (rpm <= maxRPM) {
                //Finds which column the data should go into
                int column = squeeze(rpm, 0, maxRPM, 0, table.getColumnCount() - 1);
                int row = squeeze(tps, 0, 100, 0, table.getRowCount() - 1);

                //adds the respective value to its slot and increments how many values
                //in that particular slot
                afrAvgTable[column][row] += lambda;
                injectorTimingTable[column][row] += injectorTime;
                avg[column][row] += 1;

                //update Min and Max tables
                afrMinTable[column][row] = Math.min(lambda, afrMinTable[column][row]);
                afrMaxTable[column][row] = Math.max(lambda, afrMaxTable[column][row]);
            }
        }

        //Averages out each slot of the tables
        for (int y = 0; y < table.getColumnCount() - 1; y++) {
            for (int x = 0; x < table.getRowCount(); x++) {
                if (avg[y][x] != 0) {
                    afrMinTable[y][x] = afr(afrMinTable[y][x]);
                    afrMaxTable[y][x] = afr(afrMaxTable[y][x]);
                    afrAvgTable[y][x] = afr(afrAvgTable[y][x] / avg[y][x]);
                    injectorTimingTable[y][x] = injectorTimingTable[y][x] / avg[y][x];
                }
            }
        }
    }

    /**
     * Populates each cell of the jTable with given fuel map
     *
     * @param toSet the fuel map that will populate the JTable
     */
    private void populateTable(double[][] toSet) {
        //Loops through jTable and passed in 2D array
        for (int y = 0; y < table.getColumnCount() - 1; y++) {
            for (int x = 0; x < table.getRowCount(); x++) {
                double dec = 0;
                //Updates dec if the reference array's cell value is not 0 or Double.Max_Value
                if (toSet[y][table.getRowCount() - 1 - x] != 0 && toSet[y][table.getRowCount() - 1 - x] != Double.MAX_VALUE) {
                    dec = toSet[y][table.getRowCount() - 1 - x];
                }
                //Sets jTable's cell vaule equal to the formatted array cell value
                table.setValueAt(afrFormat.format(dec), x, y + 1);
            }
        }
    }

    /**
     * Returns a color on the gradient from blue to green to red for a given a
     * cell value based on how far the cell value is from the target value
     *
     * @param val The value of the cell
     * @param targetAFR The target value of the fuel map
     * @param afrError The allowed error for the target value
     * @return A color value based on how far the cell value is from the target
     */
    private Color getColorVal(double val, double targetAFR, double afrError) {
        //The saturation and brightness values need for the color format HSB
        float saturation = 0.85f;
        float brightness = 0.75f;

        //The amount of units from the target+-error until the color max's out
        double upperBuffer = 2.5;
        double lowerBuffer = 2.5;

        //Checks if value is equal to zero or Double.MAX_VALUE
        if (val == 0 || val == Double.MAX_VALUE) {
            return Color.LIGHT_GRAY;
        //Checks if value is within the target interval
        } else if (val >= targetAFR - afrError && val <= targetAFR + afrError) {
            //Returns a shade of green
            return Color.getHSBColor(0.35f, saturation, brightness + 0.07f);
        //Checks if value is above the target interval
        } else if (val > targetAFR + afrError) {
            //Contains the percentage from target interval to upper buffer
            double offsetPerc = ((val - (targetAFR + afrError)) / upperBuffer);
            //If offset percentage is larger than one set it to one
            if (offsetPerc > 1) {
                offsetPerc = 1;
            }
            //Returns a shade of red
            return Color.getHSBColor(0.25f - (0.25f * (float) offsetPerc), saturation, brightness);
        //Last case, value's below the target interval
        } else {
            //Contains the percentage from target interval to lower buffer
            double offsetPerc = (((targetAFR - afrError) - val) / lowerBuffer);
            //If offset percentage is larger than one set it to one
            if (offsetPerc > 1) {
                offsetPerc = 1;
            }
            //Returns a shade of blue
            return Color.getHSBColor(0.45f + (0.18f * (float) offsetPerc), saturation, brightness);
        }
    }

    /**
     * Returns a color on the gradient from blue to green to red for the target
     * AFR map based on the current lambda view (average, max or min)
     *
     * @param col The column index of the cell
     * @param row The row index of the cell
     * @param targetAFR The target AFR value of the fuel map
     * @param afrError The allowed error for the target value
     * @return A color value based on the selected average, max, or min lambda map
     */
    private Color getInjectorColorVal(int col, int row, double targetAFR, double afrError) {
        switch (injectorTimeColorMap) {
            case 0: //Average lambda view
                return getColorVal(afrAvgTable[col - 1][table.getRowCount() - 1 - row], targetAFR, afrError);
            case 1: //Maximum lambda view
                return getColorVal(afrMaxTable[col - 1][table.getRowCount() - 1 - row], targetAFR, afrError);
            default: //Minimum lambda view
                return getColorVal(afrMinTable[col - 1][table.getRowCount() - 1 - row], targetAFR, afrError);
        }
    }
    
    public void tableSettingCalled(){
        //Creates a lambda map settings dialog box
        LambdaMapSettings settings = new LambdaMapSettings(currentFrame, true, maxRPM, targetAFR, afrError, injectorTimeColorMap);
        settings.setVisible(true);

        //Sets table setting values equal to user updated dialog setting values
        this.maxRPM = settings.getMaxRPM().get();
        this.targetAFR = settings.getTargetAFR().get();
        this.afrError = settings.getAcceptedError().get();
        this.injectorTimeColorMap = settings.getInjectorTimeColorMap().get();

        //Initializes new table model with new max RPM
        initTableModel(maxRPM, 100);

        //Links updated table model to JTable
        jTable.setModel(table);

        //Centers all of the cells in the data table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < jTable.getModel().getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //Updates all tables with new settings
        updateTables();

        //Populates table with previously displayed lambda table
        switch (currentLambdaView) {
            case 0: //Average lambda view
                this.populateTable(afrAvgTable);
                break;
            case 1: //Maximum lambda view
                this.populateTable(afrMaxTable);
                break;
            case 2: //Minimum labda view
                this.populateTable(afrMinTable);
                break;
            default: //Injecotr time view
                this.populateTable(injectorTimingTable);
        }
    }
    
    /**
     * Mouse clicked call for average menu item
     */
    public void averageCalled(){
        currentLambdaView = 0;
        currentViewLabel.setText("Current View: Average Lambda Map");
        populateTable(afrAvgTable);
    }
    
    /**
     * Mouse clicked call for maximum menu item
     */
    public void maxCalled(){
        currentLambdaView = 1;
        currentViewLabel.setText("Current View: Maximum Lambda Map");
        populateTable(afrMaxTable);
    }
    
    /**
     * Mouse clicked call for minimum menu item
     */
    public void minCalled(){
        currentLambdaView = 2;
        currentViewLabel.setText("Current View: Minimum Lambda Map");
        populateTable(afrMinTable);
    }
    
    /**
     * Mouse clicked call for injector time menu item
     */
    public void InjectorTimesCalled(){
        currentLambdaView = 3;
        currentViewLabel.setText("Current View: Injector Timing Map");
        populateTable(injectorTimingTable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jScrollPane = new javax.swing.JScrollPane();
        //custom initialization of jTable
        jTable = new javax.swing.JTable()
        {
            //Overrides prepareRenderer so that the jTable can be formated
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component component = super.prepareRenderer(renderer, row, col);

                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(this, this.getValueAt(row, col), false, false, row, col);
                } else if (currentLambdaView == 3) {
                    component.setBackground(getInjectorColorVal(col, row, targetAFR, afrError));
                    return component;
                } else {
                    component.setBackground(getColorVal(Double.valueOf(this.getValueAt(row, col).toString()), targetAFR, afrError));
                    return component;
                }
            }
        };
        RPMLabel = new javax.swing.JLabel();
        TPSLabel = new javax.swing.JLabel();
        currentViewLabel = new javax.swing.JLabel();
        legendLabel = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        //Renders the row headers
        final JTableHeader header = jTable.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(jTable));

        //sets the row headers
        jTable.setModel(table);
        jTable.setRowHeight(20);
        jTable.setRowSelectionAllowed(false);
        //Centers all of the cells in the data table
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i = 0; i < jTable.getModel().getColumnCount(); i++) {
            jTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //Adjusts the jTables defalut settings
        jTable.setShowVerticalLines(true);
        jTable.setShowHorizontalLines(true);
        jTable.setGridColor(Color.GRAY);
        jScrollPane.setViewportView(jTable);

        RPMLabel.setText("RPM");

        TPSLabel.setText("TPS");

        currentViewLabel.setText("Current View: Average Lambda Map");

        legendLabel.setText("<html><font color='#d11f1f'>■</font> Above Target&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#1fd131'>■</font> At Target&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='#1f46d1'>■</font> Below Target</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RPMLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(legendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                .addComponent(currentViewLabel)
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(TPSLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(RPMLabel)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(TPSLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(currentViewLabel)
                    .addComponent(legendLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RPMLabel;
    private javax.swing.JLabel TPSLabel;
    private javax.swing.JLabel currentViewLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTable jTable;
    private javax.swing.JLabel legendLabel;
    // End of variables declaration//GEN-END:variables
}

/**
 * Table header renderer class for formatting row headers (since row headers are
 * not natively supported by jTable)
 */
class HeaderRenderer implements TableCellRenderer {

    DefaultTableCellRenderer renderer;

    public HeaderRenderer(JTable jTable1) {
        renderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    }
}