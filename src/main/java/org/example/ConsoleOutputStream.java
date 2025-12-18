package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.logging.SocketHandler;

public class ConsoleOutputStream {
    private ArrayList<Activity> activities;
    private ArrayList<String> args;
    METValuesHashMap METValues = new METValuesHashMap(); //HashMap of the MET values

    public ConsoleOutputStream(ArrayList<String> args, ArrayList<Activity> activities) {
        this.args = args;
        this.activities = activities;
        ConsolePrinter();
    }

    private void ConsolePrinter(){
        String sex = null;
        double weight = -1;
        int age = -1;
        for (int i = 0; i < args.size(); i++) {
            switch (args.get(i)) {
                case "-s":
                    sex = args.get(i + 1);
                    i++; // skip value
                    break;
                case "-w":
                    weight = Double.parseDouble(args.get(i + 1));
                    i++; // skip value
                    break;
                case "-a":
                    age = Integer.parseInt(args.get(i + 1));
                    i++; // skip value
                    break;
                default:
                    // everything else is a file
            }
        }
        CalorieCalculator calorieCalculator = null;
        double cal = 0;
        for (Activity activity : activities) {
            System.out.println("     Activity: " + activity.getSport());
            System.out.println("----------------------------");
            System.out.println("Distance: " + String.format("%.2f", activity.getTotalDistance()) + " meters");
            System.out.println("Heart Rate: " + String.format("%.2f", activity.getAvgHeartRate()) + " bpm");
            System.out.println("TotalTime: " + String.format("%.2f", activity.getTotalTime()) + " mins");
            System.out.println("Average Speed: " + String.format("%.2f", activity.getAvgSpeed()) + " km/h");
            System.out.println("Average Pace: " + String.format("%.2f", activity.getAvgPace()) + " min/km");

            if (activity.getMaxPace() == 0 || activity.getMinPace() == 0) {
                System.out.println("Minimum & Maximum Pace cannot be calculated!");
            } else {
                System.out.println("Maximum pace reached: " + String.format("%.2f", activity.getMaxPace()));
                System.out.println("Minimum pace reached: " + String.format("%.2f", activity.getMinPace()));
            }
            System.out.println("----------------------------");

            //METvalues m = METvalues.OTHER;
            if ((weight != -1) && (age != -1) && (sex != null)) {
                calorieCalculator = new CalorieCalculator(sex, age, weight, activity.getTotalTime(), activity.getAvgHeartRate());
                cal = calorieCalculator.getCalories();
                System.out.println(String.format("Calories burned:" + "%.2f", cal) + " kcal\n\n");
            } else if (weight != -1) {
//                switch (activity.getSport()) {
//                    case "Walking":
//                        m = METvalues.WALKING;
//                        break;
//                    case "Running":
//                        m = METvalues.RUNNING;
//                        break;
//                    case "Cycling":
//                        m = METvalues.CYCLING;
//                        break;
//                    case "Pool Swimming":
//                        m = METvalues.COMPETITIVESWIMMING;
//                        break;
//                    case "Other":
//                        m = METvalues.OTHER;
//                        break;
//                    default:
//                        System.out.println("DIDY problem");
//                }
                double m = METValues.get(activity.getSport());
                calorieCalculator = new CalorieCalculator(m, weight, activity.getTotalTime());
                cal = calorieCalculator.getCalories();
                System.out.println(String.format("Calories burned:" + "%.2f", cal) + " kcal\n\n");
            } else {
                System.out.println("Cannot calculate calories! No personal information given!\n\n");
            }

        }
    }
}
