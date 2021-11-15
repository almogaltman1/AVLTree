public class Tester {
    public static void main (String[]args){
        AVLTree t = new AVLTree();
        AVLTree.AVLNode root = t.new AVLNode(10,"11",null);
        t.minNode=root;
        System.out.println(t.min());
    }

}
