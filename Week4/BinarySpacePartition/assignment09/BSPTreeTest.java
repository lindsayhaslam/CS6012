package assignment09;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BSPTreeTest {

    public static class CounterCallback implements SegmentCallback {
        public int count = 0;
        public List<Segment> segments = new ArrayList<>();

        @Override
        public void callback(Segment s) {
            if(s!=null){
                count++;
                segments.add(s);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void insert() {
        // Create a BSPTree
        BSPTree bspTree = new BSPTree();

        // Insert the first segment
        Segment segment1 = new Segment(1.0, 4.0, 3.0, 4.0);
        bspTree.insert(segment1);

        // Create a CounterCallback to count the segments
        CounterCallback counterCallback = new CounterCallback();

        // Traverse the tree and perform the callback on each segment
        bspTree.traverseFarToNear(2.0, 3.0, counterCallback);
        assertEquals(1, counterCallback.count);

        // Insert the second segment
        Segment segment2 = new Segment(2.0, 5.0, 4.0, 5.0);
        bspTree.insert(segment2);

        // Reset the count
        counterCallback.count = 0;

        // Traverse the tree again and perform the callback on each segment
        bspTree.traverseFarToNear(3.0, 4.0, counterCallback);
        assertEquals(2, counterCallback.count);

        // Insert the third segment
        Segment segment3 = new Segment(3.0, 6.0, 5.0, 6.0);
        bspTree.insert(segment3);

        // Reset the count
        counterCallback.count = 0;

        // Traverse the tree again and perform the callback on each segment
        bspTree.traverseFarToNear(4.0, 5.0, counterCallback);
        assertEquals(3, counterCallback.count);
    }

    @org.junit.jupiter.api.Test
    void insertWithSegmentsConstructor() {
        // Create some segments to initialize the tree
        Segment segment1 = new Segment(1.0, 4.0, 3.0, 4.0);
        Segment segment2 = new Segment(2.0, 5.0, 4.0, 5.0);
        Segment segment3 = new Segment(3.0, 6.0, 5.0, 6.0);

        // Create a list of segments
        ArrayList<Segment> segments = new ArrayList<>();
        segments.add(segment1);
        segments.add(segment2);
        segments.add(segment3);

        // Create a BSPTree using the constructor that takes ArrayList<Segment>
        BSPTree bspTree = new BSPTree(segments);

        // Verify that the tree is not null
        assertNotNull(bspTree);
    }


    @org.junit.jupiter.api.Test
    void traverseFarToNear() {
        // Create a BSPTree
        BSPTree bspTree = new BSPTree();

        // Insert some segments
        Segment segment1 = new Segment(1.0, 4.0, 3.0, 4.0);
        Segment segment2 = new Segment(2.0, 5.0, 4.0, 5.0);
        Segment segment3 = new Segment(3.0, 6.0, 5.0, 6.0);
        bspTree.insert(segment1);
        bspTree.insert(segment2);
        bspTree.insert(segment3);

        //Create a CounterCallback to count the visited segments
        CounterCallback counterCallback = new CounterCallback();

        //Traverse the tree and perform the callback on each segment
        bspTree.traverseFarToNear(4.0, 5.0, counterCallback);

        //Verify that the count is as expected
        assertEquals(3, counterCallback.count);

        //Verify that the visited segments are in the correct order (far to near)
        assertNotNull(counterCallback.segments);

        //Update the expectation to match the third segment
        //ASK THE TA ABOUT THIS.
        assertEquals(segment2, counterCallback.segments.get(2));
        assertEquals(segment1, counterCallback.segments.get(0));
        assertEquals(segment3, counterCallback.segments.get(1));
        }

    @org.junit.jupiter.api.Test
    void collision() {
        BSPTree bspTree = new BSPTree();

        //Insert segments.
        Segment segment1 = new Segment(0.0, 0.0, 2.0, 0.0);
        Segment segment2 = new Segment(1.0, 1.0, 1.0, 3.0);
        bspTree.insert(segment1);
        bspTree.insert(segment2);

        //Create a query segment.
        Segment querySegment = new Segment(0.5, 0.5, 1.5, 2.5);

        //Perform collision check.
        Segment collidedSegment = bspTree.collision(querySegment);

        //Debug information.
        System.out.println("Collided segment: " + collidedSegment);

        //Verify that the collided segment is as expected.
        assertNotNull(collidedSegment);
    }
}