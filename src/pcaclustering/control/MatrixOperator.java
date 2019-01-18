/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering.control;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author WIN8
 */
public class MatrixOperator {
    
//    Transpose matriks double biasa
    public double[][] transpose(double[][] data){
        System.out.println("--------------transpose----------------");
        double[][] transposedMatrix = new double[data[0].length][data.length];
        for(int rows = 0; rows < data.length; rows++){
            for(int cols = 0; cols < data[0].length; cols++){
                transposedMatrix[cols][rows] = data[rows][cols];
            }
        }
        for(double[] i:transposedMatrix){//2D arrays are arrays of arrays
            System.out.println(Arrays.toString(i));
        }
        return transposedMatrix;
    }
    
//    Perkalian matriks
    public double[][] multiply(DoubleMatrix2D data1, DoubleMatrix2D data2){
        System.out.println("------------multiply------------");
        double[][] multipliedMatrix = new double[data1.rows()][data2.columns()];
        for(int i=0; i<data1.rows(); i++){
            for(int j=0; j<data2.columns(); j++){
                for(int k=0; k<data1.columns(); k++){
                    multipliedMatrix[i][j] += data1.get(i, k) * data2.get(k, j);
//                    System.out.println(data1.get(i, k) + " * " + data2.get(k, j) + " = " + data1.get(i, k) * data2.get(k, j));
                }System.out.print(multipliedMatrix[i][j] + "  ");
            }System.out.print("\n");
        }
        return multipliedMatrix;
    }
    
    //    Perkalian matriks
    public double[][] multiply(DoubleMatrix1D data1, DoubleMatrix1D data2){
        System.out.println("------------multiply------------");
        double[][] multipliedMatrix = new double[data1.size()][data2.size()];
        
        for(int i=0; i<data1.size(); i++){
            for(int j=0; j<data2.size(); j++){
                multipliedMatrix[i][j] += data1.get(i) * data2.get(j);
            }System.out.print("\n");
        }
        return multipliedMatrix;
    }
    
//    Menghitung mean untuk matriks 1 dimensi
    public double computeMean(DoubleMatrix1D data){
        double sum = 0;
        for(int i=0; i<data.size(); i++){
            sum = sum + data.get(i);
        }
        double mean = sum/data.size();
        return mean;
    }
    
//    Menghitung mean untuk matriks 2 dimensi
    public double computeMean(DoubleMatrix2D data){
        double sum = 0;
        for(int i=0; i<data.rows(); i++){
            for(int j=0; j<data.columns(); j++){
                sum = sum + data.get(i,j);
            }
        }
        double mean = sum/data.size();
        return mean;
    }
    
//    Menghitung mean untuk List of double
    public double computeMean(List<Double> data){
        double sum = 0;
        for(int i=0; i<data.size(); i++){
            sum = sum + data.get(i);
        }
        double mean = sum/data.size();
        return mean;
    }
    
//    Menghitung varian untuk matriks 1 dimensi
    public double computeVariance(DoubleMatrix1D data, double mean){
        double sum = 0;
        for(int i=0; i<data.size(); i++){
//            per cell dikurang rata-rata, dipangkat 2
            sum = sum + Math.pow((data.get(i) - mean), 2);
        }
        double variance = sum/(data.size()-1);
        return variance;
    }
    
//    Menghitung varian untuk matriks 2 dimensi
    public double computeVariance(DoubleMatrix2D data, double mean){
        double sum = 0;
        for(int i=0; i<data.rows(); i++){
            for(int j=0; j<data.columns(); j++){
//            per cell dikurang rata-rata, dipangkat 2
                sum = sum + Math.pow((data.get(i, j) - mean), 2);
            
            }
        }
        double variance = sum/(data.size()-1);
        return variance;
    }
    
//    Menghitung varian untuk List
    public double computeVariance(List<Double> data, double mean, double n){
        double sum = 0;
        for(int i=0; i<data.size(); i++){
//            per cell dikurang rata-rata, dipangkat 2
            sum = sum + Math.pow((data.get(i) - mean), 2);
        }
        double variance = sum/(n-1);
        return variance;
    }
    
    
//    Menghitung standar deviasi dengan masukan varian
    public double computeStandardDeviation(double variance){
//        akar pangkat 2
        return Math.sqrt(variance);
    }
    
//    Normalisasi
    public DoubleMatrix2D normalize(DoubleMatrix2D data, double[] mean){
        DoubleMatrix2D normalizedData = new DenseDoubleMatrix2D(data.rows(), data.columns());
        for(int i=0; i<data.columns(); i++){
            for(int j=0; j<data.rows(); j++){
//                per cell dari matriks awal dikurang rata-rata
                normalizedData.set(j, i, data.get(j, i)-mean[j]);
            }
        }
        return normalizedData;
    }
    
    public DoubleMatrix2D computeCovariance(DoubleMatrix2D data1, DoubleMatrix2D data2){
//        Matriks sementara untuk menampung hasil perkalian Yt dan Y
        DoubleMatrix2D tempA = new DenseDoubleMatrix2D(multiply(data1, data2));
//        Matriks kovarian, dimensinya sesuai dimensi matriks hasil perkalian
        DoubleMatrix2D A = new DenseDoubleMatrix2D(tempA.rows(), tempA.columns());
        for(int i=0; i<A.rows(); i++){
            for(int j=0; j<A.columns(); j++){
//                Per cell / n-1
//                n = jumlah (baris) data, di contoh ada 12
                A.set(i, j, ((float)tempA.get(i, j)/(data1.columns()-1)));
            }
        }
//        Sorting descending
//        Sorting pertama untuk sorting per baris
        sort(A);
//        Sorting kedua untuk sorting per kolom
        sort(A.viewDice());
        return A;
    }
    
//    Sorting array dengan bubble sort
    private void sort(DoubleMatrix2D data){
        for(int i = 0; i < data.rows(); i++){
            for(int j = 0; j < data.columns(); j++){
                for(int k = 0; k < data.columns()-1; k++){
                    if(data.get(i, k) < data.get(i, k+1)){
                        double temp = data.get(i, k);
                        data.set(i, k, data.get(i,k+1));
                        data.set(i, k+1, temp);
                    }
                }
            }
        }
    }
    
//  Mencari nilai minimum dari suatu array dalam format DoubleMatrix2D
    public double getMinimum(DoubleMatrix2D data){
        double min = data.get(0, 0);
        for(int i=0; i<data.rows(); i++){
            for(int j=0; j<data.columns(); j++){
                if(data.get(i, j)<min){
                    min = data.get(i, j);
                }
            }
        }
        return min;
    }
    
//  Mencari nilai minimum dari suatu array dalam format array of double
    public double getMinimum(double[][] data){
        double min = data[0][0];
        for(int i=0; i<data.length; i++){
            for(int j=0; j<data[0].length; j++){
                if(data[i][j]<min){
                    min = data[i][j];
                }
            }
        }
        return min;
    }
    
//  Mencari nilai minimum dari suatu array dalam format List of double
    public double getMinimum(List<Double> data){
        double min = data.get(0);
        for(int i=1; i<data.size(); i++){
            if(data.get(i)<min){
                min = data.get(i);
            }
        }
        return min;
    }
    
}
