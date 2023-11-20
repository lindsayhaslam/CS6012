package assignment05;

import java.util.NoSuchElementException;

/**
 * Representation of a stack data structure (backed by a basic array).
 *
 * @author Erin Parker
 * @version February 24, 2022
 *
 * @param <E> - the type of elements contained in the stack
 */
public class ArrayStack<E> implements Stack<E> {

    private E[] stack;
    private int top;

    /**
     * Constructs an empty array-based stack with an initial capacity of 100.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        stack = (E[])new Object[100];
        top = -1;
    }

    /**
     * Clears all elements from the stack.
     */
    @Override
    public void clear() {
        top = -1;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E peek() throws NoSuchElementException {
        if(top == -1)
            throw new NoSuchElementException();
        return stack[top];
    }

    /**
     * Retrieves and removes the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    @Override
    public E pop() throws NoSuchElementException {
        if(top == -1)
            throw new NoSuchElementException();

        return stack[top--];
    }
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be pushed onto the stack
     */
    @SuppressWarnings("unchecked")
    @Override
    public void push(E element) {
        // expand the backing array, as needed
        if(top + 1 == stack.length) {
            Object[] temp = new Object[stack.length * 2];
            for(int i = 0; i < stack.length; i++)
                temp[i] = stack[i];
            stack = (E[])temp;
        }

        stack[++top] = element;
    }
    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return top + 1;
    }
}