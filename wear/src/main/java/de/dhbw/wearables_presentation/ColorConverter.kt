package de.dhbw.wearables_presentation

class ColorConverter {
    fun intToHex(intColor: Int): String {
        return String.format("#%06X", 0xFFFFFF and intColor)
    }
}