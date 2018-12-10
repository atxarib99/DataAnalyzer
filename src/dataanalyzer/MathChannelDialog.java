/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aribdhuka
 */
public class MathChannelDialog extends javax.swing.JFrame {

   
    int lastIndex;
    CategoricalHashMap dataMap;
    VehicleData vehicleData;
    
    /**
     * Creates new form MathChannelDialog
     */
    public MathChannelDialog() {
        initComponents();
        lastIndex = 0;
        dataMap = null;
        //listen to when the caret is updated
        equationField.addCaretListener((CaretEvent e) -> {
            lastIndex = e.getDot();
        });
    }
    
    public MathChannelDialog(CategoricalHashMap dataMap) {
        initComponents();
        lastIndex = 0;
        this.dataMap = dataMap;
        vehicleData = new VehicleData();
        //add the variables into the list
        configureVariablesList();
        //listen to when the caret is updated
        equationField.addCaretListener((CaretEvent e) -> {
            lastIndex = e.getDot();
        });
    }
    
    
    public MathChannelDialog(CategoricalHashMap dataMap, VehicleData vehicleData) {
        initComponents();
        lastIndex = 0;
        this.dataMap = dataMap;
        this.vehicleData = vehicleData;
        //add the variables into the list
        configureVariablesList();
        //listen to when the caret is updated
        equationField.addCaretListener((CaretEvent e) -> {
            lastIndex = e.getDot();
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        channelTitleText = new javax.swing.JTextPane();
        createChannelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        availableVariablesList = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        equationField = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMathChannelMenu = new javax.swing.JMenu();
        editMathChannelMenu = new javax.swing.JMenu();
        insertMathChannelMenu = new javax.swing.JMenu();
        cToFMathChannelMenuItem = new javax.swing.JMenuItem();
        fToCMathChannelMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jScrollPane1.setToolTipText("Channel Name");

        channelTitleText.setToolTipText("Channel Title");
        channelTitleText.setName(""); // NOI18N
        jScrollPane1.setViewportView(channelTitleText);

        createChannelButton.setText("Create Channel");
        createChannelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createChannelButtonPressed(evt);
            }
        });

        availableVariablesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(availableVariablesList);

        jLabel1.setText("Variables available to use");

        equationField.setColumns(20);
        equationField.setRows(5);
        jScrollPane3.setViewportView(equationField);

        jLabel2.setText("Channel Title:");

        fileMathChannelMenu.setText("File");
        jMenuBar1.add(fileMathChannelMenu);

        editMathChannelMenu.setText("Edit");
        jMenuBar1.add(editMathChannelMenu);

        insertMathChannelMenu.setText("Insert");

        cToFMathChannelMenuItem.setText("CelciusToFarenheit");
        cToFMathChannelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cToFMathChannelMenuItemActionPerformed(evt);
            }
        });
        insertMathChannelMenu.add(cToFMathChannelMenuItem);

        fToCMathChannelMenuItem.setText("FarenheitToCelcius");
        fToCMathChannelMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fToCMathChannelMenuItemActionPerformed(evt);
            }
        });
        insertMathChannelMenu.add(fToCMathChannelMenuItem);

        jMenuBar1.add(insertMathChannelMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(createChannelButton)
                        .addGap(136, 136, 136))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(createChannelButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createChannelButtonPressed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createChannelButtonPressed
        //variable that handles if equation was parsed properly so far
        
        if(channelTitleText.getText().isEmpty()) {
            // error message displayed
            MessageBox error = new MessageBox("Error: Equation not parsed properly");
            return;
        }
        //remove all spaces from string.
        String eq = equationField.getText();
        //Check the validity of the string
        EquationEvaluater.evaluate(eq, dataMap, vehicleData, channelTitleText.getText());
        this.dispose();
    }//GEN-LAST:event_createChannelButtonPressed

    private void cToFMathChannelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cToFMathChannelMenuItemActionPerformed
        //insert the celsius to farenheit equation here
        equationField.setText(equationField.getText() + "((VAR_HERE)*(9/5))+32");
    }//GEN-LAST:event_cToFMathChannelMenuItemActionPerformed

    private void fToCMathChannelMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fToCMathChannelMenuItemActionPerformed
        //insert the farenheit to celsius equation here
        equationField.setText(equationField.getText() + "($(Time,Coolant)-32)*(5/9)");
    }//GEN-LAST:event_fToCMathChannelMenuItemActionPerformed

    //Update variables list, handle list onclicks
    private void configureVariablesList() {
        //set the variables list to all the tags from the datamap
        ArrayList<String> variablesList = new ArrayList<>();
        variablesList.addAll(dataMap.tags);
        variablesList.addAll(vehicleData.getKeySet());
        availableVariablesList.setListData(variablesList.toArray(new String[dataMap.tags.size() + vehicleData.getKeySet().size()]));
        
        //on click of an item of the variables list
        availableVariablesList.addListSelectionListener((ListSelectionEvent e) -> {
            //if the value is not adjusting elsewhere, and the selected value is not null
            if(!e.getValueIsAdjusting() && availableVariablesList.getSelectedValue() != null) {
                //get the string of the equation field
                String str = equationField.getText();
                if(availableVariablesList.getSelectedValue().contains(","))
                    //insert the variable string into the string
                    str = str.substring(0, lastIndex) + "$(" + availableVariablesList.getSelectedValue() + ")" + str.substring(lastIndex);
                else
                    str = str.substring(0, lastIndex) + "&(" + availableVariablesList.getSelectedValue() + ")" + str.substring(lastIndex);
                //set the text value of the field
                equationField.setText(str);
                //clear the list selection
                availableVariablesList.clearSelection();
                //request focus back to the equation field
                equationField.requestFocus();
            }
        });
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MathChannelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MathChannelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MathChannelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MathChannelDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MathChannelDialog().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> availableVariablesList;
    private javax.swing.JMenuItem cToFMathChannelMenuItem;
    private javax.swing.JTextPane channelTitleText;
    private javax.swing.JButton createChannelButton;
    private javax.swing.JMenu editMathChannelMenu;
    private javax.swing.JTextArea equationField;
    private javax.swing.JMenuItem fToCMathChannelMenuItem;
    private javax.swing.JMenu fileMathChannelMenu;
    private javax.swing.JMenu insertMathChannelMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
