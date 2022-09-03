package algorithm.rbtree;


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
            root.color = Color.BLACK;
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
        root.color = Color.BLACK;
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

    public boolean isRBTree(RBTreeNode node) {
        if (root == null) return true;
        if (root.color != Color.BLACK) {
            System.out.println("root is not black");
        }
        return checkColor(node) && checkBlackLength(node, 0);
    }

    public void inorder(RBTreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    private int num = 0;

    public boolean checkBlackLength(RBTreeNode node, int count) {
        if (node == null) {
            if (num == 0) {
                num = count;
                return true;
            } else {
                if (count != num) {
                    System.out.println("black node is not equal in each path");
                }
                return count == num;
            }
        }
        if (node.color == Color.BLACK) {
            count++;
        }
        return checkBlackLength(node.left, count) && checkBlackLength(node.right, count);
    }

    private boolean checkColor(RBTreeNode node) {
        if (node == null) return true;
        if (node.color == Color.RED) {
            if (node.parent != null && node.parent.color == node.color) {
                System.out.println(node.parent.val + "->" + node.val);
                return false;
            }
        }
        return checkColor(node.left) && checkColor(node.right);
    }


}
