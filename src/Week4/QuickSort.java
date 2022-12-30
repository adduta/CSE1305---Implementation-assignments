package Week4;

public class QuickSort {
    /**
     * 100 / 100
     * @param elements
     *     Array of integers to be sorted.
     */
    public static void quickSort(int[] elements) {
        quickSortInPlace(elements, 0, elements.length - 1);
    }

    /**
     * The in-place variant of quick sort doesn't explicitly create arrays for smaller and larger.
     */
    public static void quickSortInPlace(int[] elements, int a, int b) {
        if (a >= b) return;

        int left = a;
        int right = b - 1;

        int pivot = elements[b];

        int temp;
        while (left <= right) {
            // Scan until you reach a value EQUAL or GREATER than the pivot.
            while (left <= right && elements[left] <= pivot) left++;
            // Scan until you reach a value EQUAL or SMALLER than the pivot.
            while (left <= right && elements[right] >= pivot) right--;
            // Indices did not cross.
            if (left <= right) {
                // Swap the values.
                temp = elements[left];
                elements[left] = elements[right];
                elements[right] = temp;
                left++;
                right--;
            }
        }

        // Put pivot in the final place.
        temp = elements[left];
        elements[left] = elements[b];
        elements[b] = temp;

        // Make the recursive calls.
        quickSortInPlace(elements, a, left - 1);
        quickSortInPlace(elements, left + 1, b);
    }
}
