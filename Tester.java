public class Tester {
    public static void main (String[]args){
        AVLTree t = new AVLTree();
        AVLTree.AVLNode root = t.new AVLNode(10,"root",null);
        AVLTree.AVLNode node1 = t.new AVLNode(9,"node1",root);
        root.setLeft(node1);

        AVLTree.AVLNode node2 = t.new AVLNode(16,"node1",root);
        root.setRight(node2);


        AVLTree.IAVLNode a =  t.treePosition(root,10);
        System.out.println(a.getKey());

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
        System.out.print(root.getKey()+":"+root.getValue() + "\n");

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
