package hw2;

// add your class here
import java.util.ArrayList;
import java.util.*;

import hw1.JaggedGridReader;

public class MazeSolver {
    private char[][] maze;
    private ArrayList<String> mazePath;
    private int numCellsVisited;
    private int mazesSolved;
    private int mazesTried;

    private final char OPEN = '.';
    private final char BLOCKED = '#';
    private final char START = 'S';
    private final char GOAL = 'G';
    private final char MARKED = '+';
    private final char UNMARKED = '#';

    public boolean solveMaze(char[][] maze) {

        // initialize other attributes
        int x = 0;
        int y = 0;
        this.mazePath = new ArrayList<String>();
        this.numCellsVisited = 0;
        this.maze = maze;

        // Locate Start Position
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == START) {
                    y = i;
                    x = j;
                    break;
                }
            }
        }

        // Run Findpath and check whetehr or not it solves
        mazesTried++;
        if (findPath(x, y) == true) {
            mazesSolved++;
            return true;
        }
        mazePath = null;
        return false;
    }

    public boolean findPath(int x, int y) {
        numCellsVisited++;

        // Base Case
        // 1: if outside return false
        if (x > maze[0].length - 1 || y > maze.length - 1 || x < 0 || y < 0) {
            return false;
        }
        // 2: if reaches goal return true
        if (maze[y][x] == GOAL) {
            return true;
        }
        // 3: if blocked return false
        if (maze[y][x] == BLOCKED || maze[y][x] == MARKED || maze[y][x] == UNMARKED) {
            return false;
        }
        // Mark position
        maze[y][x] = MARKED;

        // Recursive Case

        if (findPath(x, y - 1)) {
            mazePath.add(0, "North");
            return true;
        }
        if (findPath(x + 1, y)) {
            mazePath.add(0, "East");
            return true;
        }
        if (findPath(x, y + 1)) {
            mazePath.add(0, "South");
            return true;
        }
        if (findPath(x - 1, y)) {
            mazePath.add(0, "West");
            return true;
        }
        maze[y][x] = UNMARKED;
        return false;

        /*
         * // Base cases
         * if (x < 0 || x >= maze.length || y < 0 || y >= maze[0].length) {
         * return false; // Cell (x,y) is outside the maze
         * }
         * if (maze[x][y] == GOAL) {
         * mazePath.add("(" + x + "," + y + ")");
         * return true; // Cell (x,y) is the goal
         * }
         * if (maze[x][y] != OPEN) {
         * return false; // Cell (x,y) is not open
         * }
         * 
         * // Recursive case
         * maze[x][y] = MARKED; // Mark cell (x,y) as part of the solution path
         * mazePath.add("(" + x + "," + y + ")"); // Add cell (x,y) to the solution path
         * numCellsVisited++;
         * 
         * if (findPath(x, y - 1) || findPath(x + 1, y) || findPath(x, y + 1) ||
         * findPath(x - 1, y)) {
         * return true; // One of the recursive calls found the goal
         * }
         * 
         * // Backtracking
         * maze[x][y] = UNMARKED; // Unmark cell (x,y)
         * mazePath.remove(mazePath.size() - 1); // Remove cell (x,y) from the solution
         * path
         * return false;
         */
    }

    public String[] getMoves() {
        // return null if maze was not solved
        if (mazePath == null) {
            return null;
        }

        // return mazePath as an array
        // return mazePath.toArray(new String[mazePath.size()]);
        String[] result = new String[mazePath.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = mazePath.get(i);
        }
        return result;
    }

    public int getNumCellsVisited() {
        return numCellsVisited;
    }

    public double getPerformance() {
        double performance = mazesSolved / (double) mazesTried;
        return performance;
    }
}
