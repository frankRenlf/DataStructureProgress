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
        // adjust color
        while (parent != null && parent.color == Color.RED) {
            RBTreeNode grandParent = parent.parent;
            if (parent == grandParent.left) {
                RBTreeNode uncle = grandParent.right;
                if (uncle != null && uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandParent.color = Color.RED;

                    cur = grandParent;
                    parent = cur.parent;
                } else {
                    if (cur == parent.right) {
                        rotateLeft(parent);
                        RBTreeNode tmp = parent;
                        parent = cur;
                        cur = tmp;
                    }
                    rotateRight(grandParent);
                    grandParent.color = Color.RED;
                    parent.color = Color.BLACK;
                }
            } else {
                // parent == grandParent.right
                RBTreeNode uncle = grandParent.left;
                if (uncle != null && uncle.color == Color.RED) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandParent.color = Color.RED;

                    cur = grandParent;
                    parent = cur.parent;
                } else {
                    if (cur == parent.left) {
                        rotateRight(parent);
                        RBTreeNode tmp = parent;
                        parent = cur;
                        cur = tmp;
                    }
                    rotateLeft(grandParent);
                    grandParent.color = Color.RED;
                    parent.color = Color.BLACK;
                }
            }
        }
        return true;
    }

    private void rotateLeft(RBTreeNode parent) {
        RBTreeNode par = parent.parent;
        RBTreeNode parentRight = parent.right;

        parent.right = parentRight.left;
        if (parent.right != null) {
            parent.right.parent = parent;
        }
        parentRight.left = parent;
        parent.parent = parentRight;
        //
        //
        parentRight.parent = par;
        if (par != null) {
            if (par.val < parentRight.val) {
                par.right = parentRight;
            } else {
                par.left = parentRight;
            }
        } else {
            this.root = parentRight;
        }
    }

    private void rotateRight(RBTreeNode parent) {
        RBTreeNode par = parent.parent;
        RBTreeNode parentLeft = parent.left;

        parent.left = parentLeft.right;
        if (parent.left != null) {
            parent.left.parent = parent;
        }
        parentLeft.right = parent;
        parent.parent = parentLeft;
        //
        //
        parentLeft.parent = par;
        if (par != null) {
            if (par.val < parentLeft.val) {
                par.right = parentLeft;
            } else {
                par.left = parentLeft;
            }
        } else {
            this.root = parentLeft;
        }
    }

}
