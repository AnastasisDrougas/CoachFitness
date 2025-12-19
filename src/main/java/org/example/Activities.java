package org.example;

import org.w3c.dom.NodeList;
import java.util.ArrayList;

public class Activities {
    private ArrayList<Activity> activities = new ArrayList<>();

    public Activities(NodeList nodes) {
        ArrayListConverter<Activity> converter = new ArrayListConverter<>(nodes, node -> new Activity(node));
        activities = converter.getList();
    }

    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> list = new ArrayList<Activity>();
        for(Activity e : activities){
            list.add(e);
        }
        return list;
    }
}




