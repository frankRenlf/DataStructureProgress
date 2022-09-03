package algorithm.rbtree;

import algorithm.avltree.AVLTree;

import java.security.PublicKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : DataStructureProgress
 * @Package : algorithm
 * @createTime : 2022/9/3 13:27
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class RBTree {

    static class RBTreeNode {
        public RBTreeNode left;
        public RBTreeNode right;
        public RBTreeNode parent;
        public int val;
        public Color color;

        public RBTreeNode(int val) {
            this.val = val;
            this.color = Color.RED;
        }

    }

    public RBTreeNode root;

    public boolean insert(int val) {
        RBTreeNode node = new RBTreeNode(val);
        if (root == null) {
            root = node;
            return true;
        }
        RBTreeNode parent = null;
        RBTreeNode cur = root;
        while (cur != null) {
            if (cur.val > val) {
                parent = cur;
                cur = cur.left;
            } else if (cur.val == val) {
                return false;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }
        if (parent.val < val) {
            parent.right = node;
        } else {
            parent.left = node;
        }
        node.parent = parent;
        cur = node;

    }

}
