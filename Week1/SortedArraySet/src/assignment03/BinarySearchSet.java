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
        //Dynamically allocate memory for a temporary array that is twice the size of the original.
        capacity = 2 * capacity;
        //Copy the contents over from set to this temp array
        E[] tempArray = Arrays.copyOf(set_, capacity);
        //Set = the pointer to the temp array.
        set_ = tempArray;
        //Set the pointer to the temp array to nullptr.
        tempArray = null;
    }

    private int binarySearch(E element) {
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int cmp;

            if (comparator_ != null) {
                cmp = comparator_.compare(set_[mid], element);
            } else {
                Comparable<? super E> midVal = (Comparable<? super E>) set_[mid];

                if (midVal == null) {
                    // If the middle value is null, stop comparison
                    // (This can indicate that the elements are not comparable)
                    break;
                }

                cmp = midVal.compareTo(element);
            }

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid; // Element found
            }
        }

        return -(low + 1); // Element not found, return the insertion point
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
            set_ = (E[]) new Object[1];
            set_[0] = element;
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
            // Convert the insertionPoint to a positive index
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
        // Check the size of the original set before modification
        int originalSize = this.size();

        // Iterate through the elements to add them to the set
        for (E element : elements) {
            // Skip over duplicates (elements already in the set)
            if (!this.contains(element)) {
                this.add(element);
            }
        }

        int finalSize = this.size();
        // Check if the size of the set has changed
        return finalSize > originalSize;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public boolean contains(E element) {
    //use binary search helper function to get the index of the element we're searching for
        //if the return index is greater than 0 we know it is actually in the set
        return binarySearch(element) >= 0;
    }

    @Override
    public boolean containsAll(Collection<? extends E> elements) {
            //loop through all the elements in the collection and check if each one is in the set
            //if any of them are not in the set then return false
            for (E obj : elements) {
                if (!this.contains(obj)) {
                    return false;
                }
            }
            //if it makes it through the loop then return true
            return true;
    }

    @Override
    public boolean isEmpty() {
        //Use contains later
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    public E[] getSet_() {
        if (set_ == null){
            throw new IllegalStateException("The set_ is null. It should have been initialized.");
        }
        return set_;
    }
    //GET
//Get element from set (for testing)
    public E get(int i ){
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds for BinarySearchSet");
        }
        return set_[i];
    }

    class MyIterator implements Iterator<E> {
        private E[] set_;

        private int position = 0;

        public MyIterator(BinarySearchSet<E> set) {
            this.set_ = set.getSet_();
        }

        @Override
        public boolean hasNext() {
            if (position < size-1)
                return true;
            else
                return false;
        }

        @Override
        public E next() {
            E obj = get(position);
            if(hasNext()){
                position++;
            }
            return obj;

        }

        @Override
        public void remove() {
            E obj = get(position);
            BinarySearchSet.this.remove(obj);
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
    public boolean removeAll(Collection elements) {
        for(Object obj: elements){
            this.remove((E) obj);
        }
        for(Object obj: elements){
            if(this.contains((E) obj)){
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        // Create a new array to avoid modifying the internal set_
        E[] arrayCopy = Arrays.copyOf(set_, size);
        return arrayCopy;
    }
}
