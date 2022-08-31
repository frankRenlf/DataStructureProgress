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
        avlTree.insert(60);
        avlTree.insert(70);
        avlTree.insert(30);
        avlTree.insert(10);
        avlTree.insert(5);
        avlTree.insert(4);
        avlTree.insert(3);
        System.out.println();
    }
}
