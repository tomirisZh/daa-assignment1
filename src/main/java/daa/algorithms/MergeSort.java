package daa.algorithms;
import daa.metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 15;

    public static void sort(int[] a, Metrics metrics) {
        if (a == null || a.length < 2) return;
        int[] buffer = new int[a.length];
        metrics.startTimer();
        sort(a, buffer, 0, a.length - 1, metrics);
        metrics.stopTimer();
    }

    private static void sort(int[] a, int[] buffer, int lo, int hi, Metrics metrics) {
        metrics.enterRecursion();
        try {
            int size = hi - lo + 1;
            if (size <= CUTOFF) {
                insertionSort(a, lo, hi, metrics);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(a, buffer, lo, mid, metrics);
            sort(a, buffer, mid + 1, hi, metrics);
            merge(a, buffer, lo, mid, hi, metrics);
        } finally {
            metrics.exitRecursion();
        }
    }

    private static void merge(int[] a, int[] buffer, int lo, int mid, int hi, Metrics metrics) {
        for (int i = lo; i <= hi; i++) {
            buffer[i] = a[i];
        }

        int left = lo;
        int right = mid + 1;
        int k = lo;

        while (left <= mid && right <= hi) {
            metrics.incComparisons();
            if (buffer[left] <= buffer[right]) {
                a[k++] = buffer[left++];
            } else {
                a[k++] = buffer[right++];
            }
        }

        while (left <= mid) {
            a[k++] = buffer[left++];
        }
        while (right <= hi) {
            a[k++] = buffer[right++];
        }
    }

    private static void insertionSort(int[] a, int lo, int hi, Metrics metrics) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo) {
                metrics.incComparisons();
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }
}