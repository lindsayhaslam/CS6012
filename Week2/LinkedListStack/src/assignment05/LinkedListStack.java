package assignment05;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> stackList;

    //Constructor
    public LinkedListStack() {
        stackList = new SinglyLinkedList<>();
    }
    @Override
    public void clear() {
        stackList.clear();
    }

    @Override
    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.getFirst();
    }

    @Override
    public E pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return stackList.deleteFirst();
    }

    @Override
    public void push(E element) {
        stackList.insertFirst(element);
    }

    @Override
    public int size() {
        return stackList.size();
    }
}
