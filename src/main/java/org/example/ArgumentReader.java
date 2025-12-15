package org.example;

import java.util.ArrayList;

public class ArgumentReader {
    private ArrayList<String> fileList = new ArrayList<>();
    private ArrayList<String> specificsList = new ArrayList<>();
    private ArrayList<Activity> activities = new ArrayList<>();

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
        for(String value : fileList){
            Activities parsed = xml.fileReader(value);
            activities.addAll(parsed.getActivities());
        }
    }


    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public ArrayList<String> getSpecificsList() {
        return specificsList;
    }
}
