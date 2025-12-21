package org.example;

/**
 * @author Anastasis Drougas
 * @author Anjelo Hoxhaj
 */

/**
 * This program extracts data from a TCX file.
 * It uses multiple ArrayLists that are managed hierarchically.
 * Calculates and prints the user's statistics
 * Calculates and prints the calories burned using the extracted data and personal user info
 */

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No arguments given!");
        } else {
            ArgumentReader arg = new ArgumentReader();
            arg.separator(args);
            arg.fileNamesList();
            //Print user's stats on the Console
            ConsoleOutputStream printer = new ConsoleOutputStream(arg.getSpecificsList(), arg.getActivities());
        }
    }
}
