import javax.naming.PartialResultException;

public class Tester {
    public static void main(String[] args) {

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

        AVLTree t = new AVLTree();
        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
        root.setHeight(2);
        root.setSize(7);

        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
        root.setLeft(node1);
        node1.setHeight(1);
        node1.setSize(3);

        AVLTree.IAVLNode node2 = t.new AVLNode(6, "6", root);
        root.setRight(node2);
        node2.setHeight(1);
        node2.setSize(3);

        AVLTree.IAVLNode node3 = t.new AVLNode(1, "1", node1);
        node1.setLeft(node3);
        node3.setSize(1);

        t.minNode=node3;
        t.maxNode=node2;
        t.root=root;

        System.out.println("expected 1: actual value "+t.delete(1));
//        printTree(t.getRoot());

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
        System.out.print(root.getKey() + ":"+root.getHeight()+ "\n");

        // Process left child
        printTreeUtil(root.getLeft(), space);
    }

    // Wrapper over print2DUtil()
    static void printTree(AVLTree.IAVLNode root) {
        // Pass initial space count as 0
        printTreeUtil(root, 0);
    }

}
