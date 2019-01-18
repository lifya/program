/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering.control;

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
 
/*
 * @author WIN8
 */

public class DBI {
    
    private final DoubleMatrix1D centroids;
    private final DoubleMatrix2D partition;
    private final DoubleMatrix2D distances;
    
    public DBI(DoubleMatrix1D centroids, DoubleMatrix2D partition, DoubleMatrix2D distances){
        this.centroids = centroids;
        this.partition = partition;
        this.distances = distances;
    }
    
    public BigDecimal getDaviesBouldin() {
        BigDecimal R = BigDecimal.ZERO;
        for(int i=0; i<partition.columns(); i++){
            double si = computeIntraCluster(i);
//            System.out.println("si:" + si);
            BigDecimal maxRij = new BigDecimal("-9999");
            for(int j=0; j<partition.columns(); j++){
                if(j != i){
                    double sj = computeIntraCluster(j);
//                    System.out.println(sj);
                    double mij = Math.sqrt(Math.pow((centroids.get(i)-centroids.get(j)), 2));
//                    System.out.println(mij);
                    double tempRij = (si+sj)/mij;
                    BigDecimal rij = BigDecimal.valueOf(tempRij);
//                    System.out.println(rij);
                    if(rij.compareTo(maxRij) == 1){
                        maxRij = rij;
                    }
//                    System.out.println();
                }
            }
//            System.out.println(maxRij);
//            System.out.println("------\n");
            R = R.add(maxRij);
        }
        return R.divide(new BigDecimal(partition.columns()), 15, RoundingMode.HALF_EVEN);
    }
    
    private double computeIntraCluster(int index){
        double sum = 0;
        double n = 0;
        for(int i=0; i<distances.viewColumn(index).size(); i++){
            if(partition.get(i, index)==1){
                sum = sum + (Math.sqrt(Math.pow((distances.get(i, index)-centroids.get(index)), 2)));
                n++;
            }
        }
        
        return sum/n;
    }
}
