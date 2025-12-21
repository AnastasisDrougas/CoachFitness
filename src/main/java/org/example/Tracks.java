package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

public class Tracks {
    private ArrayList<Trackpoints> trackpoints = new ArrayList<>();

    /**
     * Constructor: Converts a NodeList of trackpoint nodes
     * into an ArrayList of trackpoint objects.
     *
     * The anonymous function tells the converter
     * how to transform each node into a Trackpoint
     * using the Trackpoint constructor.
     */
    public Tracks(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Trackpoints> converter =
            new ArrayListConverter<>(
                activityElement.getElementsByTagName("Trackpoint"),
                new Function<Node, Trackpoints>() {    //anonymous function
                    @Override
                    public Trackpoints apply(Node node) {
                        return new Trackpoints(node);
                    }
                });
        trackpoints = converter.getList();
    }

    public ArrayList<Trackpoints> getTrackpoints() {
        ArrayList<Trackpoints> list = new ArrayList<Trackpoints>();
        for(Trackpoints e : trackpoints){
            list.add(e);
        }
        return list;
    }
}
