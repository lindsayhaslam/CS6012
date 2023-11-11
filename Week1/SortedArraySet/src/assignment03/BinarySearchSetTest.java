package assignment03;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchSetTest {

//    @Test
//    public void testGrowArray() {
//        // Test with a small initial capacity
//        BinarySearchSet<Integer> smallCapacitySet = new BinarySearchSet<>();
//        smallCapacitySet.add(1);
//        smallCapacitySet.add(2);
//        smallCapacitySet.add(3);
//        smallCapacitySet.growArray();
//        assertEquals(6, smallCapacitySet.size()); // Capacity should be doubled
//
//        // Test with a larger initial capacity
//        BinarySearchSet<String> largeCapacitySet = new BinarySearchSet<>(10);
//        largeCapacitySet.add("apple");
//        largeCapacitySet.add("banana");
//        largeCapacitySet.growArray();
//        assertEquals(20, largeCapacitySet.size()); // Capacity should be doubled
//
//        // Test with an empty set
//        BinarySearchSet<Double> emptySet = new BinarySearchSet<>();
//        emptySet.growArray();
//        assertEquals(2, emptySet.size()); // Capacity should be doubled, even for an empty set
//
//        // Test with non-empty set
//        BinarySearchSet<Character> nonEmptySet = new BinarySearchSet<>();
//        nonEmptySet.add('a');
//        nonEmptySet.add('b');
//        nonEmptySet.growArray();
//        assertEquals(4, nonEmptySet.size()); // Capacity should be doubled
//
//        // Test that the elements are preserved after growing
//        BinarySearchSet<Integer> preservationSet = new BinarySearchSet<>(3);
//        preservationSet.add(1);
//        preservationSet.add(2);
//        preservationSet.add(3);
//        preservationSet.growArray();
//        assertTrue(preservationSet.contains(1));
//        assertTrue(preservationSet.contains(2));
//        assertTrue(preservationSet.contains(3));
//
////        // Test that the original array is not retained
////        BinarySearchSet<Integer> originalArraySet = new BinarySearchSet<>(3);
////        originalArraySet.add(1);
////        originalArraySet.add(2);
////        originalArraySet.add(3);
////        originalArraySet.growArray();
////        assertNull(originalArraySet.getOriginalArray()); // Original array should be set to null
////
////        // Test idempotence
////        BinarySearchSet<Integer> idempotentSet = new BinarySearchSet<>(5);
////        idempotentSet.add(1);
////        idempotentSet.add(2);
////        idempotentSet.add(3);
////        idempotentSet.growArray();
////        int firstCapacity = idempotentSet.getCapacity();
////        idempotentSet.growArray();
////        assertEquals(firstCapacity * 2, idempotentSet.getCapacity()); // Capacity should be doubled again
//    }

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
    public void testRemoveAll() {
        BinarySearchSet<Integer> testSet = new BinarySearchSet<>();
        testSet.add(1);
        testSet.add(5);
        testSet.add(2);
        testSet.add(7);
        testSet.add(8);

        Collection<Integer> elementsToRemove = new ArrayList<>();
        elementsToRemove.add(5);
        elementsToRemove.add(7);

        assertTrue(testSet.removeAll(elementsToRemove));

        assertFalse(testSet.contains(5));
        assertFalse(testSet.contains(7));
        assertTrue(testSet.contains(1));
        assertTrue(testSet.contains(2));
        assertTrue(testSet.contains(8));
    }

    @Test
    public void testRemoveIterator(){
        BinarySearchSet<String> testRemoveIterator = new BinarySearchSet<>();
        testRemoveIterator.add("red");
        testRemoveIterator.add("orange");
        testRemoveIterator.add("yellow");
        testRemoveIterator.add("green");
        testRemoveIterator.add("blue");

        assertTrue(testRemoveIterator.contains("yellow"));

        // Get an iterator and advance it to the position of "yellow"
        Iterator<String> iterator = testRemoveIterator.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next().toString();
            System.out.println(str);
            if (str.equals("yellow")) {
                break;
            }
        }
        // Call the remove method using the iterator
        iterator.remove();
        // Now "yellow" should be removed from the set
        assertFalse(testRemoveIterator.contains("yellow"));
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
}