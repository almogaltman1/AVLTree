import java.util.Arrays;

public class Tester {
    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        AVLTree.IAVLNode root = t.new AVLNode(10, "10", null);
        AVLTree.IAVLNode node1 = t.new AVLNode(6, "6", root);
        root.setLeft(node1);

        AVLTree.IAVLNode node2 = t.new AVLNode(16, "16", root);
        root.setRight(node2);

        AVLTree.IAVLNode node3 = t.new AVLNode(3, "3", node1);
        node1.setLeft(node3);

        AVLTree.IAVLNode node4 = t.new AVLNode(7, "7", node1);
        node1.setRight(node4);

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

        //build new tree for the rotation right test
        AVLTree t2 = new AVLTree();
        AVLTree.IAVLNode root2 = t2.new AVLNode(6, "6", null);
        AVLTree.IAVLNode node12 = t2.new AVLNode(10, "10", root2);
        root2.setRight(node12);

        AVLTree.IAVLNode node22 = t2.new AVLNode(3, "3", root2);
        root2.setLeft(node22);

        AVLTree.IAVLNode node32 = t2.new AVLNode(16, "16", node12);
        node12.setRight(node32);

        AVLTree.IAVLNode node42 = t2.new AVLNode(7, "7", node12);
        node12.setLeft(node42);

        t2.root = root2;

        //will not  really work until set size be good
//        t2.getRoot().setSize(5);
//        node12.setSize(3);
//        node22.setSize(1);
//        node32.setSize(1);
//        node42.setSize(1);
//        int[] keys = t2.keysToArray();
//        String[] info = t2.infoToArray();
//        System.out.println(Arrays.toString(keys));
//        System.out.println(Arrays.toString(info));

//        t2.rotationLeft(root2);
//        root2 = t2.getRoot();
//
//        printTree(root2);

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
        System.out.print(root.getKey() + ":" + root.getValue() + "\n");

        // Process left child
        printTreeUtil(root.getLeft(), space);
    }

    // Wrapper over print2DUtil()
    static void printTree(AVLTree.IAVLNode root) {
        // Pass initial space count as 0
        printTreeUtil(root, 0);
    }

}
