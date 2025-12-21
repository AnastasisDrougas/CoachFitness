package org.example;

import java.util.ArrayList;

/**
 * @author Anastasis Drougas
 * @author Anjelo Hoxhaj
 */

/**
 * A composite calculator that holds multiple Calculator<Double> objects.
 * It can run all calculators on a given Activity and return their results,
 * using two parallel ArrayLists.
 */
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
