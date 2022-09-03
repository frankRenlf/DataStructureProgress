package algorithm.avltree;


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
        while (parent != null) {
            if (cur.val > parent.val) {
                parent.balanceFactor++;
            } else {
                parent.balanceFactor--;
            }
            //
            if (parent.balanceFactor == 0) {
                // already balanced
                break;
            } else if (parent.balanceFactor == -1 || parent.balanceFactor == 1) {
                // still need to refine previous node
                cur = parent;
                parent = cur.parent;
            } else {
                if (parent.balanceFactor == 2) {
                    if (cur.balanceFactor == 1) {
                        // left rotate
                        rotateLeft(parent);
                    } else {
                        //
                        rotateRL(parent);
//                        rotateRight(cur);
//                        rotateLeft(parent);
                    }
                } else {
                    if (cur.balanceFactor == 1) {
                        //
                        rotateLR(parent);
//                        rotateLeft(cur);
//                        rotateRight(parent);
                    } else {
                        //right rotate
                        rotateRight(parent);
                    }
                }
                break;
            }
//            cur = cur.parent;
        }
        return true;
    }


    /**
     * @param parent turn right and left
     */
    private void rotateRL(TreeNode<T> parent) {
        TreeNode<T> subR = parent.right;
        TreeNode<T> subRL = subR.left;
        int key = subRL.balanceFactor;

        rotateRight(parent.right);
        rotateLeft(parent);

        if (key == 1) {
            subR.balanceFactor = 0;
            subRL.balanceFactor = 0;
            parent.balanceFactor = -1;
        } else if (key == -1) {
            subR.balanceFactor = 1;
            subRL.balanceFactor = 0;
            parent.balanceFactor = 0;
        }
    }

    /**
     * @param parent turn left and right
     */
    private void rotateLR(TreeNode<T> parent) {
        TreeNode<T> subL = parent.left;
        TreeNode<T> subLR = subL.right;
        int key = subLR.balanceFactor;

        rotateLeft(parent.left);
        rotateRight(parent);

        if (key == -1) {
            subL.balanceFactor = 0;
            subLR.balanceFactor = 0;
            parent.balanceFactor = 1;
        } else if (key == 1) {
            subL.balanceFactor = -1;
            subLR.balanceFactor = 0;
            parent.balanceFactor = 0;
        }
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

    private void rotateLeft(TreeNode<T> parent) {
        TreeNode<T> par = parent.parent;
        TreeNode<T> parentRight = parent.right;

        parent.right = parentRight.left;
        if (parent.right != null) {
            parent.right.parent = parent;
        }
        parentRight.left = parent;
        parent.parent = parentRight;
        //
        parentRight.balanceFactor = 0;
        parent.balanceFactor = 0;
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

    public void inorder(TreeNode<T> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    public int getHeight(TreeNode<T> node) {
        if (node == null) return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return Math.max(left, right) + 1;
    }

    public boolean isBalanced(TreeNode<T> root) {
        if (root == null) return true;
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (right - left != root.balanceFactor) {
            System.out.println("bf is invalid");
            return false;
        }
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
}
