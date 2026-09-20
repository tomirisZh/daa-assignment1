package daa;

import daa.algorithms.MergeSort;
import daa.algorithms.QuickSort;
import daa.metrics.Metrics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SortingTest {

    private static final Random RANDOM = new Random(123);

    @RepeatedTest(100)
    void mergeSortMatchesArraysSortOnRandomArrays() {
        int[] a = randomArray(RANDOM.nextInt(500) + 1);
        int[] expected = a.clone();
        Arrays.sort(expected);

        MergeSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @RepeatedTest(100)
    void quickSortMatchesArraysSortOnRandomArrays() {
        int[] a = randomArray(RANDOM.nextInt(500) + 1);
        int[] expected = a.clone();
        Arrays.sort(expected);

        QuickSort.sort(a, new Metrics());
        assertArrayEquals(expected, a);
    }

    @Test
    void mergeSortHandlesEmptyArray() {
        int[] a = {};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void mergeSortHandlesSingleElement() {
        int[] a = {42};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void mergeSortHandlesAllEqualElements() {
        int[] a = {5, 5, 5, 5, 5};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{5, 5, 5, 5, 5}, a);
    }

    @Test
    void mergeSortHandlesAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        MergeSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void quickSortHandlesEmptyArray() {
        int[] a = {};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{}, a);
    }

    @Test
    void quickSortHandlesSingleElement() {
        int[] a = {42};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{42}, a);
    }

    @Test
    void quickSortHandlesAllEqualElements() {
        int[] a = {7, 7, 7, 7, 7, 7};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 7}, a);
    }

    @Test
    void quickSortHandlesAlreadySortedArray() {
        int[] a = {1, 2, 3, 4, 5};
        QuickSort.sort(a, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, a);
    }

    @Test
    void quickSortDepthIsBoundedOnSortedArray() {
        int n = 100_000;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i;

        Metrics metrics = new Metrics();
        QuickSort.sort(a, metrics);

        int limit = (int) (2 * (Math.log(n) / Math.log(2)));
        org.junit.jupiter.api.Assertions.assertTrue(
                metrics.getMaxDepth() <= limit,
                "maxDepth=" + metrics.getMaxDepth() + " exceeded limit=" + limit);
    }

    private static int[] randomArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RANDOM.nextInt(1000) - 500;
        return a;
    }
}