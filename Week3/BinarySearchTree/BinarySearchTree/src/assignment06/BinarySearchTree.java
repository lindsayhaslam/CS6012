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

    private Node addRecursive(Node node, T value){
        if (node == null){
            return new Node(value);
        }
        int compareResult = value.compareTo(node.data);
        if(compareResult < 0){
            node.left = addRecursive(node.left, value);
        }else if(compareResult > 0){
            node.right = addRecursive(node.right, value);
        }
        return node;
    }


    @Override
    public boolean add(T item) {
        if(item == null){
            throw new NullPointerException("Item cannot be null.");
        }
        if(contains(item)) {
            return false;
        }
        root = addRecursive(root, item);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> items) {
        boolean modified = false;
        for (T item : items){
           if (add(item)){
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean contains(T item) throws NullPointerException{
        if (item == null){
            throw new NullPointerException("Item cannot be null.");
        }
        return containsRecursive(root, item);
    }

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
            return true;
        }
    }

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

    @Override
    public T first() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("There is nothing there!");
        }
        return findMin(root).data;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public T last() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("There is nothing there!");
        }
        return findMax(root).data;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

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

    @Override
    public int size() {
        return sizeRecursive(root);
    }

    private int sizeRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeRecursive(root.left) + sizeRecursive(root.right);
    }
    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    private void inOrderTraversal(Node root, ArrayList<T> list) {
        if (root != null) {
            inOrderTraversal(root.left, list);
            list.add(root.data);
            inOrderTraversal(root.right, list);
        }
    }
}
