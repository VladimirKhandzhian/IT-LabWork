package ua.nure.khandzhian.tripiy.lab2.methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

public class SavidgMethod {

    public static final String MAX_ROW_VALUE = "R%d";

    public static void methodSavidga(double[][] matrix) {
        System.out.println("===============================Method Savidga==================================");
        Map<String, Double> maxRowValue = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(matrix).forEach(row ->
                maxRowValue.put(String.format(MAX_ROW_VALUE, counter.getAndIncrement()), DoubleStream.of(row).max().getAsDouble())
        );
        System.out.println(maxRowValue.toString());
        System.out.println("==================================Best choice================================");
        maxRowValue.entrySet().stream().min(
                Map.Entry.comparingByValue()).ifPresent(System.out::println);
    }
}
