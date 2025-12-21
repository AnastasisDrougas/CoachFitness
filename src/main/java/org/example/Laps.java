package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

/**
 * @author Anastasis Drougas
 * @author Anjelo Hoxhaj
 */

public class Laps {
    private ArrayList<Tracks> tracks ;
    private double distanceMeters;
    private double timeInSeconds;
    /**
     * Constructor: Converts a NodeList of tracks nodes
     * into an ArrayList of track objects.
     *
     * The lambda expression tells the converter
     * how to transform each node into a track
     * using the Tracks constructor.
     */
    public Laps(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Tracks> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Track"),Tracknode -> new Tracks(Tracknode));
        tracks = converter.getList();
        //Extracting data from the TCX File that are into <Laps>
        initiator(activityElement);
    }

    private void initiator(Element element){
        NodeList distList = element.getElementsByTagName("DistanceMeters");
        String distStr = distList.item(0).getTextContent();
        if((distStr != null && !distStr.isEmpty())){
            distanceMeters = Double.parseDouble(distStr);
        }else{ distanceMeters = 0; }

        NodeList TimeList = element.getElementsByTagName("TotalTimeSeconds");
        String TimeStr = TimeList.item(0).getTextContent();
        if((TimeStr != null && !TimeStr.isEmpty())){
            timeInSeconds= Double.parseDouble(TimeStr);
        }else{ timeInSeconds = 0; }

    }

    public ArrayList<Tracks> getTracks() {
        ArrayList<Tracks> list = new ArrayList<Tracks>();
        for(Tracks e : tracks){
            list.add(e);
        }
        return list;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public double getTimeInSeconds() {
        return timeInSeconds;
    }
}
