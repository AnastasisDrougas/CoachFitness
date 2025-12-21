package org.example;

/**
 * @author Anastasis Drougas
 * @author Anjelo Hoxhaj
 */

/**
 * An implementation of the calculator interface for Total Distance.
 */
public class TotalDistanceCalculator implements Calculator<Double>{

    @Override
    public Double calculate(Activity activity) {
        double totalDistance = 0;
        for(Laps lap : activity.getLaps()){

            totalDistance += lap.getDistanceMeters();
        }
        return totalDistance;
    }
}
