package de.dhbw.wearables_presentation;

public class ColorConverter {
    public String intToHex(int intColor){
        return String.format("#%06X", (0xFFFFFF & intColor));
    }

}
