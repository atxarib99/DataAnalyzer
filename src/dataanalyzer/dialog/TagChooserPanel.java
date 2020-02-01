/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.dialog;

import dataanalyzer.DatasetSelection;
import com.arib.toast.Toast;
import dataanalyzer.DataAnalyzer;
import dataanalyzer.Dataset;
import dataanalyzer.Lap;
import dataanalyzer.LogObject;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aribdhuka
 */
public class TagChooserPanel extends javax.swing.JPanel {

    //the dataset that this panel will deal with
    Dataset dataset;
    
    //arrays that hold current choices
    String[] chosenTags;
    int[] chosenLaps;
    
    String[] titles;
    
    //Parent
    TagChooserDialog parent;

        //String array that populates the categories list
    //TODO: add tags to each list element.
    AnalysisCategory[] analysisCategories = new AnalysisCategory[] { 
        new AnalysisCategory("Brakes").addTag("Time,BrakePressureFront").addTag("Time,BrakePressureRear").addTag("Time,xAccel").addTag("Time,yAccel").addTag("Time,zAccel"),
        new AnalysisCategory("Brake Balance").addTag("Time,BrakePressureFront").addTag("Time,BrakePressureRear").addTag("Time,BrakeBalance"),
        new AnalysisCategory("Coolant").addTag("Time,Coolant").addTag("Time,RadiatorInlet"), 
        new AnalysisCategory("Acceleration").addTag("Time,xAccel").addTag("Time,yAccel").addTag("Time,zAccel").addTag("Time,RPM").addTag("Time,WheelspeedFront").addTag("Time,WheelspeedRear"),
        new AnalysisCategory("Endurance").addTag("Time,RPM"), 
        new AnalysisCategory("Skidpad")};
    /**
     * Creates new form TagChooserPanel
     */
    public TagChooserPanel(TagChooserDialog parent, Dataset dataset) {
        this.parent = parent;
        this.dataset = dataset;
        initComponents();
        datasetNameLabel.setText(dataset.getName());
        fillDataList(dataset.getDataMap().getTags());
    }

    //returns a dataset selection based on the users selection
    public DatasetSelection getDatasetSelection() {
        ArrayList<String> tags = new ArrayList<>();
        ArrayList<Integer> laps = new ArrayList<>();
        if(chosenTags != null) {
            for(String s : chosenTags)
                tags.add(s);
        }
        if(chosenLaps != null) {
            for(int l : chosenLaps)
                laps.add(l);
        }
        DatasetSelection ds = new DatasetSelection(dataset, tags, laps);
        return ds;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lapList = new javax.swing.JList<>();
        datasetNameLabel = new javax.swing.JLabel();

        searchField.setToolTipText("Search");
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        jScrollPane3.setMaximumSize(new java.awt.Dimension(0, 0));
        jScrollPane3.setMinimumSize(new java.awt.Dimension(0, 0));

        categoryList.setSize(new java.awt.Dimension(177, 298));
        categoryList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                categoryListKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(categoryList);

        jScrollPane1.setMaximumSize(new java.awt.Dimension(0, 0));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(0, 0));

        dataList.setSize(new java.awt.Dimension(177, 298));
        dataList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dataListKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(dataList);

        lapList.setSize(new java.awt.Dimension(177, 128));
        lapList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lapListMouseClicked(evt);
            }
        });
        lapList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lapListKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(lapList);

        datasetNameLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datasetNameLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(datasetNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        //if the key is alphabetic
        if(Character.isAlphabetic(evt.getKeyChar()) &&
            Character.isLetter(evt.getKeyChar())) {
            //if the titles array is not null
            if(titles != null && titles.length > 0) {
                //create array list of new titles that will hold all matches
                ArrayList<String> newTitles = new ArrayList<>();

                //holds if all titles were properly initialized
                boolean properInit = true;
                //for each array element of titles array
                for(String s : titles) {
                    if(s == null) {
                        properInit = false;
                        break;
                    }
                    //if the element contains the search box text
                    if(s.contains(searchField.getText())) {
                        //add it to the array list
                        newTitles.add(s);
                    }
                }

                if(properInit)
                //set the data list view to all the elements of the array list
                dataList.setListData(newTitles.toArray(new String[newTitles.size()]));
            }
        }

        //if the search field becomes empty, set the data list to the original list
        if(searchField.getText().isEmpty()) {
            dataList.setListData(titles);
        }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void categoryListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categoryListKeyReleased

    }//GEN-LAST:event_categoryListKeyReleased

    private void dataListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dataListKeyReleased
        //if the dataList has focus and a key is pressed
        //get the key code
        int code = evt.getKeyCode();
        //if the data list has an index that is selected
        if(dataList.getSelectedIndex() > -1) {
            //depending on the code
            switch(code) {
                //if its backspace, remove the item from the datamap
                case KeyEvent.VK_DELETE :
                case KeyEvent.VK_BACK_SPACE : deleteSelected("tag"); break;
            }
        }
    }//GEN-LAST:event_dataListKeyReleased

    private void lapListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lapListMouseClicked
        //Determine how many clicks, and if right click: open edit Lap dialog
        //get list
        JList list = (JList) evt.getSource();
        //if two clicks
        if (SwingUtilities.isRightMouseButton(evt)) {
            //if more than one is selected, toast that only one should be selected to edit
            int selectedCount = list.getSelectedIndices().length;
            if(selectedCount > 1) {
                Toast.makeToast(this, "Please select one list to open edit dialog", Toast.DURATION_LONG);
            }

            //get the list model to get element at index
            ListModel model = list.getModel();
            //get indices of items selected
            int selectedIndex = list.getSelectedIndex();
            //holds how many different domains are in selected
            int domainCount = 0;
            //get string at index
            String selected = ""+model.getElementAt(selectedIndex);

            //get lap number
            int lapNumber = Integer.parseInt(selected.substring(0, selected.indexOf('(')));
            Lap toEdit = getLapFromLapNumber(lapNumber);

            //calculate used laps
            ArrayList<Integer> usedLaps = new ArrayList<>();
            for(Lap l : dataset.getLapBreaker()) {
                usedLaps.add(l.getLapNumber());
            }
            //remove current lap
            usedLaps.remove(new Integer(lapNumber));
            //TODO:CHECK IF TEMP JFRAME WORKS
            JFrame tempFrame = new JFrame();
            tempFrame.setVisible(false);
            //create dialog
            LapDataDialog ldd = new LapDataDialog(tempFrame, true, toEdit, usedLaps);
            ldd.setVisible(true);
            //while the dialog is running
            while(ldd.isRunning()) {
                try {
                    Thread.currentThread().wait(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TagChooserDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //apply the lap data to the datasets
            Lap.applyToDataset(dataset.getDataMap(), dataset.getLapBreaker());

            //fill lap list
            fillDataList(dataset.getDataMap().getTags());
        }
    }//GEN-LAST:event_lapListMouseClicked

    private void lapListKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lapListKeyReleased
        //if the lapList has focus and a key is pressed
        //get the key code
        int code = evt.getKeyCode();
        //if the lap list has an index that is selected
        if(lapList.getSelectedIndex() > -1) {
            //depending on the code
            switch(code) {
                //if its backspace or delete, remove the item from the lapBreaker and update the Lap for the data objects.
                case KeyEvent.VK_DELETE :
                case KeyEvent.VK_BACK_SPACE : deleteSelected("lap"); break;
            }
        }
    }//GEN-LAST:event_lapListKeyReleased

    private void fillDataList(ArrayList<String> allTags) {
        // Use the tags list to get the title for each tag
        titles = new String[allTags.size()];

        // Make a list of titles
        // Get (Title)"RPM vs Time" from (Tag)"Time, RPM"
        String str = "";
        for (int i = 0; i < titles.length; i++) {
            str = "";
            str += allTags.get(i).split(",")[1];
            str += " vs ";
            str += allTags.get(i).split(",")[0];
            titles[i] = str;
        }
        // Add the list of titles to the data List View 
        dataList.setListData(titles);
        
        //fill lap data
        String[] lapData = new String[dataset.getLapBreaker().size()];
        for(int i = 0; i < lapData.length; i++) {
            lapData[i] = dataset.getLapBreaker().get(i).toString();
        }
        lapList.setListData(lapData);
        
        //allow multiple selections and deselect
        lapList.setSelectionModel(new DefaultListSelectionModel() {
            private static final long serialVersionUID = 1L;

            boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(!gestureStarted){
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (isAdjusting == false) {
                    gestureStarted = false;
                }
            }
        });
        
        //allow multiple selections and deselect for category list
        categoryList.setSelectionModel(new DefaultListSelectionModel() {
            private static final long serialVersionUID = 1L;

            boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(!gestureStarted){
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (isAdjusting == false) {
                    gestureStarted = false;
                }
            }
        });
        
        //allow multiple selections and deselect for category list
        dataList.setSelectionModel(new DefaultListSelectionModel() {
            private static final long serialVersionUID = 1L;

            boolean gestureStarted = false;

            @Override
            public void setSelectionInterval(int index0, int index1) {
                if(!gestureStarted){
                    if (isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
                gestureStarted = true;
            }

            @Override
            public void setValueIsAdjusting(boolean isAdjusting) {
                if (isAdjusting == false) {
                    gestureStarted = false;
                }
            }
        });

        // If another item is selected in the data combo box, change the chart
        dataList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if(dataList.getSelectedIndex() != -1) {
                        int[] selected = dataList.getSelectedIndices();
                        chosenTags = new String[selected.length];
                        for(int i = 0; i < chosenTags.length; i++){
                            chosenTags[i] = allTags.get(selected[i]);
                        }
                      
                    }
                }
            }
        });

        // If a different or another lap is selected, change the graph accordingly
        lapList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if(dataList.getSelectedIndex() != -1) {
                        int[] selected;
                        if(lapList.getSelectedIndex() != -1) {
                            selected = lapList.getSelectedIndices();
                            chosenLaps = new int[selected.length];
                            if(chosenLaps.length > 0) {
                                ArrayList<String> selectedLaps = (ArrayList) lapList.getSelectedValuesList();
                                for(int i = 0; i < chosenLaps.length; i++) {
                                    chosenLaps[i] = Integer.parseInt(selectedLaps.get(i).charAt(0) + "");
                                }
                            }
                        } else {
                            chosenLaps = null;
                        }
                    }
                }
            }
        });
        
        //what to do when a category is selected
        categoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!e.getValueIsAdjusting()) {
                    //if an item is selected
                    if(categoryList.getSelectedIndex() != -1) {
                        //get the category selected
                        ArrayList<String> selectedCategory = (ArrayList) categoryList.getSelectedValuesList();
                        ArrayList<String> compatibleTags = new ArrayList<>();
                        for(String s : selectedCategory) {
                            AnalysisCategory cat = getCategoryFromString(s);
                            for(String tag : cat.TAG) {
                                if(dataset.getDataMap().getTags().contains(tag) && !compatibleTags.contains(tag))
                                    compatibleTags.add(tag);
                            }
                        }
                        
                        titles = new String[compatibleTags.size()];
                        String str;
                        for (int i = 0; i < titles.length; i++) {
                            str = "";
                            str += compatibleTags.get(i).split(",")[1];
                            str += " vs ";
                            str += compatibleTags.get(i).split(",")[0];
                            titles[i] = str;
                        }
                        
                        dataList.setListData(titles);
                        

                    } else {
                        //reset tags
                        // Use the tags list to get the title for each tag
                        titles = new String[allTags.size()];

                        // Make a list of titles
                        // Get (Title)"RPM vs Time" from (Tag)"Time, RPM"
                        String str = "";
                        for (int i = 0; i < titles.length; i++) {
                            str = "";
                            str += allTags.get(i).split(",")[1];
                            str += " vs ";
                            str += allTags.get(i).split(",")[0];
                            titles[i] = str;
                        }
                        // Add the list of titles to the data List View 
                        dataList.setListData(titles);
                    }
                }
            }
        });
    }
    
    /**
     * Returns the Lap object from the lapBreaker list given a lapNumber
     * @param lapNumber the lapNumber we are looking for
     * @return the Lap with the given lapNumber, null if not found
     */
    private Lap getLapFromLapNumber(int lapNumber) {
        Lap toReturn = null;
        for(Lap l : dataset.getLapBreaker()) {
            if(l.getLapNumber() == lapNumber)
                toReturn = l;
            break;
        }
        return toReturn;
    }
    
    /**
     * Given a string which is representative of which menu item to delete from
     * get the current selected item(s) and delete them
     * handle any other actions required
     * @param which which menu to delete from
     */
    private void deleteSelected(String which) {
        boolean shouldDelete = true;
        if(!which.equals("marker")) {
            shouldDelete = DataAnalyzer.createConfirmDialog("Delete " + which + "?", "Are you sure you want to delete? This action cannot be undone.");
        }
        if(!shouldDelete)
            return;
        if(which.equals("tag")) {
            for(String title : dataList.getSelectedValuesList()) {
                dataset.getDataMap().remove(titleToTag(title)[0]);
            }
        } else if(which.equals("lap")) {
            //holds which laps we are deleting
            ArrayList<Integer> deleted = new ArrayList<>();
            
            //for each selected lap, delete and add to list of deleted
            for(String lap : lapList.getSelectedValuesList()) {
                deleted.add(Integer.parseInt(lap.charAt(0) + ""));
                dataset.getLapBreaker().remove(getLapFromLapNumber(Integer.parseInt(lap.charAt(0) + "")));
            }
            
            //holds the number of tags
            int tagSize = dataset.getStaticMarkers().getTags().size();
            //for each tag
            for(int iTag = 0; iTag < tagSize; iTag++) {
                //get number of markers in this tag
                int markerSize = dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)).size();
                //for each lap
                for(int lapnumber : deleted) {
                    //for each marker
                    for(int iMarker = 0; iMarker < markerSize; iMarker++) {
                        //if is a start or end marker for the recently deleted lap
                        if(dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)).get(iMarker).getNotes().equals("Start Lap" + lapnumber) || dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)).get(iMarker).getNotes().equals("End Lap" + lapnumber)) {
                            //delete marker
                            dataset.getStaticMarkers().remove(dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)).get(iMarker));
                            //move back index to check
                            iMarker--;
                            //update sizes, markersize will have changed, tags may have if last marker was deleted
                            int lastTagSize = tagSize;
                            tagSize = dataset.getStaticMarkers().getTags().size();
                            if(tagSize != lastTagSize) {
                                markerSize = 0;
                                iTag--;
                            } else if(iTag < tagSize) {
                                if(dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)) != null) {
                                    markerSize = dataset.getStaticMarkers().getList(dataset.getStaticMarkers().getTags().get(iTag)).size();
                                }
                            }
                        }
                    }
                }
            }
            
            //update UI
            fillDataList(dataset.getDataMap().getTags());
            Lap.applyToDataset(dataset.getDataMap(), dataset.getLapBreaker());
        }
    }
    
    /**
     * Gets the tag from the active chart and formats it into a String array of TAGs
     * @return String of the TAGs of the active charts
     */
    private String[] titleToTag() {
        return titleToTag("");
    }
    
    //given a chart title or dataList title we can create the tag
    /**
     * Reformats a title into a String array of TAGs
     * @param title String value of the title of a chart
     * @return String value of the TAGs from the title given
     */
    private String[] titleToTag(String title) {
        //if empty get from chart
        if(title.isEmpty()) {
            return null;
        }
        
        //create array of tags
        String[] titleSplit = title.split(" vs ");
        String[] tags = new String[titleSplit.length - 1];
        for (int i = 0; i < titleSplit.length - 1; i++) {
            tags[i] = titleSplit[titleSplit.length - 1] + "," + titleSplit[i];
        }
        
        return tags;
    }

    /**
     * Iterates through the analysis categories list and finds the AnalysisCategory that matches the title provided in the paramter
     * @param category The title of the category
     * @return AnalysisCategory who's title matches the parameter, null if not found
     */
    private AnalysisCategory getCategoryFromString(String category) {
        for(AnalysisCategory cat : analysisCategories) {
            if(cat.title.equals(category))
                return cat;
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> categoryList;
    private javax.swing.JList<String> dataList;
    private javax.swing.JLabel datasetNameLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lapList;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
    private class AnalysisCategory {
        String title;
        ArrayList<String> TAG;
        
        public AnalysisCategory() {
            title = "";
            TAG = new ArrayList<>();
        }
        
        public AnalysisCategory(String title) {
            this.title = title;
            TAG = new ArrayList<String>();
        }
        
        public AnalysisCategory(String title, ArrayList<String> TAG) {
            this.title = title;
            this.TAG = TAG;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public ArrayList<String> getTAG() {
            return TAG;
        }

        public void setTAG(ArrayList<String> TAG) {
            this.TAG = TAG;
        }
        
        public AnalysisCategory addTag(String elem) {
            TAG.add(elem);
            return this;
        }
    }
}

