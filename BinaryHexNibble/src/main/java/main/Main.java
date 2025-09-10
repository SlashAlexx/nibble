package main;

import window.*;
import converter.*;

// Application to test users over Binary/Hex Nibble Conversion
// Alex Roberts

public class Main {

    public static void main(String[] args) {

        Window window = new Window(500, 500);
        window.displayWindow(true);

        Converter converter = new Converter();
        System.out.println(converter.getRandomHex(2));
    }
}