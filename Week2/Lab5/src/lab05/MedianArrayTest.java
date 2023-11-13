package lab05;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MedianArrayTest {

    Integer[]sortedArr=new Integer[]{1, 3, 5, 7, 9};
    Integer[]unsortedArr=new Integer[]{2, 6, 8, 4, 10};
    Integer[]evenArr=new Integer[]{1, 2, 3, 4};

    @org.junit.jupiter.api.Test
    void findMedian() {
        int sortedMedian=MedianArray.findMedian(sortedArr);
        int unsortMedian=MedianArray.findMedian(unsortedArr);
        int array1Median=MedianArray.findMedian(evenArr);

        assertEquals(5, sortedMedian);
        assertEquals(6, unsortMedian);
        assertEquals(2, array1Median);
        assertEquals("bird", MedianArray.findMedian(new String[]{"bird", "cat", "dog", "ant"}));

    }

    @org.junit.jupiter.api.Test
    void findMedianWithComparator() {

        Student student1 = new Student("Lindsay", 11111);
        Student student2 = new Student("Tina", 33333);
        Student student3 = new Student("Corinne", 22222);

        Student[] listofStudents = new Student[] {student1, student2, student3};

        Student gpaMedian = MedianArray.findMedianWithComparator(listofStudents, Student.ageComparator);
        Student nameMedian=MedianArray.findMedianWithComparator(listofStudents, Student.nameComparator);

        assertEquals(student3.getGpa(), gpaMedian.getGpa());
        assertEquals(student1.getName(), nameMedian.getName());

    }
}