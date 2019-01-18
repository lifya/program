/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering;

import pcaclustering.control.DBI;
import pcaclustering.control.KMeans;
import pcaclustering.control.MatrixOperator;
import pcaclustering.control.Pembobotan;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import java.util.Arrays;

/**
 *
 * @author WIN8
 */
public class TesClustering {
    public static void main(String[] args){
        String path = "D:\\salenati";
        Pembobotan bobot = new Pembobotan(path);
        bobot.doPembobotan();
        System.out.println("Hasil pembobotan: " + bobot.getHasilPembobotan().length + " x " + bobot.getHasilPembobotan()[0].length);
        MatrixOperator op = new MatrixOperator();
        DoubleMatrix2D data = new DenseDoubleMatrix2D(op.transpose(bobot.getHasilPembobotan()));
        KMeans kmeans = new KMeans(data, true);
        kmeans.cluster(5);
        
        System.out.println();
        System.out.println("K ideal: "+kmeans.getIdealK());
        
        System.out.println();
        DBI dbi = new DBI(kmeans.getCentroid(), kmeans.getPartition(), kmeans.getDistances());
        System.out.println("DBI: "+dbi.getDaviesBouldin());
        
//        System.out.println();
//        System.out.println(kmeans.getPartition());
    }
}
