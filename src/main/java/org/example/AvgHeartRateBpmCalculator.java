package org.example;

/**
 * @author Anastasis Drougas
 * @author Anjelo Hoxhaj
 */

/**
* An implementation of the calculator interface for Average BPM.
*/
public class AvgHeartRateBpmCalculator implements Calculator<Double> {

    @Override
    public Double calculate(Activity activity) {
        double totalHr = 0;
        int count = 0;
        for(Laps lap : activity.getLaps()){
            for(Tracks tracks : lap.getTracks()){
                for(Trackpoints trackpoints : tracks.getTrackpoints()){
                    count++;
                    totalHr += trackpoints.getHeartRate();
                }
              }

        }
        if (count == 0) return 0.0;
        return totalHr/count;
    }
}
