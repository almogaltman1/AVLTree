//import java.lang.Math;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.Arrays;
//import java.util.Random;
//
//public class AVLTreeSectionB extends AVLTree {
//
//    private int cntSearch = 0;
//    @Override
//    /**
//     * protected IAVLNode treePosition(IAVLNode x, int k)
//     * <p>
//     * Looks for k in the tree. Returns the last node encountered
//     * <p>
//     * precondition: none
//     * postcondition: null iff tree is empty
//     * time complexity - O(logn)
//     */
//    protected IAVLNode treePosition(IAVLNode x, int k) {
//        cntSearch++;
//        x = this.maxNode;
//        IAVLNode y = null;
//
//        //empty tree
//        if (x == null) {
//            return y;
//        }
//        //new max
//        if (k > x.getKey()){
//            return x;
//        }
//
//        //go up
//        while (x.getParent() != null && (k > x.getKey() || k<x.getParent().getKey()) && k < x.getKey()) {
//            cntSearch++;
//            x = x.getParent();
//        }
//        //go down
//        while (x != null && x.isRealNode()) {
//            cntSearch++;
//            y = x;
//            if (k == x.getKey()) {
//                return x;
//            }
//            x = (k < x.getKey()) ? x.getLeft() : x.getRight();
//        }
//        return y;
//    }
//
//
//    /**
//     * public double[] split2(int x)
//     * <p>
//     * splits the tree into 2 trees according to the key x.
//     * Returns an array with avg join cost and max join cost
//     * <p>
//     * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
//     * postcondition: none
//     * time complexity - O(logn)
//     */
////    public double[] split2(int x) {
////        //min and max of subtrees will not br correct until end
////        AVLTree t1 = new AVLTree();
////        AVLTree t2 = new AVLTree();
////        AVLTree temp = new AVLTree();
////        int cntJoin = 0;
////        int sumJoin = 0;
////        int maxJoin = 0;
////        int tempJoin = 0;
////
////        IAVLNode nodex = treePosition(this.getRoot(), x); //find the node with key = x
////        IAVLNode sonToTree;
////        //insert children of x to t1 and t2
////        if (nodex.getLeft().isRealNode())
////        {
////            IAVLNode leftx = nodex.getLeft();
////            t1.fromNodeToTree(leftx);
////        }
////        if (nodex.getRight().isRealNode())
////        {
////            IAVLNode rightx = nodex.getRight();
////            t2.fromNodeToTree(rightx);
////        }
////
////        //split the parent of x and upwards
////        nodex = nodex.getParent();
////        IAVLNode parentx;
////        while (nodex != null) {
////            parentx = nodex.getParent(); //save pointer to parent of nodex
////            if (nodex.getKey() < x) //nodex and his left child belong to t1
////            {
////                sonToTree = nodex.getLeft();
////                if (sonToTree.isRealNode()){
////                    temp.fromNodeToTree(sonToTree); //turn temp to tree with sonToTree in his root
////                }
////                //change nodex to be isolated node
////                nodex.setParent(null);
////                nodex.setRight(new AVLNode(nodex));
////                nodex.setLeft(new AVLNode(nodex));
////                nodex.setHeight(0);
////                nodex.setSize(1);
////                tempJoin = t1.join(nodex,temp);
////                sumJoin += tempJoin;
////                cntJoin++;
////                if (tempJoin > maxJoin){
////                    maxJoin = tempJoin;
////                }
////            }
////            else { //nodex and his right child belong to t2
////                sonToTree = nodex.getRight();
////                if (sonToTree.isRealNode()){
////                    temp.fromNodeToTree(sonToTree); //turn temp to tree with sonToTree in his root
////                }
////                //change nodex to be isolated node
////                nodex.setParent(null);
////                nodex.setRight(new AVLNode(nodex));
////                nodex.setLeft(new AVLNode(nodex));
////                nodex.setHeight(0);
////                nodex.setSize(1);
////                tempJoin = t2.join(nodex, temp);
////                sumJoin += tempJoin;
////                cntJoin++;
////                if (tempJoin > maxJoin){
////                    maxJoin = tempJoin;
////                }
////            }
////            nodex = parentx;
////        }
////        //now we have t1 and t2, but min and max are wrong
////        if (!t1.empty()){
////            t1.updateMinMaxForJoin();
////        }
////        if (!t2.empty()){
////            t2.updateMinMaxForJoin();
////        }
////
////        double avgJoin = (double)sumJoin/cntJoin;
////        double[] splited = {avgJoin, maxJoin};
////        return splited;
////    }
////
////    private int maxSub(){
////        IAVLNode x = this.getRoot();
////        if (x.getLeft().isRealNode()){
////            x = x.getLeft();
////            while (x.getRight().isRealNode()){
////                x = x.getRight();
////            }
////        }
////       return x.getKey();
////    }
//
//
//
//
//
//    public static void main(String[] args){
//        //Q1
//        int i = 1; //serial number
//        AVLTreeSectionB tOrder = new AVLTreeSectionB();
//        AVLTreeSectionB tRandom = new AVLTreeSectionB();
//        int n = (int) (1000 * Math.pow(2, i));
//        List<Integer> li = new ArrayList<>();
//
//        //insert to order
//        for (int j = n; j > 0; j--) {
//            li.add(j); //for later
//            tOrder.insert(j, String.valueOf(j));
//        }
//
//        //insert to random
//        List<Integer> liRand = new ArrayList<>(li);
//        Collections.shuffle(liRand);
//        for (int j = 0; j < n; j++) {
//            tRandom.insert(liRand.get(j), String.valueOf(liRand.get(j)));
//        }
//
//        int cntOrderChanges = 0;
//        int cntRandomChanges = 0;
//
//        //counting changes
//        for (int k = 0; k < n; k++) {
//            for (int l = k + 1; l < n; l++) {
//                if (li.get(k) > li.get(l)) {
//                    cntOrderChanges++;
//                }
//                if (liRand.get(k) > liRand.get(l)) {
//                    cntRandomChanges++;
//                }
//            }
//        }
//
//        System.out.println(cntOrderChanges);
//        System.out.println(tOrder.cntSearch);
//        System.out.println(cntRandomChanges);
//        System.out.println(tRandom.cntSearch);
//
//        //Q2
////        //for (int i=1; i<=10; i++) {
////            int i = 1; //serial number
////            AVLTreeSectionB tRandom = new AVLTreeSectionB();
////            AVLTreeSectionB tMax = new AVLTreeSectionB();
////            int n = (int) (1000 * Math.pow(2, i));
////            List<Integer> li = new ArrayList<>();
////
////            //insert to list
////            for (int j = 1; j <= n; j++) {
////                li.add(j);
////            }
////            List<Integer> liRand = new ArrayList<>(li);
////            Collections.shuffle(liRand);
////
////            //insert to trees
////            for (int j = 0; j < n; j++) {
////                tRandom.insert(liRand.get(j), String.valueOf(liRand.get(j)));
////                tMax.insert(liRand.get(j), String.valueOf(liRand.get(j)));
////            }
////
////            Random r = new Random();
////            int rndIndex = r.nextInt(n + 1) + 1; //random number from 1 to n
////
////            System.out.println("height " + tMax.getRoot().getHeight());
////        System.out.println("height right son " + tMax.getRoot().getRight().getHeight());
////            double[] rndJoin = tRandom.split2(rndIndex);
////            double[] maxJoin = tMax.split2(tMax.maxSub());
////
////            System.out.println("---------------");
////            System.out.println("i is: " + i);
////            System.out.println(rndJoin[0]);
////            System.out.println(rndJoin[1]);
////            System.out.println(maxJoin[0]);
////            System.out.println(maxJoin[1]);
////        //}
//
//
//
//
//
////       Q1 tests
//       AVLTreeSectionB t = new AVLTreeSectionB();
////        t.insert(5,"5");
////        t.insert(4,"4");
////        t.insert(10,"10");
////        t.insert(2,"2");
////        t.insert(8,"8");
////        t.insert(20,"20");
////        t.insert(15,"15");
////        t.insert(14,"14");
////        t.insert(1,"1");
////        t.insert(3,"3");
////        t.insert(30, "30");
////        t.insert(7,"7");
////        t.insert(13,"13");
////        Tester.printTree(t.getRoot());
////        System.out.println(t.maxSub());
//
////        System.out.println(t.treePosition(t.maxNode, 8).getKey());
////        System.out.println(t.treePosition(t.maxNode, 10).getKey());
////        System.out.println(t.treePosition(t.maxNode, 2).getKey());
////        System.out.println(t.treePosition(t.maxNode, 4).getKey());
////        System.out.println(t.treePosition(t.maxNode, 5).getKey());
////        System.out.println(t.treePosition(t.maxNode, 14).getKey());
////        System.out.println(t.treePosition(t.maxNode, 9).getKey()); //not in tree
////        System.out.println(t.treePosition(t.maxNode, 20).getKey());
////        System.out.println(t.treePosition(t.maxNode, 15).getKey());
////        System.out.println(t.cntSearch);
//
////        AVLTreeSectionB empty = new AVLTreeSectionB();
////        System.out.println(empty.treePosition(empty.maxNode, 5) == null);
////        System.out.println(empty.cntSearch);
//
//    }
//}