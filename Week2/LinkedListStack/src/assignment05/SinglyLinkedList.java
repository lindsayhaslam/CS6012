package assignment05;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    Node head;
    Node tail;
    int size;

    //Constructor
    public SinglyLinkedList() {
        head = new Node(null);
        size = 0;
    }
    /**
     * Creates a singly-linked list from an ArrayList.
     *
     * @param arr the ArrayList containing elements to be added to the list.
     */
    public SinglyLinkedList(ArrayList<E> arr) {
        tail = head;
        for (var x : arr) {
            //check if LinkedList is empty
            if (head == null) {
                //Head and tail pointers need to be updated to point to the first element being added.
                //Create a new Node and assign it to head.
                head = new Node(x);
                //Update tail to point to the same Node as head.
                tail = head;
            } else {
                //Create a new Node and assign it to tail.next.
                tail.next = new Node(x);
                //Update tail to point to the newly added Node.
                tail = tail.next;
            }

        }
        size = arr.size();

    }
    /**
     * A private inner class representing a node in the singly-linked list.
     * Each node contains a value and a reference to the next node in the sequence.
     */
    private class Node {
        E value;
        Node next;
        /**
         * Creates a new node with the specified value.
         *
         * @param d the value to be stored in the node
         */
        Node(E d) {
            value = d;
            next = null;
        }
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;

        size++;
    }

    /**
     * Inserts an element at the specified index in the list.
     *
     * @param index   the index at which the element should be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        //Check for index of 0
        if (index == 0) {
            insertFirst(element);
            return;
        }
        Node newNode = new Node(element);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;

        //Check if inserting at the end, updating tail.
        if (index == size) {
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        return head.value;
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }
    /**
     * Removes and returns the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (head == null)
            return null;
        //Store OG head in temporary node.
        Node temp = head;
        //Move the head pointer to the next node.
        head = head.next;
        size--;
        return temp.value;
    }

    /**
     * Removes and returns the element at the specified index in the list.
     *
     * @param index the index of the element to be removed
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E delete(int index) throws IndexOutOfBoundsException {
        if (head == null || index < 0 || index >= size) {
            return null;
        }

        Node temp = head;
        E deletedValue = null;

        if (index == 0) {
            deletedValue = temp.value;
            head = temp.next;

            // If the list becomes empty, update tail to null
            if (head == null) {
                tail = null;
            }
        } else {
            for (int i = 0; temp != null && i < index - 1; i++) {
                temp = temp.next;
            }

            if (temp == null || temp.next == null) {
                return null;
            }

            Node nextNode = temp.next.next;
            deletedValue = temp.next.value;
            temp.next = nextNode;

            // If the last element is deleted, update tail
            if (nextNode == null) {
                tail = temp;
            }
        }

        size--;

        // If the list is empty, set both head and tail to null
        if (size == 0) {
            head = null;
            tail = null;
        }

        return deletedValue;
    }

    /**
     * Returns the index of the first occurrence of the element in the list.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the element, or -1 if not found.
     */
    @Override
    public int indexOf(E element) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.value.equals(element)) {
                //Found in the element, return the index.
                return index;
            }
            current = current.next;
            index++;
        }
        //Element not found.
        return -1;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the list is empty.
     *
     * @return true if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes all elements from list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }
    /**
     * Returns an array containing all of the elements in the list in proper sequence.
     *
     * @return an array containing all of the elements in the list
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;

        while (current != null) {
            array[index++] = current.value;
            current = current.next;
        }

        return array;
    }
    /**
     * Returns an iterator over the elements in the list.
     *
     * @return an iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }
    /**
     * An inner class representing an iterator.
     * Allows for iterating over the elements and removing them during iteration.
     */
    class MyIterator implements Iterator<E> {
        private Node current;
        private Node previous;
        private boolean calledNext = false;

        public MyIterator() {
            current = head;
            previous = null;
        }
        /**
         * Checks if there are more elements in the iteration.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null && current.value != null;
        }
        /**
         * Retrieves the next element in the iteration.
         *
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements exist in the list");
            }
            E value = current.value;
            previous = current;
            current = current.next;
            calledNext = true;
            return value;
        }

        /**
         * Removes the last element returned by the iterator.
         *
         * @throws NoSuchElementException if no more elements to remove.
         */
        @Override
        public void remove() {
            if (!calledNext) {
                throw new NoSuchElementException("No more elements to iterate over");
            }

            if (previous == null) {
                // Removing the first element.
                head = head.next;
                if (head == null) {
                    // If the list becomes empty, update tail to null.
                    tail = null;
                }
                current = head;
            } else if (current != null) {
                // Removing a non-first element.
                previous.next = current.next;
                if (current.next == null) {
                    // If the last element is removed, update tail to null.
                    tail = previous;
                }
                current = current.next;
            }

            //After removing an element, always decrement the size.
            //FOR EXCEPTION
            --size;

            //If the list is empty, set both head and tail to null.
            if (size == 0) {
                head = null;
                tail = null;
                current = null;
            }

            calledNext = false;
        }
    }
}