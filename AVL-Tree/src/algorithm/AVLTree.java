package algorithm;

import com.sun.source.tree.Tree;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : DataStructureProgress
 * @Package : algorithm
 * @createTime : 2022/8/31 12:01
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class AVLTree<T> {
    static class TreeNode<T> {
        public int val;
        public T value;
        public int balanceFactor;
        public TreeNode<T> left;
        public TreeNode<T> right;
        public TreeNode<T> parent;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode<T> left, TreeNode<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode<T> root;

    public boolean insert(int val) {
        TreeNode<T> node = new TreeNode<>(val);
        if (root == null) {
            root = node;
            return true;
        }
        TreeNode<T> parent = null;
        TreeNode<T> cur = root;
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

        // modify the balance factor
        cur = node;
        while (cur != null ) {
            if (cur == parent.right) {
                parent.balanceFactor++;
            } else {
                parent.balanceFactor--;
            }
            if (parent.balanceFactor == 0) {
                // already balanced
                break;
            } else if (parent.balanceFactor == -1 || parent.balanceFactor == 1) {
                // still need to refine previous node
                cur = parent;
                parent = cur.parent;
                if(parent==null)break;
            } else {
                if (parent.balanceFactor == 2) {
                    if (cur.balanceFactor == 1) {

                    } else {
                        //
                    }
                } else {
                    if (cur.balanceFactor == 1) {

                    } else {
                        //right rotate
                        rotateRight(parent);
                        break;
                    }

                }
            }
            cur = cur.parent;
        }
        return true;
    }

    private void rotateRight(TreeNode<T> parent) {
        TreeNode<T> par = parent.parent;
        TreeNode<T> parentLeft = parent.left;

        parent.left = parentLeft.right;
        if (parent.left != null) {
            parent.left.parent = parent;
        }
        parentLeft.right = parent;
        parent.parent = parentLeft;
        //
        parentLeft.balanceFactor = 0;
        parent.balanceFactor = 0;
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
