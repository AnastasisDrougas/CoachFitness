package org.example;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class ViewUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainContainer;

    //Fields.
    public JButton loadBtn = new JButton("Load your TCX files");
    private JRadioButton hrButton = new JRadioButton("Advanced Method");
    private JRadioButton metButton = new JRadioButton("Classic Method");
    private ButtonGroup group;

    JButton next1 = new JButton("Next");
    JButton next2 = new JButton("Next");
    JButton addActivity = new JButton("Add Activity");

    public  JTextField weightField = new JTextField(5);
    public  JTextField ageField = new JTextField(5);
    public  JTextField goalField = new JTextField(5);

    private JRadioButton male = new JRadioButton("Male");
    private JRadioButton female = new JRadioButton("Female");
    private ButtonGroup group2;

    Font labelFont = new Font("SansSerif", Font.BOLD, 20);
    Font desciptionFont = new Font("SansSerif", Font.PLAIN, 15);
    Font fieldFont = new Font("SansSerif", Font.PLAIN, 20);

    private JTable Table;
    private DefaultTableModel tableModel;

    public ViewUI(){
        setTitle("Fitness Buddy ULTRA ProMax Edition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setMinimumSize(new Dimension(600, 500));

        //main container that swaps views
        mainContainer = new JPanel(cardLayout);


        //CARD 1: User's profile info card
        JPanel InputCard = new JPanel(new GridLayout(0, 2, 10, 10));

        addInputRow(InputCard, "Weight:", weightField, 5, labelFont, fieldFont);
        addInputRow(InputCard, "Age:", ageField, 5, labelFont, fieldFont);

        JLabel sexLabel = new JLabel("Sex:");
        sexLabel.setFont(labelFont);
        sexLabel.setBorder(new EmptyBorder(0, 40, 60, 0));
        InputCard.add(sexLabel);
        group2 = new ButtonGroup();
        addRadioButton(InputCard, male, female, group2, labelFont);

        addInputRow(InputCard, "Set Daily Goal:", goalField, 5, labelFont, fieldFont);

        JPanel inputWrapper = new JPanel(new BorderLayout());
        inputWrapper.add(InputCard, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(next1);
        inputWrapper.add(bottomPanel, BorderLayout.SOUTH);


        //CARD 2: formula panel
        JPanel formulaCard = new JPanel();
        group = new ButtonGroup();
        addRadioButton(formulaCard, hrButton, metButton, group, labelFont);
        JLabel infoLabel = new JLabel("<html><body style='width: 400px; padding: 20px;'>" +
                "<b>Description:</b><br><br>" +
                "1. <b>Classic Method:</b> Standard MET values.<br>" +
                "2. <b>Advanced Method:</b> Personalised (Weight, Age, Sex). " +
                "</body></html>");
        infoLabel.setFont(desciptionFont);
        infoLabel.setBorder(new EmptyBorder(0, 40, 60, 0));
        formulaCard.add(infoLabel);

        JPanel inputWrapper2 = new JPanel(new BorderLayout());
        inputWrapper2.add(formulaCard, BorderLayout.CENTER);

        JPanel bottomPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel2.add(next2);
        inputWrapper2.add(bottomPanel2, BorderLayout.SOUTH);


        //CARD 3: results
        JPanel resultsPanel = new JPanel(new BorderLayout());
        initTable();
        resultsPanel.add(new JScrollPane(Table), BorderLayout.CENTER);
        resultsPanel.add(loadBtn, BorderLayout.SOUTH);

        JPanel inputWrapper3 = new JPanel(new BorderLayout());
        inputWrapper3.add(resultsPanel, BorderLayout.CENTER);

        JPanel bottomPanel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel3.add(addActivity);
        inputWrapper3.add(bottomPanel3, BorderLayout.SOUTH);


        mainContainer.add(inputWrapper, "PROFILE");
        mainContainer.add(inputWrapper2, "FORMULA");
        mainContainer.add(inputWrapper3, "RESULTS");
        add(mainContainer);

        showProfile();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean isHeartRateMethodSelected(){
        return hrButton.isSelected();
    }

    public void initTable() {
        String[] columns = {"Sport", "Distance (m)", "Time (min)", "Avg HR (bpm)","Avg Speed (km/h)", "Calories"};
        tableModel = new DefaultTableModel(columns, 0);
        Table = new JTable(tableModel);
        add(new JScrollPane(Table), BorderLayout.CENTER);
    }

    private void addInputRow(JPanel panel, String labelText, JTextField field, int columns, Font labelFont, Font fieldFont) {
        JLabel label = new JLabel(labelText);
        label.setFont(labelFont);

        field.setFont(fieldFont);
        field.setColumns(columns);

        panel.add(label);

        label.setBorder(new EmptyBorder(0, 40, 60, 0));

        JPanel fieldWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldWrapper.add(field);

        panel.add(fieldWrapper);
    }

    private void addRadioButton(JPanel panel, JRadioButton btn1, JRadioButton btn2, ButtonGroup group, Font font){
        btn1.setFont(font);
        btn2.setFont(font);

        group.add(btn1);
        group.add(btn2);

        JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
        wrapper.setBorder(new EmptyBorder(0, 0, 0, 50));
        wrapper.add(btn1);
        wrapper.add(btn2);

        panel.add(wrapper);
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }

    public JButton getAddActivity() { return addActivity; }

    public JRadioButton getHrButton() { return hrButton; }

    public JRadioButton getMetButton() { return metButton; }

    public JRadioButton getMale() { return male; }

    public JRadioButton getFemale() { return female; }

    public JButton getNext1() { return next1; }

    public JButton getNext2() { return next2; }

    public JTextField getWeightField() {
        return weightField;
    }

    public String getSexField() {
        if(male.isSelected()){
            return "m";
        } else {
            return "f";
        }
    }

    public JTextField getAgeField() {
        return ageField;
    }

    public JTextField getGoalField() {
        return goalField;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void showProfile() {
        cardLayout.show(mainContainer, "PROFILE");
    }

    public void showFormula() {
        cardLayout.show(mainContainer, "FORMULA");
    }

    public void showResults() {
        cardLayout.show(mainContainer, "RESULTS");
    }
}
