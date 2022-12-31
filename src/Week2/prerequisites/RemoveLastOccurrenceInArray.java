package Week2.prerequisites;

public class RemoveLastOccurrenceInArray {
    /**
     * Takes the array and the last occurring element x,
     * shifting the rest of the elements left. I.e.
     * [1, 4, 7, 9], with x=7 would result in:
     * [1, 4, 9].
     *
     * @param x
     *     the entry to remove from the array
     * @param arr
     *     to remove an entry from
     * @return the updated array, without the last occurrence of x
     */
    public static int[] removeLastOccurrence(int x, int[] arr) {
        int length = arr.length;
        int n = arr.length;
        int el = 0;

        for ( int i = 0; i < n; i++){
            if ( arr[i] == x ){ length--;break; }
        }

        int[] result = new int[length];
        int position = -1;
        for ( int i = n - 1; i >= 0; i--){
            if ( arr[i] == x ){ position = i;break; }
        }

        for ( int i = 0; i < n; i++){
            if ( i == position ){ continue; }
            else{ result[el++] = arr[i]; }
        }

        return result;
    }
}
