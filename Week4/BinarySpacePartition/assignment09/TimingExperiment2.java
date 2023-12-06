package assignment09;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class TimingExperiment2{
    static int count;

    //newCollision method provided by professor.
    public static int newCollision(Segment query, BSPTree tree) {

        tree.traverseFarToNear(0.5, 0.5, //they don't matter
                (segment) -> {
                    if(segment.intersects(query)){
                        count++;
                    }
                });
        return count;
    }

    //Set iteration count to 100.
    private static final int ITER_COUNT = 100;

    public static void main(String[] args) {
        int beginning=1;
        int end=10001;
        int increment=1000;


        try (FileWriter fw = new FileWriter(new File("CollisionDetection.tsv"))) {

            //Set up the size of the experiment.
            for (int size = beginning; size <= end; size+=increment) {

                long totalTime = 0;

                ArrayList<Segment> as = new ArrayList<>();
                Random rand = new Random(42);

                double y =0;
                for (int i=0; i<size; i++){
                    as.add(new Segment (rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), rand.nextDouble()));
                    y+=.01;
                }

                BSPTree bspTree = new BSPTree(as);

                Segment query = new Segment(0.5, 0, .5, 1);


                int numCollisions=0;

                //Start loop.
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    //Start time.
                    long start = System.nanoTime();

                    //Execute collision.
                    var elem = bspTree.collision(query);

                    //Stop time.
                    long stop = System.nanoTime();

                    //Get the total time.
                    totalTime += stop - start;

                    //Count collision.
                    if(elem!=null){
                        numCollisions++;
                    }
                }
                //Get the average time from all 100 experiments per sample size.
                double optimizedTime = totalTime / (double) ITER_COUNT;

                totalTime=0;
                numCollisions=0;

                //Start loop.
                for (int iter = 0; iter < ITER_COUNT; iter++) {

                    //Start time.
                    long start = System.nanoTime();

                    //Call newCollision.
                    numCollisions+=newCollision(query, bspTree);

                    //Stop time.
                    long stop = System.nanoTime();

                    //Get the total time.
                    totalTime += stop - start;
                }

                //Get the average time from all 100 experiments per sample size.
                double notOpt = totalTime / (double) ITER_COUNT;


                //Write to console and file.
                var output = (size + "," + optimizedTime + "," + notOpt + "\r\n");
                System.out.println(output);
                fw.write(output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
