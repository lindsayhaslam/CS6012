package assignment05;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    Node head;
    Node tail;
    int size;

    //Constructor
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }
    /**
     * Creates a singly-linked list from an ArrayList.
     *
     * @param arr the ArrayList containing elements to be added to the list.
     */
//    public SinglyLinkedList(ArrayList<E> arr) {
//        tail = head;
//        for (var x : arr) {
//            //check if LinkedList is empty
//            if (head == null) {
//                //head and tail pointers need to be updated to point to the first element being added
//                head = new Node(x); // Create a new Node and assign it to head
//                tail = head; // Update tail to point to the same Node as head
//            } else {
//                tail.next = new Node(x); // Create a new Node and assign it to tail.next
//                tail = tail.next; // Update tail to point to the newly added Node
//            }
//
//        }
//        size = arr.size();
//
//    }

    /**
     * A private inner class representing a node in the singly-linked list.
     * Each node contains a value and a reference to the next node in the sequence.
     */
    private class Node<E> {
        E value;
        Node<E> next;

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

    Node<E> head_;
    private Comparator<? super E> comparator_;
    public int size_;


    /**
     * Inserts an element at the beginning of the list.
     *
     * @param element - the element to add
     */
    @Override
    public void insertFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (head_ == null) {
            head = newNode;
        } else {
            newNode.value = element;
            newNode.next = head_;
            head_ = newNode;
        }
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
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of range.");
        }
        //Check for index of 0
        if (index == 0) {
            insertFirst(element);
        } else {
            Node<E> current = head_;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node<E> newNode = new Node<>(element);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    /**
     * Returns the first element in the list.
     *
     * @return the first element in the list
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
        if (head_ == null) {
            throw new NoSuchElementException("List is empty.");
        }
        return head_.value;
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
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is out of range.");
        }
        Node<E> current = head_;
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
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }
        E value = head_.value;
        head_ = head_.next;
        size_--;
        return value;
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
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is beyond list range.");
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node<E> current = head_;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        E data = current.next.value;
        if (current.next.next == null) {
            current.next = null;
        } else {
            current.next = current.next.next;
        }
        size_--;
        return data;
    }

    /**
     * Returns the index of the first occurrence of the element in the list.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the element, or -1 if not found.
     */
    @Override
    public int indexOf(E element) {
        Node<E> current = head_;
        for (int i = 0; i < size(); i++) {
            if (current.value.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size_;
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
        size = 0;
    }
    /**
     * Returns an array containing all of the elements in the list in proper sequence.
     *
     * @return an array containing all of the elements in the list
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size_];
        Node<E> current = head_;
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

    class MyIterator implements Iterator<E> {
        private Node<E> current;
        private Node<E> previous;
        private boolean canRemove;

        MyIterator() {
            this.current = head_;
            this.previous = null;
            this.canRemove = false;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements exist in the list");
            }
            previous = current;
            E value = current.value;
            current = current.next;
            canRemove = true;
            return previous.value;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("No more elements to iterate over");
            }

            if (current == head_) {
                //Removing the first element.
                head_ = head_.next;
                current = head_;
            } else {
                previous.next = current.next;
                canRemove = false;
                size_--;
            }
        }
    }
}