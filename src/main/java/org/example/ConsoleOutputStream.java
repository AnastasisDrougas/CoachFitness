package org.example;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;
import java.util.logging.SocketHandler;

public class ConsoleOutputStream {
    private ArrayList<Activity> activities;
    private ArrayList<String> args;

    public ConsoleOutputStream(ArrayList<String> args, Activities activities) {
        this.args = args;
        this.activities = activities.getActivities();
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

//        String sex = null;
//        int weight = -1;
//        int age = -1;
//        for (int i = 0; i < args.size(); i++) {
//            if(args.get(i).equals("-s"))
//                sex = args.get(i+1);
//            if(args.get(i).equals("-w"))
//                weight = Integer.parseInt(args.get(i+1));
//            if(args.get(i+1).equals("-a"))
//                age = Integer.parseInt(args.get(i+1));
//        }


        CalorieCalculator calorieCalculator = null;
        for(Activity activity : activities) {
            System.out.println("Acitvity: " + activity.getSport());
            System.out.println("----------------------------");
            System.out.println("Distance: " + activity.getTotalDistance() + " meters");
            System.out.println("Heart Rate: " + activity.getAvgHeartRate() + " bpm");
            System.out.println("TotalTime: " + activity.getTotalTime() + " mins");
            System.out.println("Average Speed " + activity.getAvgSpeed() + " km/h");
            System.out.println("Average Pace " + activity.getAvgPace() + " min/km");
            if (activity.getMaxPace() == 0 || activity.getMinPace() == 0) {
                System.out.println("Minimum & Maximum Pace cannot be calculated!");
            } else {
                System.out.println("Maximum pace reached: " + activity.getMaxPace());
                System.out.println("Minimum pace reached: " + activity.getMinPace());
            }

            System.out.println("\n");
            METvalues m = METvalues.OTHER;
            if ((weight != -1) && (age != -1) && (sex != null)) {

                calorieCalculator = new CalorieCalculator(sex, age, weight, activity.getTotalTime(), activity.getAvgHeartRate());
            } else if (weight != -1) {

                switch (activity.getSport()) {
                    case "Walking":
                        m = METvalues.WALKING;
                        break;
                    case "Running":
                        m = METvalues.RUNNING;
                        break;
                    case "Cycling":
                        m = METvalues.CYCLING;
                        break;
                    case "Pool Swimming":
                        m = METvalues.COMPETITIVESWIMMING;
                        break;
                    case "Other":
                        m = METvalues.OTHER;
                        break;
                    default:
                        System.out.println("DIDY problem");
                }
                calorieCalculator = new CalorieCalculator(m.getValue(), weight, activity.getTotalTime());
            } else {
                System.out.println("DIDY problem");
            }
            System.out.println("Calories: " + calorieCalculator.getCalories());
        }
    }
}
