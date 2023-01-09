package Week7.mainmatter;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckTreeAVL {

    /**
     * Computes whether the BinaryTree is an AVL tree.
     *  100 / 100
     *
     * @param tree
     *     the BinaryTree to check.
     * @return true iff the BinaryTree is an AVL tree, else false.
     */
    public static boolean isTreeAVL(BinaryTree tree) {
        if (tree == null) return true;

        // AVL tree : a binary search tree that satisfies the height-balance property.
        boolean isBST = isTreeBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
        boolean isBalanced = heightBalanceProperty(tree);

        return isBST && isBalanced;
    }

    public static boolean isTreeBST(BinaryTree tree, int min, int max) {
        if (tree == null) return true;

        if (min >= tree.getKey()) return false;
        if (max <= tree.getKey()) return false;

        return isTreeBST(tree.getLeft(), min, tree.getKey()) && isTreeBST(tree.getRight(), tree.getKey(), max);
    }

    public static boolean heightBalanceProperty(BinaryTree tree) {
        if (tree == null) return true;

        return (Math.abs(calculateHeight(tree.getLeft()) - calculateHeight(tree.getRight())) <= 1)
                && heightBalanceProperty(tree.getLeft())
                && heightBalanceProperty(tree.getRight());
    }

    public static int calculateHeight(BinaryTree tree) {
        if (tree == null) return 0;

        int heightLeft = calculateHeight(tree.getLeft());
        int heightRight = calculateHeight(tree.getRight());

        return 1 + Math.max(heightLeft, heightRight);
    }

    public static boolean containsDuplicates(BinaryTree tree, HashSet<Integer> set) {
        if (tree == null) return false;

        if (set.contains(tree.getKey())) return true;

        set.add(tree.getKey());

        return containsDuplicates(tree.getLeft(), set) && containsDuplicates(tree.getRight(), set);
    }

}

class TestSuite {

    @Test
    public void testJustRoot() {
        BinaryTree tree = new BinaryTree(42);
        assertTrue(CheckTreeAVL.isTreeAVL(tree));
    }

    @Test
    public void testOneLevelTrue() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(21), new BinaryTree(84));
        assertTrue(CheckTreeAVL.isTreeAVL(tree));
    }

    @Test
    public void testOneLevelFalse() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(84), new BinaryTree(21));
        assertFalse(CheckTreeAVL.isTreeAVL(tree));
    }

    @Test
    public void testOneLevelFalseDuplicate() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(42), new BinaryTree(21));
        assertFalse(CheckTreeAVL.isTreeAVL(tree));
    }

    @Test
    public void testOneLeftChild() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(21), null);
        assertTrue(CheckTreeAVL.isTreeAVL(tree));
        tree = new BinaryTree(42, new BinaryTree(84), null);
        assertFalse(CheckTreeAVL.isTreeAVL(tree));
    }

    /*
          42
         /
        36
       /  \
      21  39
    */
    @Test
    public void testTwoLevelsSkew2() {
        BinaryTree tree =
                new BinaryTree(
                        42, new BinaryTree(36, new BinaryTree(21), new BinaryTree(39)), null);
        assertFalse(CheckTreeAVL.isTreeAVL(tree));
    }
}
