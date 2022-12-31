package Week7.prerequisites;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeightBalanceProperty {

    /**
     * This method checks whether the given tree has the height-balance property.
     * 100 / 100
     *
     * @param tree
     *     the tree to check.
     * @return true iff the tree has the height-balance property, false otherwise.
     */
    public static boolean isTreeBalanced(BinaryTree tree) {
        if (tree == null) return true;

        return Math.abs(calculateHeight(tree.getLeft()) - calculateHeight(tree.getRight())) <= 1
                && isTreeBalanced(tree.getLeft())
                && isTreeBalanced(tree.getRight());
    }

    public static int calculateHeight(BinaryTree tree) {
        if (tree == null) return 0;

        if (!tree.hasLeft() && !tree.hasRight()) return 1;

        int heightLeft = calculateHeight(tree.getLeft());

        int heightRight = calculateHeight(tree.getRight());

        return 1 + Math.max(heightLeft, heightRight);
    }

}

class HeightBalancePropertyTest {

    @Test
    public void testEmptyTree() {
        assertTrue(HeightBalanceProperty.isTreeBalanced(null));
    }

    @Test
    public void testOneLevel() {
        BinaryTree tree = new BinaryTree(42, new BinaryTree(84), new BinaryTree(21));
        assertTrue(HeightBalanceProperty.isTreeBalanced(tree));
    }

    @Test
    public void testLinearTree() {
        assertFalse(
                HeightBalanceProperty.isTreeBalanced(
                        new BinaryTree(1, new BinaryTree(2, new BinaryTree(3), null), null)));
    }
}
