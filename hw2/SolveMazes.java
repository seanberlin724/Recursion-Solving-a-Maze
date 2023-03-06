package hw2;

// add SolveMazes program here
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;

import hw1.JaggedGridReader;

public class SolveMazes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MazeSolver solver = new MazeSolver();

        while (true) {

            System.out.print("Enter a file name (or 'stop' to exit): ");
            String fileName = input.nextLine();

            // use JaggedGridReader to read the file
            // JaggedGridReader mazeBoard = new JaggedGridReader(fileName);

            if (fileName.toLowerCase().equals("stop")) {
                break;
            }
            try {
                // use JaggedGridReader to read the file
                JaggedGridReader mazeBoard = new JaggedGridReader(fileName);

                // get copy of jaggedGrid object and store as char [][] tempMaze
                char[][] tempMaze = mazeBoard.getCopy();// return char[][]

                solver.solveMaze(tempMaze);
                System.out.println();
                String[] solution = solver.getMoves();
                System.out.print("Solution: ");

                if (solution == null) {
                    System.out.println("No solution");
                } else {
                    System.out.println(solution[0]);
                    for (int i = 1; i < solution.length; i++) {
                        System.out.print(", " + solution[i]);
                    }
                }

                System.out.println("\nCells Visited: " + solver.getNumCellsVisited());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // finishes processing the mazes then print the performance of the maze solver
        // as a percentage with no decimal
        // places and then the program should end. Ex: Percent Correct: 100%
        System.out.println("Percent Correct: " + (int) (solver.getPerformance() * 100) + "%");

    }// end main
}// end class
