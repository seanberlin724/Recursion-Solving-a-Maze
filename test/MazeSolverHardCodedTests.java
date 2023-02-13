/**
 * File: MazeSolverHardCodedTests
 * Purpose: test the MazeSolver class
 * Date: Feb 4, 2022, Revised Feb 9, 2023
 * Version: 4 - moved to test package. 
 */
package test;
import java.util.Scanner;
import java.io.File;
import test.Mazes;
import hw2.MazeSolver;

public class MazeSolverHardCodedTests {
    public static void main(String[] args) {
        
        final String N = "North";
        final String S = "South";
        final String E = "East";
        final String W = "West";
        boolean allTests = false;
        int numPass = 0;
        // the test to perform
        int index = -1;

        // get the test from the command line
        if(args.length > 0) {
            try {
                index = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        Mazes m = new  Mazes();
        char[][][] testMazes = {m.oneStepE,
            m.oneStepN,m.oneStepS,m.oneStepW,
            m.twoStepEN,m.twoStepSE,m.twoStepSW,m.twoStepWN,
            m.threeStepsFromCenter,m.sevenSteps,m.sevenStepsBack,
            m.noSolution1,m.noSolution2,m.noSolution5,
            m.noSolution10};
        
        String[][] testSolutions = {{E},{N},{S},{W},
             {E,N},{S,E},{S,W},{W,N},{E,N,E},
             {E,S,S,E,E,N,E},{N,N,W,W,N,N,W},
             null,null,null,null};
        // to keep track of which test to run
        boolean[] test = new boolean[testMazes.length];

        // either run 1 test or all tests
        if(index >= 0 && index < test.length) {
            test[index] = true;
            System.out.println("Runing test: " + index);
        } else {
            System.out.println("Running all tests!");
            for (int i = 0; i < test.length; ++i) {
                test[i] = true;
            }
            allTests = true;
        }

        // in case GridReader or any of the File operations
        // throw an exception
        try {
            
            // make a MazeSolver to be tested
            MazeSolver solver = new MazeSolver();
            for(int i = 0; i < test.length; ++i) {
            // test getFileName()
            if(test[i]) {
                boolean solved = 
                    solver.solveMaze(testMazes[i]);
                if(!solved) {
                    if(testSolutions[i] != null) {
                      System.out.println("Solver found no solution");
                      System.out.println("Correct solution is:");
                      for(String y : testSolutions[i]) {
                          System.out.print(y + " ");
                      }
                      System.out.println();
                      System.out.println("Test " + i + ": FAIL");
                    } else {
                      System.out.println("Test " + i + ": PASS");
                    }
                } else {
                    numPass++;
                    String[] x = solver.getMoves();
                    if (x == null) {
                        System.out.println("Maze solved with NO MOVES");
                        System.out.println("Test " + i + ": FAIL");
                    } else if(testSolutions[i] != null) {                    
                        if(x.length != testSolutions[i].length) {
                            System.out.print("Solver solution is: ");
                            for(String y : x) {
                                System.out.print(y + " ");
                            }
                            System.out.println();
                            System.out.println("Correct solution is:");
                            for(String y : testSolutions[i]) {
                                System.out.print(y + " ");
                            }
                            System.out.println();
                            System.out.println("Test " + i + ": FAIL");
                        } else {
                            boolean solCorrect = true;
                            for(int k = 0; k < x.length; ++k) {
                                if(testSolutions[i][k] != x[k]) {
                                    solCorrect = false;
                                }
                            }
                            if(solCorrect) {
                                System.out.println("Test " + i + ": PASS");
                            } else {
                                System.out.print("Solver solution is: ");
                                for(String y : x) {
                                    System.out.print(y + " ");
                                }
                                System.out.println();
                                System.out.println("Correct solution is:");
                                for(String y : testSolutions[i]) {
                                    System.out.print(y + " ");
                                }
                                System.out.println();
                                System.out.println("Test " + i + ": FAIL");
                            }
                        }       
                    } else {
                        System.out.println("Maze solved for a maze with NO SOLUTION");
                        System.out.print("Solver solution is: ");
                        for(String y : x) {
                            System.out.print(y + " ");
                        }
                        System.out.println();
                        System.out.println("Correct solution is: no solution");
                        System.out.println("Test " + i + ": FAIL");
                    }
                }   
                
            }
        }
        double performance = solver.getPerformance();
        if (Math.abs(performance - (numPass/15.0)) < 0.000001) {
            System.out.println("Computes Performance Test: PASS");
        } else {
            System.out.println("Computes Performance Test: FAIL");
        }
       
        } catch(Exception e) {
            System.out.println("Exception thrown");
            System.out.println("Test: FAIL");
            e.printStackTrace();
        }
    }
}
