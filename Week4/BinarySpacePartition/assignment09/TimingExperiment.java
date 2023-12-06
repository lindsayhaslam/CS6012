package assignment09;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class TimingExperiment {

    //Set iteration count to 100.
    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        int beginning=1;
        int end=10001;
        int increment=1000;
        try (FileWriter fw = new FileWriter(new File("BSPconstruction.tsv"))) {

            //Set up the size of the experiment.
            for (int size = beginning; size <= end; size+=increment) {

                //Set total time to 0.
                long totalTime = 0;
                ArrayList<Segment> as = new ArrayList<>();

                double y =0;
                for (int i=0; i<size; i++){
                    as.add(new Segment (0, y, 1, y));
                    y+=.01;
                }


                //Start loop
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    //Start time.
                    long start = System.nanoTime();

                    //Construct new BSP tree.
                    BSPTree bsp = new BSPTree(as);

                    //Stop time.
                    long stop = System.nanoTime();

                    totalTime += stop - start;
                }
                //Get the average time from all 100 experiments per sample size.
                double bulkTime = totalTime / (double) ITER_COUNT;

                totalTime=0;

                //Start loop
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    //Start time.
                    long start = System.nanoTime();

                    //Call oneAtTime.
                    BSPTree oneAtTime=new BSPTree();

                    //Iterate and insert.
                    for (Segment a : as) {
                        oneAtTime.insert(a);
                    }

                    //Stop time.
                    long stop = System.nanoTime();

                   //Get time and concatenate.
                    totalTime += stop - start;
                }

                //Get the average time from all 100 experiments per sample size.
                double oneAtTime = totalTime / (double) ITER_COUNT;


                //Write to console and file.
                var output = (size + "," + bulkTime + "," + oneAtTime);
                System.out.println(output);
                fw.write(output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
