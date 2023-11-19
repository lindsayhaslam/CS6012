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

    public SinglyLinkedList(ArrayList<E> arr) {
//        tail = head;
        for (var x : arr) {
            //check if LinkedList is empty
            if (head == null) {
                //head and tail pointers need to be updated to point to the first element being added
                head = new Node(x); // Create a new Node and assign it to head
                tail = head; // Update tail to point to the same Node as head
            } else {
                tail.next = new Node(x); // Create a new Node and assign it to tail.next
                tail = tail.next; // Update tail to point to the newly added Node
            }

        }
        size = arr.size();

    }

    private class Node {
        E value;
        Node next;

        Node(E d) {
            value = d;
            next = null;
        }
    }

    @Override
    public void insertFirst(E element) {
        Node newNode = new Node(element);
        newNode.next = head;
        head = newNode;

        size++;
    }

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

    @Override
    public E getFirst() throws NoSuchElementException {
        return head.value;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public E deleteFirst() throws NoSuchElementException {
        if (head == null)
            return null;
        //Store OG head in temporary node?
        Node temp = head;
        //Move the head pointer to the next node.
        head = head.next;
        size--;
        return temp.value;
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

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

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<E> {
        private Node current;
        private Node previous;
        private boolean calledNext = false;

        public MyIterator() {
            current = head;
            previous = null;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.value != null;
        }

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