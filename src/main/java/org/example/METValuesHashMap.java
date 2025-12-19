package org.example;

import java.util.HashMap;
import java.util.Map;

public class METValuesHashMap {

    private final Map<String, Double> metValues;

    public METValuesHashMap(){
        Map<String, Double> temp = new HashMap<>();

        //Sports that we extracted from the XML File.
        temp.put("Running",10.0);
        temp.put("Walking",3.5);
        temp.put("Pool Swimming",11.0);
        temp.put("Cycling",6.5);

        this.metValues = Map.copyOf(temp);
    }

    public double get(String key){
        /*
        *Everything Sport that is not
        * initialized ("Other") gets the default
        * MET value -> 3.0
        */
        return metValues.getOrDefault(key, 3.0);
    }

}
