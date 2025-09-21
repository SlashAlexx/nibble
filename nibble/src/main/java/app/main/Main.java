package main;

import window.Window;

import value.Value;

// Application to test users over Binary/Hex Nibble Conversion
// Alex Roberts

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Window window = new Window(500, 500);
        window.displayWindow(true);

        while (true){

            Value currentValue = new Value();
            currentValue.newValue(); 
            boolean skipping_question = false;

            Value.COMPARE_TYPE baseSettingType = window.selectedBaseSetting;
            Value.COMPARE_TYPE expectedSettingType = window.selectedExpectedSetting;

            switch (baseSettingType) {
                case DEC: window.setPromptValue(Integer.toString(currentValue.getDec()), baseSettingType, expectedSettingType); break;
                case BIN: window.setPromptValue(currentValue.getBin(), baseSettingType, expectedSettingType); break;
                case HEX: window.setPromptValue(currentValue.getHex(), baseSettingType, expectedSettingType); break;
                default: break;
            }

            while (!window.answerEntered && !skipping_question){
                if (baseSettingType != window.selectedBaseSetting || expectedSettingType != window.selectedExpectedSetting) skipping_question = true;
                Thread.sleep(25);
            }  

            if (!skipping_question){
                boolean result = currentValue.compare(window.lastAnswer, expectedSettingType);
                window.setAnswerStatusText(result);
            }
        }
    }
}