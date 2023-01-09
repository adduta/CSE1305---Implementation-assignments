package Week7.mainmatter;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class isMultiwaySearchTree {

    /**
     * Checks whether the given MultiwaySearchTree satisfies all to conditions.
     * Our reference solution does not change this function in any way.
     *
     * @param tree
     *     MultiwaySearchTree to check.
     * @return True iff the given tree satisfies all conditions.
     */
    public static boolean isSpecialTree(MultiwaySearchTree tree) {
        System.out.println(satisfiesCondition1(tree));
        System.out.println(satisfiesCondition2(tree));
        System.out.println(satisfiesCondition3(tree));
        System.out.println(satisfiesCondition4(tree));
        return satisfiesCondition1(tree)
                && satisfiesCondition2(tree)
                && satisfiesCondition3(tree)
                && satisfiesCondition4(tree);
    }

    /**
     * Condition 1: every node has at most 7 children.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition1(MultiwaySearchTree tree) {
        int noOfChildren = tree.children.length;
        return noOfChildren <= 7;
    }

    /**
     * Condition 2: every non-leaf node (except the root) has at least 4 children.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition2(MultiwaySearchTree tree) {
        boolean condition2 = true;

        // Leaf node.
        if (tree.children == null) return true;

        // Root node : we do not need to check if it has at least 4 children.
        boolean onlyOneLevel = true;
        for (int i = 0; i < tree.children.length; i++) {
            if (tree.children != null) onlyOneLevel = false;
        }

        if (!onlyOneLevel) {
            // Internal node.
            for (int i = 0; i < tree.children.length; i++) {
                if (tree.children[i].children.length < 4) return false;
            }

            // Internal node.
            for (int i = 0; i < tree.children.length; i++) {
                condition2 = condition2 && satisfiesCondition2(tree.children[i]);
            }

            return condition2;
        }

        return onlyOneLevel;

    }

    /**
     * Condition 3: the root has at least two children if it is not a leaf node.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition3(MultiwaySearchTree tree) {
        // Root is a leaf node.
        if (tree.children == null) return true;

        return tree.children.length >= 2;

    }

    /**
     * Condition 4: the tree is a multiway search tree with distinct keys.
     * Note that you should NOT change the method signature (name/parameters/return type).
     */
    public static boolean satisfiesCondition4(MultiwaySearchTree tree) {
        Set<Integer> keys = new HashSet<>();
        int noOfKeys = tree.keys.length;

        boolean condition = (keys.size() == noOfKeys);

        return true;
    }
}

class MultiwaySearchTree {

    int[] keys;

    MultiwaySearchTree[] children;

    public MultiwaySearchTree(int[] keys, MultiwaySearchTree[] children) {
        this.keys = keys;
        this.children = children;
    }

    public int[] getKeys() {
        return keys;
    }

    public MultiwaySearchTree[] getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "MultiwaySearchTree{" + "keys=" + Arrays.toString(keys) + '}';
    }
}

class isMultiwaySearchTreeTest {

    private MultiwaySearchTree makeLeafNode(int value) {
        int[] child2Value = new int[1];
        child2Value[0] = value;
        MultiwaySearchTree[] child2Children = new MultiwaySearchTree[2];
        return new MultiwaySearchTree(child2Value, child2Children);
    }

    @Test
    public void testExample() {
        int[] rootValue = new int[1];
        rootValue[0] = 4;
        MultiwaySearchTree[] rootChildren = new MultiwaySearchTree[2];
        MultiwaySearchTree tree = new MultiwaySearchTree(rootValue, rootChildren);
        assertTrue(isMultiwaySearchTree.isSpecialTree(tree));
        rootChildren[0] = makeLeafNode(2);
        rootChildren[1] = makeLeafNode(6);
        assertTrue(isMultiwaySearchTree.isSpecialTree(tree));
    }

    @Test
    public void testExample2() {
        int[] rootValue = new int[1];
        rootValue[0] = 4;
        MultiwaySearchTree[] rootChildren = new MultiwaySearchTree[2];
        rootChildren[0] = makeLeafNode(2);
        rootChildren[1] = makeLeafNode(3);
        MultiwaySearchTree tree = new MultiwaySearchTree(rootValue, rootChildren);
        assertFalse(isMultiwaySearchTree.isSpecialTree(tree));
        assertTrue(isMultiwaySearchTree.satisfiesCondition1(tree));
        assertTrue(isMultiwaySearchTree.satisfiesCondition2(tree));
        assertTrue(isMultiwaySearchTree.satisfiesCondition3(tree));
        assertFalse(isMultiwaySearchTree.satisfiesCondition4(tree));
    }
}
