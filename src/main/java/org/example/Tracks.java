package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Tracks {
    private ArrayList<Trackpoints> trackpoints = new ArrayList<>();

    public Tracks(Node node) {
        Element activityElement = (Element) node;
        ArrayListConverter<Trackpoints> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Trackpoint"),Trackpointnode -> new Trackpoints(Trackpointnode));
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
