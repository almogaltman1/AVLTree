import javax.naming.PartialResultException;
import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {

        AVLTree t = new AVLTree();
        t.insert(20,"20");
        t.insert(19,"19");
        t.insert(18,"18");
        t.insert(17,"17");
        t.insert(16,"16");
        t.insert(15,"15");
        t.insert(14,"14");

        printTree(t.getRoot());


//        AVLTree splitt = new AVLTree();
//        splitt.insert(100, "100");
//        splitt.insert(101, "101");
//        splitt.insert(102,"102");
//        splitt.insert(103,"103");
//        splitt.insert(104,"104");
//        splitt.insert(105,"105");
//        splitt.insert(106,"106");
//        splitt.insert(107,"107");
//        splitt.insert(108,"108");
//       //printTree(splitt.getRoot());
//        AVLTree[] splitarr = splitt.split(108);
//        AVLTree t1 = splitarr[0];
//        AVLTree t2 = splitarr[1];
//        System.out.println("t1 min: " + t1.min());
//        System.out.println("t1 max: " + t1.max());
//        System.out.println("t2 min: " + t2.min());
//        System.out.println("t2 max: " + t2.max());
//        printTree(splitarr[0].getRoot());
//        System.out.println("------------------------------");
//        printTree(splitarr[1].getRoot());

//        AVLTree t1 = new AVLTree();
//        AVLTree t2 = new AVLTree();
////        t1.insert(101,"101");
////        t1.insert(102,"102");
////        t2.insert(105, "105");
////        t2.insert(104, "104");
////        t2.insert(107, "107");
////        t2.insert(106, "106");
////        t2.insert(108, "108");
////        printTree(t1.getRoot());
////        printTree(t2.getRoot());
//        AVLTree x = new AVLTree();
//        x.insert(103,"103");
//        AVLTree.IAVLNode xNode = x.getRoot();
//        t1.join(xNode,t2);
//        printTree(t1.getRoot());



//        AVLTree joint = new AVLTree();
//        AVLTree t = new AVLTree();
//        joint.insert(12,"12");
//        joint.insert(20,"20");
//        joint.insert(15,"15");
//        joint.insert(17,"17");
//        printTree(joint.getRoot());
//        t.insert(10,"10");
//        t.insert(2,"2");
//        System.out.println("----------------------------------------");
//        AVLTree x = new AVLTree();
//        x.insert(11,"11");
//        AVLTree.IAVLNode xNode = x.getRoot();
//        System.out.println("diff:");
//        System.out.println(joint.join(xNode,t));
//        printTree(joint.getRoot());

//
//        AVLTree tree = new AVLTree();
//        if (!tree.empty()) {
//            System.out.println("tree need to be empty");;
//        }
//        int[] values = new int[]{16, 24, 36, 19, 44, 28, 61, 74, 83, 64, 52, 65, 86, 93, 88};
//        for (int val : values) {
//            tree.insert(val, "" + val);
//        }
//        if (!tree.min().equals("16")) {
//            System.out.println("min is not 16");
//        }
//        if (!tree.max().equals("93")) {
//            System.out.println("max is not 93");
//        }
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after inserts");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after inserts");
//        }
//        tree.delete(88);
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 88");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 88");
//        }
//        if (tree.search(88) != null) {
//            System.out.println("88 still in tree after delete 88");
//        }
//
//        tree.delete(19);
//
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 19");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 19");
//        }
//        if (tree.search(19) != null) {
//            System.out.println("19 still in tree after delete 19");
//        }
//
//        tree.delete(16);
//
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 16");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 16");
//        }
//        if (tree.search(16) != null) {
//            System.out.println("16 still in tree after delete 16");
//        }
//
//        tree.delete(28);
//
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 28");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 28");
//        }
//        if (tree.search(28) != null) {
//            System.out.println("28 still in tree after delete 28");
//        }
//        tree.delete(24);
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 24");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 24");
//        }
//        if (tree.search(24) != null) {
//            System.out.println("24 still in tree after delete 24");
//        }
//
//        tree.delete(36);
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 36");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 36");
//        }
//        if (tree.search(36) != null) {
//            System.out.println("36 still in tree after delete 36");
//        }
//        tree.delete(52);
//
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 52");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 52");
//        }
//        if (tree.search(52) != null) {
//            System.out.println("52 still in tree after delete 52");
//        }
//        tree.delete(93);
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 93");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 93");
//        }
//        if (tree.search(93) != null) {
//            System.out.println("93 still in tree after delete 93");
//        }
//        tree.delete(86);
//
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 86");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 86");
//        }
//        if (tree.search(86) != null) {
//            System.out.println("86 still in tree after delete 86");
//        }
//
//        tree.delete(83);
//        if (!checkBalanceOfTree(tree.getRoot())) {
//            System.out.println("not balance after delete 83");
//        }
//        if (!checkOrderingOfTree(tree.getRoot())) {
//            System.out.println("not order after delete 83");
//        }
//        if (tree.search(83) != null) {
//            System.out.println("83 still in tree after delete 83");
//        }
//        System.out.println("done all :)");

//        AVLTree avlTree = new AVLTree();
//        for (int i = 0; i < 100; i++) {
//            avlTree.insert(i, "num" + i);
//        }
//        System.out.println("size need to be 100: " + avlTree.size());
//        for (int i = 0; i < 50; i++) {
//            if (i==1){
//                printTree(avlTree.getRoot());
//            }
//            avlTree.delete(i);
//            System.out.println("delete " + i);
//        }
//        System.out.println("size need to be 50" + avlTree.size());
//        for (int i = 0; i < 25; i++) {
//            avlTree.insert(i, "num" + i);
//        }
//        System.out.println("size need to be 75" + avlTree.size());

        //try insert with rebalnce:
        //AVLTree t = new AVLTree();

//        for (int i = 0; i < 1000; i++) {
//                t.insert(i, "num" + i);
//            }
//        System.out.println(t.size());

//        t.insert(5, "5");
//        t.insert(20, "20");
//        t.insert(3, "3");
//        t.insert(2, "2");
//        t.insert(1, "1");
//        t.insert(34, "34");
//        t.insert(25, "25");
//        t.insert(22, "22");
//        printTree(t.getRoot());



        // only left subtrees
//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
//        root.setHeight(2);
//
//        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
//        root.setLeft(node1);
//        node1.setHeight(1);
//
//        AVLTree.IAVLNode node2 = t.new AVLNode(1, "1", node1);
//        node1.setLeft(node2);
//        node2.setHeight(0);
//
//        t.minNode=node2;
//        t.maxNode=root;
//        t.root=root;

        // only right subtree
//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
//        root.setSize(3);
//
//        AVLTree.IAVLNode node1 = t.new AVLNode(5, "5", root);
//        root.setRight(node1);
//        node1.setSize(2);
//
//        AVLTree.IAVLNode node2 = t.new AVLNode(7, "7", node1);
//        node1.setRight(node2);
//        node2.setSize(1);
//
//        t.minNode=root;
//        t.maxNode=node2;
//        t.root=root;


        // full tree
//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
//        root.setHeight(2);
//        root.setSize(7);
//
//        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
//        root.setLeft(node1);
//        node1.setHeight(1);
//        node1.setSize(3);
//
//        AVLTree.IAVLNode node2 = t.new AVLNode(6, "6", root);
//        root.setRight(node2);
//        node2.setHeight(1);
//        node2.setSize(3);
//
//        AVLTree.IAVLNode node3 = t.new AVLNode(1, "1", node1);
//        node1.setLeft(node3);
//        node3.setSize(1);
//
//        AVLTree.IAVLNode node4 = t.new AVLNode(3, "3", node1);
//        node1.setRight(node4);
//        node3.setSize(1);
//
//        AVLTree.IAVLNode node5 = t.new AVLNode(5, "5", node2);
//        node2.setLeft(node5);
//        node3.setSize(1);
//
//        AVLTree.IAVLNode node6 = t.new AVLNode(7, "7", node2);
//        node2.setRight(node6);
//        node3.setSize(1);
//        printTree(t.getRoot());



        // test case 1
//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
//        root.setHeight(2);
//        root.setSize(7);
//
//        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
//        root.setLeft(node1);
//        node1.setHeight(1);
//        node1.setSize(3);
//        t.minNode=node1;
//        t.maxNode=root;
//        t.root=root;
//        System.out.println("expected 1: actual value "+t.delete(2));

        // test case 2

//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
//        root.setHeight(2);
//        root.setSize(7);
//
//        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
//        root.setLeft(node1);
//        node1.setHeight(1);
//        node1.setSize(3);
//
//        AVLTree.IAVLNode node2 = t.new AVLNode(6, "6", root);
//        root.setRight(node2);
//        node2.setHeight(1);
//        node2.setSize(3);
//
//        AVLTree.IAVLNode node3 = t.new AVLNode(1, "1", node1);
//        node1.setLeft(node3);
//        node3.setSize(1);
//
//        t.minNode=node3;
//        t.maxNode=node2;
//        t.root=root;
//
//        System.out.println("expected 1: actual value "+t.delete(1));
//        printTree(t.getRoot());







        //almog insert test:
//        AVLTree tInsert = new AVLTree();
//        tInsert.insert(25,"25");
//        System.out.println("need to be 25,25,25");
//        System.out.println(tInsert.getRoot().getKey());
//        System.out.println(tInsert.min());
//        System.out.println(tInsert.max());
//        System.out.println("need to be 1");
//        AVLTree.IAVLNode node25 = tInsert.treePosition(tInsert.root, 25);
//        System.out.println(node25.getSize());
//        tInsert.insert(9,"9");
//        System.out.println("need to be 25, 9, 25");
//        System.out.println(tInsert.getRoot().getKey());
//        System.out.println(tInsert.min());
//        System.out.println(tInsert.max());
//        System.out.println("need to be 2");
//        System.out.println(node25.getSize());
//        System.out.println("need to be -1");
//        System.out.println(tInsert.insert(9,"9"));
//        tInsert.insert(33,"33");
//        System.out.println("need to be 25, 9, 33");
//        System.out.println(tInsert.getRoot().getKey());
//        System.out.println(tInsert.min());
//        System.out.println(tInsert.max());
//        System.out.println("need to be 3");
//        System.out.println(node25.getSize());
//        tInsert.insert(5,"5");
//        System.out.println("need to be 4");
//        System.out.println(node25.getSize());
//        tInsert.insert(59,"59");
//        tInsert.insert(29,"29");
//        tInsert.insert(13,"13");
//        tInsert.insert(31,"31");
//        tInsert.insert(2,"2");
//        tInsert.insert(11,"11");
//        tInsert.insert(20,"20");
//        tInsert.insert(23,"23");
//        tInsert.insert(18,"18");
//        System.out.println("need to be 2");
//        AVLTree.IAVLNode node13 = tInsert.treePosition(tInsert.root, 13);
//        System.out.println(node13.getSize());
//        System.out.println("need to be 59");
//        System.out.println(tInsert.max());
//        System.out.println("need to be 2");
//        System.out.println(tInsert.min());
//
//        printTree(tInsert.getRoot());




        //old tests:
//        AVLTree t = new AVLTree();
//        AVLTree.IAVLNode root = t.new AVLNode(10, "10", null);
//        AVLTree.IAVLNode node1 = t.new AVLNode(6, "6", root);
//        root.setLeft(node1);
//
//        AVLTree.IAVLNode node2 = t.new AVLNode(16, "16", root);
//        root.setRight(node2);
//
//        AVLTree.IAVLNode node3 = t.new AVLNode(3, "3", node1);
//        node1.setLeft(node3);
//
//        AVLTree.IAVLNode node4 = t.new AVLNode(7, "7", node1);
//        node1.setRight(node4);

//        System.out.println(t.predecessor(node2).getKey());
//        System.out.println(t.successor(node4).getKey());
//
//        t.root=root;
//        System.out.println(t.search(16));
//        printTree(root);
//
//        t.rotationRight(node1);
//        root = t.getRoot();
//        printTree(root);

        //build new tree for the rotation left test
//        AVLTree t2 = new AVLTree();
//        AVLTree.IAVLNode root2 = t2.new AVLNode(6, "6", null);
//        AVLTree.IAVLNode node12 = t2.new AVLNode(10, "10", root2);
//        root2.setRight(node12);
//
//        AVLTree.IAVLNode node22 = t2.new AVLNode(3, "3", root2);
//        root2.setLeft(node22);
//
//        AVLTree.IAVLNode node32 = t2.new AVLNode(16, "16", node12);
//        node12.setRight(node32);
//
//        AVLTree.IAVLNode node42 = t2.new AVLNode(7, "7", node12);
//        node12.setLeft(node42);
//
//        t2.root = root2;
//
//        AVLTree t2 = new AVLTree();
//        t2.insert(7,"blah");
//        t2.insert(3,"bloop");
//        t2.insert(10,"blap");
//        t2.insert(4,"blip");
//        int[] keys = t2.keysToArray();
//        String[] info = t2.infoToArray();
//        System.out.println(Arrays.toString(keys));
//        System.out.println(Arrays.toString(info));
//
//        printTree(t2.getRoot());



    }

    static boolean checkOrderingOfTree(AVLTree.IAVLNode current) {
        if (current.getLeft().isRealNode()) {
            if (Integer.parseInt(current.getLeft().getValue()) > Integer.parseInt(current.getValue()))
                return false;
            else
                return checkOrderingOfTree(current.getLeft());
        } else if (current.getRight().isRealNode()) {
            if (Integer.parseInt(current.getRight().getValue()) < Integer.parseInt(current.getValue()))
                return false;
            else
                return checkOrderingOfTree(current.getRight());
        } else if (!current.getLeft().isRealNode() && !current.getRight().isRealNode())
            return true;

        return true;
    }
    static boolean checkBalanceOfTree(AVLTree.IAVLNode current) {
        boolean balancedRight = true, balancedLeft = true;
        int leftHeight = 0, rightHeight = 0;
        if (current.getRight() != null) {
            balancedRight = checkBalanceOfTree(current.getRight());
            rightHeight = getDepth(current.getRight());
        }
        if (current.getLeft() != null) {
            balancedLeft = checkBalanceOfTree(current.getLeft());
            leftHeight = getDepth(current.getLeft());
        }

        return balancedLeft && balancedRight && Math.abs(leftHeight - rightHeight) < 2;
    }
    static int getDepth(AVLTree.IAVLNode n) {
        int leftHeight = 0, rightHeight = 0;

        if (n.getRight() != null)
            rightHeight = getDepth(n.getRight());
        if (n.getLeft() != null)
            leftHeight = getDepth(n.getLeft());

        return Math.max(rightHeight, leftHeight) + 1;
    }


    static final int COUNT = 10;

    static void printTreeUtil(AVLTree.IAVLNode root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += COUNT;

        // Process right child first
        printTreeUtil(root.getRight(), space);

        // Print current node after space
        // count
        System.out.print("\n");
        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getKey() + ":"+root.getHeight()+ ":::"+root.getSize() + "\n");

        // Process left child
        printTreeUtil(root.getLeft(), space);
    }

    // Wrapper over print2DUtil()
    static void printTree(AVLTree.IAVLNode root) {
        // Pass initial space count as 0
        printTreeUtil(root, 0);
    }

}
