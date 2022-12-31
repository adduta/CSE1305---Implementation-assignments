package Week1.mainmatter;

class ReverseArray {
    /**
     * Reverses the order of the elements of the given array.
     *
     * @param arr
     *     the array to reverse
     */
    public static void reverse(int[] arr) {
        if ( arr == null ) return;
        int n = arr.length - 1;
        for ( int i = 0; i < arr.length/2; i++){
            int temp = arr[i];
            arr[i] = arr[n-i];
            arr[n-i] = temp;
        }
    }
}
