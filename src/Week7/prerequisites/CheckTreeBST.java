package Week7.prerequisites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTreeBST {
    /**
     * Computes whether the BinaryTree is a binary search tree.
     * 64 / 100
     *
     * @param tree the BinaryTree to check.
     * @return true iff the BinaryTree is a binary search tree, else false.
     */
    public static boolean isTreeBST(BinaryTree tree) {

        return isValidBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }
    public static boolean isValidBST(BinaryTree tree, int min, int max) {
        if (tree == null) return true;

        if (tree.getKey() < min || tree.getKey() > max) return false;

        return isValidBST(tree.getLeft(), min, tree.getKey()) && isValidBST(tree.getRight(), tree.getKey(), max);
    }

}
class TestSuite {

    @Test
    public void testOneLevelTrue() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(21), new BinaryTree(84));
        assertTrue(CheckTreeBST.isTreeBST(tree));
    }

    @Test
    public void testOneLevelFalse() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(84), new BinaryTree(21));
        assertFalse(CheckTreeBST.isTreeBST(tree));
    }

    @Test
    public void testOneLevelFalseDuplicate() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(42), new BinaryTree(21));
        assertFalse(CheckTreeBST.isTreeBST(tree));
    }

    @Test
    public void testOneLeftChild() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(21), null);
        assertTrue(CheckTreeBST.isTreeBST(tree));
        tree = new BinaryTree(42, new BinaryTree(84), null);
        assertFalse(CheckTreeBST.isTreeBST(tree));
    }
}
