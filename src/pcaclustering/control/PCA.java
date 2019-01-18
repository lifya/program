/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering.control;

import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.linalg.SingularValueDecomposition;

/**
 *
 * @author WIN8
 */
public class PCA {

    /**
     * @param args the command line arguments
     */
    
    private double[][] data;
    
    public PCA(double[][] data){
        this.data = data;
    }
    
    public double[][] getPCA() {
        // TODO code application logic here
        MatrixOperator operator = new MatrixOperator();
//        Matriks dibalik dari yang di contoh. Disesuaikan lagi setelah ada data yang asli
//        double[][] data = {{8,10,6,11,8,7,10,9,10,6,12,9},
//            {64,71,53,67,55,58,77,57,56,51,76,68},
//            {57,59,49,62,51,50,55,48,42,42,61,57}};
//        double[][] data = {{2,3,4,1,3,7,6,4,9},{5,3,7,1,1,7,0,4,2},{2,3,4,1,4,5,4,4,7}};
//        double[][] data = {{4,0}, {3,-5}};
        
//        double[][] contoh = {{5,5.5,6,6.5,7,6,7,8,9,10},{5,6,7,7.5,7.8,8,8.5,8.5,9,9.5}};
        
        double[] mean = new double[data.length];
        double[] variance = new double[data.length];
        double[] standarddeviation = new double[data.length];
        
        for(int i=0; i<data.length; i++){
//            Hitung mean per baris
            mean[i] = operator.computeMean(new DenseDoubleMatrix1D(data[i]));
//            Hitung varian per baris
            variance[i] = operator.computeVariance(new DenseDoubleMatrix1D(data[i]), mean[i]);
//            Hitung standar deviasi per baris
            standarddeviation[i] = operator.computeStandardDeviation(variance[i]);
            System.out.println("av: "+mean[i]+"\tvar: "+variance[i]+"\tdev: "+standarddeviation[i]);
        }
        
//        Normalisasi data
        DoubleMatrix2D normalizedData = operator.normalize(new DenseDoubleMatrix2D(data), mean).viewDice();
        System.out.println("Normalize:");
        System.out.println(normalizedData.toString());
        
//        Menghitung matriks kovarian
//        method viewDice() untuk transpose matriks DoubleMatrix2D
        DoubleMatrix2D covarianceMatrix = operator.computeCovariance(normalizedData.viewDice(), normalizedData);
        System.out.println("Covariance:");
        System.out.println(covarianceMatrix.toString());
        
        SingularValueDecomposition svd = new SingularValueDecomposition(covarianceMatrix);
        System.out.println("U:");
        System.out.println(svd.getU());
//        System.out.println(svd.getU().viewDice());
//        System.out.println("My method:");
//        double[][] transposeU = operator.transpose(svd.getU().toArray());
//        System.out.println(svd.getS());
//        System.out.println(svd.getV().viewDice());
        
        System.out.println("\n");
        double[][] pca = operator.multiply(normalizedData, svd.getU());
        System.out.println("Perkalian selesai");
        
        return pca;
    }
    
}
