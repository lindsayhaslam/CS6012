package assignment06;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    //Inner Node class
    class Node {
        T data;
        Node left;
        Node right;

        Node(T data) {
            this.data = data;
            right = null;
            left = null;
        }
    }

    private Node root;

    //Constructor
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Recursively adds a new node containing the specified value to the binary search tree.
     *
     * @param node
     *        - the current node in the recursion
     * @param value
     *        - the value to be added to the tree
     * @return the updated node after adding the new value
     */
    private Node addRecursive(Node node, T value){
        //Leaf node or empty subtree.
        if (node == null){
            return new Node(value);
        }
        int compareResult = value.compareTo(node.data);
        //Less than 0 if value is smaller than node.data.
        if(compareResult < 0){
            node.left = addRecursive(node.left, value);
        //Greater than 0 if value is greater than node.data.
        }else if(compareResult > 0){
            node.right = addRecursive(node.right, value);
        }else{
            //Duplicate found, do nothing.
            return node;
        }
        return node;
    }


    /**
     * Ensures that this set contains the specified item.
     *
     * @param item
     *          - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually inserted); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean add(T item) {
        if(item == null){
            throw new NullPointerException("Item cannot be null.");
        }
        //Restrict duplicates, return false.
        if(contains(item)) {
            return false;
        }
        root = addRecursive(root, item);
        return true;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items
     *          - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually inserted); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean modified = false;
        //Iterate over each item in items.
        for (T item : items){
           if (add(item)){
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item
     *          - the item sought in this set
     * @return true if there is an item in this set that is equal to the input item;
     *         otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean contains(T item) throws NullPointerException{
        if (item == null){
            throw new NullPointerException("Item cannot be null.");
        }
        return containsRecursive(root, item);
    }

    /**
     * Recursively determines if there is an item in the specified binary search tree
     * that is equal to the specified item.
     *
     * @param node
     *        - the current node in the recursion
     * @param item
     *        - the item sought in the binary search tree
     * @return true if there is an item in the tree that is equal to the input item;
     *         otherwise, returns false
     */
    public boolean containsRecursive(Node node, T item){
        if (node == null){
            return false;
        }
       int compareResult = item.compareTo(node.data);
        if (compareResult < 0){
            return containsRecursive(node.left, item);
        } else if (compareResult > 0){
            return containsRecursive(node.right, item);
        }else{
            //Has been found
            return true;
        }
    }

    /**
     * Determines if for each item in the specified collection, there is an item in
     * this set that is equal to it.
     *
     * @param items
     *          - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an item
     *         in this set that is equal to it; otherwise, returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {
        for (T item : items){
            if (item == null) {
                throw new NullPointerException("Item cannot be null");
            }
            if(!contains(item)){
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("There is nothing there!");
        }
        return findMin(root).data;
    }

    /**
     * Finds the node with the minimum value in the binary search tree rooted at the specified node.
     *
     * @param node
     *        - the root node of the binary search tree
     * @return the node with the minimum value in the binary search tree rooted at the specified node
     */
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException
     *           if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("There is nothing there!");
        }
        return findMax(root).data;
    }

    /**
     * Finds the node with the maximum value in the binary search tree rooted at the specified node.
     *
     * @param node
     *        - the root node of the binary search tree
     * @return the node with the maximum value in the binary search tree rooted at the specified node
     */
    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item
     *          - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         the input item was actually removed); otherwise, returns false
     * @throws NullPointerException
     *           if the item is null
     */
    @Override
    public boolean remove(T item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null");
        }

        int initialSize = size();
        root = removeRecursive(root, item);

        //If size decreased, the item was removed
        return size() < initialSize;
    }

    /**
     * Recursively removes the node containing the specified item from the binary search tree
     * rooted at the given node.
     *
     * @param node
     *        - the current node in the recursion
     * @param item
     *        - the item to be removed from the binary search tree
     * @return the updated node after removing the specified item
     */
    private Node removeRecursive(Node node, T item) {
        if (node == null) {
            return null;
        }

        int compareResult = item.compareTo(node.data);
        if (compareResult < 0) {
            node.left = removeRecursive(node.left, item);
        } else if (compareResult > 0) {
            node.right = removeRecursive(node.right, item);
        } else {
            //Node to be deleted found
            //Case 1: Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            //Case 2: Node with two children
            node.data = findMin(node.right).data;
            node.right = removeRecursive(node.right, node.data);
        }
        return node;
    }
    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items
     *          - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if
     *         any item in the input collection was actually removed); otherwise,
     *         returns false
     * @throws NullPointerException
     *           if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        List<T> itemsToRemove = new ArrayList<>(items);
        boolean modified = false;

        for (T item : itemsToRemove) {
            if (remove(item)) {
                modified = true;
            }
        }

        return modified;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return sizeRecursive(root);
    }

    /**
     * Recursively calculates the size (number of nodes) of the binary search tree
     * rooted at the specified node.
     *
     * @param root
     *        - the root node of the binary search tree
     * @return the size of the binary search tree rooted at the specified node
     */
    private int sizeRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeRecursive(root.left) + sizeRecursive(root.right);
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    /**
     * Performs an in-order traversal of the binary search tree rooted at the specified node
     * and appends the elements to the provided ArrayList in ascending order.
     *
     * @param root
     *        - the root node of the binary search tree to traverse
     * @param list
     *        - the ArrayList to which the elements are appended during the traversal
     */
    private void inOrderTraversal(Node root, ArrayList<T> list) {
        if (root != null) {
            inOrderTraversal(root.left, list);
            list.add(root.data);
            inOrderTraversal(root.right, list);
        }
    }
}
