package assignment06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @org.junit.jupiter.api.Test
    void add() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test adding elements.
        assertTrue(bst.add(10));
        assertTrue(bst.add(5));
        assertTrue(bst.add(15));
        assertTrue(bst.add(3));
        assertTrue(bst.add(7));
        assertTrue(bst.add(12));
        assertTrue(bst.add(20));

        //Returning false for duplicates.
        assertFalse(bst.add(10));
        assertFalse(bst.add(7));
        assertFalse(bst.add(20));

        //Test NullPointerException.
        assertThrows(NullPointerException.class, () -> bst.add(null));

        //Test adding a collection of elements.
        assertTrue(bst.addAll(Arrays.asList(25, 8, 18, 30)));
        //Check duplicates.
        assertFalse(bst.addAll(Arrays.asList(10, 20, 30)));

        //Test adding a collection with null elements.
        assertThrows(NullPointerException.class, () -> bst.addAll(Arrays.asList(10, null, 20)));

        //Test adding Strings.
        BinarySearchTree<String> stringBst = new BinarySearchTree<>();
        assertTrue(stringBst.add("apple"));
        assertTrue(stringBst.add("banana"));
        assertTrue(stringBst.add("orange"));
    }

    @org.junit.jupiter.api.Test
    void addAll() {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        //Test adding a collection of elements.
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));

        //Test adding a collection of duplicates.
        assertFalse(bst.addAll(Arrays.asList(10, 7, 20)));

        //Test NullPointerException.
        assertThrows(NullPointerException.class, () -> bst.addAll(Arrays.asList(10, null, 20)));

        //Test adding an empty collection/return false.
        assertFalse(bst.addAll(Arrays.asList()));

        //Test adding Strings.
        BinarySearchTree<String> stringBst = new BinarySearchTree<>();
        assertTrue(stringBst.addAll(Arrays.asList("apple", "banana", "orange")));

        //Test adding elements to an initially empty tree
        BinarySearchTree<Integer> emptyBst = new BinarySearchTree<>();
        assertTrue(emptyBst.addAll(Arrays.asList(30, 15, 45, 10, 25, 40, 60)));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Load bst with elements.
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));

        //Clear the tree.
        bst.clear();

        //Test that it is empty, size is 0, and throws NoSuchElement exception.
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertThrows(NoSuchElementException.class, () -> bst.first());
        assertThrows(NoSuchElementException.class, () -> bst.last());
        assertFalse(bst.contains(10));
        assertFalse(bst.containsAll(Arrays.asList(5, 15, 20)));
        assertEquals(0, bst.toArrayList().size());

        //Test adding elements after it has been cleared.
        assertTrue(bst.add(30));
        assertTrue(bst.add(25));
        assertTrue(bst.add(35));

        //Test that bst contains the elements.
        assertTrue(bst.contains(30));
        assertTrue(bst.containsAll(Arrays.asList(25, 35)));
        assertEquals(3, bst.size());
        assertEquals(25, bst.first());
        assertEquals(35, bst.last());
        assertEquals(Arrays.asList(25, 30, 35), bst.toArrayList());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test contains on an empty tree.
        assertFalse(bst.contains(10));

        //Load tree with elements.
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));
        //Test size.
        assertEquals(7, bst.toArrayList().size());

        //Test contains on single items.
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(20));

        //Test contains on items that are not there.
        assertFalse(bst.contains(100));
        assertFalse(bst.contains(8));
        assertFalse(bst.contains(25));

        //Test Strings
        BinarySearchTree<String> stringBst = new BinarySearchTree<>();
        assertTrue(stringBst.addAll(Arrays.asList("apple", "banana", "orange")));
        assertTrue(stringBst.contains("apple"));


        //Test NullPointerException.
        assertThrows(NullPointerException.class, () -> bst.contains(null));
    }

    @org.junit.jupiter.api.Test
    void containsAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test containsAll on an empty tree and an empty collection.
        assertTrue(bst.containsAll(Arrays.asList()));
        assertFalse(bst.containsAll(Arrays.asList(1, 2, 3)));

        //Test adding elements.
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));

        //Test containsAll on existing elements.
        assertTrue(bst.containsAll(Arrays.asList(5, 3, 20)));
        assertTrue(bst.containsAll(Arrays.asList(10, 7, 12, 15)));
        assertTrue(bst.containsAll(Arrays.asList(10)));

        //Test containsAll on non-existing elements.
        assertFalse(bst.containsAll(Arrays.asList(100, 8, 25)));
        assertFalse(bst.containsAll(Arrays.asList(100, 10)));  // One non-existing element should make the entire collection not contained

        //Test NullPointerException.
        assertThrows(NullPointerException.class, () -> bst.containsAll(Arrays.asList(10, null, 20)));
    }

    @org.junit.jupiter.api.Test
    void first() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));
        assertEquals(bst.first(), 3);

        //Remove first item and test for new item.
        assertTrue(bst.remove(3));
        assertEquals(bst.first(), 5);

        //Test for a non-existing item.
        assertNotEquals(bst.first(), 7);
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test isEmpty on an empty tree.
        assertTrue(bst.isEmpty());

        //Load the tree.
        bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20));

        //Test that it is not empty.
        assertFalse(bst.isEmpty());

        //Test isEmpty after removing one element.
        bst.remove(10);
        assertFalse(bst.isEmpty());

        bst.clear();
        assertTrue(bst.isEmpty());

        //Test isEmpty after adding elements again
        bst.addAll(Arrays.asList(30, 15, 45, 10, 25, 40, 60));
        assertFalse(bst.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void last() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test NoSuchElementException.
        assertThrows(NoSuchElementException.class, bst::last);

        //Test adding elements.
        bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20));

        //Test last after adding elements.
        assertEquals(20, bst.last());

        //Test last after remove.
        bst.remove(20);
        assertEquals(15, bst.last());

        bst.remove(15);
        assertEquals(12, bst.last());

        //Test last after clear.
        bst.clear();
        assertThrows(NoSuchElementException.class, bst::last);

        //Test last after adding elements again
        bst.addAll(Arrays.asList(30, 15, 45, 10, 25, 40, 60));
        assertEquals(60, bst.last());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test removing elements from an empty tree.
        assertFalse(bst.remove(10));

        //Test adding elements.
        assertTrue(bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20)));

        //Test removing elements.
        assertTrue(bst.remove(10));
        assertFalse(bst.contains(10));
        assertEquals(6, bst.size());

        assertTrue(bst.remove(3));
        assertFalse(bst.contains(3));
        assertEquals(5, bst.size());

        //Test removing a non-existing element.
        assertFalse(bst.remove(100));
        assertEquals(5, bst.size());

        //Test removing elements and check placements after.
        assertTrue(bst.remove(15));
        assertTrue(bst.remove(7));
        assertTrue(bst.remove(20));

        assertEquals(2, bst.size());
        assertEquals(5, bst.first());
        assertEquals(12, bst.last());
        assertTrue(bst.containsAll(Arrays.asList(5, 12)));
        assertEquals(Arrays.asList(5, 12), bst.toArrayList());

        //Test removing all elements.
        assertTrue(bst.remove(5));
        assertTrue(bst.remove(12));

        //Check if it is empty, the size, and NoSuchElementException.
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertThrows(NoSuchElementException.class, () -> bst.first());
        assertThrows(NoSuchElementException.class, () -> bst.last());
        assertFalse(bst.contains(5));
        assertFalse(bst.containsAll(Arrays.asList(5, 12)));
        assertEquals(0, bst.toArrayList().size());
    }

    @org.junit.jupiter.api.Test
    void removeAll() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        // Test removeAll on an empty tree and an empty collection
        assertFalse(bst.removeAll(Arrays.asList()));  // Nothing to remove, should return false

        // Test adding elements
        bst.addAll(java.util.Arrays.asList(10, 5, 15, 3, 7, 12, 20));

        // Test removeAll on a non-empty collection with some existing elements
        assertTrue(bst.removeAll(Arrays.asList(5, 12, 20)));
        assertEquals(4, bst.size());  // Ensure size is as expected after removal

        // Test removeAll on a non-empty collection with all existing elements
        assertTrue(bst.removeAll(Arrays.asList(10, 3, 15, 7, 20)));
        assertTrue(bst.isEmpty());  // Ensure the tree is empty after removal

        // Test removeAll on a non-empty collection with non-existing elements
        assertFalse(bst.removeAll(Arrays.asList(100, 200, 300)));
        assertTrue(bst.isEmpty());  // Ensure the tree is still empty
    }

    @org.junit.jupiter.api.Test
    void size() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test empty tree.
        assertEquals(0, bst.size());

        //Test adding elements.
        bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20));

        //Test size after adding elements.
        assertEquals(7, bst.size());

        //Test size after remove.
        bst.remove(10);
        assertEquals(6, bst.size());

        bst.remove(3);
        assertEquals(5, bst.size());

        //Test size after clear.
        bst.clear();
        assertEquals(0, bst.size());

        // Test size after adding elements again
        bst.addAll(Arrays.asList(30, 15, 45, 10, 25, 40, 60));
        assertEquals(7, bst.size());
    }

    @org.junit.jupiter.api.Test
    void toArrayList() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        //Test an empty tree.
        assertEquals(new ArrayList<>(), bst.toArrayList());

        //Test adding elements.
        bst.addAll(Arrays.asList(10, 5, 15, 3, 7, 12, 20));

        //Test toArray on a non-empty tree.
        assertEquals(Arrays.asList(3, 5, 7, 10, 12, 15, 20), bst.toArrayList());

        //Test toArray after removing elements.
        bst.remove(10);
        assertEquals(Arrays.asList(3, 5, 7, 12, 15, 20), bst.toArrayList());

        bst.remove(3);
        assertEquals(Arrays.asList(5, 7, 12, 15, 20), bst.toArrayList());

        //Test toArray after clearing the tree.
        bst.clear();
        assertEquals(new ArrayList<>(), bst.toArrayList());
    }
}