package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

public class Activity {

    private ArrayList<Laps> laps;

    private double totalDistance;
    private double avgHeartRate;
    private double totalTime;
    private double avgSpeed; // km/h.
    private double avgPace; // min/km.
    private double maxPace;
    private double minPace;
    private String sport;

    /**
     * Constructor: Converts a NodeList of Laps nodes
     * into an ArrayList of Lap objects.
     *
     * The anonymous function tells the converter
     * how to transform each node into a lap
     * using the Laps constructor.
     */
    public Activity(Node node) {
        Element activityElement = (Element) node;
        sport = activityElement.getAttribute("Sport");
        ArrayListConverter<Laps> converter =
            new ArrayListConverter<>(
                activityElement.getElementsByTagName("Lap"),
                new Function<Node, Laps>() {    //anonymous function
                    @Override
                    public Laps apply(Node node) {
                        return new Laps(node);
                    }
                });
        laps = converter.getList();
        //Initialising all the calculators
        initiator();
    }

    public Activity(String sport, double dist, double time,double speed, int hr) {
        this.sport = sport;
        this.totalDistance = dist;
        this.totalTime = time;
        this.avgHeartRate = hr;
        this.avgSpeed = speed;
    }

    private void initiator(){
        ArrayList<Double> results;
        /**
        * All calculators are grouped together using the Composite pattern
        *  this is using with two parallel ArrayLists.
        */
        CompositeCalculator composite = new CompositeCalculator();

        composite.addCalculator(new AvgHeartRateBpmCalculator());
        composite.addCalculator(new TotalDistanceCalculator());
        composite.addCalculator(new TotalTimeCalculator());
        composite.addCalculator(new MaxPaceCalculator());
        composite.addCalculator(new MinPaceCalculator());

        results = composite.calculate(this);

        avgHeartRate = (double) results.get(0);
        totalDistance = (double) results.get(1);
        totalTime = (double) results.get(2) / 60.0;

        avgSpeed = (totalDistance / 1000.0) / (totalTime / 60.0); // km/h.

        if(totalDistance != 0) {
            avgPace = totalTime / (totalDistance / 1000.0); // min/km.
        } else { avgPace = 0.0; }

        maxPace = (double) results.get(3);
        minPace = (double) results.get(4);

    }

    public double getAvgHeartRate() {
        return avgHeartRate;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public String getSport() {
        return sport;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public double getAvgPace() {
        return avgPace;
    }

    public double getMaxPace() {
        return maxPace;
    }

    public double getMinPace() {return minPace; }

    public ArrayList<Laps> getLaps() {
        ArrayList<Laps> list = new ArrayList<Laps>();
        for(Laps e : laps){
            list.add(e);
        }
        return list;
    }
}
