package assignment03;

import java.util.*;

public class BinarySearchSet<E> implements SortedSet<E>{

    //Declare variables of the array
    private E[] set_;
    private int size = 0;
    private int capacity;

    //Declare the Comparator
    private Comparator<? super E> comparator_;


    /**
     * Default constructor for BinarySearchSet.
     * Initializes the set with a default capacity of 10.
     */
    public BinarySearchSet(){
        capacity = 10;
        set_ = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Custom constructor for BinarySearchSet.
     * Initializes the set with a default capacity of 10 and a specified comparator.
     * @param comparator the comparator to be used for element comparison
     */
    public BinarySearchSet(Comparator<? super E> comparator){
        comparator_ = comparator;
        capacity = 10;
        set_ = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Returns the comparator used for element comparison in this set.
     * @return the comparator used for element comparison.
     */
    @Override
    public Comparator<? super E> comparator() {
        return comparator_;
    }

    /**
     * Returns the first (smallest) element in the set.
     * @return the first element in the set
     * @throws NoSuchElementException if the set is empty
     */
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

    /**
     * Returns the last (largest) element in the set.
     * @return the last element in the set
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public E last() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException("Set is empty");
        }
        return set_[size-1];
    }

    /**
     * Doubles the capacity of the array, copying existing elements to the new array.
     */
    public void growArray() {
        // Dynamically allocate memory for a temporary array that is twice the size of the original.
        capacity = 2 * capacity;
        // Copy the contents over from set to the temp array
        E[] tempArray = Arrays.copyOf(set_, capacity);
        // Set_ is the pointer to the temp array.
        set_ = tempArray;
        // Set the pointer to the temp array to null.
        tempArray = null;
    }

    /**
     * Performs a binary search for the specified element.
     * @param element the element to search for
     * @return the index of the element if found; otherwise, the insertion point with a negated value
     */
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

    /**
     * Adds the specified element to the set.
     * @param element the element to add
     * @return true if the element was added; false if the element was already present
     */
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
            //Assign the insertion point, ternary condition
            //The ternary condition converts this negative insertion point to a valid array index by negating the value and subtracting 1.
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

    /**
     * Adds all elements from the specified collection to the set.
     * Skips duplicates (elements already in the set).
     *
     * @param elements the collection containing elements to be added
     * @return true if any new elements were added; false if no new elements were added
     */
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

    /**
     * Removes all elements from the set, making it empty.
     */
    @Override
    public void clear() {
        size = 0;
    }

    /**
     * Checks if the set contains the specified element using binary search.
     *
     * @param element the element to check for
     * @return true if the set contains the element; false otherwise
     */
    @Override
    public boolean contains(E element) {
        return binarySearch(element) >= 0;
    }

    /**
     * Checks if the set contains all elements from the specified collection.
     *
     * @param elements the collection containing elements to check for
     * @return true if the set contains all elements; false if any element is not in the set
     */
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

    /**
     * Checks if the set is empty.
     *
     * @return true if the set is empty; false if it contains elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator iterator() {
        //Creating and returning new instance of MyIterator
        return new MyIterator(this);
    }

    /**
     * Returns a copy of the internal array representing the set.
     * Throws IllegalStateException if the set is not initialized.
     *
     * @return a copy of the internal array representing the set.
     * @throws IllegalStateException if the set is not initialized.
     */
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

    /**
     * Returns the element at the specified index in the set.
     * Throws IndexOutOfBoundsException if the index is out of bounds.
     *
     * @param i the index of the element to retrieve
     * @return the element at the specified index in the set
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
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

        /**
         * Constructs an iterator for BinarySearchSet.
         * @param set the BinarySearchSet to iterate over
         */
        public MyIterator(BinarySearchSet<E> set) {
            this.set_ = set.getSet_();
        }

        /**
         * Checks if there are more elements in the set.
         * @return true if there are more elements; false otherwise
         */
        @Override
        public boolean hasNext() {
            return position + 1 < size;
        }
        /**
         * Retrieves the next element in the set.
         * @return the next element in the set
         * @throws NoSuchElementException if there are no more elements
         */
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

        /**
         * Removes the last element retrieved by the iterator from the set.
         * @throws IllegalStateException if remove is called without a preceding or subsequent call to next()
         */
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

    /**
     * Removes the specified element from the set.
     * @param element the element to remove
     * @return true if the element was removed; false if the element was not found
     */
    @Override
    public boolean remove(E element) {
        int index = this.binarySearch(element);

        if (index >= 0) {
            // Shifting elements to the left
            System.arraycopy(set_, index + 1, set_, index, size - index - 1);
            size--;
            return true;
        }
        // Element not found
        return false;
    }

    /**
     * Removes all elements in the specified collection from the set.
     * @param elements the collection of elements to remove
     * @return true if any elements were removed; false if no elements were removed
     */
    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        MyIterator iterator = new MyIterator(this);

        //Flag to track whether any elements were removed.
        boolean removed = false;

        while (iterator.hasNext()) {
            E obj = iterator.next();
            if (elements.contains(obj)) {
                iterator.remove();
                removed = true;
            }
        }

        System.out.println("Removed: " + removed);
        System.out.println("Set after removal: " + Arrays.toString(toArray()));

        return removed;
    }

    /**
     * Returns the size of the set.
     * @return the size of the set
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Returns an array containing all the elements in the set.
     * The array is sorted according to the set's comparator.
     * @return an array containing all the elements in the set
     */
    @Override
    public E[] toArray() {
        E[] sortedArray = (E[]) new Object[size];
        System.arraycopy(set_, 0, sortedArray, 0, size);
        // Sort the array in ascending order
        Arrays.sort(sortedArray, comparator_);
        return sortedArray;
    }
}
