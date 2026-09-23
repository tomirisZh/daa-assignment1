package daa.algorithms;

import daa.metrics.Metrics;

public class QuickSelect {

    public static int select(int[] a, int k, Metrics metrics) {
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }
        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException(
                    "k=" + k + " is out of range for array of length " + a.length);
        }

        metrics.startTimer();
        int result = selectIterative(a, 0, a.length - 1, k, metrics);
        metrics.stopTimer();
        return result;
    }

    private static int selectIterative(int[] a, int lo, int hi, int k, Metrics metrics) {
        metrics.enterRecursion();
        try {
            while (lo <= hi) {
                if (lo == hi) {
                    return a[lo];
                }
                int[] bounds = QuickSort.sharedPartition3way(a, lo, hi, metrics);
                int lt = bounds[0];
                int gt = bounds[1];

                if (k < lt) {
                    hi = lt - 1;
                } else if (k > gt) {
                    lo = gt + 1;
                } else {
                    return a[k];
                }
            }
            return a[k];
        } finally {
            metrics.exitRecursion();
        }
    }
}