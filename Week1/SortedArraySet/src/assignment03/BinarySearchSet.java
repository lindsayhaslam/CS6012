package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>{

    //Declare variables of the array
    private E[] set_;
    private int size = 0;
    private int capacity;

    //Declare the Comparator
    private Comparator<? super E> comparator_;


    //Default constructor
    public BinarySearchSet(){
        capacity = 10;
        set_ = (E[]) new Object[capacity];
        size = 0;
    }

    //Custom constructor (takes comparator as a parameter)
    public BinarySearchSet(Comparator<? super E> comparator){
        comparator_ = comparator;
        capacity = 10;
        set_ = (E[]) new Object[capacity];
        size = 0;
    }

    //Returning the comparator
    @Override
    public Comparator<? super E> comparator() {
        return comparator_;
    }

    @Override
    public E first() throws NoSuchElementException {
        if (isEmpty() || set_ == null) {
            throw new NoSuchElementException("Set is empty");
        }
        //otherwise return the 0th (first) element
        for (E element : set_) {
            if (element != null) {
                return element;
            }
        }
        throw new NoSuchElementException("No non-null element found");
    }

    @Override
    public E last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Set is empty");
        }
        return set_[size-1];
    }

    public void growArray() {
        // Dynamically allocate memory for a temporary array that is twice the size of the original.
        capacity = 2 * capacity;
        // Copy the contents over from set to this temp array
        E[] tempArray = Arrays.copyOf(set_, capacity);
        // Set = the pointer to the temp array.
        set_ = tempArray;
        // Set the pointer to the temp array to nullptr.
        tempArray = null;
        // Update the size after growing the array
        size = size(); // Update size based on the current number of elements in the set
    }

    private int binarySearch(E element) {
        int low = 0;
        int high = size - 1;
        int cmp;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (comparator_ != null) {
                cmp = comparator_.compare(set_[mid], element);
            } else {
                Comparable<? super E> midVal = (Comparable<? super E>) set_[mid];
                if (midVal == null) {
                    break;
                }
                cmp = midVal.compareTo(element);
            }

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;  // Element found
            }
        }

        // Element not found, return the insertion point
        return -low - 1;
    }

//
//    private int binarySearch(E element,int start , int end , E[] array){
//        if(start > end){
//            return  -1;
//        }
//        if (start <= end){
//            int mid = start + (end-start)/2 ;
//            E midValue = array[mid];
//            int compare = ((Comparable<E>)midValue).compareTo(element);
//            if(compare == 0){
//                return mid;
//            }
//            else if(compare == -1){
//                return binarySearch(element,start,mid-1,array);
//            }
//            else{
//                return binarySearch(element,mid+1,end,array);
//            }
//        }
//        return  -1;
//    }

    @Override
    public boolean add(E element) {
        boolean added = false;
        if (set_ == null) {
            //Create a new array of type E with length of 1
            set_ = (E[]) new Object[1];
            //Place element
            set_[0] = element;
            //Set the size to 1
            size = 1;
            added = true;
            return added;
        }
        if (!contains(element)) {
            if (size == capacity) {
                growArray();
            }
            // Call binarySearch to find the insertion point
            int insertionPoint = binarySearch(element);
            // ssign the insertion point, ternary condition
            //By negating the value and subtracting 1, it ensures that the insertion point is
            //distinguishable from a valid array index.
            insertionPoint = insertionPoint >= 0 ? insertionPoint : -(insertionPoint + 1);
            // Shift the elements to the right, starting from the insertion point, to make space for the new element.
            System.arraycopy(set_, insertionPoint, set_, insertionPoint + 1, size - insertionPoint);
            // Assign the new element to the insertion point and increment the size of the set.
            set_[insertionPoint] = element;
            size++;
            added = true;
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        int originalSize = this.size();

        // Create an iterator for the elements to be added
        Iterator<? extends E> elementsIterator = elements.iterator();

        // Iterate through the elements to add them to the set
        while (elementsIterator.hasNext()) {
            E element = elementsIterator.next();
            // Skip over duplicates (elements already in the set)
            if (!this.contains(element)) {
                this.add(element);
            }
        }

        int finalSize = this.size();
        // Compare sizes
        return finalSize > originalSize;
    }

    @Override
    public void clear() {
        size = 0;
    }

    //Is used in the binary search.
    //If the return index is > 0 we know it is in the set.
    @Override
    public boolean contains(E element) {
        return binarySearch(element) >= 0;
    }

    //Iterate through elements in collection and check if each one is in the set.
    //If any of them are not in the set then return false.
    @Override
    public boolean containsAll(Collection<? extends E> elements) {
        boolean containsAll = false;
        for (E obj : elements) {
                if (!this.contains(obj)) {
                    return containsAll;
                }
            }
            containsAll = true;
            return containsAll;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        //Creating and returning new instance of MyIterator
        return new MyIterator(this);
    }

    public E[] getSet_() {
        if (set_ == null) {
            throw new IllegalStateException("The set_ is null. It should have been initialized.");
        }
        // Create a new array of the desired type
        E[] newArray = (E[]) new Object[size];
        // Copy the elements manually
        System.arraycopy(set_, 0, newArray, 0, size);
        return newArray;
    }

    public E get(int i ){
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for BinarySearchSet");
        }
        return set_[i];
    }

    //MyIterator Class
    class MyIterator implements Iterator<E> {
        private E[] set_;

        private int position = -1;
        private boolean removeCalled = false;

        public MyIterator(BinarySearchSet<E> set) {
            this.set_ = set.getSet_();
        }

        @Override
        public boolean hasNext() {
            return position + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }

            // Increment position before retrieving the current element
            position++;
            removeCalled = false;
            return get(position);
        }

        @Override
        public void remove() {
            if (position < 0 || position >= size || removeCalled) {
                throw new IllegalStateException("Cannot remove without a preceding or subsequent call to next()");
            }

            E obj = get(position);
            BinarySearchSet.this.remove(obj);

            set_ = BinarySearchSet.this.getSet_();
            size--;
            removeCalled = true;
        }
    }

    @Override
    public boolean remove(E element) {
        int index = this.binarySearch(element);

        if (index >= 0) {
            // Element found, remove it
            System.arraycopy(set_, index + 1, set_, index, size - index - 1);
            set_[--size] = null;  // Clear the last element
            return true;
        }
        // Element not found
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        MyIterator iterator = new MyIterator(this);

        // Flag to track whether any elements were removed
        boolean removed = false;

        while (iterator.hasNext()) {
            E obj = iterator.next();
            if (elements.contains(obj)) {
                iterator.remove();
                removed = true;
                continue;
            }
        }

        System.out.println("Removed: " + removed);
        System.out.println("Set after removal: " + Arrays.toString(toArray()));

        return removed;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        E[] sortedArray = (E[]) new Object[size];
        System.arraycopy(set_, 0, sortedArray, 0, size);
        // Sort the array in ascending order
        Arrays.sort(sortedArray, comparator_);
        return sortedArray;
    }
}
