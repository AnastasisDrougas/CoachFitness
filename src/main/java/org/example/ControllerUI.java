package org.example;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerUI {
    private ViewUI view;
    private ArrayList<Activity> activities = new ArrayList<>();
    private double calories = 0;

    public ControllerUI(ViewUI view){
        this.view = view;
        attachListeners();
    }

    private void attachListeners(){
        //Listener for loading .tcx files.
        view.getLoadBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setMultiSelectionEnabled(true);

                //filter only xml files
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TCX Files", "tcx");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);

                int retVal = chooser.showOpenDialog(view);
                if(retVal == JFileChooser.APPROVE_OPTION){
                    processXMLFiles(chooser.getSelectedFiles());
                } else {
                    return;
                }

                updateStats();
                view.getTableModel().setRowCount(0);

                for(Activity a : activities) {
                    Object[] row = {a.getSport(), a.getTotalDistance(), a.getTotalTime(), a.getAvgHeartRate(),calories};
                    view.getTableModel().addRow(row);
                }
            }
        });

        view.getNext1().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO check
                try {
                    double weight = Double.parseDouble(view.getWeightField().getText());
                    double goal = Double.parseDouble(view.getGoalField().getText());
                    int age = Integer.parseInt(view.getAgeField().getText());

                    if (!view.getMale().isSelected() && !view.getFemale().isSelected()) {
                        throw new IllegalArgumentException("You must select one option.");
                    }
                    view.showFormula();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for all fields.");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        view.getNext2().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!view.getHrButton().isSelected() && !view.getMetButton().isSelected()) {
                        throw new IllegalArgumentException("You must select one option.");
                    }
                    view.showResults();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        //TODO -> Daily goal.
    }

    private void processXMLFiles(File[] fileList){
        XMLFileReader xml = new XMLFileReader();
        for (File f : fileList) {
            Activities activityList = xml.fileReader(f.getAbsolutePath());
            activities.addAll(activityList.getActivities());
        }
        //TODO -> view.updateTable(activities);
    }

    private void updateStats(){

        double weight = Double.parseDouble(view.getWeightField().getText());
        double goal = Double.parseDouble(view.getGoalField().getText());

        double totalCalBurned = 0;

        //For each Activity:
        for(Activity a : activities){
            CalorieCalculator calc;
            //Choose Calorie calculating formula.
            if(view.isHeartRateMethodSelected()){
                int age = Integer.parseInt(view.getAgeField().getText());
                String sex = view.getSexField();
                calc = new CalorieCalculator(sex, age, weight, a.getTotalTime(), a.getAvgHeartRate());
            }else{
                METValuesHashMap METValues = new METValuesHashMap();
                double m = METValues.get(a.getSport());
                calc = new CalorieCalculator(m, weight, a.getTotalTime());
            }
            calories = calc.getCalories();
            totalCalBurned += calc.getCalories();
        }
        //TODO -> view.setGoalResult(totalBurned, goal);
    }
}
