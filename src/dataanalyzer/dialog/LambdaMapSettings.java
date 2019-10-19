/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.dialog;

import dataanalyzer.Referencer;

/**
 *
 * @author aribdhuka
 */
public class LambdaMapSettings extends javax.swing.JDialog {

    private static int maxRPM;
    private static double targetAFR;
    private static double acceptedError;

    /**
     * Creates new form LambdaMapSettings
     */
    public LambdaMapSettings(java.awt.Frame parent, boolean modal, int maxRPM, double targetAFR, double acceptedError) {
        super(parent, modal);
        this.maxRPM = maxRPM;
        this.targetAFR = targetAFR;
        this.acceptedError = acceptedError;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maxRPMLabel = new javax.swing.JLabel();
        maxRpmField = new javax.swing.JTextField();
        targetAFRLabel = new javax.swing.JLabel();
        targetAfrField = new javax.swing.JTextField();
        afrErrorLabel = new javax.swing.JLabel();
        afrOffsetField = new javax.swing.JTextField();
        cancelButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        maxRPMLabel.setText("Max RPM");

        maxRpmField.setText(String.valueOf(maxRPM));
        maxRpmField.setToolTipText("Enter the Max RPM setting of the ECU");

        targetAFRLabel.setText("Target AFR");

        targetAfrField.setText(String.valueOf(targetAFR));
        targetAfrField.setToolTipText("Enter the desired AFR value you are looking for");

        afrErrorLabel.setText("Acceptable AFR Error");

        afrOffsetField.setText(String.valueOf(acceptedError));
        afrOffsetField.setToolTipText("Enter the Accepted error for the desired AFR you entered above");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Apply");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsApplied(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(afrErrorLabel)
                    .addComponent(targetAFRLabel)
                    .addComponent(maxRPMLabel)
                    .addComponent(maxRpmField)
                    .addComponent(targetAfrField)
                    .addComponent(afrOffsetField))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(maxRPMLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxRpmField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(targetAFRLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(targetAfrField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(afrErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(afrOffsetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        maxRpmField.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void settingsApplied(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsApplied
        //TODO: Input sanatizing
        maxRPM = Integer.parseInt(maxRpmField.getText());
        targetAFR = Double.parseDouble(targetAfrField.getText());
        acceptedError = Double.parseDouble(afrOffsetField.getText());
        this.dispose();
    }//GEN-LAST:event_settingsApplied

    public Referencer<Integer> getMaxRPM() {
        return new Referencer(maxRPM);
    }

    public Referencer<Double> getTargetAFR() {
        return new Referencer(targetAFR);
    }

    public Referencer<Double> getAcceptedError() {
        return new Referencer(acceptedError);
    }

    public void close() {
        this.dispose();
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
            java.util.logging.Logger.getLogger(LambdaMapSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LambdaMapSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LambdaMapSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LambdaMapSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LambdaMapSettings dialog = new LambdaMapSettings(new javax.swing.JFrame(), true, maxRPM, targetAFR, acceptedError);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel afrErrorLabel;
    private javax.swing.JTextField afrOffsetField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel maxRPMLabel;
    private javax.swing.JTextField maxRpmField;
    private javax.swing.JLabel targetAFRLabel;
    private javax.swing.JTextField targetAfrField;
    // End of variables declaration//GEN-END:variables
}
