package ua.nure.khandzhian.tripiy.lab2.methods;

import org.apache.commons.math3.util.Precision;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

public class GurvitzMethod {

    public static final String MAX_ROW_VALUE = "H%d";
    public static final double PESSIMISM_COEFFICIENT = 0.6;

    public static void methodGurvitza(double[][] matrix) {
        System.out.println("===============================Method Gurvitza==================================");
        Map<String, Double> coefficients = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(matrix).forEach(row -> {
                    double h = Precision.round(PESSIMISM_COEFFICIENT * DoubleStream.of(row).min().getAsDouble() +
                            (1 - PESSIMISM_COEFFICIENT) * DoubleStream.of(row).max().getAsDouble(), 2);
                    coefficients.put(String.format(MAX_ROW_VALUE, counter.getAndIncrement()), h);
                }
        );
        System.out.println(coefficients.toString());
        System.out.println("==================================Best choice================================");
        coefficients.entrySet().stream().max(
                Map.Entry.comparingByValue()).ifPresent(System.out::println);
    }
}
