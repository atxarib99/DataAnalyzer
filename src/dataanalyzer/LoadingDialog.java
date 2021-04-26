    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer;

import dataanalyzer.InfiniteProgressPanel;
import java.awt.Color;

/**
 *
 * @author aribdhuka
 */
public class LoadingDialog extends javax.swing.JFrame {

    private InfiniteProgressPanel prog;
    /**
     * Creates new form LoadingDialog
     */
    public LoadingDialog(String title) {
        initComponents();
        this.setTitle(title);
        this.setSize(200, 225);
        this.setBackground(Color.BLACK);
        prog = new InfiniteProgressPanel();
        prog.setSize(200, 200);
        this.setContentPane(prog);
        prog.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 62, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void stop() {
        //stop the thread
        prog.stop();
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}