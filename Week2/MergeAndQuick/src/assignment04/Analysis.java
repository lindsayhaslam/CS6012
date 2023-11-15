package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import static assignment04.SortUtil.quicksort;

public class Analysis {

    public static <T> void mergesort(ArrayList<T> array, Comparator<? super T> comparator, int threshold) {
        int start = 0;
        int end = array.size() - 1;

        mergesort(array, start, end, comparator, threshold);
    }

    private static <T> void mergesort(ArrayList<T> array, int start, int end, Comparator<? super T> comparator, int threshold) {
        if (end - start > threshold) {
            int middle = (start + end) / 2;
            mergesort(array, start, middle, comparator, threshold);
            mergesort(array, middle + 1, end, comparator, threshold);
            merge(array, start, middle, end, comparator);
        } else {
            insertionSort(array, start, end, comparator);
        }
    }

    private static <T> void insertionSort(ArrayList<T> array, int start, int end, Comparator<? super T> comparator) {
        for (int i = start + 1; i <= end; i++) {
            T key = array.get(i);
            int j = i - 1;

            while (j >= start && comparator.compare(array.get(j), key) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            array.set(j + 1, key);
        }
    }

    private static <T> void merge(ArrayList<T> array, int start, int middle, int end, Comparator<? super T> comparator) {
        //Create new arrays of type T at their appropriate beginning and end.
        T[] leftArray = (T[]) new Comparable[middle - start + 1];
        T[] rightArray = (T[]) new Comparable[end - middle];

        //Iterate through left side.
        for (int i = 0; i < leftArray.length; i++) {
            leftArray[i] = array.get(start + i);
        }
        //Iterator through right side.
        for (int i = 0; i < rightArray.length; i++) {
            rightArray[i] = array.get(middle + 1 + i);
        }

        //Beginning indeces for both arrays.
        int leftIndex = 0, rightIndex = 0;

        int currentIndex = start;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {

            //Compare the two arrays at the same index with comparator.
            if (comparator.compare(leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                array.set(currentIndex, leftArray[leftIndex]);
                leftIndex++;
            } else {
                array.set(currentIndex, rightArray[rightIndex]);
                rightIndex++;
            }
            currentIndex++;
        }
        //Check that you are at the end of either of the arrays.
        while (leftIndex < leftArray.length) {
            array.set(currentIndex++, leftArray[leftIndex++]);
        }

        while (rightIndex < rightArray.length) {
            array.set(currentIndex++, rightArray[rightIndex++]);
        }
    }

    private static <T> ArrayList<T> copyArrayList(ArrayList<T> original) {
        return new ArrayList<>(original);
    }

    public static void main(String[] args) throws IOException {
        //Inputs
        int[] inputSizes = {100, 500, 1000, 5000};
        //Random threshold values for part 1
        int[] thresholdValues = {5, 10, 20, 50, Integer.MAX_VALUE};
        try (FileWriter fw = new FileWriter(new File("quickSort.tsv"))) {

//        for (int threshold : thresholdValues) {
//            long totalDuration = 0;

            for (int size : inputSizes) {
                ArrayList<Integer> originalList = generateRandomList(size);
                ArrayList<Integer> inputList = copyArrayList(originalList);

                //**************MERGE THRESHOLD*****************
//                //Get and store start time
//                long startTime = System.nanoTime();
//                //Call mergesort, using the copy of the array, comparing to natural order, according to threshold
//                mergesort(inputList, Comparator.naturalOrder(), threshold);
//                //Get and store end time
//                long endTime = System.nanoTime();
//                //Get and store duration
//                long duration = endTime - startTime;
//                totalDuration += duration;
//                System.out.println("Size: " + size + ", Threshold: " + threshold + ", Duration: " + duration + " ns");
//                fw.write(size + "\t" + threshold + "\t" + duration + "\n");

                //***********QUICK SORT PIVOT********************
                long startTime = System.nanoTime();
                quicksort(inputList, Comparator.naturalOrder());
                long endTime = System.nanoTime();
                long duration = endTime - startTime;

                System.out.println("Size: " + size + ", Duration: " + duration + " ns");
                fw.write(size + "\t" + duration + "\n");
            }

        }
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }



    //For testing
    private static ArrayList<Integer> generateRandomList(int size) {
        ArrayList<Integer> list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            list.add(random.nextInt());
        }

        return list;
    }
}
