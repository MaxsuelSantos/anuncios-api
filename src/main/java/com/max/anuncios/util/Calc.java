package com.max.anuncios.util;

public class Calc {

    public static double visualization(double value) {
        return ((value * 30) + (value * 21.6) + (value * 15.552) + (value * 11.197) + (value * 8.062));
    }

    public static double click(double value) {
        double vis = visualization(value);
        return vis / 100 * 12;
    }

    public static double sharing(double value) {
        double click = click(value);
        return click / 20 * 3;
    }

}
