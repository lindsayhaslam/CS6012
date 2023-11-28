package lab06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TimingExperiments {

    private static final int MEASURED_RUNS = 100;

//    public static void main(String[] args) {
//        // Experiment 1.
//       // timeDataStructureBuild();
//        // Experiment 2.
//        timeRemovalOperation();
//    }

//    private static void timeDataStructureBuild() {
//        int[] sizes = {10, 30, 50, 100, 500, 1000, 5000, 10000, 20000, 30000, 50000, 100000};
//
//        try (FileWriter fw = new FileWriter(new File("dataStructureBuild.tsv"))) {
//            // Sorted data
//            for (int size : sizes) {
//                PriorityQueue<Integer> treeSetPQ = new TreeSetVersion<>();
//                long startTime = System.nanoTime();
//                for (int i = 0; i < size; i++) {
//                    treeSetPQ.add(i);
//                }
//                long endTime = System.nanoTime();
//                System.out.println("TreeSet (In-order) - Size: " + size + ", Time: " + (endTime - startTime) + " ms");
//                fw.write("TreeSet (Sorted)" + size + "\t" + (endTime - startTime) + "\n");
//
//                ArrayListVersion<Integer> heapPQInOrder = new ArrayListVersion<>();
//                ArrayList<Integer> inOrderList = new ArrayList<>();
//                for (int i = 0; i < size; i++) {
//                    inOrderList.add(i);
//                }
//                startTime = System.nanoTime();
//                ArrayListVersion<Integer> heapPQInOrderTest = new ArrayListVersion<>(inOrderList);
//                endTime = System.nanoTime();
//                System.out.println("ArrayList (In-order) - Size: " + size + ", Time: " + (endTime - startTime) + " ms");
//                fw.write("ArrayList (Sorted)" + size + "\t" + (endTime - startTime) + "\n");
//
//                // Random data
//                ArrayList<Integer> permutatedList = new ArrayList<>(inOrderList);
//                Collections.shuffle(permutatedList);
//
//                PriorityQueue<Integer> treeSetPQPermutated = new TreeSetVersion<>();
//                startTime = System.nanoTime();
//                for (int i = 0; i < size; i++) {
//                    treeSetPQPermutated.add(permutatedList.get(i));
//                }
//                endTime = System.nanoTime();
//                System.out.println("TreeSet (Permutated) - Size: " + size + ", Time: " + (endTime - startTime) + " ms");
//                fw.write("TreeSet (Random)" + size + "\t" + (endTime - startTime) + "\n");
//
//                startTime = System.nanoTime();
//                ArrayListVersion<Integer> heapPQPermutatedTest = new ArrayListVersion<>(permutatedList);
//                endTime = System.nanoTime();
//                System.out.println("ArrayList (Permutated) - Size: " + size + ", Time: " + (endTime - startTime) + " ms");
//
//                // Write to file
//                fw.write("ArrayList (Random)" + size + "\t" + (endTime - startTime) + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        //Set up the filewriter to hold the data
        try (FileWriter fw = new FileWriter(new File("treeRemoveShuffle.tsv"))) {

            //set up data types
            TreeSetVersion<Integer> tree = new TreeSetVersion<>();
            ArrayList<Integer> al = new ArrayList<>();

            Random rand = new Random();

            //Setting up the size of each experiment
            for (int exp = 0; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);

                //Adding elements to the arrayList
                for (int j = 0; j < size; j++) {
                    al.add(j);
                }

                //shuffling arrayList
//                for(int k = 0; k < size; k++){
//                    al.set(k, al.get(rand.nextInt(size)));
//                }

                Collections.shuffle(al);

                TreeSetVersion<Integer> alQueue = new TreeSetVersion<>();
                for(int l = 0; l < size; l++) {
                    tree.add(al.get(l));
                }

                // Measure execution time for the specified number of runs
                long totalTime = 0;
                for (int iter = 0; iter < MEASURED_RUNS; iter++) {
                    long start = System.nanoTime();

                    while(!alQueue.isEmpty()){
                        alQueue.removeMin();
                    }

                    long stop = System.nanoTime();
                    totalTime += stop - start;
                }

                // Calculate average time by dividing total time by the number of measured runs
                double averageTime = totalTime / (double) MEASURED_RUNS;

                // Write to the console and to a file what the average time was for each sample size
                System.out.println("Size: " + size + "\tTime: " + averageTime);
                fw.write(size + "\t" + averageTime + "\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
