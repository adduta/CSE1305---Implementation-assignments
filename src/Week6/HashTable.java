package Week6;

import java.util.*;

/**
 * Week6.Entry objects are used to represent "Key-Value" pairs.
 * An entry can be created by using new Week6.Entry(String key, Integer Value)
 * The .equals() method of Week6.Entry will compare the keys only.
 */
class Entry {

    public final String key;

    public final Integer value;

    public Entry(String s, Integer v) {
        key = s;
        value = v;
    }

    public boolean equals(String s) {
        return s == null && key == null || key.equals(s);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && this.equals(((Entry) o).key);
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

/**
 * 71 / 100
 */
abstract class HashTable {

    protected LinkedList<Entry>[] myTable;

    /**
     * Constructs a new Week6.HashTable.
     *
     * @param capacity
     *     to be used as capacity of the table.
     * @throws IllegalArgumentException
     *     if the input capacity is invalid.
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        this.myTable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            myTable[i] = new LinkedList<>();
        }
    }

    /**
     * Add a key value pair to the Week6.HashTable.
     *
     * @param key
     *     to identify the value.
     * @param value
     *     that is identified by the key.
     */
    public void put(String key, Integer value) {
        int index = hash(key);

        if (containsKey(key)) {
            int i = myTable[index].indexOf(new Entry(key, value));
            myTable[index].remove(i);
        }

        myTable[index].add(new Entry(key, value));
    }

    /**
     * @param key
     *     to look for in the Week6.HashTable.
     * @return true iff the key is in the Week6.HashTable.
     */
    public boolean containsKey(String key) {

        for (int i = 0; i < myTable.length; i++) {
            for (int j = 0; j < myTable[i].size(); j++) {
                Entry current = myTable[i].get(j);
                if (current.getKey().equals(key)) return true;
            }
        }

        return false;
    }

    /**
     * Get a value from the Week6.HashTable.
     *
     * @param key
     *     that identifies the value.
     * @return the value associated with the key or `null` if the key is not in the Week6.HashTable.
     */
    public Integer get(String key) {

        for (int i = 0; i < myTable.length; i++) {
            for (int j = 0; j < myTable[i].size(); j++) {
                Entry current = myTable[i].get(j);
                if (current.getKey().equals(key)) return current.getValue();
            }
        }

        return null;
    }

    /**
     * Gets the capacity of this Week6.HashTable.
     *
     * @return the capacity of the Week6.HashTable.
     */
    public int getCapacity() {
        return myTable.length;
    }

    /**
     * Hashes a string/key.
     *
     * @param item
     *     to hash.
     * @return the hashcode of the string, modulo the capacity of the Week6.HashTable.
     */
    public abstract int hash(String item);
}

