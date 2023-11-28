package lab06;

import java.util.TreeSet;

public class TreeSetVersion<T extends Comparable<? super T>> implements PriorityQueue<T> {

    private TreeSet<T> set;

    public TreeSetVersion(){
        set = new TreeSet<>();
    }
    @Override
    public void add(T element) {
        set.add(element);
    }

    @Override
    public T removeMin() {
        if (isEmpty()){
            throw new NullPointerException("It's empty here!");
        }
        return set.pollFirst();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
