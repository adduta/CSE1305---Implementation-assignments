package Week6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ETHHash extends HashTable {

    public ETHHash(int size) {
        super(size);
    }

    @Override
    public int hash(String item) {
        if (item == null) return 0;

        int b = 1;
        for (int i = 0; i < item.length(); i++) {
            int currentB = b;
            b = ((int) item.charAt(i)) * ((currentB % 257) + 1);
        }

        int hashValue = b % (this.getCapacity());
        return hashValue;
    }
}

class GNUCPPHash extends HashTable {

    public GNUCPPHash(int size) {
        super(size);
    }

    @Override
    public int hash(String item) {
        if (item == null) return 0;

        int b = 0;
        for (int i = 0; i < item.length(); i++) {
            int currentB = b;
            b = (4 * currentB) + ((int) item.charAt(i));
        }

        int mask = 0x7FFFFFFF;
        int hashValue = (b & mask) % (this.getCapacity());
        return hashValue;
    }
}

class GNUCC1Hash extends HashTable {

    public GNUCC1Hash(int size) {
        super(size);
    }

    @Override
    public int hash(String item) {
        if (item == null) return 0;

        int b = item.length();
        for (int i = 0; i < item.length(); i++) {
            int currentB = b;
            b = (613 * currentB) + ((int) item.charAt(i));
        }

        int mask = 0x3FFFFFFF;
        int hashValue = (b & mask) % (this.getCapacity());
        return hashValue;
    }
}

class HashCodeHash extends HashTable {

    public HashCodeHash(int size) {
        super(size);
    }

    @Override
    public int hash(String item) {
        if (item == null) return 0;

        int hash = item.hashCode();
        if (hash < 0) hash = Math.abs(hash);
        return hash % item.length();
    }
}

class Test4 {

    private static final int DEFAULT_SIZE = 4;

    @Test
    public void testETHHashNull() {
        assertEquals(0, new ETHHash(DEFAULT_SIZE).hash(null));
    }

    @Test
    public void testGNUCPPHashNull() {
        assertEquals(0, new GNUCPPHash(DEFAULT_SIZE).hash(null));
    }

    @Test
    public void testGNUCC1HashNull() {
        assertEquals(0, new GNUCC1Hash(DEFAULT_SIZE).hash(null));
    }

    @Test
    public void testHashCodeHashNull() {
        assertEquals(0, new HashCodeHash(DEFAULT_SIZE).hash(null));
    }

    @Test
    public void testETHHashHelloWorld() {
        assertEquals(1, new ETHHash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCPPHashHelloWorld() {
        assertEquals(1, new GNUCPPHash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCC1HashHelloWorld() {
        assertEquals(1, new GNUCC1Hash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testHashCodeHashHelloWorld() {
        assertEquals(3, new HashCodeHash(DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testETHHashHelloWorld2xSize() {
        assertEquals(5, new ETHHash(2 * DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCPPHashHelloWorld2xSize() {
        assertEquals(1, new GNUCPPHash(2 * DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testGNUCC1HashHelloWorld2xSize() {
        assertEquals(1, new GNUCC1Hash(2 * DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testHashCodeHashHelloWorld2xSize() {
        assertEquals(3, new HashCodeHash(2 * DEFAULT_SIZE).hash("Hello World!"));
    }

    @Test
    public void testETHHashArthurDent2xSize() {
        assertEquals(4, new ETHHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testGNUCPPHashArthurDent2xSize() {
        assertEquals(4, new GNUCPPHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testGNUCC1HashArthurDent2xSize() {
        assertEquals(0, new GNUCC1Hash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testHashCodeHashArthurDent2xSize() {
        assertEquals(3, new HashCodeHash(2 * DEFAULT_SIZE).hash("Arthur Dent"));
    }

    @Test
    public void testETHHashFortyTwo2xSize() {
        assertEquals(2, new ETHHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testGNUCPPHashFortyTwo2xSize() {
        assertEquals(3, new GNUCPPHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testGNUCC1HashFortyTwo2xSize() {
        assertEquals(6, new GNUCC1Hash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }

    @Test
    public void testHashCodeHashFortyTwo2xSize() {
        assertEquals(6, new HashCodeHash(2 * DEFAULT_SIZE).hash("FortyTwo"));
    }
}
