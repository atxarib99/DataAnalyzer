/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JInternalFrame;

/**
 *
 * @author Peter
 */
public class LambdaMapFrame extends javax.swing.JFrame {
    //class variables
    CategoricalHashMap dataMap;
    DataAnalyzer dataAnalyzer;
    LambdaMapPanel lambdaMapPanel;
    boolean hasData;
    
    /**
     * Creates new form LambdaMapFrame
     */
    public LambdaMapFrame() {
        //initializes LambdaMapFrame
        initComponents();
        
        //sets the content panel of the frame to a LambdaMapPanel
        lambdaMapPanel = new LambdaMapPanel(this);
        this.setContentPane(lambdaMapPanel);
        
        //Disables pop-in buttom when frame is ran independently
        popInMenu.setEnabled(false);
    }
    
    /**
     * Creates new form LambdaMapFrame with a DataAnalyzer frame
     *
     * @param dataAnalyzer current DataAnalyzer frame
     */
    public LambdaMapFrame(DataAnalyzer dataAnalyzer) {
        //initializes LambdaMapFrame
        initComponents();
        
        //sets class variables
        this.dataAnalyzer = dataAnalyzer;
        this.hasData = false;
        
        //sets the content panel of the frame to a LambdaMapPanel
        lambdaMapPanel = new LambdaMapPanel(this);
        this.setContentPane(lambdaMapPanel);
    }
    
    /**
     * Creates new form GPSGraphFrame with a DataAnalyzer frame
     *
     * @param dataAnalyzer current DataAnalyzer frame
     * @param dataMap log data for the car's ECU stored in a CategoricalHashMap
     */
    public LambdaMapFrame(DataAnalyzer dataAnalyzer, CategoricalHashMap dataMap) {
        //initializes LambdaMapFrame
        initComponents();
        
        //sets class variables
        this.dataAnalyzer = dataAnalyzer;
        this.hasData = true;
        this.dataMap = dataMap;
        
        //sets the content panel of the frame to a LambdaMapPanel
        lambdaMapPanel = new LambdaMapPanel(this, dataMap);
        this.setContentPane(lambdaMapPanel);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar = new javax.swing.JMenuBar();
        tableMenu = new javax.swing.JMenu();
        tableSettingsMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        averageLambdaMenuItem = new javax.swing.JMenuItem();
        minimumLambdaMenuItem = new javax.swing.JMenuItem();
        maximumLambdaMenuItem = new javax.swing.JMenuItem();
        injectorTimeMenuItem = new javax.swing.JMenuItem();
        popInMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 650));

        tableMenu.setText("Table");

        tableSettingsMenuItem.setText("Table Settings");
        tableSettingsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableSettingsMenuItemActionPerformed(evt);
            }
        });
        tableMenu.add(tableSettingsMenuItem);

        jMenuBar.add(tableMenu);

        viewMenu.setText("View");

        averageLambdaMenuItem.setText("Average Lambda");
        averageLambdaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                averageLambdaMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(averageLambdaMenuItem);

        minimumLambdaMenuItem.setText("Minimum Lambda");
        minimumLambdaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                minimumLambdaMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(minimumLambdaMenuItem);

        maximumLambdaMenuItem.setText("Maximum Lambda");
        maximumLambdaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maximumLambdaMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(maximumLambdaMenuItem);

        injectorTimeMenuItem.setText("Injector Time");
        injectorTimeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                injectorTimeMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(injectorTimeMenuItem);

        jMenuBar.add(viewMenu);

        popInMenu.setText("  Pop-In  ");
        jMenuBar.add(Box.createHorizontalGlue());
        popInMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popInMenuMouseClicked(evt);
            }
        });
        jMenuBar.add(popInMenu);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tableSettingsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableSettingsMenuItemActionPerformed
        lambdaMapPanel.tableSettingCalled();
    }//GEN-LAST:event_tableSettingsMenuItemActionPerformed

    private void averageLambdaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_averageLambdaMenuItemActionPerformed
        lambdaMapPanel.averageCalled();
    }//GEN-LAST:event_averageLambdaMenuItemActionPerformed

    private void minimumLambdaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_minimumLambdaMenuItemActionPerformed
        lambdaMapPanel.minCalled();
    }//GEN-LAST:event_minimumLambdaMenuItemActionPerformed

    private void maximumLambdaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maximumLambdaMenuItemActionPerformed
        lambdaMapPanel.maxCalled();
    }//GEN-LAST:event_maximumLambdaMenuItemActionPerformed

    private void injectorTimeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_injectorTimeMenuItemActionPerformed
        lambdaMapPanel.InjectorTimesCalled();
    }//GEN-LAST:event_injectorTimeMenuItemActionPerformed

    private void popInMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popInMenuMouseClicked
        JInternalFrame lambdaMapInternalFrame;
        
        //calls the correct constructor based on wheather data has been loaded
        if(hasData){
            lambdaMapInternalFrame = new LambdaMapInternalFrame(dataAnalyzer, dataMap);
        } else {
            lambdaMapInternalFrame = new LambdaMapInternalFrame(dataAnalyzer);
        }
        lambdaMapInternalFrame.setVisible(true);
        
        //adds the LambdaMapInternalFrame to a list, to keep track of them
        dataAnalyzer.getLambdaMap().add(lambdaMapInternalFrame);
        
        //sets the location and size of the LambdaMapInternalFrame
        Dimension frameSize = dataAnalyzer.getSize();
        lambdaMapInternalFrame.setLocation(0, 0);
        lambdaMapInternalFrame.setSize(frameSize.width-15, frameSize.height-70);
        
        //adds the LambdaMapInternalFrame to the DataAnalyzer frame
        dataAnalyzer.getContentPane().add(lambdaMapInternalFrame);
        
        //closes the frame, so that the internal frame can replace it
        dispose();
    }//GEN-LAST:event_popInMenuMouseClicked

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
            java.util.logging.Logger.getLogger(LambdaMapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LambdaMapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LambdaMapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LambdaMapFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LambdaMapFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem averageLambdaMenuItem;
    private javax.swing.JMenuItem injectorTimeMenuItem;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem maximumLambdaMenuItem;
    private javax.swing.JMenuItem minimumLambdaMenuItem;
    private javax.swing.JMenu popInMenu;
    private javax.swing.JMenu tableMenu;
    private javax.swing.JMenuItem tableSettingsMenuItem;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
