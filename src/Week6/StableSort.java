package Week6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * 8 / 100
 */
public class StableSort {

    public static void stableSort(String[][] table, int column) {
        int n = table.length;
        if (n == 0) return;

        for (int i = 1; i < table.length; i++) {

            String[] currentRow = table[i];
            String current = table[i][column];

            int j = i - 1;
            while (j > 0 && (table[j][column].compareToIgnoreCase(current) >= 0)) {
                    table[j + 1] = table[j];
                    j--;
            }

            table[j] = currentRow;
        }

        for (int i = 0; i < table.length; i++) {
            for (int k = 0; k < table[i].length; k++) {
                System.out.println(table[i][k]);
            }
        }
    }

}

class Test2 {

    @Test
    public void testEmpty() {
        String[][] data = {};
        String[][] data2 = {};
        StableSort.stableSort(data, 0);
        assertArrayEquals(data2, data);
    }

    @Test
    public void testOneColumn() {
        String[][] data = {{"d"}, {"a"}, {"e"}, {"b"}, {"g"}, {"c"}, {"f"}};
        String[][] data2 = {{"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}};
        StableSort.stableSort(data, 0);
        assertArrayEquals(data2, data);
    }

    @Test
    public void testMixed() {
        String[][] data = {{"aaa", "ddd"}, {"ccc", "bbb"}};
        String[][] data2 = {{"aaa", "ddd"}, {"ccc", "bbb"}};
        String[][] data3 = {{"ccc", "bbb"}, {"aaa", "ddd"}};
        StableSort.stableSort(data, 0);
        assertArrayEquals(data2, data);
        StableSort.stableSort(data, 1);
        assertArrayEquals(data3, data);
    }
}
