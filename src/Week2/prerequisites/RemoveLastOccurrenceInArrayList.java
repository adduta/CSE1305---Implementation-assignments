package Week2.prerequisites;

import java.util.ArrayList;

public class RemoveLastOccurrenceInArrayList {
    /**
     * Removes all elements from the ArrayList, using the removeLastOccurrence method.
     *
     * @param list
     *     to remove the elements from.
     */
    public static void removeAll(ArrayList<Integer> list) {
        int n = list.size();
        for ( int i = 0; i < n; i++){
            removeLastOccurrence(list.get(list.size() - 1), list);
        }
    }

    /**
     * Takes an ArrayList and removes last occurrence of x,
     * shifting the rest of the elements left.
     * I.e. [5, 1, 5, 9, 8], with x = 5
     * would result in: [5, 1, 9, 8].
     * Note that this method does not return a new list.
     * Instead, the list that is passed as a parameter is changed.
     *
     * @param list
     *     to remove an element from.
     * @param x
     *     element value to look for
     */
    public static void removeLastOccurrence(int x, ArrayList<Integer> list) {
        int position = -1;
        int n = list.size();

        for ( int  i = n - 1; i >= 0; i-- ){
            if ( list.get(i) == x ){ position = i;break;}
        }

        if ( position > -1 ) list.remove(position);

    }
}
