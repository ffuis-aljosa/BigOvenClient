package bigovenclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainFrame extends javax.swing.JFrame {

    BigOvenAPI api = new BigOvenAPI("dvx3l5Ep57z942tATiUC7x8E9J8E50vz");
    ArrayList<Recipe> recipes;
    
    public MainFrame() {
        initComponents();
        
        recipeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = e.getFirstIndex();
                
                int id = recipes.get(index).getId();
                
                try {
                    Recipe fullRecipe = api.getRecipe(id);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        masterPanel = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        recipeList = new javax.swing.JList();
        detailPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchTextField.setToolTipText("Search recipes");

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(recipeList);

        javax.swing.GroupLayout masterPanelLayout = new javax.swing.GroupLayout(masterPanel);
        masterPanel.setLayout(masterPanelLayout);
        masterPanelLayout.setHorizontalGroup(
            masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(masterPanelLayout.createSequentialGroup()
                        .addComponent(searchTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton)))
                .addContainerGap())
        );
        masterPanelLayout.setVerticalGroup(
            masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(masterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(masterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout detailPanelLayout = new javax.swing.GroupLayout(detailPanel);
        detailPanel.setLayout(detailPanelLayout);
        detailPanelLayout.setHorizontalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        detailPanelLayout.setVerticalGroup(
            detailPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(masterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(masterPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(detailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try {
            recipes = api.searchRecipes(searchTextField.getText(), 1, 20);
            DefaultListModel listModel = new DefaultListModel();
            
            for (int i = 0; i < recipes.size(); i++) {
                listModel.add(i, recipes.get(i));
            }
            
            recipeList.setModel(listModel);
            
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel detailPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel masterPanel;
    private javax.swing.JList recipeList;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    // End of variables declaration//GEN-END:variables
}
