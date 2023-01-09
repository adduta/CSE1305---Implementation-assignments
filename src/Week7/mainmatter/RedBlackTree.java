package Week7.mainmatter;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedBlackTree {

    /**
     * Checks whether the given BinaryTree is a Red Black Tree.
     * 100 / 100
     *
     * @param tree
     *     BinaryTree to check.
     * @return True if the given tree is a Red Black Tree, false otherwise.
     */
    public static boolean isRedBlackTree(BinaryTreeWithColors tree) {
        if (tree == null) return true;
        // Check if the given tree is a BST.
        boolean isBST = isBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);

        // Check the root property : the root is black.
        boolean rootIsBlack = tree.isBlack();

        // Check external property : every leaf is black.
        // We can assume that this is true as the null leaves are not explicitly colored.
        boolean externalProperty = true;

        // Check the red property : the children of a red node are black.
        boolean redProperty = redProperty(tree);

        // Check the depth property : all external nodes have the same black depth.
        boolean depthProperty = depthProperty(tree);

        return isBST && rootIsBlack && externalProperty && redProperty && depthProperty;

    }

    public static boolean isBST(BinaryTreeWithColors tree, int min, int max) {
         if (tree == null) return true;

         if (tree.getValue() < min) return false;
         if (tree.getValue() > max) return false;

         return isBST(tree.getLeft(), min, tree.getValue()) && isBST(tree.getRight(), tree.getValue(), max);
    }

    public static boolean redProperty(BinaryTreeWithColors tree) {
        if (tree == null) return true;

        if (tree.isRed()) {
            if (tree.hasLeft() && tree.getLeft().isRed()) return false;
            if (tree.hasRight() && tree.getRight().isRed()) return false;
        }

        return redProperty(tree.getLeft()) && redProperty(tree.getRight());
    }

    public static boolean externalProperty(BinaryTreeWithColors tree) {
        // We have reached the leaf node.
        if (tree == null) {
            if (tree.isRed()) return false;
            return true;
        } else {
            // Continue looking for the leaf node.
            return externalProperty(tree.getLeft()) && externalProperty(tree.getRight());
        }
    }

    public static boolean depthProperty(BinaryTreeWithColors tree) {
        Set<Integer> leavesAndDepth = new HashSet<>();
        depthPropertyHelper(tree, 0, leavesAndDepth);

        if (leavesAndDepth.size() > 1) return false;

        return true;
    }

    public static void depthPropertyHelper(BinaryTreeWithColors tree, int blackAncestors, Set<Integer> leavesAndDepth) {
        if (tree == null) {
            leavesAndDepth.add(blackAncestors);
            return;
        }

        if (tree.isBlack()) blackAncestors++;

        depthPropertyHelper(tree.getLeft(), blackAncestors, leavesAndDepth);
        depthPropertyHelper(tree.getRight(), blackAncestors, leavesAndDepth);
    }

}

class BinaryTreeWithColors {

    private int value;

    private BinaryTreeWithColors left, right;

    private boolean isRed;

    /**
     * Simple constructor.
     *
     * @param value
     *     Value for this tree set as value.
     * @param isRed
     *     True if this node is red, false otherwise.
     */
    public BinaryTreeWithColors(int value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }

    /**
     * Extended constructor.
     *
     * @param value
     *     to set as value.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
     */
    public BinaryTreeWithColors(int value, boolean isRed, BinaryTreeWithColors left, BinaryTreeWithColors right) {
        this(value, isRed);
        setLeft(left);
        setRight(right);
    }

    /**
     * @return the value of this tree.
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     *     the new value of this tree.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the left child.
     */
    public BinaryTreeWithColors getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public BinaryTreeWithColors getRight() {
        return right;
    }

    /**
     * @return true if this node is red, false otherwise.
     */
    public boolean isRed() {
        return isRed;
    }

    /**
     * @return true if this node is black, false otherwise.
     */
    public boolean isBlack() {
        return !isRed;
    }

    /**
     * @return True if the tree has a left child, false otherwise.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * @return True if the tree has a right child, false otherwise.
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left
     *     Left subtree to set.
     */
    public void setLeft(BinaryTreeWithColors left) {
        this.left = left;
    }

    /**
     * @param right
     *     Right subtree to set.
     */
    public void setRight(BinaryTreeWithColors right) {
        this.right = right;
    }

    /**
     * @param red
     *     True if the new color is red, false otherwise.
     */
    public void setRed(boolean red) {
        isRed = red;
    }
}

class RedBlackTreeTest {

    @Test
    public void testDepthProperty() {
        BinaryTreeWithColors tree = new BinaryTreeWithColors(4, false);
        assertTrue(RedBlackTree.isRedBlackTree(tree));
        tree.setLeft(new BinaryTreeWithColors(2, false));
        tree.setRight(new BinaryTreeWithColors(6, false));
        assertTrue(RedBlackTree.isRedBlackTree(tree));
        tree.getLeft().setLeft(new BinaryTreeWithColors(1, false));
        tree.setRight(null);
        assertFalse(RedBlackTree.isRedBlackTree(tree));
    }

    @Test
    public void testRedProperty() {
        BinaryTreeWithColors tree = new BinaryTreeWithColors(4, false);
        assertTrue(RedBlackTree.isRedBlackTree(tree));
        tree.setLeft(new BinaryTreeWithColors(2, true));
        tree.setRight(new BinaryTreeWithColors(6, true));
        assertTrue(RedBlackTree.isRedBlackTree(tree));
        tree.getLeft().setLeft(new BinaryTreeWithColors(1, true));
        tree.setRight(null);
        assertFalse(RedBlackTree.isRedBlackTree(tree));
    }

    @Test
    public void testRootIsBlack() {
        BinaryTreeWithColors tree = new BinaryTreeWithColors(4, true);
        assertFalse(RedBlackTree.isRedBlackTree(tree));
    }

}

