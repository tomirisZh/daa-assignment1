package daa.benchmark;

import java.util.Random;

public class InputGenerator {

    private static final Random RANDOM = new Random();

    public static int[] random(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = RANDOM.nextInt();
        }
        return a;
    }

    public static int[] sorted(int[] a) {
        int[] copy = random(a.length);
        java.util.Arrays.sort(copy);
        return copy;
    }

    public static int[] sorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }
        return a;
    }

    public static int[] duplicates(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = RANDOM.nextInt(10);
        }
        return a;
    }
}