/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pcaclustering.control;


/**
 *
 * @author User-pc
 */

import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.doublealgo.Statistic;
import cern.colt.matrix.doublealgo.Statistic.VectorVectorFunction;
import cern.colt.matrix.impl.DenseDoubleMatrix1D;
import cern.colt.matrix.impl.DenseDoubleMatrix2D;
import cern.colt.matrix.impl.SparseDoubleMatrix2D;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;


public class KMeans {

   private DoubleMatrix2D means;
   private DoubleMatrix2D partition;
   private int maxIterations = 1000;
   private RandomGenerator randomGenerator = new MersenneTwister();
   private PartitionGenerator partitionGenerator = new HardRandomPartitionGenerator();
//   private VectorVectorFunction distanceMeasure2 = Statistic.EUCLID;
   private CosineDistance distanceMeasure = new CosineDistance();
   private final DoubleMatrix2D data;
   private DoubleMatrix2D SV;
   private MatrixOperator operator = new MatrixOperator();
   private int totalIterations;
   private double distance = 0;
   private long output = 0;
   private boolean fixed_no_of_clusters;
   private double old_intra_cluster = Double.MAX_VALUE;
   private double old_inter_cluster = -1;
   private double new_intra_cluster;
   private double new_inter_cluster;
   private int idealK = 0;
   private DoubleMatrix2D finalDistances;
   private DoubleMatrix1D centroid;

   
   public KMeans(DoubleMatrix2D data, boolean fixed_no_of_clusters) {
       this.data = data;
       this.fixed_no_of_clusters = fixed_no_of_clusters;
   }

    public KMeans(DoubleMatrix2D data, DoubleMatrix2D SV, boolean fixed_no_of_clusters) {
       this.data = data;
       this.SV = SV;
       this.fixed_no_of_clusters = fixed_no_of_clusters;
   }
   
   public void cluster(int clusters) {
      long lStartTime = System.currentTimeMillis();
      int n = data.rows(); // Number of features
      int p = data.columns(); // Dimensions of features

      partition = new SparseDoubleMatrix2D(n, clusters);
      partitionGenerator.setRandomGenerator(randomGenerator);
      partitionGenerator.generate(partition);

      means = new DenseDoubleMatrix2D(p, clusters);

      boolean changedPartition = true;

      // Begin the main loop of alternating optimization
      for (int itr = 0; itr < maxIterations && changedPartition; ++itr) {
         // Get new prototypes (v) for each cluster using weighted median
         for (int k = 0; k < clusters; k++) {

            for (int j = 0; j < p; j++) {
               double sumWeight = 0;
               double sumValue = 0;

               for (int i = 0; i < n; i++) {
                  double Um = partition.getQuick(i, k);
                  sumWeight += Um;
                  sumValue += data.getQuick(i, j) * Um;
               }

               means.setQuick(j, k, sumValue / sumWeight);
            }
         }

         // Calculate distance measure:
         DoubleMatrix2D distances = new DenseDoubleMatrix2D(n, clusters);
         for (int k = 0; k < clusters; k++) {
            for (int i = 0; i < n; i++) {
               // Cosine distance calculation
               if(SV != null){
                   distance = distanceMeasure.calculateDistance(means.viewColumn(k), SV.viewRow(i));
               }else{
                   distance = distanceMeasure.calculateDistance(means.viewColumn(k), data.viewRow(i));
               }
               distances.setQuick(i, k, distance);
            }
         }

         // Get new partition matrix U:
         changedPartition = false;
         for (int i = 0; i < n; i++) {
            double minDistance = Double.MAX_VALUE;
            int closestCluster = 0;

            for (int k = 0; k < clusters; k++) {
               // U = 1 for the closest prototype
               // U = 0 otherwise

               if (distances.getQuick(i, k) < minDistance) {
                  minDistance = distances.getQuick(i, k);
                  closestCluster = k;
               }
            }

            if (partition.getQuick(i, closestCluster) == 0) {
               changedPartition = true;

               for (int k = 0; k < clusters; k++) {
                  partition.setQuick(i, k, (k == closestCluster) ? 1 : 0);
               }
            }
         }
         totalIterations = itr;
         finalDistances = distances;
      }
      
      setCentroid(partition, finalDistances);
      System.out.println("\nDistances:");
      System.out.println(finalDistances);
//      System.out.println("\nCentroid Occurences:");
//      System.out.println(partition);
      System.out.println("\nCentroid:");
      System.out.println(centroid);
      
      //Proses penghitungan inter dan intra cluster
      if(!fixed_no_of_clusters){
          try{
              setInterCluster();
              setIntraCluster(clusters);
              
              System.out.println();
              System.out.println("New Inter: " + new_inter_cluster + ",\tNew Intra: " + new_intra_cluster);
              System.out.println("Old Inter: " + old_inter_cluster + ",\tOld Intra: " + old_intra_cluster);
              
              System.out.println();
              System.out.println(partition);
              
              if(new_intra_cluster<old_intra_cluster && new_inter_cluster>old_inter_cluster){
                  System.out.println();
                  System.out.println("---------------------------------------------------------------");
                  
                  System.out.println("Beginnning clustering with new K process.....");
                  Thread.sleep(0);
                  
                  old_intra_cluster = new_intra_cluster;
                  old_inter_cluster = new_inter_cluster;
                  clusters = clusters + 1;
                  output = output + (System.currentTimeMillis() - lStartTime);
                  this.cluster(clusters);
              }else{
                  idealK = clusters;
              }
          }catch(InterruptedException e){
              System.out.println(e);
          }
      }
      long lEndTime = System.currentTimeMillis();
      
      output = output + (lEndTime - lStartTime);
   }

   public DoubleMatrix2D getMeans() {
      return means;
   }

   public DoubleMatrix2D getPartition() {
      return partition;
   }
   
   public double getExecTime(){
       return (double) output/1000;
   }
   
   public int getTotalIterations(){
       return totalIterations+1;
   }

   public int getMaxIterations() {
      return maxIterations;
   }

   public void setMaxIterations(int maxIterations) {
      this.maxIterations = maxIterations;
   }

   public RandomGenerator getRandomGenerator() {
      return randomGenerator;
   }

   public void setRandomGenerator(RandomGenerator random) {
      this.randomGenerator = random;
   }
   
   private void setInterCluster(){
       List<Double> substractedCentroid = new ArrayList<>();
       for(int i=0; i<centroid.size()-1; i++){
           substractedCentroid.add(Math.abs(centroid.get(i)-centroid.get(i+1)));
       }
//       System.out.println(substractedCentroid);
       this.new_inter_cluster = operator.getMinimum(substractedCentroid);
   }
   
   private void setIntraCluster(int clusters){
       List<Double> dataPointPerCluster = new ArrayList<>();
       double stdevp = 0;
       for(int j=0; j<partition.columns(); j++){
           dataPointPerCluster.clear();
           double n = 0;
           for(int i=0; i<partition.rows(); i++){
               if(partition.get(i, j)==1){
                   double sum = 0;
                   if(SV!=null){
                       for(int k=0; k<SV.viewRow(i).size(); k++){
                           sum = sum + SV.viewRow(i).get(k);
                       }
                       dataPointPerCluster.add(sum/SV.viewRow(i).size());
                   }else{
                       for(int k=0; k<data.viewRow(i).size(); k++){
                           sum = sum + data.viewRow(i).get(k);
                       }
                       dataPointPerCluster.add(sum/data.viewRow(i).size());
                   }
                   n++;
               }
           }
           stdevp = stdevp + Math.pow(Math.abs(operator.computeStandardDeviation(operator.computeVariance(dataPointPerCluster, operator.computeMean(dataPointPerCluster), n))),2);
       }
       this.new_intra_cluster = Math.sqrt(stdevp/clusters);
   }
   
   public double getInterCluster(){
       return new_inter_cluster;
   }
   
   public double getIntraCluster(){
       return new_intra_cluster;
   }
   
   public int getIdealK(){
       return idealK;
   }
   
   private void setCentroid(DoubleMatrix2D partition, DoubleMatrix2D distances){
       centroid = new DenseDoubleMatrix1D(partition.columns());
       
       for(int j=0; j<partition.columns(); j++){
           double totalOccurences = 0;
           double totalDistances = 0;
           for(int i=0; i<partition.rows(); i++){
               if(partition.get(i, j) == 1){
                   totalDistances = totalDistances + distances.get(i, j);
                   totalOccurences++;
               }
           }
           centroid.set(j, totalDistances/totalOccurences);
       }
   }
   
   public DoubleMatrix1D getCentroid(){
       return centroid;
   }
   
   public DoubleMatrix2D getDistances(){
       return finalDistances;
   }

//   public VectorVectorFunction getDistanceMeasure() {
//      return distanceMeasure;
//   }
//
//   public void setDistanceMeasure(VectorVectorFunction distanceMeasure) {
//      this.distanceMeasure = distanceMeasure;
//   }
}
