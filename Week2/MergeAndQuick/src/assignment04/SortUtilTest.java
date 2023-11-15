//package assignment04;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class SortUtilTest {
////=======================MERGE SORT TESTS========================//
//
//    /**
//     * Tests MergeSort with numbers in random order
//     */
//    @Test
//    void mergesortAverageCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateAverageCase(100);
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.mergesort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests MergeSort with numbers in descending order
//     */
//    @Test
//    void mergesortWorstCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateWorstCase(100);
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.mergesort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests MergeSort with numbers in ascending order
//     */
//    @Test
//    void mergesortBestCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateBestCase(100);
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.mergesort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests MergeSort with an odd amount of numbers in the array
//     */
//    @Test
//    void mergesortOddTest() {
//        ArrayList<Integer> mergeSortOdd = new ArrayList<>(List.of(1, 3, 2, 5, 4));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.mergesort(mergeSortOdd, myComparator);
//        for (int i = 0; i < mergeSortOdd.size() - 1; i++) {
//            assertTrue(myComparator.compare(mergeSortOdd.get(i), mergeSortOdd.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests MergeSort with an array of two items
//     */
//    @Test
//    void mergesortTwoArrayTest() {
//        ArrayList<Integer> mergeSortDouble = new ArrayList<>(List.of(2, 1));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//        mergeSortDouble.add(2);
//        mergeSortDouble.add(1);
//
//        SortUtil.mergesort(mergeSortDouble, myComparator);
//        for (int i = 0; i < mergeSortDouble.size() - 1; i++) {
//            assertTrue(myComparator.compare(mergeSortDouble.get(i), mergeSortDouble.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests MergeSort with an array with multiple duplicate integers
//     */
//    @Test
//    void mergesortDuplicateTest() {
//
//        ArrayList<Integer> mergeSortDuplicate = new ArrayList<>(List.of(4, 1, 2, 3, 1, 4, 2, 3));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.mergesort(mergeSortDuplicate, myComparator);
//        for (int i = 0; i < mergeSortDuplicate.size() - 1; i++) {
//            assertTrue(myComparator.compare(mergeSortDuplicate.get(i), mergeSortDuplicate.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests MergeSort with an array of one item
//     */
//    @Test
//    void mergesortSingleArrayTest() {
//
//        ArrayList<Integer> mergeSortSingle = new ArrayList<>(List.of(1));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.mergesort(mergeSortSingle, myComparator);
//        for (int i = 0; i < mergeSortSingle.size() - 1; i++) {
//            assertTrue(myComparator.compare(mergeSortSingle.get(i), mergeSortSingle.get(i + 1)) <= 0);
//        }
//    }
//
//    //=======================QUICK SORT TESTS=========================//
//
//    /**
//     * Tests QuickSort with numbers in random order
//     */
//    @Test
//    void quicksortAverageCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateAverageCase(100);
//
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.quicksort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests QuickSort with numbers in descending order
//     */
//    @Test
//    void quicksortWorstCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateWorstCase(50);
//
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.quicksort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests QuickSort with numbers in ascending order
//     */
//    @Test
//    void quicksortBestCaseTest() {
//        ArrayList<Integer> myList = SortUtil.generateBestCase(50);
//
//        Comparator<Integer> myComparator = Comparator.naturalOrder(); // Use natural order comparator for integers
//
//        SortUtil.quicksort(myList, myComparator);
//
//        for (int i = 0; i < myList.size() - 1; i++) {
//            assertTrue(myComparator.compare(myList.get(i), myList.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests QuickSort with an odd amount of numbers in the array
//     */
//    @Test
//    void quicksortOdd() {
//        ArrayList<Integer> quickSortList = new ArrayList<>(List.of(1, 3, 2, 5, 4));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.quicksort(quickSortList, myComparator);
//        for (int i = 0; i < quickSortList.size() - 1; i++) {
//            assertTrue(myComparator.compare(quickSortList.get(i), quickSortList.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests QuickSort with an array of two items
//     */
//    @Test
//    void quickSortTwoArrayTest() {
//        ArrayList<Integer> quickSortSmall = new ArrayList<>(List.of(2, 1));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.quicksort(quickSortSmall, myComparator);
//        for (int i = 0; i < quickSortSmall.size() - 1; i++) {
//            assertTrue(myComparator.compare(quickSortSmall.get(i), quickSortSmall.get(i + 1)) <= 0);
//        }
//
//    }
//
//    /**
//     * Tests QuickSort with an array with multiple duplicate integers
//     */
//    @Test
//    void quickSortDuplicateTest() {
//        ArrayList<Integer> quickSortDuplicate = new ArrayList<>(List.of(4, 1, 2, 3, 1, 4, 2));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.quicksort(quickSortDuplicate, myComparator);
//        for (int i = 0; i < quickSortDuplicate.size() - 1; i++) {
//            assertTrue(myComparator.compare(quickSortDuplicate.get(i), quickSortDuplicate.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests QuickSort with an array of one item
//     */
//    @Test
//    void quickSortArrayTest() {
//        ArrayList<Integer> quickSortSingle = new ArrayList<>(List.of(1));
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//
//        SortUtil.quicksort(quickSortSingle, myComparator);
//        for (int i = 0; i < quickSortSingle.size() - 1; i++) {
//            assertTrue(myComparator.compare(quickSortSingle.get(i), quickSortSingle.get(i + 1)) <= 0);
//        }
//
//    }
//
//    //=======================CASE GENERATION TESTS=========================//
//
//    /**
//     * Tests that an array is created in ascending order
//     */
//    @Test
//    void generateBestCase() {
//        ArrayList<Integer> bestCase = SortUtil.generateBestCase(50);
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//        System.out.println(bestCase);
//        for (int i = 0; i < bestCase.size() - 1; i++) {
//            assertTrue(myComparator.compare(bestCase.get(i), bestCase.get(i + 1)) <= 0);
//        }
//    }
//
//    /**
//     * Tests that an array is created in randomized order
//     */
//    @Test
//    void generateAverageCase() {
//        ArrayList<Integer> averageCase = SortUtil.generateAverageCase(50);
//        System.out.println(averageCase);
//    }
//
//    /**
//     * Tests that an array is created in descending
//     */
//    @Test
//    void generateWorstCase() {
//        ArrayList<Integer> worstCase = SortUtil.generateWorstCase(50);
//        Comparator<Integer> myComparator = Comparator.naturalOrder();
//        System.out.println(worstCase);
//        for (int i = 0; i < worstCase.size() - 1; i++) {
//            assertTrue(myComparator.compare(worstCase.get(i), worstCase.get(i + 1)) >= 0);
//        }
//    }
//}