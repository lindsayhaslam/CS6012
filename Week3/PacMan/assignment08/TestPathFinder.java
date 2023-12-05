package assignment08;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class TestPathFinder {

    private static final String tinyMazeInput = "tinyMaze.txt";
    private static final String tinyMazeOutput = "tinyMazeOutput.txt";

    private static final String writeToFile = "writeToFile.txt";



    @Test
    public void testSolveMazeWithValidInput() {
        //Ensure that solveMaze successfully creates an output file.
        PathFinder.solveMaze(tinyMazeInput, tinyMazeOutput);
        File outputFile = new File(tinyMazeOutput);
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
    }
    @Test
    public void testReadInFile() {
        char[][] maze = PathFinder.readInFile(tinyMazeInput);
        assertNotNull(maze);
        assertEquals(7, maze.length);
        assertEquals(9, maze[0].length);
        assertEquals('S', maze[5][6]);
        assertEquals('G', maze[5][1]);


        //Top-left corner.
        assertEquals('X', maze[0][0]);
        //Bottom-right corner.
        assertEquals('X', maze[6][8]);
        //Check empty spaces.
        assertEquals(' ', maze[1][1]);
        assertEquals(' ', maze[2][4]);
    }

    @Test
    public void testWriteToFile() {
        //Read in file.
        char[][] maze = PathFinder.readInFile(writeToFile);
        PathFinder.maze_ = maze;
        //Initialize the graph
        PathFinder.graph = new Graph(maze, 'S', 'G');

        //Ensure that writeToFile successfully creates an output file
        PathFinder.writeToFile(writeToFile);

        //Check the existence of the output file
        File outputFile = new File(writeToFile);
        assertTrue(outputFile.exists());

        // Check the content of the output file
        try {
            String fileContent = Files.readString(outputFile.toPath());
            // Assuming the expected content of tinyMazeOutput.txt is the same as the content of tinyMaze.txt
            assertEquals(fileContent.trim(), "5 5\nXXXXX\nXS  X\nX   X\nX  GX\nXXXXX");
        } catch (IOException e) {
            fail("Failed to read content from " + writeToFile);
        }
    }

    @Test
    public void testWriteOriginalToFile() {
        //Assuming tinyMazeInput content is provided.
        String tinyMazeInput = "7 9\nXXXXXXXXX\nX       X\nXXXX XX X\nX       X\nX XX XX X\nXGX   S X\nXXXXXXXXX";
        File inputFile = new File("testWriteOriginalToFileInput.txt");

        //Write the content to the sample input file for testing.
        try (PrintWriter inputWriter = new PrintWriter(new FileWriter(inputFile))) {
            inputWriter.write(tinyMazeInput);
        } catch (IOException e) {
            fail("Failed to create sample input file.");
        }

        assertTrue(inputFile.exists());
        assertTrue(inputFile.length() > 0);

        //Call writeOriginalToFile method.
        PathFinder.maze_ = PathFinder.readInFile(inputFile.getPath());
        PathFinder.graph = new Graph(PathFinder.maze_, 'S', 'G');

        File outputFile = PathFinder.writeOriginalToFile("testWriteOriginalToFileInput.txt");

        //Check the existence and length of the output file
        assertTrue(outputFile.exists());
        assertTrue(outputFile.length() > 0);
    }

    public static void main(String[] args) {

        /*
         * The below code assumes you have a file "tinyMaze.txt" in your project folder.
         * If PathFinder.solveMaze is implemented, it will create a file "tinyMazeOutput.txt" in your project folder.
         *
         * REMEMBER - You have to refresh your project to see the output file in your package explorer.
         * You are still required to make JUnit tests...just lookin' at text files ain't gonna fly.
         */
        PathFinder.solveMaze("tinyMaze.txt", "tinyMaze.txt");

    }
}