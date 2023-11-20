package assignment05;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> stackList;

    //Constructor
    /**
     * Constructs an empty linked list stack.
     */
    public LinkedListStack() {
        stackList = new SinglyLinkedList<>();
    }
    /**
     * Clears all elements from the stack.
     */
    @Override
    public void clear() {
        stackList.clear();
    }
    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.getFirst();
    }
    /**
     * Retrieves and removes the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.deleteFirst();
    }
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    @Override
    public void push(E element) {
        stackList.insertFirst(element);
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return stackList.size();
    }
}
