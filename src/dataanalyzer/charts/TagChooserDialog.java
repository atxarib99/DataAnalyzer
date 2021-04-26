/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.charts;

import dataanalyzer.charts.Selection;
import com.arib.categoricalhashtable.CategoricalHashTable;
import com.arib.toast.Toast;
import dataanalyzer.data.CategoricalHashMap;
import dataanalyzer.data.CategorizedValueMarker;
import dataanalyzer.DataAnalyzer;
import dataanalyzer.data.Dataset;
import dataanalyzer.data.Lap;
import dataanalyzer.data.LogObject;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aribdhuka
 */
public class TagChooserDialog extends javax.swing.JDialog {

    //Dataset that the user will have the option from
    LinkedList<Dataset> datasets;
    
    //Data parameters that need to be outputed to the screen
    Selection selection;
    
    //holds if window is active and runngin
    private boolean running;
    
    //main panel for the scroll pane
    JPanel mainPanel;
    
    //All active panels
    LinkedList<TagChooserPanel> activePanels;
    
    public TagChooserDialog(java.awt.Frame parent, LinkedList<Dataset> datasets, Selection selection) {
        super(parent, true);
        initComponents();
        running = true;
        this.selection = selection;
        mainPanel = new JPanel();
        this.datasets = datasets;
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        mainScrollPane.setViewportView(mainPanel);
        activePanels = new LinkedList<>();
        setupPanels();
        
        //on window closing, dispose properly.
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                running = false;
            }
        });
    }
    
    private void setupPanels() {
        //holds number of datasets we have
        int count = 0;
        //add a tag chooser panel for each dataset object
        for(Dataset dataset : datasets) {
            TagChooserPanel panel = new TagChooserPanel(this, dataset);
            mainPanel.add(panel);
            activePanels.add(panel);
            count++;
        }
        //set size of main window
        this.setSize(215*count + 20, this.getHeight());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        applyButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        mainScrollPane = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        applyButton.setBackground(new java.awt.Color(0, 122, 255));
        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 78, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(applyButton))
                    .addComponent(mainScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(applyButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        running = false;
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        //for each active panel, get its selections.
        selection.clearSelections();
        for(TagChooserPanel panel : activePanels) {
            selection.addDatasetSelection(panel.getDatasetSelection());
        }
        running = false;
        this.dispose();
    }//GEN-LAST:event_applyButtonActionPerformed
       
    /**
     * getter for running object
     * @return returns true if the dialog is unfinished 
     */
    public boolean isRunning() {
        return running;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane mainScrollPane;
    // End of variables declaration//GEN-END:variables

}