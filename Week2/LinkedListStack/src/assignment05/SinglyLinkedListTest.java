package assignment05;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {

    @org.junit.jupiter.api.Test
    void insertFirst() {
        //Create a SinglyLinkedList and add elements.
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(3);
        assertEquals(3, list.getFirst());
        list.insertFirst(2);
        list.insertFirst(1);

        //Check if the size is updated.
        assertEquals(3, list.size());

        //Check if the elements are inserted at the first position.
        assertEquals(1, list.getFirst());
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void insert() {
        //Create a SinglyLinkedList with initial elements.
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        //Insert at the beginning.
        list.insert(0, 1);
        //Insert at the end.
        list.insert(1, 3);
        //Insert in the middle.
        list.insert(1, 2);

        //Check if the size is updated.
        assertEquals(3, list.size());

        //Check if the elements are inserted at the right position.
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void insertWithStrings() {
        //Create a SinglyLinkedList with initial elements.
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        //Insert at the beginning.
        list.insert(0, "Apple");
        //Insert at the end.
        list.insert(1, "Banana");
        //Insert in the middle.
        list.insert(1, "Orange");

        //Check if the size is updated.
        assertEquals(3, list.size());

        //Check if the elements are inserted at the right position.
        assertEquals("Apple", list.get(0));
        assertEquals("Orange", list.get(1));
        assertEquals("Banana", list.get(2));
    }

    @org.junit.jupiter.api.Test
    void getFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(1, list.getFirst());
    }

    @org.junit.jupiter.api.Test
    void get() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(2, list.get(1));
    }

    @org.junit.jupiter.api.Test
    void deleteFirst() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(1, list.deleteFirst());
        //Check that head is updated.
        assertEquals(2, list.getFirst());
        assertEquals(2, list.size());
    }
    @Test
    void deleteFirstAfterClear(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        list.clear();
        assertNull(list.deleteFirst());
    }

    @org.junit.jupiter.api.Test
    void delete() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(2, list.delete(1));
        //Check that head hasn't been affected.
        assertEquals(1, list.getFirst());
        assertEquals(2, list.size());
    }
    @Test
    void deleteMultiple() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        Integer deletedValue1 = list.delete(0);
        Integer deletedValue2 = list.delete(1);

        // Check the values of the deleted elements
        assertEquals(Integer.valueOf(1), deletedValue1);
        assertEquals(Integer.valueOf(3), deletedValue2);
        assertEquals(1, list.size());
    }

    @Test
    void deleteAfterClear(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        list.clear();
        assertNull(list.delete(1));
    }

    @org.junit.jupiter.api.Test
    void indexOf() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3, 4, 5)));
        //Element 3 is at index 2.
        assertEquals(2, list.indexOf(3));
        //Element 1 is at index 0.
        assertEquals(0, list.indexOf(1));
        //Element 5 is at index 4.
        assertEquals(4, list.indexOf(5));
    }

    @Test
    void indexOfNotInList(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3, 4, 5)));
        //Check for elements not in list.
        assertEquals(-1, list.indexOf(6));
        assertEquals(-1, list.indexOf(0));
    }

    @org.junit.jupiter.api.Test
    void size() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertEquals(3, list.size());
        list.insertFirst(0);
        assertEquals(4, list.size());
        list.delete(2);
        assertEquals(3, list.size());
    }

    @Test
    void sizeOfEmpty(){
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        assertEquals(0, list.size());
    }

    @org.junit.jupiter.api.Test
    void isEmpty() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void clear() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @org.junit.jupiter.api.Test
    void toArray() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        Object[] array = list.toArray();

        assertArrayEquals(new Object[]{1, 2, 3}, array);
    }

    @Test
    void testIterator() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.insert(i, i);
            data.add(i);
        }

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());

        // Test iterator remove
        iterator = list.iterator();
        iterator.next();
        iterator.remove(); // remove the first element

        // Check the size after removal
        assertEquals(data.size() - 1, list.size());

    }

    @Test
    void iteratorHasNext() {
        //Test when the list is not empty.
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        Iterator<Integer> iterator = list.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());

        //Test when the list is empty.
        SinglyLinkedList<Integer> emptyList = new SinglyLinkedList<>();
        Iterator<Integer> emptyIterator = emptyList.iterator();
        assertFalse(emptyIterator.hasNext());
    }
    @Test
    void iteratorRemove() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        Iterator<Integer> iterator = list.iterator();

        //Advance to the second element.
        iterator.next();

        //Remove the second element.
        iterator.remove();
        assertEquals(2, list.size());

        //Check the contents after removing the second element.
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));

        //Advance to the third element.
        iterator.next();

        //Remove the third element.
        iterator.remove();
        assertEquals(1, list.size());

        //Check the contents after removing the third element.
        assertEquals(Integer.valueOf(1), list.get(0));
    }
    @Test
    void iteratorRemoveNoSuchElementException() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(new ArrayList<>(List.of(1, 2, 3)));
        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        assertEquals(0, list.size());

        // Create a new list after removing all elements
        list = new SinglyLinkedList<>();

        // Now create a new iterator on the new list
        Iterator<Integer> newIterator = list.iterator();

        assertFalse(newIterator.hasNext());

        // Ensure that calling next() or remove() on an empty iterator throws NoSuchElementException
        assertThrows(NoSuchElementException.class, newIterator::next);
        assertThrows(NoSuchElementException.class, newIterator::remove);
    }
}