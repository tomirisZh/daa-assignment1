package daa.algorithms;

import daa.metrics.Metrics;
import java.util.Random;

public class QuickSort {

    private static final Random RANDOM = new Random();

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length < 2) return;
        metrics.startTimer();
        sort(a, 0, a.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void sort(int[] a, int lo, int hi, Metrics metrics) {
        while (lo < hi) {
            int[] bounds = partition3way(a, lo, hi, metrics);
            int lt = bounds[0];
            int gt = bounds[1];
            int leftSize = lt - lo;
            int rightSize = hi - gt;

            if (leftSize < rightSize) {
                metrics.enterRecursion();
                try {
                    sort(a, lo, lt - 1, metrics);
                } finally {
                    metrics.exitRecursion();
                }
                lo = gt + 1;
            } else {
                metrics.enterRecursion();
                try {
                    sort(a, gt + 1, hi, metrics);
                } finally {
                    metrics.exitRecursion();
                }
                hi = lt - 1;
            }
        }
    }

    private static int[] partition3way(int[] a, int lo, int hi, Metrics metrics) {
        int pivotIndex = lo + RANDOM.nextInt(hi - lo + 1);
        swap(a, lo, pivotIndex);
        int pivot = a[lo];
        int lt = lo;
        int i = lo + 1;
        int gt = hi;

        while (i <= gt) {
            metrics.incComparisons();
            if (a[i] < pivot) {
                swap(a, lt, i);
                lt++;
                i++;
            } else {
                metrics.incComparisons();
                if (a[i] > pivot) {
                    swap(a, i, gt);
                    gt--;
                } else {
                    i++;
                }
            }
        }
        return new int[]{lt, gt};
    }

    static int[] sharedPartition3way(int[] a, int lo, int hi, Metrics metrics) {
        return partition3way(a, lo, hi, metrics);
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}