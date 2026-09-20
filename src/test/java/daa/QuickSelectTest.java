package daa;

import daa.algorithms.QuickSelect;
import daa.metrics.Metrics;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuickSelectTest {

    private static final Random RANDOM = new Random(7);

    @RepeatedTest(100)
    void selectMatchesSortedArray() {
        int n = RANDOM.nextInt(300) + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = RANDOM.nextInt(1000) - 500;

        int[] sorted = a.clone();
        Arrays.sort(sorted);

        int k = RANDOM.nextInt(n);
        int result = QuickSelect.select(a.clone(), k, new Metrics());

        assertEquals(sorted[k], result);
    }

    @Test
    void selectThrowsOnEmptyArray() {
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[0], 0, new Metrics()));
    }

    @Test
    void selectThrowsOnNegativeK() {
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics()));
    }

    @Test
    void selectThrowsOnKTooLarge() {
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics()));
    }
}