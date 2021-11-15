public class Tester {
    public static void main (String[]args){
        AVLTree t = new AVLTree();
        AVLTree.AVLNode root = t.new AVLNode(10,"root",null);
        printTree(root);

    }

    static final int COUNT = 10;

    static void printTreeUtil(AVLTree.IAVLNode root, int space)
    {
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
        System.out.print(root.getKey() + "\n");

        // Process left child
        printTreeUtil(root.getLeft(), space);
    }

    // Wrapper over print2DUtil()
    static void printTree(AVLTree.IAVLNode root)
    {
        // Pass initial space count as 0
        printTreeUtil(root, 0);
    }

}
