package Week6;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 100 / 100
 */
class BucketSort {

    /**
     * Sorts the elements from an array into an array of buckets.
     * @param array
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Queue<Integer>[] fillBuckets(int[] array) {
        int n = array.length;

        if (n == 0) return new Queue[0];

        // Find the minimum value.
        int vmin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (array[i] < vmin) vmin = array[i];
        }

        // Find the maximum value.
        int vmax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (array[i] > vmax) vmax = array[i];
        }

        // Fill the buckets.
        Queue<Integer>[] buckets = new Queue[vmax - vmin + 1];
        for (int i = 0; i < n; i++) {
            buckets[array[i] - vmin] = new LinkedList<>();
        }

        for (int i = 0; i < n; i++) {
            buckets[array[i] - vmin].add(array[i]);
        }

        return buckets;
    }

    /**
     * Turns the buckets created in fillBuckets() into a sorted array.
     * @param buckets
     * @return
     */
    public static int[] readBuckets(Queue<Integer>[] buckets) {
        int n = buckets.length;

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (buckets[i] != null) {
                count += buckets[i].size();
            }
        }

        int[] result = new int[count];
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (buckets[i] != null) {
                while (!buckets[i].isEmpty()) {
                    result[k++] = buckets[i].poll();
                }
            }
        }

        return result;
    }
}

class Test1 {

    @Test
    public void testFillBucketsZeroElements() {
        int[] a = {};
        Queue<Integer>[] buckets = BucketSort.fillBuckets(a);
        // Check a has not been changed.
        assertEquals(0, a.length);
        // Check no buckets have been created
        assertEquals(0, buckets.length);
    }

    @Test
    public void testFillBucketsOneElement() {
        int[] a = {42};
        Queue<Integer>[] buckets = BucketSort.fillBuckets(a);
        // Check a has not been changed.
        assertEquals(42, a[0]);
        // Check one bucket has been created
        assertEquals(1, buckets.length);
        assertEquals(Integer.valueOf(42), buckets[0].peek());
    }

    @Test
    public void testFillBucketsTwoElements() {
        int[] a = {42, 21};
        Queue<Integer>[] buckets = BucketSort.fillBuckets(a);
        // Check a has not been changed.
        assertEquals(42, a[0]);
        assertEquals(21, a[1]);
        // Check enough buckets have been created
        assertEquals(22, buckets.length);
        // Check that the values are in the right buckets
        assertEquals(Integer.valueOf(21), buckets[0].peek());
        assertEquals(Integer.valueOf(42), buckets[21].peek());
    }

    @Test
    public void testFillBucketsThreeElements() {
        int[] a = {42, 42, 21};
        Queue<Integer>[] buckets = BucketSort.fillBuckets(a);
        // Check a has not been changed.
        assertEquals(42, a[0]);
        assertEquals(42, a[1]);
        assertEquals(21, a[2]);
        // Check one bucket has been created
        assertEquals(22, buckets.length);
        assertEquals(Integer.valueOf(21), buckets[0].poll());
        assertEquals(Integer.valueOf(42), buckets[21].poll());
        assertEquals(Integer.valueOf(42), buckets[21].poll());
    }

    @Test
    public void testSortZeroElements() {
        int[] a = {};
        int[] sorted = BucketSort.readBuckets(BucketSort.fillBuckets(a));
        // Check a has not been changed.
        assertEquals(0, a.length);
        // Check sorted array has the same length.
        assertEquals(0, sorted.length);
    }

    @Test
    public void testSortOneElement() {
        int[] a = {42};
        int[] sorted = BucketSort.readBuckets(BucketSort.fillBuckets(a));
        // Check that a new array has been built.
        assertFalse(a == sorted);
        // Check sorted array.
        assertArrayEquals(a, sorted);
    }

    @Test
    public void testSortTwoElements() {
        int[] a = {42, 21};
        int[] expected = {21, 42};
        int[] sorted = BucketSort.readBuckets(BucketSort.fillBuckets(a));
        // Check sorted array has the same length.
        assertArrayEquals(expected, sorted);
    }

    @Test
    public void testSortThreeElements() {
        int[] a = {42, 15, 21};
        int[] expected = {15, 21, 42};
        int[] sorted = BucketSort.readBuckets(BucketSort.fillBuckets(a));
        // Check sorted array has the same length.
        assertArrayEquals(expected, sorted);
    }
}
