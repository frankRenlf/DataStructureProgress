package test;

import algorithm.rbtree.RBTree;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : DataStructureProgress
 * @Package : test
 * @createTime : 2022/9/3 16:57
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class testRb {

    public static void main(String[] args) {
        RBTree rbTree = new RBTree();
        int[] array1 = new int[]{4, 2, 6, 1, 3, 5, 15, 7, 16, 14};
        int[] array2 = new int[]{16, 3, 7, 11, 9, 26, 18, 14, 15};
        for (int x : array1) {
            rbTree.insert(x);
        }
        rbTree.inorder(rbTree.root);
        System.out.println();
        System.out.println(rbTree.isRBTree(rbTree.root));
    }

}
