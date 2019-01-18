/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering.boundary;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.SingularValueDecomposition;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pcaclustering.control.DBI;
import pcaclustering.control.KMeans;
import pcaclustering.control.MatrixOperator;
import pcaclustering.control.PCA;
import pcaclustering.control.Pembobotan;
import pcaclustering.control.ReduksiDimensiSVD;

/**
 *
 * @author WIN8
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    
    private Pembobotan bobot;
    private final MatrixOperator matrixGen;
    
    public MainFrame() {
        initComponents();
        matrixGen =  new MatrixOperator();
        radioButtonKMeans.setEnabled(false);
        radioButtonDynamicKmeans.setEnabled(false);
        radioButtonKMeansSVD.setEnabled(false);
        radioButtonKMeansPCA.setEnabled(false);
        buttonCluster.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupCluster = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        buttonMuatDokumen = new javax.swing.JButton();
        textFieldMuatDokumen = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        radioButtonKMeans = new javax.swing.JRadioButton();
        radioButtonKMeansSVD = new javax.swing.JRadioButton();
        radioButtonKMeansPCA = new javax.swing.JRadioButton();
        radioButtonDynamicKmeans = new javax.swing.JRadioButton();
        labelJumlahCluster = new javax.swing.JLabel();
        textfieldJumlahCluster = new javax.swing.JTextField();
        labelKideal = new javax.swing.JLabel();
        nilaiKideal = new javax.swing.JLabel();
        buttonCluster = new javax.swing.JButton();
        labelJumlahTerm = new javax.swing.JLabel();
        labelJumlahDokumen = new javax.swing.JLabel();
        labelDBI = new javax.swing.JLabel();
        textFieldTerm = new javax.swing.JTextField();
        textFieldDokumen = new javax.swing.JTextField();
        textFieldDBI = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCluster = new javax.swing.JTable();
        labelIterasi = new javax.swing.JLabel();
        iterasi = new javax.swing.JLabel();
        labelWaktuEksekusi = new javax.swing.JLabel();
        waktuEksekusi = new javax.swing.JLabel();
        labelClusterMethod = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonMuatDokumen.setText("Muat");
        buttonMuatDokumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMuatDokumenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonMuatDokumen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textFieldMuatDokumen)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonMuatDokumen)
                    .addComponent(textFieldMuatDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        buttonGroupCluster.add(radioButtonKMeans);
        radioButtonKMeans.setText("KMeans");

        buttonGroupCluster.add(radioButtonKMeansSVD);
        radioButtonKMeansSVD.setText("KMeans + SVD");

        buttonGroupCluster.add(radioButtonKMeansPCA);
        radioButtonKMeansPCA.setText("KMeans + PCA");

        buttonGroupCluster.add(radioButtonDynamicKmeans);
        radioButtonDynamicKmeans.setText("Dynamic KMeans");
        radioButtonDynamicKmeans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButtonDynamicKmeansActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioButtonKMeansPCA)
                    .addComponent(radioButtonKMeansSVD)
                    .addComponent(radioButtonKMeans)
                    .addComponent(radioButtonDynamicKmeans))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radioButtonKMeans)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(radioButtonDynamicKmeans)
                .addGap(18, 18, 18)
                .addComponent(radioButtonKMeansSVD, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioButtonKMeansPCA)
                .addGap(15, 15, 15))
        );

        labelJumlahCluster.setText("Jumlah Cluster (k):");

        labelKideal.setText("k Ideal:");

        buttonCluster.setText("Mulai");
        buttonCluster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonClusterActionPerformed(evt);
            }
        });

        labelJumlahTerm.setText("Jumlah Term:");

        labelJumlahDokumen.setText("Jumlah Dokumen:");

        labelDBI.setText("DBI:");

        textFieldTerm.setEditable(false);

        textFieldDokumen.setEditable(false);

        textFieldDBI.setEditable(false);

        tableCluster.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableCluster);

        labelIterasi.setText("Iterasi:");

        labelWaktuEksekusi.setText("Waktu Eksekusi:");

        labelClusterMethod.setText("Metode Cluster:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(labelIterasi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iterasi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelWaktuEksekusi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(waktuEksekusi)
                .addGap(176, 176, 176))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelJumlahCluster)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                    .addComponent(textfieldJumlahCluster, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(labelKideal)
                                    .addGap(18, 18, 18)
                                    .addComponent(nilaiKideal))
                                .addComponent(buttonCluster, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labelClusterMethod))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelJumlahTerm)
                                    .addComponent(textFieldTerm, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelJumlahDokumen)
                                    .addComponent(textFieldDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelDBI)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(textFieldDBI)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelJumlahTerm)
                    .addComponent(labelJumlahDokumen)
                    .addComponent(labelDBI)
                    .addComponent(labelClusterMethod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelJumlahCluster)
                            .addComponent(textfieldJumlahCluster, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKideal)
                            .addComponent(nilaiKideal))
                        .addGap(36, 36, 36)
                        .addComponent(buttonCluster))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textFieldTerm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldDokumen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textFieldDBI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelIterasi)
                        .addComponent(iterasi))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelWaktuEksekusi)
                        .addComponent(waktuEksekusi)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonMuatDokumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMuatDokumenActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(""));
        chooser.setDialogTitle("Pilih Folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            try {
                String selectedFolder = String.valueOf(chooser.getSelectedFile());
                textFieldMuatDokumen.setText(selectedFolder);
                System.out.println("Selected Folder: " + selectedFolder);
                bobot = new Pembobotan(selectedFolder);
                bobot.doPembobotan();
                textFieldTerm.setText(String.valueOf(bobot.getGlobalTermList().getTotalTerm()));
                textFieldDokumen.setText(String.valueOf(bobot.getListDocument().size()));
                
                radioButtonKMeans.setEnabled(true);
                radioButtonDynamicKmeans.setEnabled(true);
                radioButtonKMeansSVD.setEnabled(true);
                radioButtonKMeansPCA.setEnabled(true);
                
                buttonCluster.setEnabled(true);
                
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null, "Dalam folder yang dipilih "
                        + "terdapat file yang bukan .txt", "Gagal Muat", JOptionPane.ERROR_MESSAGE);
            }
        }else {
            System.out.println("Gagal Muat");
        }
    }//GEN-LAST:event_buttonMuatDokumenActionPerformed

    private void buttonClusterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonClusterActionPerformed
        // TODO add your handling code here:
        if(textfieldJumlahCluster.getText().isEmpty() || Integer.valueOf(textfieldJumlahCluster.getText()) < 1){
            JOptionPane.showMessageDialog(null,"Nilai K tidak boleh kosong atau nol", 
                    "Gagal", JOptionPane.ERROR_MESSAGE);
        }else if(Integer.valueOf(textfieldJumlahCluster.getText()) >= bobot.getListDocument().size()){
            JOptionPane.showMessageDialog(null,"Nilai K tidak boleh sama atau lebih dari jumlah dokumen", 
                    "Gagal", JOptionPane.ERROR_MESSAGE);
        }else{
            if(radioButtonKMeans.isSelected()){ //usecase ke 3
                
                System.out.println("\n----------------------KMeans-----------------------\n");
                DoubleMatrix2D data = new DenseDoubleMatrix2D(matrixGen.transpose(bobot.getHasilPembobotan()));
                KMeans kmeans = new KMeans(data, true);
                kmeans.cluster(Integer.valueOf(textfieldJumlahCluster.getText()));
                populateTable(kmeans.getPartition());
                waktuEksekusi.setText(String.valueOf(kmeans.getExecTime()) + " s");
                iterasi.setText(String.valueOf(kmeans.getTotalIterations()));
                System.out.println(kmeans.getPartition());
                DBI dbi = new DBI(kmeans.getCentroid(), kmeans.getPartition(), kmeans.getDistances());
                textFieldDBI.setText(String.valueOf(dbi.getDaviesBouldin()));
                System.out.println("DBI: "+dbi.getDaviesBouldin());
                if(kmeans.getIdealK() != 0){
                    nilaiKideal.setText(String.valueOf(kmeans.getIdealK()));
                }else{
                    nilaiKideal.setText("-");
                }
                
            }else if(radioButtonDynamicKmeans.isSelected()){ //usecase ke 2
            
                System.out.println("\n----------------------Dynamic KMeans-----------------------\n");
                DoubleMatrix2D data = new DenseDoubleMatrix2D(matrixGen.transpose(bobot.getHasilPembobotan()));
                KMeans kmeans = new KMeans(data, false);
                kmeans.cluster(Integer.valueOf(textfieldJumlahCluster.getText()));
                populateTable(kmeans.getPartition());
                waktuEksekusi.setText(String.valueOf(kmeans.getExecTime()) + " s");
                iterasi.setText(String.valueOf(kmeans.getTotalIterations()));
                DBI dbi = new DBI(kmeans.getCentroid(), kmeans.getPartition(), kmeans.getDistances());
                textFieldDBI.setText(String.valueOf(dbi.getDaviesBouldin()));
                System.out.println("DBI: "+dbi.getDaviesBouldin());
                if(kmeans.getIdealK() != 0){
                    nilaiKideal.setText(String.valueOf(kmeans.getIdealK()));
                }else{
                    nilaiKideal.setText("-");
                }                
            
            }else if(radioButtonKMeansSVD.isSelected()){ //usecase ke 4
                
                System.out.println("\n--------------KMeans + SVD------------------------\n");
                DoubleMatrix2D data = new DenseDoubleMatrix2D(bobot.getHasilPembobotan());
                SingularValueDecomposition svd = new SingularValueDecomposition(data);
                System.out.println("U:\n");
                System.out.println(svd.getU() + "\n");
                System.out.println("S:\n");
                System.out.println(svd.getS() + "\n");
                System.out.println("Vt:\n");
                System.out.println(svd.getV().viewDice() + "\n");
                System.out.println("U*S:");
                double[][] US = matrixGen.multiply(svd.getU(), svd.getS());
                System.out.println("S*V:");
                double[][] SV = matrixGen.multiply(svd.getS(), svd.getV().viewDice());
                System.out.println("\n");
                ReduksiDimensiSVD reduksi = new ReduksiDimensiSVD(bobot.getGlobalTermList(), bobot.getListDocument(), US);
                reduksi.doReduksiDimensi();

                DoubleMatrix2D reducedMatrix = new DenseDoubleMatrix2D(reduksi.getReducedMatrix());
                DoubleMatrix2D transposedSV = new DenseDoubleMatrix2D(matrixGen.transpose(SV));
                KMeans kmeans = new KMeans(reducedMatrix, transposedSV, true);
                kmeans.cluster(Integer.valueOf(textfieldJumlahCluster.getText()));
                System.out.println(kmeans.getPartition());
                populateTable(kmeans.getPartition());
                waktuEksekusi.setText(String.valueOf(kmeans.getExecTime()) + " s");
                iterasi.setText(String.valueOf(kmeans.getTotalIterations()));
                DBI dbi = new DBI(kmeans.getCentroid(), kmeans.getPartition(), kmeans.getDistances());
                textFieldDBI.setText(String.valueOf(dbi.getDaviesBouldin()));
                System.out.println("DBI: "+dbi.getDaviesBouldin());
                if(kmeans.getIdealK() != 0){
                    nilaiKideal.setText(String.valueOf(kmeans.getIdealK()));
                }else{
                    nilaiKideal.setText("-");
                }
                
            }else if(radioButtonKMeansPCA.isSelected()){ //usecase ke 5
                
                System.out.println("\n--------------KMeans + PCA------------------------\n");
                PCA pca = new PCA(matrixGen.transpose(bobot.getHasilPembobotan()));
                DoubleMatrix2D data = new DenseDoubleMatrix2D(matrixGen.transpose(pca.getPCA()));
//                System.out.println(data);
                KMeans kmeans = new KMeans(data, true);
                kmeans.cluster(Integer.valueOf(textfieldJumlahCluster.getText()));
                System.out.println(kmeans.getPartition());
                populateTable(kmeans.getPartition());
                waktuEksekusi.setText(String.valueOf(kmeans.getExecTime()) + " s");
                iterasi.setText(String.valueOf(kmeans.getTotalIterations()));
                DBI dbi = new DBI(kmeans.getCentroid(), kmeans.getPartition(), kmeans.getDistances());
                textFieldDBI.setText(String.valueOf(dbi.getDaviesBouldin()));
                System.out.println("DBI: "+dbi.getDaviesBouldin());
                if(kmeans.getIdealK() != 0){
                    nilaiKideal.setText(String.valueOf(kmeans.getIdealK()));
                }else{
                    nilaiKideal.setText("-");
                }
            }
        }
    }//GEN-LAST:event_buttonClusterActionPerformed

    private void radioButtonDynamicKmeansActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioButtonDynamicKmeansActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioButtonDynamicKmeansActionPerformed

    void populateTable(DoubleMatrix2D hasilClustering){
        DefaultTableModel tabelModel = new DefaultTableModel(hasilClustering.rows(), hasilClustering.columns());
        for(int i=0; i<hasilClustering.rows(); i++){
            for(int j=0; j<hasilClustering.columns(); j++){
                if(hasilClustering.get(i, j) == 1){
                    tabelModel.setValueAt(bobot.getListDocument().get(i).getJudul(), i, j);
                    break;
                }
            }
        } 
        
        tableCluster.setModel(tabelModel);

    }
    
    static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCluster;
    private javax.swing.ButtonGroup buttonGroupCluster;
    private javax.swing.JButton buttonMuatDokumen;
    private javax.swing.JLabel iterasi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelClusterMethod;
    private javax.swing.JLabel labelDBI;
    private javax.swing.JLabel labelIterasi;
    private javax.swing.JLabel labelJumlahCluster;
    private javax.swing.JLabel labelJumlahDokumen;
    private javax.swing.JLabel labelJumlahTerm;
    private javax.swing.JLabel labelKideal;
    private javax.swing.JLabel labelWaktuEksekusi;
    private javax.swing.JLabel nilaiKideal;
    private javax.swing.JRadioButton radioButtonDynamicKmeans;
    private javax.swing.JRadioButton radioButtonKMeans;
    private javax.swing.JRadioButton radioButtonKMeansPCA;
    private javax.swing.JRadioButton radioButtonKMeansSVD;
    private javax.swing.JTable tableCluster;
    private javax.swing.JTextField textFieldDBI;
    private javax.swing.JTextField textFieldDokumen;
    private javax.swing.JTextField textFieldMuatDokumen;
    private javax.swing.JTextField textFieldTerm;
    private javax.swing.JTextField textfieldJumlahCluster;
    private javax.swing.JLabel waktuEksekusi;
    // End of variables declaration//GEN-END:variables
}
