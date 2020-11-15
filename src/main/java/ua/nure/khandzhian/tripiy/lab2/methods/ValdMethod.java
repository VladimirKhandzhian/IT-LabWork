package ua.nure.khandzhian.tripiy.lab2.methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

public class ValdMethod {

    public static final String MIN_ROW_VALUE = "W%d";

    public static void methodValda(double[][] matrix) {
        System.out.println("===============================Method Valda==================================");
        Map<String, Double> minRowValue = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(matrix).forEach(row ->
                minRowValue.put(String.format(MIN_ROW_VALUE, counter.getAndIncrement()), DoubleStream.of(row).min().getAsDouble())
        );
        System.out.println(minRowValue.toString());
        System.out.println("==================================Best choice================================");
        minRowValue.entrySet().stream().max(
                Map.Entry.comparingByValue()).ifPresent(System.out::println);
    }
}
