package assignment07;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    @Test
    void testAddWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());
        assertTrue(set.add("apple"));
        assertTrue(set.add("banana"));
        //Duplicate, should return false.
        assertFalse(set.add("apple"));
        assertTrue(set.contains("banana"));
        assertFalse(set.contains("grape"));
        //Check size.
        assertEquals(2, set.size());
    }

    @Test
    void testAddWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());
        assertTrue(set.add("apple"));
        assertTrue(set.add("banana"));
        //Duplicate, should return false.
        assertFalse(set.add("apple"));
        assertTrue(set.contains("banana"));
        assertFalse(set.contains("grape"));
        //Check size.
        assertEquals(2, set.size());
    }

    @Test
    void testAddWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());
        assertTrue(set.add("apple"));
        assertTrue(set.add("banana"));
        //Duplicate, should return false.
        assertFalse(set.add("apple"));
        assertTrue(set.contains("banana"));
        assertFalse(set.contains("grape"));
        //Check size.
        assertEquals(2, set.size());
    }
    @Test
    void testAddAllWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertFalse(set.addAll(items)); // Adding the same items again, should return false
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());
    }

    @Test
    void testAddAllWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        //Adding the same items again, should return false.
        assertFalse(set.addAll(items));
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());
    }

    @Test
    void testAddAllWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        //Adding the same items again, should return false.
        assertFalse(set.addAll(items));
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());
    }
    @Test
    void testClearWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());

        //Clear the set.
        set.clear();

        //Check that the set is empty.
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains("apple"));
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));
    }

    @Test
    void testClearWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());

        //Clear the set.
        set.clear();

        //Check that the set is empty.
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains("apple"));
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));
    }

    @Test
    void testClearWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.containsAll(items));
        assertEquals(3, set.size());

        //Clear the set.
        set.clear();

        //Check that the set is empty.
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertFalse(set.contains("apple"));
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));
    }

    @Test
    void testContainsWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());
        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains each item.
        for (String item : items) {
            assertTrue(set.contains(item));
        }
        //Check that the set does not contain other items.
        assertFalse(set.contains("grape"));
        assertFalse(set.contains("melon"));
    }

    @Test
    void testContainsWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());
        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains each item.
        for (String item : items) {
            assertTrue(set.contains(item));
        }
        //Check that the set does not contain other items.
        assertFalse(set.contains("grape"));
        assertFalse(set.contains("melon"));
    }

    @Test
    void testContainsWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());
        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains each item.
        for (String item : items) {
            assertTrue(set.contains(item));
        }
        //Check that the set does not contain other items.
        assertFalse(set.contains("grape"));
        assertFalse(set.contains("melon"));
    }

    @Test
    void testContainsAllWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains all items.
        assertTrue(set.containsAll(items));

        //Add an extra item that is not in the set.
        assertFalse(set.containsAll(Arrays.asList("apple", "banana", "orange", "grape")));
    }
    @Test
    void testContainsAllWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains all items.
        assertTrue(set.containsAll(items));

        //Add an extra item that is not in the set.
        assertFalse(set.containsAll(Arrays.asList("apple", "banana", "orange", "grape")));
    }

    @Test
    void testContainsAllWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());

        //Add some items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Check that the set contains all items.
        assertTrue(set.containsAll(items));

        //Add an extra item that is not in the set.
        assertFalse(set.containsAll(Arrays.asList("apple", "banana", "orange", "grape")));
    }

    @Test
    void testIsEmptyWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());
        assertTrue(set.isEmpty());

        //Load items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Should not be empty.
        assertFalse(set.isEmpty());

        //Clear the set, and it should be empty again.
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testIsEmptyWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());
        assertTrue(set.isEmpty());

        //Load items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Should not be empty.
        assertFalse(set.isEmpty());

        //Clear the set, and it should be empty again.
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testIsEmptyWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());
        assertTrue(set.isEmpty());

        //Load items.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange"));
        assertTrue(set.addAll(items));

        //Should not be empty.
        assertFalse(set.isEmpty());

        //Clear the set, and it should be empty again.
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testRemoveWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());
        //Non-existing item.
        assertFalse(set.remove("apple"));

        //Load something in.
        assertTrue(set.add("apple"));
        assertTrue(set.remove("apple"));

        //Load more items.
        Set<String> items = new HashSet<>(Arrays.asList("blueberries", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.remove("banana"));
        assertTrue(set.remove("blueberries"));
        assertTrue(set.remove("orange"));
        assertTrue(set.isEmpty());
    }

    @Test
    void testRemoveWithBadHash()  {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());
        //Non-existing item.
        assertFalse(set.remove("apple"));

        //Load something in.
        assertTrue(set.add("apple"));
        assertTrue(set.remove("apple"));

        //Load more items.
        Set<String> items = new HashSet<>(Arrays.asList("blueberries", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.remove("banana"));
        assertTrue(set.remove("blueberries"));
        assertTrue(set.remove("orange"));
        assertTrue(set.isEmpty());
    }

    @Test
    void testRemoveWithMediocreHash()  {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());
        //Non-existing item.
        assertFalse(set.remove("apple"));

        //Load something in.
        assertTrue(set.add("apple"));
        assertTrue(set.remove("apple"));

        //Load more items.
        Set<String> items = new HashSet<>(Arrays.asList("blueberries", "banana", "orange"));
        assertTrue(set.addAll(items));
        assertTrue(set.remove("banana"));
        assertTrue(set.remove("blueberries"));
        assertTrue(set.remove("orange"));
        assertTrue(set.isEmpty());
    }

    @Test
    void testRemoveAllWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());


        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        // Remove a subset of items
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check non-existing items.
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));

        //Check items still in set.
        assertTrue(set.contains("apple"));
        assertTrue(set.contains("grape"));
    }

    @Test
    void testRemoveAllWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());


        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        // Remove a subset of items
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check non-existing items.
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));

        //Check items still in set.
        assertTrue(set.contains("apple"));
        assertTrue(set.contains("grape"));
    }

    @Test
    void testRemoveAllWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());


        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        // Remove a subset of items
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check non-existing items.
        assertFalse(set.contains("banana"));
        assertFalse(set.contains("orange"));

        //Check items still in set.
        assertTrue(set.contains("apple"));
        assertTrue(set.contains("grape"));
    }

    @Test
    void testSizeWithGoodHash() {
        ChainingHashTable set = new ChainingHashTable(10, new GoodHashFunctor());

        //Check size is 0.
        assertEquals(0, set.size());

        //Load some times.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        //Check size.
        assertEquals(4, set.size());

        //Remove a sub set of items.
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check size.
        assertEquals(2, set.size());

        //Clear the set, and the size should be back to 0.
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    void testSizeWithBadHash() {
        ChainingHashTable set = new ChainingHashTable(10, new BadHashFunctor());

        //Check size is 0.
        assertEquals(0, set.size());

        //Load some times.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        //Check size.
        assertEquals(4, set.size());

        //Remove a sub set of items.
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check size.
        assertEquals(2, set.size());

        //Clear the set, and the size should be back to 0.
        set.clear();
        assertEquals(0, set.size());
    }

    @Test
    void testSizeWithMediocreHash() {
        ChainingHashTable set = new ChainingHashTable(10, new MediocreHashFunctor());

        //Check size is 0.
        assertEquals(0, set.size());

        //Load some times.
        Set<String> items = new HashSet<>(Arrays.asList("apple", "banana", "orange", "grape"));
        assertTrue(set.addAll(items));

        //Check size.
        assertEquals(4, set.size());

        //Remove a sub set of items.
        Set<String> itemsToRemove = new HashSet<>(Arrays.asList("banana", "orange"));
        assertTrue(set.removeAll(itemsToRemove));

        //Check size.
        assertEquals(2, set.size());

        //Clear the set, and the size should be back to 0.
        set.clear();
        assertEquals(0, set.size());
    }
}