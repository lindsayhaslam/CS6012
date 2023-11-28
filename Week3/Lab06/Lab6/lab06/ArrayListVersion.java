package lab06;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListVersion <T extends Comparable<? super T>> implements PriorityQueue<T>{

    ArrayList<T> data;

    ArrayListVersion(ArrayList<T> elem){
        data = elem;
        heapify();
    }

    public ArrayListVersion() {
        data = new ArrayList<>();
    }

    private void heapify() {
        for (int i = data.size() / 2 - 1; i >= 0; i--) {
            percolateDown(i);
        }
    }

    @Override
    public void add(T element) {
        data.add(element);
        percolateUp(data.size() - 1);
    }

    private void percolateUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (data.get(index).compareTo(data.get(parentIndex)) < 0) {
                Collections.swap(data, index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    @Override
    public T removeMin() {
        T ret = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        percolateDown(0);
        return ret;
    }

    private void percolateDown(int index) {
        while (index < data.size()) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallest = index;

            if (leftChild < data.size() && data.get(leftChild).compareTo(data.get(smallest)) < 0) {
                smallest = leftChild;
            }

            if (rightChild < data.size() && data.get(rightChild).compareTo(data.get(smallest)) < 0) {
                smallest = rightChild;
            }

            if (smallest != index) {
                Collections.swap(data, index, smallest);
                index = smallest;
            } else {
                break;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
