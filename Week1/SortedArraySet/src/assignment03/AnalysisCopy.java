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
        //set up the filewriter to hold the data
//      Originally in try-catch: (FileWriter fw = new FileWriter(new File("addMethodAnalysis.tsv")))
        //setting up the size of each experiment - initially 2^10 and goes up to 2^20
        for (int exp = 10; exp <= 20; exp++) {
            int size = (int) Math.pow(2, exp);
            //initialize the total time at 0 to be updated later
            long totalTime = 0;
            //start loop
            for (int iter = 0; iter < ITER_COUNT; iter++) {
                //create a set of integers
                BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
                //add ints up to the experiment size to the array
                for (int i = 0; i < size; i++) {
                    integerSet.add(i);
                }
                integerSet.remove(300);
                //for Q3
                //int randomNumToSearchFor = rand.nextInt(size);
                //start the timer (get the start time)
                long start = System.nanoTime();
                //execute the contains function - for Q3
                //integerSet.contains(randomNumToSearchFor);
                //execute the contains function for 300
                integerSet.add(300);
                //stop the timer (get the end time)
                long stop = System.nanoTime();
                //get the total time (stop time - start time = execution time)
                totalTime += stop - start;
            }
            //get the average time from all 100 experiments per sample size
            double averageTime = totalTime / (double) ITER_COUNT;
            //write to the console and to a file what the average time was for each sample size
            System.out.println("Size: " + size + "\tTime: " + averageTime);
//                fw.write(size + "\t" + averageTime + "\n");
        }
    }

}