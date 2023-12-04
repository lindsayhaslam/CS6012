package assignment08;


import java.io.*;
import java.util.Scanner;

public class PathFinder {
    static char[][] maze_;
    static Graph graph;

    public static void solveMaze(String inputFile, String outputFile) {
        //Read in the maze file and assign it to the array maze_.
        maze_ = readInFile(inputFile);
        //Create a new instance of Graph with start and goal nodes.
        graph = new Graph(maze_, 'S', 'G');
        //Call Breadth First Search method on graph to check that it exists.
        boolean pathExist = graph.breadthFirstSearch();
        //If path exists, call the setPath method, and write the outputFile.
        if (pathExist) {
            graph.setPath();
            writeToFile(outputFile);
        }
        //If not, write out the original file.
        else {
            writeOriginalToFile(outputFile);
        }
    }

    public static char[][] readInFile(String inputFile) {
        //To store the maze data.
        char[][] maze;
        try {
            //Create a new File object using inputFile.
            File file = new File(inputFile);
            //Create new Scanner object and read data from file.
            Scanner scanner = new Scanner(file);

            //For extracting the dimensions.
            //Read the first line of the file (the dimensions).
            String dimensionsLine = scanner.nextLine();
            //Split the dimensions line into an array.
            String[] dimensions = dimensionsLine.split(" ");
            //Convert first element of dimensions array to an int
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);

            //Create the maze layout
            maze = new char[height][width];
            //Iterate over each row of the maze.
            for (int i = 0; i < height; i++) {
                //Read the next line
                String line = scanner.nextLine();
                //Iterate over each column.
                for (int j = 0; j < width; j++) {
                    //Assign character at the respective position.
                    maze[i][j] = line.charAt(j);
                }
            }
            //Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return maze;
    }

    static void writeToFile(String outputFile) {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
            //Determine the height and width of maze_.
            int height = maze_.length;
            int width = maze_[0].length;
            //write graph dimensions
            output.println(height + " " + width);

            //Two for loops to iterate over each cell in maze.
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    //If there is a period, print out a period
//                    if (graph.nodes[i][j].value == '.') {
//                        output.print('.');
//                    } else {
//                        //Print out the original char in the maze.
//                        output.print(maze_[i][j]);
//                    }
                    output.print(graph.nodes[i][j].value);
                }
                output.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static File writeOriginalToFile(String outputFile) {
        File output = null;
        try (Scanner scanner = new Scanner(new File(outputFile));
             PrintWriter writer = new PrintWriter(new FileWriter("testOutput.txt"))) {
            //Read each line from the file.
            //Print as you go.
            while (scanner.hasNextLine()) {
                writer.write(scanner.nextLine());
                writer.write("\n");
            }
            output = new File("testOutput.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}
