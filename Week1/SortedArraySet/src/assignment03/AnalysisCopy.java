package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class AnalysisCopy {

    //initializing the iteration count to 100
    private static final int ITER_COUNT = 100;
    public static void main(String[] args) {
        Random rand = new Random();
        //FileWriter
        //Originally in try-catch: (FileWriter fw = new FileWriter(new File("addMethodAnalysis.tsv")))
        for (int exp = 10; exp <= 20; exp++) {
            int size = (int) Math.pow(2, exp);
            long totalTime = 0;
            for (int iter = 0; iter < ITER_COUNT; iter++) {
                //Create a set of integers.
                BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
                for (int i = 0; i < size; i++) {
                    integerSet.add(i);
                }
                integerSet.remove(300);
                //For question 3.
                //int randomNumber = rand.nextInt(size);
                //Start time
                long start = System.nanoTime();
                //Contains Function
                //integerSet.contains(randomNumber);
                integerSet.add(300);
                //Stop time
                long stop = System.nanoTime();
                //Get the total time (stop time - start time = execution time)
                totalTime += stop - start;
            }
            //get the average time from all experiments
            double averageTime = totalTime / (double) ITER_COUNT;
            //Write to file and console.
            System.out.println("Size: " + size + "\tTime: " + averageTime);
//                fw.write(size + "\t" + averageTime + "\n");
        }
    }

}