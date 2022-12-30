package Week7;

import java.util.ArrayList;
import java.util.List;

public class FlattenBST {

    /**
     * Return all elements in the given BST in descending order.
     *
     * @param tree
     *     The BST to traverse.
     * @return A list of all elements in reverse order.
     */
    public static List<Integer> descendingOrder(BinaryTree tree) {
        if (tree == null) return null;

        List<Integer> result = new ArrayList<>();
        if (!tree.hasLeft() && !tree.hasRight()) return null;
        return null;
    }
}

class BinaryTree {

    private int key;

    private BinaryTree left, right;

    /**
     * Simple constructor.
     *
     * @param key
     *     to set as key.
     */
    public BinaryTree(int key) {
        this.key = key;
    }

    /**
     * Extended constructor.
     *
     * @param key
     *     to set as key.
     * @param left
     *     to set as left child.
     * @param right
     *     to set as right child.
     */
    public BinaryTree(int key, BinaryTree left, BinaryTree right) {
        this.key = key;
        setLeft(left);
        setRight(right);
    }

    public int getKey() {
        return key;
    }

    /**
     * @return the left child.
     */
    public BinaryTree getLeft() {
        return left;
    }

    /**
     * @return the right child.
     */
    public BinaryTree getRight() {
        return right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    /**
     * @param left
     *     to set
     */
    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    /**
     * @param right
     *     to set
     */
    public void setRight(BinaryTree right) {
        this.right = right;
    }
}
