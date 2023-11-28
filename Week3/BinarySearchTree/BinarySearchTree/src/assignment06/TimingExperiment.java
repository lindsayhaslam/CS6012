package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.SortedSet;

public class TimingExperiment {

    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {

        int beginning=1;
        int end=5001;
        int increment=500;

        //You spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("BSTRandomSortedOrder.tsv"), false)) {

            for (int size = beginning; size <= end; size+=increment) { //This is used as the exponent to calculate the size of the set.
//                int size = (int) Math.pow(2, exp); // or ..

                //Do the experiment multiple times, and average out the results
                long totalTime = 0;

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                    ArrayList<Integer> set = new ArrayList<>();

                    for (int i = 0; i < size; i++) {
                        set.add(i);
                    }

                    BinarySearchTree<Integer> bst = new BinarySearchTree<>();

                    bst.addAll(set);

                    //TIME IT!
                    long start = System.nanoTime();

                    for(int i=0; i<bst.size(); i++) {
                        bst.contains(i);
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double sortedOrder = totalTime / (double) ITER_COUNT;

                //Adding in random order.
                totalTime=0;

                ArrayList<Integer> randList = new ArrayList<>();
                Random random = new Random();

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    // SET UP!
                    for (int i = 0; i < size; i++) {
                        randList.add(i);
                    }

                    for(int k = 0; k < size; k++){
                        randList.set(k, randList.get(random.nextInt(size)));
                    }

                    BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();

                    bst2.addAll(randList);

                    //TIME IT!
                    long start = System.nanoTime();

                    for(int i=0; i<bst2.size(); i++) {
                        bst2.contains(i);
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                double randomOrder=totalTime/(double) ITER_COUNT;


                var output=size + "," + sortedOrder + "," + randomOrder  + "\r\n";
                //Print to console
                System.out.println(output);
                //Write to file.
                fw.write(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}