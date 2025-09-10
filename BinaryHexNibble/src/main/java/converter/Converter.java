package converter;

// Converter Interface Class with methods for binary/hex conversion
// Alex Roberts

import java.util.Random;

public class Converter {

    private Random random = new Random();

    public String getRandomHex(int length){
        
        String val = "";
        for (int i = 0; i < length; i++){
            int hex = random.nextInt(16);
            val += Integer.toHexString(hex);
        }
        return "0x" + val;
    }
}
