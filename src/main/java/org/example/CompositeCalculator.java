package org.example;

import java.util.ArrayList;

public class CompositeCalculator {
    private ArrayList<Calculator<Double>> calculatorArrayList = new ArrayList<>();

    public void addCalculator(Calculator<Double> calculator) {
        calculatorArrayList.add(calculator);
    }

    public ArrayList<Double> calculate(Activity activity) {
        ArrayList<Double> results = new ArrayList<Double>();
        for(int i = 0; i < calculatorArrayList.size(); i++){
            results.add(calculatorArrayList.get(i).calculate(activity));
        }
        return results;
    }

}
