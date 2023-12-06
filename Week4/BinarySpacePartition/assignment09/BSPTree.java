package assignment09;
import org.w3c.dom.Node;
import java.util.ArrayList;
public class BSPTree {
    Node root;

    /**
     * Default constructor for creating an empty BSP tree.
     */
    public BSPTree() {
        this.root = null;
    }

    /**
     * Constructor for building a BSP tree from a list of segments.
     *
     * @param segments The list of segments used to construct the BSP tree.
     */
    public BSPTree(ArrayList<Segment> segments) {
        this.root = buildTree(segments);
        }

    /**
     * Private method to recursively build a BSP tree from a list of segments.
     *
     * @param segments The list of segments to partition.
     * @return The root node of the constructed BSP tree.
     */
    private Node buildTree(ArrayList<Segment> segments) {
        if (segments == null || segments.isEmpty()) {
            return null;
        }
        Segment dividingSegment = segments.get((int) (Math.random() * segments.size()));
        segments.remove(dividingSegment);
        Node node = new Node(dividingSegment);

        ArrayList<Segment> frontSegments = new ArrayList<>();
        ArrayList<Segment> backSegments = new ArrayList<>();
        for (Segment segment : segments) {
            if (segment.whichSide(dividingSegment) > 0) {
                frontSegments.add(segment);
            } else if (segment.whichSide(dividingSegment) < 0) {
                backSegments.add(segment);
            } else {
                Segment[] splitResult = dividingSegment.split(segment);
                frontSegments.add(splitResult[0]);
                backSegments.add(splitResult[1]);
            }
        }
        node.left = buildTree(frontSegments);
        node.right = buildTree(backSegments);

        return node;
    }

    /**
     * Node class representing a node in the BSP tree.
     */
    private static class Node {
        Segment segment;
        Node left;
        Node right;

        /**
         * Constructor for creating a node with a specified segment.
         *
         * @param segment The segment associated with the node.
         */
        public Node(Segment segment) {
            this.segment = segment;
            this.left = null;
            this.right = null;
        }
    }


    /**
     * Inserts a segment into the BSP tree.
     *
     * @param segment The segment to be inserted.
     */
    public void insert(Segment segment) {
        this.root = insertRec(this.root, segment);
    }

    /**
     * Private method to recursively insert a segment into the BSP tree.
     *
     * @param root The current root node of the subtree.
     * @param segment The segment to be inserted.
     * @return The updated root node after insertion.
     */
    public Node insertRec(Node root, Segment segment) {
        if (root == null){
            return new Node(segment);
        }
        int side = root.segment.whichSide(segment);
        if(side == 1){
            root.right = insertRec(root.right, segment);
        } else if (side == -1){
            root.left = insertRec(root.left, segment);
        } // TODO split segment when side == 0
        return root;
    }

    /**
     * Traverses the BSP tree from far to near with respect to a specified point and invokes a
     * callback for each traversed segment.
     *
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param callback The callback to be invoked for each traversed segment.
     */
    public void traverseFarToNear(double x, double y, SegmentCallback callback) {
        traverseFarToNearRec(root, x, y, callback);
    }

    /**
     * Private method to recursively traverse the BSP tree from far to near with respect to a
     * specified point and invoke a callback for each traversed segment.
     *
     * @param root The current root node of the subtree.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param callback The callback to be invoked for each traversed segment.
     */
    private void traverseFarToNearRec(Node root, double x, double y, SegmentCallback callback) {
        if (root != null){
            int side = root.segment.whichSidePoint(x,y);
            if(side == 1){
                traverseFarToNearRec(root.left, x, y, callback);
                callback.callback(root.segment);
                traverseFarToNearRec(root.right, x, y, callback);
            } else {
                traverseFarToNearRec(root.right, x, y, callback);
                callback.callback(root.segment);
                traverseFarToNearRec(root.left, x, y, callback);
            }
        }
    }

    /**
     * Detects collisions between a query segment and segments in the BSP tree.
     *
     * @param query The query segment for collision detection.
     * @return The first segment that collides with the query segment, or null if no collision occurs.
     */
    public Segment collision(Segment query) {
        return collisionRec(root, query);
    }

    /**
     * Private method to recursively detect collisions between a query segment and segments in the
     * BSP tree.
     *
     * @param root  The current root node of the subtree.
     * @param query The query segment for collision detection.
     * @return The first segment that collides with the query segment, or null if no collision occurs.
     */

    private Segment collisionRec(Node root, Segment query) {
        if (root == null) {
            return null;
        }

        if (query.intersects(root.segment)) {
            System.out.println("Collision detected!");
            return root.segment;
        }

        int side = root.segment.whichSide(query);

        if (side < 0) {
            return collisionRec(root.left, query);
        } else {
            return collisionRec(root.right, query);
        }
    }
}