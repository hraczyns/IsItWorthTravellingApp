package com.hraczynski.isitworthtravellingapp.utils;

public class CelsiusConverter {

    public static double convertFromKelvin(double temp) {
        return Double.parseDouble(String.format("%.2f", (temp - 273.15)).replace(",","."));
    }
}
