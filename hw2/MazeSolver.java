package hw2;

// add your class here
import java.util.ArrayList;
import java.util.*;

public class MazeSolver {
    private final char OPEN;
    private final char BLOCKED;
    private final char START;
    private final char GOAL;
    private final char MARKED;
    private final char UNMARKED;
    private char[][] maze;
    private ArrayList<String> mazePath;
    private int numCellsVisited;
    private int mazesSolved;
    private int mazesTried;

    public MazeSolver(char[][] maze) {
        // intialize maze chars
        OPEN = '.';
        BLOCKED = '#';
        START = 'S';
        GOAL = 'G';
        MARKED = '+';
        UNMARKED = 'x';

        // initialize other attributes
        this.maze = maze;
        this.mazePath = new ArrayList<>();
        this.numCellsVisited = 0;
        this.mazesSolved = 0;
        this.mazesTried = 0;
    }

    public boolean solveMaze(char[][] maze) {
        findPath(1, 1);
        if (mazePath != null) {
            mazesSolved++;
            mazesTried++;
            return true;
        } else {
            mazesTried++;
            return false;
        }
    }

    private boolean findPath(int x, int y) {
        // Base cases
        if (y < 0 || y >= maze.length || x < 0 || x >= maze[0].length) {
            return false; // Cell (x,y) is outside the maze
        }
        if (maze[y][x] == GOAL) {
            mazePath.add("(" + x + "," + y + ")");
            return true; // Cell (x,y) is the goal
        }
        if (maze[y][x] != OPEN) {
            return false; // Cell (x,y) is not open
        }

        // Recursive case
        maze[y][x] = MARKED; // Mark cell (x,y) as part of the solution path
        mazePath.add("(" + x + "," + y + ")"); // Add cell (x,y) to the solution path
        numCellsVisited++;

        if (findPath(x, y - 1) || findPath(x + 1, y) || findPath(x, y + 1) || findPath(x - 1, y)) {
            return true; // One of the recursive calls found the goal
        }

        // Backtracking
        maze[y][x] = UNMARKED; // Unmark cell (x,y)
        mazePath.remove(mazePath.size() - 1); // Remove cell (x,y) from the solution path
        return false;
    }

    public String[] getMoves() {
        // return null if maze was not solved
        if (mazePath == null) {
            return null;
        }

        // return mazePath as an array
        return mazePath.toArray(new String[mazePath.size()]);
    }

    public int getNumCellsVisited() {
        return numCellsVisited;
    }

    public double getPerformance() {
        double performance = mazesSolved / mazesTried;
        return performance;
    }
}
