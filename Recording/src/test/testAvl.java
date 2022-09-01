package test;

import algorithm.AVLTree;

/**
 * Created with IntelliJ IDEA.
 *
 * @author : Frank.Ren
 * @version : 1.0
 * @Project : DataStructureProgress
 * @Package : test
 * @createTime : 2022/8/31 17:17
 * @Email : sc19lr@leeds.ac.uk
 * @github : https://github.com/frankRenlf
 * @Description :
 */
public class testAvl {

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
//        avlTree.insert(30);
//        avlTree.insert(10);
//        avlTree.insert(60);
////        avlTree.insert(40);
//        avlTree.insert(70);
//        avlTree.insert(80);
//        avlTree.insert(90);
//        avlTree.insert(100);
//        avlTree.insert(60);
//        avlTree.insert(70);
//        avlTree.insert(30);
//        avlTree.insert(10);
//        avlTree.insert(5);
//        avlTree.insert(4);
//        avlTree.insert(3);
        //
//        avlTree.insert(90);
//        avlTree.insert(100);
//        avlTree.insert(30);
//        avlTree.insert(10);
//        avlTree.insert(60);
//        avlTree.insert(70);

//        avlTree.insert(60);
//        avlTree.insert(50);
//        avlTree.insert(100);
//        avlTree.insert(110);
//        avlTree.insert(80);
//        avlTree.insert(90);
        int[] array1 = new int[]{4, 2, 6, 1, 3, 15, 7, 16, 14};
        int[] array2 = new int[]{16, 3, 7, 11, 9, 26, 18, 14, 15};
        for (int x : array2) {
            avlTree.insert(x);
        }
        avlTree.inorder(avlTree.root);
        System.out.println();
        System.out.println(avlTree.getHeight(avlTree.root));
        System.out.println(avlTree.isBalanced(avlTree.root));
        System.out.println();
    }
}
