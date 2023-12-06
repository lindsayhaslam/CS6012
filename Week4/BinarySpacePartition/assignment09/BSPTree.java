package assignment09;
import org.w3c.dom.Node;
import java.util.ArrayList;
public class BSPTree {
    Node root;

    //Constructor
    public BSPTree() {
        this.root = null;
    }

    //Constructor taking ArrayList<Segment>
    public BSPTree(ArrayList<Segment> segments) {
        for (Segment segment : segments) {
            insert(segment);
        }
    }

    //Node class representing a node in the BSPTree
    private static class Node {
        Segment segment;
        Node left;
        Node right;

        public Node(Segment segment) {
            this.segment = segment;
            this.left = null;
            this.right = null;
        }
    }

    public Node getRoot() {
        return root;
    }

    public void insert(Segment segment) {
        this.root = insertRec(this.root, segment);
    }

    public Node insertRec(Node root, Segment segment) {
        if (root == null) {
            return new Node(segment);
        }

        int side = root.segment.whichSide(segment);

        if (side < 0) {
            // Segment is on the left side, insert to the left subtree
            root.left = insertRec(root.left, segment);
        } else if (side > 0) {
            // Segment is on the right side, insert to the right subtree
            root.right = insertRec(root.right, segment);
        } else {
            // Segment crosses the splitting line, insert on both sides
            root.left = insertRec(root.left, segment);
            root.right = insertRec(root.right, segment);
        }

        return root;
    }

    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseFarToNearRec(root, x, y, callback);
    }

    private void traverseFarToNearRec(Node root, double x, double y, SegmentCallback callback) {
        if (root != null) {
            int side = root.segment.whichSidePoint(x, y);

            if (side < 0) {
                traverseFarToNearRec(root.left, x, y, callback);
                callback.callback(root.segment);
                traverseFarToNearRec(root.right, x, y, callback);
            } else if (side > 0) {
                traverseFarToNearRec(root.right, x, y, callback);
                callback.callback(root.segment);
                traverseFarToNearRec(root.left, x, y, callback);
            } else {
                // For segments that intersect the point, visit both sides but only once
                callback.callback(root.segment);
                traverseFarToNearRec(root.left, x, y, callback);
                traverseFarToNearRec(root.right, x, y, callback);
            }
        }
    }

    public Segment collision(Segment query) {
        return collisionRec(root, query);
    }

    private Segment collisionRec(Node root, Segment query) {
        if (root == null) {
            return null;
        }

        System.out.println("Checking intersection for: " + query + " and " + root.segment);

        if (query.intersects(root.segment)) {
            System.out.println("Collision detected!");
            return root.segment;
        }

        int side = root.segment.whichSide(query);

        System.out.println("Side of query segment with respect to root: " + side);

        if (side < 0) {
            return collisionRec(root.left, query);
        } else {
            return collisionRec(root.right, query);
        }
    }
}