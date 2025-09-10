package window;

// Window Class for User Interaction and GUI Functionality
// Alex Roberts

import java.io.*;
import javax.swing.*;
import java.awt.Font;

public class Window {

    private JFrame frame = null;
    private int frame_width = 0;
    private int frame_height = 0;

    public Window(int width, int height){

        frame_width = width;
        frame_height = height;

        // Create GUI on Instantiation, but don't display
        frame = new JFrame();
        frame.setSize(width, height);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false); // Force Use of displayWindow() method
        
        // Header Label: ToConvert
        JLabel label = new JLabel("Convert to Hex: 000011110000", SwingConstants.CENTER);
        label.setBounds(0, 35, width, 20);
        Font toConvLabelFont = new Font("Serif", Font.PLAIN, 24);
        label.setFont(toConvLabelFont);
        frame.add(label);

        // Answer Input
        JTextField answerField = new JTextField(SwingConstants.CENTER);
        answerField.setBounds(150, 250, 200, 25);
        frame.add(answerField);

    }

    public void displayWindow(boolean value){
        frame.setVisible(value);
    }
}
