package assignment07;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
public class ChainingHashTable implements Set<String>{
    private static final double loadFactor = 1;
    private LinkedList<String>[] storage;
    private HashFunctor functor;
   private int size;
    private int capacity;
    @SuppressWarnings("unchecked")
    public ChainingHashTable(int capacity, HashFunctor functor){
        this.capacity = capacity;
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        this.functor = functor;
        size = 0;
    }
    /**
     * Ensures that this set contains the specified item.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually inserted); otherwise, returns false
     */
    @Override
    public boolean add(String item) {
        int hashCode = functor.hash(item);
        int index = Math.abs(hashCode) % storage.length;
        //Check if LinkedList isn't there, then make a new one.
        if (storage[index] == null) {
            storage[index] = new LinkedList<>();
        }
        //option 1- search through the entire set
        //Block duplicates.
        if(storage[index].contains(item)){
            return false;
        }
//        //option 2 - only search through list at a certain index
//        //if there's already a list started at that index, loop through all the strings in it and check that
//        //the string we're trying to add isn't already there
//        for (String element : storage[index]) {
//            if (element.equals(item)) {
//                return false;
//            }
//        }
        //if it doesn't already exist add it to the storage array and increment size
        storage[index].add(item);
        size++;
        //if the load factor is surpassed need to make a new array of linked lists that is bigger
//        if(size / storage.length >= 2) {
//            resize();
//        }
        return true;
    }
    @SuppressWarnings("unchecked")
//    private void resize() {
//        int newCapacity = 2 * capacity;
//        LinkedList<String>[] newStorage = (LinkedList<String>[]) new LinkedList[newCapacity];
//        //Iterate over storage.
//        for (LinkedList<String> list : storage) {
//            if (list != null) {
//                //Iterate over list.
//                for (String item : list) {
//                    int index = Math.abs(functor.hash(item)) % newCapacity;
//                    //If it is null, create new LinkedList.
//                    if (newStorage[index] == null) {
//                        newStorage[index] = new LinkedList<>();
//                    }
//                    newStorage[index].add(item);
//                }
//            }
//        }
//        storage = newStorage;
//        capacity = newCapacity;
//    }
    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually inserted); otherwise,
     * returns false
     */
    @Override
    public boolean addAll(Collection<? extends String> items) {
        //Store size.
        int origSize = size;
        //Iterate over and add all items.
        for (String s : items){
            this.add(s);
        }
        //New size.
        int finalSize = size;
        //Check if the size changed.
        return finalSize > origSize;
    }
    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        //storage = new LinkedList[capacity];
        Arrays.fill(storage, null);
        size = 0;
    }
    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     * otherwise, returns false
     */
    @Override
    public boolean contains(String item) {
        if (isEmpty()){
            return false;
        }
        //Get hashCode and index.
        int hashCode = functor.hash(item);
        int index = Math.abs(hashCode) % storage.length;
        //Check that item exists, if not return false.
        if (storage[index] != null) {
            //Check all strings in storage.
            for (String s : storage[index]) {
                //If the item is there, return true.
                if (s.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     * in this set that is equal to it; otherwise, returns false
     */
    @Override
    public boolean containsAll(Collection<? extends String> items) {
        //Check for a match in items.
        for(String s : items){
            //If found, return true.
            if(!contains(s)){
                return false;
            }
        }
        return true;
    }
    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * the input item was actually removed); otherwise, returns false
     */
    @Override
    public boolean remove(String item) {
        int hashCode = functor.hash(item);
        int index = Math.abs(hashCode) % storage.length;
        Boolean result =  storage[index].remove(item);
        if(result){
            size--;
        }

        return result;

    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     * any item in the input collection was actually removed); otherwise,
     * returns false
     */
    @Override
    public boolean removeAll(Collection<? extends String> items) {
        int origSize = size;
        for(String s : items){
            this.remove(s);
        }
        int finalSize = size;
        return finalSize < origSize;
    }
    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return size;
    }

    public LinkedList<String>[] getStorage() {
        return storage;
    }
}