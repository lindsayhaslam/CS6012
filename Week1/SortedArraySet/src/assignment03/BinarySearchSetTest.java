package assignment03;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

    @Test
    public void testGrowArray() {
        BinarySearchSet<Integer> testSet = new BinarySearchSet<>();  // Specify the type explicitly
        assertEquals(10, testSet.size());

        // Add 15 elements to trigger array growth
        for (int i = 1; i <= 15; i++) {
            testSet.add(i);
        }

        assertEquals(20, testSet.getSet_().length);
        assertEquals(15, testSet.size());

        // Manually create the expected sorted array
        Integer[] expectedSortedArray = new Integer[15];
        for (int i = 1; i <= 15; i++) {
            expectedSortedArray[i - 1] = i;
        }

        // Call growArray to trigger array growth
        testSet.growArray();

        // Get the actual array after growth without using foreach
        Iterator<Integer> iterator = testSet.iterator();
        Integer[] actualSortedArray = new Integer[testSet.size()];
        int index = 0;
        while (iterator.hasNext()) {
            actualSortedArray[index++] = iterator.next();
        }

        // Check if elements are still in the set after growth
        assertArrayEquals(expectedSortedArray, actualSortedArray);
    }



    @Test
    public void testAdd() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();

        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertTrue(set.add(3));
        assertTrue(set.add(1));
        assertTrue(set.add(5));

        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
        assertFalse(set.contains(2));

        // Add duplicates, expect false return
        assertFalse(set.add(3));
        assertFalse(set.add(1));
        assertFalse(set.add(5));

        assertEquals(3, set.size());

        // Add multiple elements at once
        List<Integer> elementsToAdd = Arrays.asList(2, 4, 6);
        assertTrue(set.addAll(elementsToAdd));

        assertEquals(6, set.size());
        assertTrue(set.containsAll(elementsToAdd));
    }

    @Test
    public void testAdd2() {
        BinarySearchSet<Integer> integerSet = new BinarySearchSet<>();
        System.out.println(integerSet.size());
        integerSet.add(1);
        System.out.println(integerSet.size());
        integerSet.add(5);
        integerSet.add(2);
        integerSet.add(7);
        integerSet.add(8);
        assertEquals(integerSet.size(), 5);

        assertEquals(integerSet.first(), 1);
        assertEquals(integerSet.last(), 8);
    }

    @Test
    public void testAddAll() {
        BinarySearchSet<String> addAllTest = new BinarySearchSet<>();
        addAllTest.add("blue");
        addAllTest.add("red");
        addAllTest.add("green");

        Set<String> elementsToAdd = new HashSet<>(Arrays.asList("purple", "pink", "yellow"));
        addAllTest.addAll(elementsToAdd);

        assertTrue(addAllTest.contains("purple"));
        assertTrue(addAllTest.contains("pink"));
        assertTrue(addAllTest.contains("yellow"));
        assertTrue(addAllTest.contains("blue"));
        assertTrue(addAllTest.contains("red"));
        assertTrue(addAllTest.contains("green"));
    }

    @Test
    public void testEmpty() {
        BinarySearchSet<Integer> emptySet = new BinarySearchSet<>();
        BinarySearchSet<Integer> fullSet = new BinarySearchSet<>();
        fullSet.add(1);
        fullSet.add(2);
        assertTrue(emptySet.isEmpty());
        assertFalse(fullSet.isEmpty());
    }

    @Test
    public void testContains() {
        BinarySearchSet<Integer> set = new BinarySearchSet<>();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertFalse(set.contains(1));

        set.add(3);
        set.add(1);
        set.add(5);

        assertFalse(set.isEmpty());
        assertEquals(3, set.size());

        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(5));
        assertFalse(set.contains(2));
    }

    @Test
    void containsAllTest() {
        BinarySearchSet<String> stringSet = new BinarySearchSet<>();
        stringSet.add("blue");
        stringSet.add("red");
        stringSet.add("green");

        Set<String> elementsToCheck = new HashSet<>(Arrays.asList("blue", "red", "green"));
        assertTrue(stringSet.containsAll(elementsToCheck));
    }

    @Test
    public void testClear() {
        BinarySearchSet<Integer> clearSetTest = new BinarySearchSet<>();
        assertTrue(clearSetTest.isEmpty());
        clearSetTest.add(2);
        clearSetTest.add(3);
        clearSetTest.add(5);

        clearSetTest.clear();

        assertTrue(clearSetTest.isEmpty());

        // Test with different types of elements (e.g., Strings)
        BinarySearchSet<String> stringSet = new BinarySearchSet<>();
        stringSet.add("apple");
        stringSet.add("banana");
        stringSet.add("orange");
        stringSet.clear();
        assertTrue(stringSet.isEmpty());
    }

    @Test
    public void testRemove() {
        BinarySearchSet<Integer> removeSetTest = new BinarySearchSet<>();
        assertTrue(removeSetTest.isEmpty());
        assertEquals(0, removeSetTest.size());

        removeSetTest.add(6);
        removeSetTest.add(7);
        removeSetTest.add(8);

        removeSetTest.remove(7);
        assertEquals(removeSetTest.last(), 8);
        assertEquals(removeSetTest.size(), 2);

        // Test with different types of elements (e.g., Strings)
        BinarySearchSet<String> stringSet = new BinarySearchSet<>();
        stringSet.add("strawberries");
        stringSet.add("blueberries");
        stringSet.add("blackberries");
        stringSet.add("raspberries");

        stringSet.remove("blueberries");
        assertFalse(stringSet.contains("blueberries"));
    }

    @Test
    public void testRemoveAllWithString() {
        BinarySearchSet<String> testSet = new BinarySearchSet<>();
        testSet.add("apple");
        testSet.add("banana");
        testSet.add("orange");
        testSet.add("grape");


        Collection<String> elementsToRemove = new ArrayList<>();
        elementsToRemove.add("orange");
        elementsToRemove.add("grape");

        assertTrue(testSet.removeAll(elementsToRemove));

        assertTrue(testSet.contains("banana"));
        assertTrue(testSet.contains("apple"));
        assertFalse(testSet.contains("orange"));
        assertFalse(testSet.contains("grape"));
    }

    @Test
    public void testRemoveIterator() {
        BinarySearchSet<String> testRemoveIterator = new BinarySearchSet<>();
        testRemoveIterator.add("red");
        testRemoveIterator.add("orange");
        testRemoveIterator.add("yellow");
        testRemoveIterator.add("green");
        testRemoveIterator.add("blue");

        assertTrue(testRemoveIterator.contains("yellow"));  // Corrected assertion

        // Get an iterator and advance it to the position of "yellow"
        Iterator<String> iterator = testRemoveIterator.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            System.out.println(str);
            if (str.equals("yellow")) {  // Corrected break condition
                break;
            }
        }
        // Call the remove method using the iterator
        iterator.remove();

        // Now "yellow" should be removed from the set
        assertFalse(testRemoveIterator.contains("yellow"));  // Corrected assertion
    }

    @Test
    void testIterator() {
        BinarySearchSet<Integer> iteratorTestSet = new BinarySearchSet<>();
        iteratorTestSet.add(1);
        iteratorTestSet.add(5);
        iteratorTestSet.add(2);
        iteratorTestSet.add(7);
        iteratorTestSet.add(8);
        var iterator = iteratorTestSet.iterator();
        while (iterator.hasNext()) {
            var x = iterator.next();
            System.out.println(x);
        }
    }

    @Test
    public void testGet() {
        BinarySearchSet<Integer> testGetSet = new BinarySearchSet<>();
        testGetSet.add(1);
        testGetSet.add(5);
        testGetSet.add(2);
        testGetSet.add(7);
        testGetSet.add(8);

        // Test with a valid index
        int validIndex = 2;
        assertEquals(Integer.valueOf(5), testGetSet.get(validIndex));

        // Test with an invalid index (out of bounds)
        int invalidIndex = ((Object[]) testGetSet.getSet_()).length + 1;
        try {
            testGetSet.get(invalidIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
        // Test with negative index
        int negativeIndex = -1;
        try {
            testGetSet.get(negativeIndex);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // This exception is expected
        }
    }

    @Test
    public void testToArray() {
        BinarySearchSet<String> testToArraySet = new BinarySearchSet<>();
        testToArraySet.add("orange");
        testToArraySet.add("pineapple");
        testToArraySet.add("lemon");

        testToArraySet.addAll(Arrays.asList("apple", "banana", "cherry"));

        Object[] sortedArray = testToArraySet.toArray();

        // Assert that the array is sorted in ascending order
        assertArrayEquals(new Object[]{"apple", "banana", "cherry", "lemon", "orange", "pineapple"}, sortedArray);
    }
}