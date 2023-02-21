package hw1;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class JaggedGridReader {
    // Attributes store 2D grid and fileName
    private char[][] grid;
    private String fileName;

    //
    // Constructor
    public JaggedGridReader(String fileName) {// store the filename, read the specified file and convert it
        // to a two dimensional array of characters and store it in the grid field
        this.fileName = fileName;
        try {
            // Scanner object to read file
            Scanner fileScanner = new Scanner(new File(fileName));
            int numRows = 0;
            // loop through each line to get number of rows
            while (fileScanner.hasNextLine()) {
                numRows++;
                fileScanner.nextLine();
            }
            fileScanner.close(); // close scanner
            fileScanner = new Scanner(new File(fileName));
            // initialize the grid with the number of rows just found
            grid = new char[numRows][];
            for (int i = 0; i < numRows; i++) {
                String temp = fileScanner.nextLine();
                grid[i] = temp.toCharArray(); // convert each line to character array and store in grid
            }
            fileScanner.close(); // close scanner
        } catch (FileNotFoundException e) {
            grid = null; // set grid to null if file not found
        }

    }// end constructor

    public String toString() {// return string that is identical to contents of file
        // String gridString = grid.length + " " + grid[0].length + "\n";// Add size of
        // grid to string to check correct dimensions
        String gridString = "";
        for (int i = 0; i < grid.length; i++) // Add the grid to string
        {
            for (int j = 0; j < grid[i].length; j++) {
                gridString += grid[i][j] + "";
            }
            gridString += "\n";
        }
        return gridString;
    }

    public char[][] getCopy() {// return a deep copy of the grid or null if the file specified was not found
        if (grid == null) {
            return null; // return null if grid is null
        }
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
    /*
     * if (fileName == null) {
     * return null;
     * } else {
     * char[][] copy = new char[grid.length][]; // only need to initialize # of rows
     * // char[][] copy = new char[grid.length][grid[0].length]; // Initialize new
     * 2D
     * // array to store copy
     * 
     * // copy the grid
     * for (int i = 0; i < grid.length; i++) {
     * copy[i] = grid[i].clone();
     * /*
     * for (int j = 0; j < grid[i].length; j++) {
     * copy[i][j] = grid[i][j];
     * }
     */

    // }
    // }

    public String getFileName() {// return the file name specified on construction
        return fileName;
    }

}// end class
