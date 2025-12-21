package org.example;

import java.util.ArrayList;

/**
 * @author Anastasis Drougas
 * @author Angjelo Hoxhaj
 */

public class ArgumentReader {
    private ArrayList<String> fileList = new ArrayList<>();
    private ArrayList<String> specificsList = new ArrayList<>();
    private ArrayList<Activity> activities = new ArrayList<>();

    /**
    * Seperating the values of the user's args.
    * one ArrayList for specifics (weight, age, sex).
    * and one ArrayList for the TCX files.
    */
    public void separator(String[] args){
        for(int i=0; i< args.length; i++){
            if(args[i].equals("-w") || args[i].equals("-s") || args[i].equals("-a")){
                specificsList.add(args[i]);
                specificsList.add(args[i+1]);
                i++;
            } else {
                fileList.add(args[i]);
            }
        }
    }

    public void fileNamesList(){
        XMLFileReader xml = new XMLFileReader();
        if (!fileList.isEmpty()) {
            for (String value : fileList) {
                Activities parsed = xml.fileReader(value);
                activities.addAll(parsed.getActivities());
            }
        } else {
            System.out.println("No file given!");
        }
    }


    public ArrayList<Activity> getActivities() {
        ArrayList<Activity> list = new ArrayList<Activity>();
        for(Activity e : activities){
            list.add(e);
        }
        return list;
    }

    public ArrayList<String> getSpecificsList() {
        ArrayList<String> list = new ArrayList<String>();
        for(String e : specificsList){
            list.add(e);
        }
        return list;
    }
}
