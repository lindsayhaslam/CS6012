package lab06;


public interface PriorityQueue<T extends Comparable<? super T>> {

    void add(T element);
    T removeMin();
    boolean isEmpty();
}
