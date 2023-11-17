package assignment05;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class StackAnalysis {

    //initializing the iteration count to 100
    private static final int ITER_COUNT = 100;
    public static void main(String[] args) {
        Random rand = new Random();
        //FileWrite
        try (FileWriter fw = new FileWriter(new File("arrayPush.tsv"))) {
            //Set up size: 2^10 - 2^20
            for (int exp = 10; exp <= 20; exp++) {
                int size = (int) Math.pow(2, exp);
                long totalTime = 0;
                long totalTimeArray = 0;


                //Set up arrayStack
                ArrayStack<Integer> arrayStack = new ArrayStack<>();
                for(int i = 0; i < size; i++){
                    arrayStack.push(i);
                }

                //Set up linkedStack
                LinkedListStack<Integer> linkedStack = new LinkedListStack<>();
                for(int i = 0; i < size; i++){
                    linkedStack.push(i);
                }

                //Iterations
                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //Start time
                    long start = System.nanoTime();
                    //Call function
                    linkedStack.push(10);
                    //End time
                    long stop = System.nanoTime();
                    //Calculate total
                    totalTime += stop - start;
                }

                for (int iter = 0; iter < ITER_COUNT; iter++) {
                    //Start time
                    long startArray = System.nanoTime();
                    //Call function
                    arrayStack.push(10);
                    //Stop time
                    long stopArray = System.nanoTime();
                    //Calculate total time
                    totalTimeArray += stopArray - startArray;
                }

                //Get the average
                double averageTime = totalTime / (double) ITER_COUNT;
                double averageTimeArray = totalTimeArray / (double) ITER_COUNT;
                //Write to file
                System.out.println("LINKED Size: " + size + "\tTime: " + averageTime);
                fw.write("LINKED Size: " + "\t"+ size + "\t" + averageTime + "\n");
                System.out.println("ARRAY Size: " + size + "\tTime: " + averageTimeArray);
                fw.write("ARRAY Size: " + "\t" +size + "\t" + averageTimeArray + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}