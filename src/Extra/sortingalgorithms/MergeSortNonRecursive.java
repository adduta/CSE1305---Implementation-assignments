package Extra.sortingalgorithms;

public class MergeSortNonRecursive {

    public static void mergeSortBottomUp(int[] elements) {
        int n = elements.length;

        int[] source = elements;
        int[] destination = new  int[n];
        int[] temp;

        for (int i = 1; i < n; i *= 2) {
            for (int j = 0; j < n; j += 2*i) {
                merge(source, destination, j, i);
            }
            temp = source;
            source = destination;
            destination = temp;
        }
    }

    public static void merge(int[] source, int[] destination, int start, int inc) {
        int end1 = Math.min(start + inc, source.length);
        int end2 = Math.min(start + 2 * inc, destination.length);

        int x = start;
        int y = start + inc;
        int z = start;

        while (x < end1 && y < end2) {
            if (source[x] < source[y]) destination[z++] = source[x++];
            else destination[z++] = source[y++];
        }

        if (x < end1) {
            System.arraycopy(source, x, destination, z, end1 - x);
        }

        if (y < end2) {
            System.arraycopy(source, y, destination, z, end2 - y);
        }
    }
}
