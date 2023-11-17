package assignment04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class SortUtil {

    /**
     * Sorts the given ArrayList using the merge sort algorithm with the specified Comparator.
     * @param array the ArrayList to be sorted
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */
    public static <T> void mergesort(ArrayList<T> array, Comparator<? super T> comparator) {
        //Index for start and end.
        int start = 0;
        int end = array.size() - 1;

        mergesort(array, start, end, comparator);
    }

    /**
     * Recursively applies the merge sort algorithm to a subarray within the given ArrayList.
     * Uses the given Comparator for element ordering.
     *
     * @param array the Arraylist to be sorted.
     * @param start the starting index of the subarray.
     * @param end the ending index of the subarray.
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */
    private static <T> void mergesort(ArrayList<T> array, int start, int end, Comparator<? super T> comparator) {
        int threshold = 1;

        if (end - start > threshold) {
            //Find the middle for the end and beginning of the two arrays.
            int middle = (start + end) / 2;
            mergesort(array, start, middle, comparator);
            mergesort(array, middle + 1, end, comparator);
            merge(array, start, middle, end, comparator);
        }
        else {
            insertionSort(array, start, end, comparator);
        }
    }

    /**
     * Merges two sorted subarrays within the given ArrayList.
     * Uses the specified Comparator for element ordering.
     *
     * @param array the ArrayList containing the subarrays to be merged.
     * @param start the starting index of the first subarray.
     * @param middle the ending index of the first subarray and the starting index of the second subarray.
     * @param end the ending index of the second subarray.
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */

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

    /**
     * Sorts a subarray within the given ArrayList using insertion sort.
     * Uses the specified Comparator for element ordering.
     *
     * @param array the ArrayList to be sorted.
     * @param start the starting index of the subarray.
     * @param end the ending index of the subarray.
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */
    public static <T> void insertionSort(ArrayList<T> array, int start, int end, Comparator<? super T> comparator){
        for (int i = start + 1; i <= end; i++){
            //Store the current element to insert.
            T key = array.get(i);
            int j = i - 1;

            //Move elements greater than the key to one position ahead of their current position.
            while (j >= start && comparator.compare(array.get(j), key) > 0) {
                array.set(j + 1, array.get(j));
                j--;
            }
            //Place the key in its correct position in the sorted order.
            array.set(j + 1, key);
        }
    }

    /**
     * Sorts the entire ArrayList using quicksort.
     * Uses the specified Comparator for element ordering.
     *
     * @param array the ArrayList to be sorted.
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */
    public static <T> void quicksort(ArrayList<T> array, Comparator<? super T> comparator) {
        //Declare start and end of ArrayList.
        int start=0;
        int end=array.size()-1;
        //Sort according to comparator.
        quicksort(array, start, end, comparator);

    }

    /**
     * Recursively applies quicksort algorithm to a subarray within the given ArrayList.
     * Uses the specified Comparator for element ordering.
     *
     * @param array the ArrayList to be sorted.
     * @param startIndex the starting index of the subarray.
     * @param endIndex the ending index of the subarray.
     * @param comparator the Comparator to determine the order of elements.
     * @param <T> the type of elements in the list.
     */
    private static <T> void quicksort(ArrayList<T> array, int startIndex, int endIndex, Comparator<? super T> comparator)
    {
        //Verify that the start and end index have not overlapped.
        if (startIndex < endIndex) {
            //Calculate the pivotIndex using partition.
            int pivotIndex = partition(array, startIndex, endIndex, comparator);
            //Sort the left subarray.
            quicksort(array, startIndex, pivotIndex, comparator);
            //Sort the right sub-array.
            quicksort(array, pivotIndex + 1, endIndex, comparator);
        }
    }

    /**
     * Partitions a subarray within the given ArrayList based on a pivot value.
     * Uses the specified Comparator for element ordering.
     *
     * @param array the ArrayList to be partitioned.
     * @param startIndex the starting index of the subarray.
     * @param endIndex the ending index of the subarray.
     * @param comparator the Comparator to determine the order of elements.
     * @return the final index of the pivot element after partitioning.
     * @param <T> the type of elements in the list.
     */
    private static <T> int partition(ArrayList<T> array, int startIndex, int endIndex, Comparator<? super T> comparator)
    {
        int pivotIndex = medianIndex(startIndex, endIndex);
        T pivotValue = array.get(pivotIndex);
        startIndex--;
        endIndex++;

        while (true) {
            //Start at the first index of the sub-array and increment.
            //Forward until we find a value that is > pivotValue.
            do startIndex++;
            while ((comparator.compare(array.get(startIndex), pivotValue)) < 0) ;


            //Start at the last index of the sub-array and increment.
            //Backward until we find a value that is < pivotValue.
            do endIndex--;
            while ((comparator.compare(array.get(endIndex), pivotValue)) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            //Swap values at the startIndex and endIndex.
            T temp=array.get(startIndex);
            array.set(startIndex, array.get(endIndex));
            array.set(endIndex, temp);
        }
    }


    /**
     * Returns an index that starts on the far left, index[0].
     * @param startIndex
     * @return startIndex.
     */
    public static int leftIndex(int startIndex){
        return startIndex;
    }

    /**
     * Generates a random integer within bounds.
     * @param startIndex
     * @param endIndex
     * @return random number.
     */
    public static int randomIndex(int startIndex, int endIndex) {
        Random rand = new Random();
        return rand.nextInt(endIndex - startIndex + 1) + startIndex;
    }

    /**
     * Finds the median of an ArrayList.
     * @param startIndex
     * @param endIndex
     * @return the median.
     */
    public static int medianIndex(int startIndex, int endIndex){
        return (startIndex + endIndex) / 2;
    }


    /**
     * Generates an ArrayList for best-case scenario for sorting algorithms.
     * The list contains integers from 1 to the specified size.
     *
     * @param size the size of the ArrayList.
     * @return an ArrayList in ascending order from 1 to size.
     */
    public static ArrayList<Integer> generateBestCase(int size) {
        //Create new ArrayList.
        ArrayList<Integer> bestCaseArray = new ArrayList<>();
        //Populate the list with consecutive numbers.
        for (int i = 1; i <= size; i++) {
            bestCaseArray.add(i);
        }
        return bestCaseArray;
    }

    /**
     * Generates an ArrayList for average-case scenario for sorting algorithms.
     * The list contains integers from 1 to the specified size.
     *
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
        //Create new Random type object
        Random rand = new Random();
        //Create new ArrayList.
        ArrayList<Integer> avgCaseArray = new ArrayList<>();

        //Populate the list with consecutive numbers.
        for (int i = 1; i <= size; i++) {
            avgCaseArray.add(i);
        }
        //Randomly shuffle the elements in the ArrayList.
        for (int i = 0; i < size; i++) {
            int j = rand.nextInt(size);
            //Swap elements at positions i and j.
            int temp = avgCaseArray.get(i);
            avgCaseArray.set(i, avgCaseArray.get(j));
            avgCaseArray.set(j, temp);
        }
        return avgCaseArray;
    }

    /**
     * Generates an ArrayList for worst-case scenario for sorting algorithms.
     * The list contains integers (in descending order) from the specified size to 1.
     *
     * @param size the size of the ArrayList.
     * @return returns ArrayList for worse-case.
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
        //Create a new ArrayList.
        ArrayList<Integer> worstCaseArray = new ArrayList<>();
        //Populate the ArrayList with integers in descending order.
        for (int i = size; i >= 1; i--) {
            worstCaseArray.add(i);
        }
        return worstCaseArray;
    }

}