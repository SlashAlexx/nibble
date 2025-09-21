package window;

// Window Class for User Interaction and GUI Functionality
// Alex Roberts


import javax.swing.*;
import javax.swing.border.EmptyBorder;

import main.Main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import value.Value;

public class Window {

    private int frame_width = 0;
    private int frame_height = 0;
    private JFrame frame = null;

    private JLabel promptLabel = null;
    public boolean answerEntered = false;
    public String lastAnswer = "";

    private JLabel answerStatusLabel = null;

    private JButton settingsGear = null;
    private boolean settingsEnabled = false;
    private JComboBox<String> baseSettings = null;
    private JComboBox<String> expectedSettings = null;
    private JLabel settingsConvToText = null;
    public Value.COMPARE_TYPE selectedBaseSetting = Value.COMPARE_TYPE.BIN;
    public Value.COMPARE_TYPE selectedExpectedSetting = Value.COMPARE_TYPE.DEC;

    public Window(int width, int height){

        frame_width = width;
        frame_height = height;

        // Create GUI on Instantiation, but don't display
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false); // Force Use of displayWindow() method
        frame.setDefaultCloseOperation((JFrame.DO_NOTHING_ON_CLOSE));
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){
                System.exit(0);
            }
        });
        
        // Header Label: ToConvert
        promptLabel = new JLabel("");
        promptLabel.setBounds(0, 125, width, 40);
        promptLabel.setHorizontalAlignment(JTextField.CENTER);
        Font promptLabelFont = new Font("Serif", Font.PLAIN, 32);
        promptLabel.setFont(promptLabelFont);
        frame.add(promptLabel);

        // Answer Input
        JTextField answerField = new JTextField();
        answerField.setBounds(150, 250, 200, 30);
        answerField.setHorizontalAlignment(JTextField.CENTER);
        answerField.addActionListener(e -> {
            
            String answer = answerField.getText();
            if (answer.isEmpty()) lastAnswer = "0";
            else lastAnswer = answer;
            
            answerEntered = true;
            answerField.setText("");
        });
        
        frame.add(answerField);

        // Settings Button
        settingsGear = new JButton();
        settingsGear.setBounds(445, 15, 20, 20);
        settingsGear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsGear.setBackground(Color.WHITE);
        settingsGear.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
        try{   
            ImageIcon icon = new ImageIcon(Main.class.getResource("/assets/gear-solid-full.png"));
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            settingsGear.setIcon(new ImageIcon(img));
        } catch (Exception e){
        System.out.println("Unable to locate image assets");
        }
        settingsGear.addActionListener(e -> {
            settingsEnabled = !settingsEnabled;
            toggleSettings(settingsEnabled);    
        });
        frame.add(settingsGear);
        
        // Answer Correct/Incorrect Text
        answerStatusLabel = new JLabel();
        answerStatusLabel.setBounds(0, 180, width, 40);
        answerStatusLabel.setHorizontalAlignment(JTextField.CENTER);
        Font answerStatusLabelFont = new Font("Serif", Font.PLAIN, 36);
        answerStatusLabel.setFont(answerStatusLabelFont);
        
        frame.add(answerStatusLabel);
        
        
        // Dropdown for Base Value Type
        String baseValueSettings[] = {"Decimal", "Binary", "Hex"};
        baseSettings = new JComboBox<>(baseValueSettings);
        baseSettings.setBounds(250, 50, 80, 30);
        baseSettings.setSelectedIndex(1);
        baseSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        baseSettings.addActionListener(e -> {
            switch (baseSettings.getSelectedIndex()){
                case 0: selectedBaseSetting = Value.COMPARE_TYPE.DEC; break;
                case 1: selectedBaseSetting = Value.COMPARE_TYPE.BIN; break;
                case 2: selectedBaseSetting = Value.COMPARE_TYPE.HEX; break;
                default: selectedBaseSetting = Value.COMPARE_TYPE.DEC; break;
            }
        });
        frame.add(baseSettings);

        settingsConvToText = new JLabel("to");
        settingsConvToText.setBounds(350, 50, 30, 30);
        Font settingsConvToTextFont = new Font("Serif", Font.PLAIN, 16);
        settingsConvToText.setFont(settingsConvToTextFont);
        frame.add(settingsConvToText);
        
        // Dropdown for Expected Conversion Type
        String expectedValueSettings[] = {"Decimal", "Binary", "Hex"};
        expectedSettings = new JComboBox<>(expectedValueSettings);
        expectedSettings.setBounds(380, 50, 85, 30);
        expectedSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
        expectedSettings.addActionListener(e -> {
            switch (expectedSettings.getSelectedIndex()){
                case 0: selectedExpectedSetting = Value.COMPARE_TYPE.DEC; break;
                case 1: selectedExpectedSetting = Value.COMPARE_TYPE.BIN; break;
                case 2: selectedExpectedSetting = Value.COMPARE_TYPE.HEX; break;
                default: selectedExpectedSetting = Value.COMPARE_TYPE.DEC; break;
            }
        });
        frame.add(expectedSettings);

        toggleSettings(false);
    }

    public void displayWindow(boolean value){
        frame.setVisible(value);
    }

    public void setPromptValue(String value, Value.COMPARE_TYPE inputType, Value.COMPARE_TYPE convertToType){
        
        String convertToText = "";
        switch (convertToType) {
            case DEC: convertToText = "Decimal"; break;
            case BIN: convertToText = "Binary"; break;
            case HEX: convertToText = "Hex"; break;        
            default: break;
        }

        String inputTypeText = "";
        switch (inputType) {
            case DEC: inputTypeText = ""; break;
            case BIN: inputTypeText = "0b"; break;
            case HEX: inputTypeText = "0x"; break;        
            default: break;
        }

        promptLabel.setText("Convert to " + convertToText + ": " + inputTypeText + value);
        answerEntered = false;
    }


    public void setAnswerStatusText(boolean correct_answer) throws InterruptedException{
        if (correct_answer) {
            answerStatusLabel.setForeground(Color.green);
            answerStatusLabel.setText("Correct!");
        }
        else {
            answerStatusLabel.setForeground(Color.red);
            answerStatusLabel.setText("Incorrect");
        }
        
        Timer timer = new Timer(1500, e -> {
            answerStatusLabel.setForeground(Color.BLACK);
            answerStatusLabel.setText("");
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void toggleSettings(boolean value){
        baseSettings.setVisible(value);
        expectedSettings.setVisible(value);
        settingsConvToText.setVisible(value);
    }
}
