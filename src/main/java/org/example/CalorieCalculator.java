package org.example;

public class CalorieCalculator {
    private double calories;

    public CalorieCalculator(double m, double weight, double time){
        calories = m * weight * (time/60.0);
    }

    public CalorieCalculator(String  sex, int age, double weight, double time, double hbpm){

        if(sex.equals("f")){
            calories = ((-20.4022 + (0.4472 * hbpm) + (0.1263 * weight) + (0.074 * age) )* time)/4.184;
        } else if (sex.equals("m")) {
            calories = ((-55.0969 + (0.6309 * hbpm) + (0.1966 * weight) + (0.2017 * age) )* time)/4.184;
        } else {
            throw new IllegalArgumentException("Invalid input for sex");
        }
    }

    public double getCalories() {
        return calories;
    }
}
