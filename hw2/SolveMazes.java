package hw2;

// add SolveMazes program here
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import hw1.JaggedGridReader.java;

public class SolveMazes {
    public static void main(String[] args) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a file name (or 'stop' to exit): ");
            String fileName = input.nextLine();

            // use JaggedGridReader to read the file
            JaggedGridReader mazeBoard = new JaggedGridReader(fileName);
            // use the MazeSolver to solve the maze
            mazeBoard.solveMaze();

            if (fileName == "stop") {
                // finishes processing the mazes then
                // print the performance of the maze solver as a percentage with no decimal
                // places
                // and then the program should end
                // Ex: Percent Correct: 100%
                System.out.println("Percent Correct: " + mazeBoard.getPerformance() + "%");
                break;
            }

            try {
                // use JaggedGridReader to read the file
                // JaggedGridReader mazeBoard = new JaggedGridReader(filename);

                // If maze was solved then print solution
                // and # of cells visited.
                // else print "No Solution" if maze was not solved
                if (mazeBoard.solveMaze() == true) {
                    // print the solution to the maze
                    System.out.println(mazeBoard.getMoves());
                    System.out.println("-----------------------");
                    System.out.println("Number of cells visited:" + mazeBoard.getNumCellsVisited());
                } else {
                    System.out.println("No Solution");
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileName);
            }
        }

    }// end main
}// end class
