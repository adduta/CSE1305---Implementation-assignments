package Week1.mainmatter;

public class MergeSortedArrays {
    /**
     * Merges two sorted arrays such that the resulting array has all elements
     * from both arrays and is also sorted. Assumes that the elements in the
     * given arrays are sorted in non-decreasing order. If there are duplicate
     * elements in the input arrays, these should also be present in the
     * resulting array. If both arrays are null the result should be null, or a
     * copy of the non-null array if only one is null.
     *
     * Efficiency requirements: merge the two arrays in a single pass.
     *
     * @param arr1 first sorted array to be merged
     * @param arr2 second sorted array to be merged
     * @return sorted array containing all elements from both arrays
     */
    public static int[] merge(int[] arr1, int[] arr2) {
        if ( arr1 == null && arr2 == null ) return null;
        if ( arr1 == null && arr2 != null ){
            int[] merged = new int[arr2.length];
            int el = 0;
            for ( int i = 0; i < arr2.length; i++ ){
                merged[el++] = arr2[i];
            }
            return merged;
        }
        if ( arr1 != null && arr2 == null ){
            int[] merged = new int[arr1.length];
            int el = 0;
            for ( int i = 0; i < arr1.length; i++ ){
                merged[el++] = arr1[i];
            }
            return merged;
        }

        int length1 = arr1.length;
        int length2 = arr2.length;
        int[] merged = new int[length1 + length2];

        int i = 0, j = 0, el = 0;
        while ( i < length1 && j < length2 ){
            if ( arr1[i] <= arr2[j] ){
                merged[el++] = arr1[i];
                i++;
            } else {
                merged[el++] = arr2[j];
                j++;
            }
        }

        while ( i < length1 ){
            merged[el++] = arr1[i];
            i++;
        }

        while ( j < length2 ){
            merged[el++] = arr2[j];
            j++;
        }

        return merged;
    }
}
