package value;

// Class representing nibble value in dec, hex, and binary

import java.util.Random;

public class Value {

    public enum COMPARE_TYPE {DEC, BIN, HEX};

    private int dec = 0;
    private String bin = "";
    private String hex = "";
    private Random random = new Random();
    private int nibble_max = 16; // Exclusive

    // -------------------------------------------------------------------------------------------------------------------

    public int getDec() {return dec;}
    public String getBin() {return bin;}
    public String getHex() {return hex;}

    public void newValue(){
        dec = random.nextInt(nibble_max);
        bin = String.format("%4s", Integer.toBinaryString(dec)).replace(' ', '0');;
        hex = Integer.toHexString(dec).toUpperCase();
    }
    
    public boolean compare(String value, COMPARE_TYPE inputType){
        int inputDec = parseToDec(value, inputType);
        return inputDec == dec; // Always compare against dec
    }

    private int parseToDec(String value, COMPARE_TYPE type){
        switch (type){
            case DEC: return Integer.parseInt(value);
            case BIN: return Integer.parseInt(value, 2);
            case HEX: return Integer.parseInt(value, 16);
            default: return -1;
        }
    }
}
