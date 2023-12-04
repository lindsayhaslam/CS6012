package assignment08;

import java.util.*;

public class Graph {
    Node start;
    Node end;
    Node[][] nodes;

    //Nested node class.
    class Node{
        //Char value of the node.
        char value;
        boolean visited;
        //Reference to the node from which it was visited.
        Node cameFrom;
        List<Node> neighbors;

        //Constructor for Node class.
        public Node(char value) {
            this.value = value;
            this.visited = false;
            this.neighbors = new ArrayList<>();
        }
    }

    //Constructor for graph
    public Graph(char[][] maze, char startValue, char endValue) {
        //Establish rows and columns.
        int rows = maze.length;
        int cols = maze[0].length;

        //Create nodes for each element in the maze
        //New Node object with respective rows and columns
        nodes = new Node[rows][cols];
        //Iterate over each row and column
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //Create a new Node object with value of maze[i][j]
                nodes[i][j] = new Node(maze[i][j]);
                //Check for start and end nodes.
                if (nodes[i][j].value == startValue) {
                    start = nodes[i][j];
                } else if (nodes[i][j].value == endValue) {
                    end = nodes[i][j];
                }
            }
        }
        //Iterate over all the rows and columns.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (nodes[i][j] != null) {
                    //Checks if there is a node above and is not blocked by an X.
                    if (i > 0 && nodes[i - 1][j].value != 'X') {
                        //Add it as a neighbor.
                        nodes[i][j].neighbors.add(nodes[i - 1][j]);
                    }
                    //Checks if there is a node above and is not blocked by an X.
                    if (i < rows - 1 && nodes[i + 1][j].value != 'X') {
                        //Add it as a neighbor.
                        nodes[i][j].neighbors.add(nodes[i + 1][j]);
                    }
                    //Checks if there is a node left and is not blocked by an X.
                    if (j > 0 && nodes[i][j - 1].value != 'X') {
                        nodes[i][j].neighbors.add(nodes[i][j - 1]);
                    }
                    //Checks if there is a node right and is not blocked by an X.
                    if (j < cols - 1 && nodes[i][j + 1].value != 'X') {
                        nodes[i][j].neighbors.add(nodes[i][j + 1]);
                    }
                }
            }
        }
    }

//Breadth First Search
boolean breadthFirstSearch() {
    start.visited = true;
    //To keep track of nodes to be visited.
    Queue<Node>queue = new LinkedList<>();
    //Add start node to queue.
    queue.add(start);

    while (!queue.isEmpty()){
        //Dequeue a node from the front of the queue.
        Node current = queue.poll();
        //Check if it is equal to end node.
        if(current == end){
            return true;
        }
        //Iterates over neighbors of current node.
        for (Node neighbor: current.neighbors){
            if(!neighbor.visited){
                neighbor.visited = true;
                neighbor.cameFrom = current;
                //Add that to the queue.
                queue.add(neighbor);
            }
        }
    }
    return false;
}

public void setPath(){
    Node current = end;
    //Traverse the path from the end to start node.
    while (current != null){
        //Check that the cameFrom node isn't null, and that it isn't the start node.
        if (current.cameFrom != null && current.cameFrom != start){
            //Current becomes cameFrom node
            current = current.cameFrom;
            //Update value to '."
            current.value = '.';
        } else{
            break;
        }
    }
}

}