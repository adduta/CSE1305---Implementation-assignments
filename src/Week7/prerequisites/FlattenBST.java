package Week7.prerequisites;

import java.util.ArrayList;
import java.util.List;

public class FlattenBST {

    /**
     * Return all elements in the given BST in descending order.
     * 100 / 100
     *
     * @param tree
     *     The BST to traverse.
     * @return A list of all elements in reverse order.
     */
    public static List<Integer> descendingOrder(BinaryTree tree) {
        if (tree == null) return null;

        List<Integer> result = new ArrayList<>();

        if (tree.hasRight()) {
            result.addAll(descendingOrder(tree.getRight()));
        }

        result.add(tree.getKey());

        if (tree.hasLeft()) {
            result.addAll(descendingOrder(tree.getLeft()));
        }

        return result;
    }
}

