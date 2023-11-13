package lab05;
import java.util.Arrays;
import java.util.Comparator;

public class MedianArray {
    public static <E extends Comparable<? super E>> E findMedian(E[]arr){
        Arrays.sort(arr);
        int mid=(arr.length-1)/2;
        return arr[mid];
    }

    public static <E> E findMedianWithComparator(E[] array, Comparator<E> comparator) {
        Arrays.sort(array, comparator);
        int mid= (array.length-1) / 2;
        return array[mid];
    }
}
