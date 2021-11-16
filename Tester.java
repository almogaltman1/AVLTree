public class Tester {
    public static void main(String[] args) {
        AVLTree t = new AVLTree();
        AVLTree.IAVLNode root = t.new AVLNode(4, "4", null);
        root.setHeight(2);

        AVLTree.IAVLNode node1 = t.new AVLNode(2, "2", root);
        root.setLeft(node1);
        node1.setHeight(1);

        AVLTree.IAVLNode node2 = t.new AVLNode(6, "6", root);
        root.setRight(node2);
        node2.setHeight(1);

        AVLTree.IAVLNode node3 = t.new AVLNode(1, "1", node1);
        node1.setLeft(node3);

        AVLTree.IAVLNode node4 = t.new AVLNode(3, "3", node1);
        node1.setRight(node4);

        AVLTree.IAVLNode node5 = t.new AVLNode(5, "5", node2);
        node2.setLeft(node5);

        AVLTree.IAVLNode node6 = t.new AVLNode(7, "7", node2);
        node2.setRight(node6);

        t.root=root;
        printTree(root);


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
