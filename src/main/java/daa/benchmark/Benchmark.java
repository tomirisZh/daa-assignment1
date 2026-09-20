package daa.benchmark;

import daa.algorithms.MergeSort;
import daa.algorithms.QuickSort;
import daa.algorithms.QuickSelect;
import daa.metrics.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class Benchmark {

    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final String[] INPUT_TYPES = {"random", "sorted", "duplicates"};
    private static final int REPEATS = 5;

    public static void main(String[] args) throws IOException {
        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("algorithm,input,n,time_ms,comparisons,max_depth\n");

            for (int n : SIZES) {
                for (String type : INPUT_TYPES) {
                    runCase("mergesort", type, n, writer);
                    runCase("quicksort", type, n, writer);
                    runCase("quickselect", type, n, writer);
                }
            }
        }
        System.out.println("Done. Results saved to results.csv");
    }

    private static void runCase(String algorithm, String inputType, int n, FileWriter writer) throws IOException {
        double[] times = new double[REPEATS];
        long[] comparisons = new long[REPEATS];
        int[] depths = new int[REPEATS];

        for (int run = 0; run < REPEATS; run++) {
            int[] data = generateInput(inputType, n);
            Metrics metrics = new Metrics();

            switch (algorithm) {
                case "mergesort" -> MergeSort.sort(data, metrics);
                case "quicksort" -> QuickSort.sort(data, metrics);
                case "quickselect" -> QuickSelect.select(data, n / 2, metrics); // ищем медиану
            }

            times[run] = metrics.getElapsedMillis();
            comparisons[run] = metrics.getComparisons();
            depths[run] = metrics.getMaxDepth();
        }

        int medianIndex = medianIndexByTime(times);
        writer.write(String.format(Locale.US, "%s,%s,%d,%.4f,%d,%d%n",
                algorithm, inputType, n,
                times[medianIndex], comparisons[medianIndex], depths[medianIndex]));

        System.out.printf(Locale.US, "%s | %s | n=%d | median time=%.3f ms%n",
                algorithm, inputType, n, times[medianIndex]);
    }

    private static int[] generateInput(String type, int n) {
        return switch (type) {
            case "random" -> InputGenerator.random(n);
            case "sorted" -> InputGenerator.sorted(n);
            case "duplicates" -> InputGenerator.duplicates(n);
            default -> throw new IllegalArgumentException("Unknown input type: " + type);
        };
    }
    private static int medianIndexByTime(double[] times) {
        Integer[] indices = new Integer[times.length];
        for (int i = 0; i < times.length; i++) indices[i] = i;
        Arrays.sort(indices, (i1, i2) -> Double.compare(times[i1], times[i2]));
        return indices[times.length / 2];
    }
}