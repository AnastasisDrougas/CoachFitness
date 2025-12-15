package org.example;

public class MaxPaceCalculator implements Calculator<Double> {
    @Override
    public Double calculate(Activity activity) {
        double max = Double.MIN_VALUE; //unreachable value
        for (Laps lap : activity.getLaps()) {
            for (Tracks tracks : lap.getTracks()) {
                for (Trackpoints trackpoints : tracks.getTrackpoints()) {
                    double speed = trackpoints.getSpeed();
                    if (speed > 0.5) {
                        double pace = 1000 / (speed * 60);
                        if (pace > max) {
                            max = pace;
                        }
                    }
                }
            }
        }
        return max == Double.MIN_VALUE ? 0 : max;
    }
}