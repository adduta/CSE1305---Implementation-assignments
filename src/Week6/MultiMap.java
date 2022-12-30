package Week6;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 45 / 100
 */
class MultiMap {

    private Map<Integer, List<Integer>> map;

    /**
     * Creates a new Week6.MultiMap.
     */
    public MultiMap() {
        this.map = new HashMap<>();
    }

    /**
     * Gets the size of this Week6.MultiMap.
     *
     * @return The number of (key, value) pairs in the Week6.MultiMap.
     */
    public int size() {
        return map.size();
    }

    /**
     * Gets whether this Week6.MultiMap is empty.
     *
     * @return True if the Week6.MultiMap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return map.isEmpty();
    }

    /**
     * Adds the given (key, value) pair to the Week6.MultiMap.
     *
     * @param key Key for the new item.
     * @param value New item to add to the Week6.MultiMap.
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            map.put(key, List.of(value));
        }
    }

    /**
     * Returns all values in the Week6.MultiMap for the given key.
     *
     * @param key Key to return all entries for.
     * @return A list of all entries for the given key.
     *         If the key is not in the map, return an empty list.
     */
    public List<Integer> get(int key) {
        if (!map.containsKey(key)) return new ArrayList<>();
        return map.get(key);
    }

    /**
     * Removes the given (key, value) pair from the Week6.MultiMap.
     *
     * @param key Key for the value that should be removed.
     * @param value Value to remove.
     * @return True if removal was successful, false otherwise.
     */
    public boolean remove(int key, int value) {
        if (!map.containsKey(key)) return false;
        else if (!(map.get(key).contains(value))) return false;
        map.remove(key);
        return true;
    }
}

class Test3 {
    @Test
    public void testEverythingOneItem() {
        MultiMap map = new MultiMap();
        map.put(1, 2);
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());
        assertEquals(Collections.singletonList(2), map.get(1));
        assertFalse(map.remove(1, 3));
        assertTrue(map.remove(1, 2));
    }
}

