package ua.nure.khandzhian.tripiy.lab2;

import ua.nure.khandzhian.tripiy.lab2.methods.GurvitzMethod;
import ua.nure.khandzhian.tripiy.lab2.methods.LaplasMethod;
import ua.nure.khandzhian.tripiy.lab2.methods.SavidgMethod;
import ua.nure.khandzhian.tripiy.lab2.methods.ValdMethod;

public class Main {

    public static void main(String[] args) {
        double[][] payoffMatrix = new double[][]{{154, -216, 252, -216}, {105, 168, 105, 105},
                {-105, 69, -105, 198}, {210, -168, 120, -168}};
        double[][] riskMatrix = new double[][]{{56, 384, 0, 414}, {105, 0, 147, 93},
                {315, 99, 357, 0}, {0, 336, 132, 366}};
        double[][] profitMatrix = new double[][]{{252, 154, -216}, {168, 105, -92},
                {198, 69, -105}, {210, 120, -168}};
        LaplasMethod.methodLaplasa(payoffMatrix);
        ValdMethod.methodValda(payoffMatrix);
        SavidgMethod.methodSavidga(riskMatrix);
        GurvitzMethod.methodGurvitza(profitMatrix);
    }
}
