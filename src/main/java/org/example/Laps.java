package org.example;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class Laps {
    ArrayList<Tracks> tracks = new ArrayList<>();

    public Laps(Node node) {

        ArrayListConverter<Laps> converter = new ArrayListConverter<>(activityElement.getElementsByTagName("Tracks"),Lapnode -> new Laps(Lapnode));
        tracks = converter.getList();
    }

}
