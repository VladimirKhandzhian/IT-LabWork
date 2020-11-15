package ua.nure.khandzhian.tripiy.lab2.methods;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.DoubleStream;

public class LaplasMethod {

    public static final String AVG_ROW_VALUE = "M(x%d)";

    public static void methodLaplasa(double[][] matrix) {
        System.out.println("===============================Method Laplasa==================================");
        Map<String, Double> avgRowValue = new HashMap<>();
        AtomicInteger counter = new AtomicInteger(1);
        Arrays.stream(matrix).forEach(row ->
                avgRowValue.put(String.format(AVG_ROW_VALUE, counter.getAndIncrement()),
                        DoubleStream.of(row).sum() / row.length)
        );
        System.out.println(avgRowValue.entrySet().toString());
        System.out.println("==================================Best choice================================");
        avgRowValue.entrySet().stream().max(
                Map.Entry.comparingByValue()).ifPresent(System.out::println);
    }
}

