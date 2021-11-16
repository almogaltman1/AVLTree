/**
 * AVLTree
 * <p>
 * An implementation of an AVL Tree with
 * distinct integer keys and info.
 * <p>
 * inv: tree is empty iff root is null
 */

public class AVLTree {

    IAVLNode root;
    IAVLNode minNode;
    IAVLNode maxNode;


    /**
     * public boolean empty()
     * <p>
     * Returns true if and only if the tree is empty.
     */
    public boolean empty() {
        IAVLNode root = getRoot();
        return root == null;
    }

    /**
     * public String search(int k)
     * <p>
     * Returns the info of an item with key k if it exists in the tree.
     * otherwise, returns null.
     */
    public String search(int k) {
        IAVLNode x = getRoot();
        if (x != null) {
            while (x.isRealNode()) {
                if (k == x.getKey()) {
                    return x.getValue();
                } else if (k < x.getKey()) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        }
        return null;
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * Inserts an item with key k and info i to the AVL tree.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        IAVLNode parentToNewNode = treePosition(this.root, k); //will be null iff tree is empty
        IAVLNode newNode = new AVLNode(k, i, parentToNewNode);

        //insert to an empty tree
        if (this.root == null) {
            this.root = newNode;
            this.maxNode = newNode;
            this.minNode = newNode;
            return 0; //no re-balance
        }
        //else, tree is not empty and parentToNewNose is not null
        if (parentToNewNode.getKey() == k) {
            //we already have this key in the tree
            return -1;
        }

        //else - insert (tree is not empty, and key is valid)
        //replace min or max if needed
        if (newNode.getKey() < this.minNode.getKey()) {
            this.minNode = newNode;
        }
        else if (newNode.getKey() > this.maxNode.getKey()) {
            this.maxNode = newNode;
        }
        //chose right or left son
        if (newNode.getKey() < parentToNewNode.getKey()) {
            parentToNewNode.setLeft(newNode);
        }
        else {
            parentToNewNode.setRight(newNode);
        }
        //update size to all parents in the subtree
        IAVLNode parentSizeUpdate = parentToNewNode;
        while (parentSizeUpdate != null) {
            parentSizeUpdate.setSize(parentSizeUpdate.getSize() + 1);
            parentSizeUpdate = parentSizeUpdate.getParent();
        }

        return 420;    // to be replaced by student code
    }

    /**
     * public int delete(int k)
     * <p>
     * Deletes an item with key k from the binary tree, if it is there.
     * The tree must remain valid, i.e. keep its invariants.
     * Returns the number of re-balancing operations, or 0 if no re-balancing operations were necessary.
     * A promotion/rotation counts as one re-balance operation, double-rotation is counted as 2.
     * Returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        return 421;    // to be replaced by student code
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty.
     */
    public String min() {
        if (this.minNode == null) {
            return null;
        }
        return this.minNode.getValue();
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty.
     */
    public String max() {
        if (this.maxNode == null) {
            return null;
        }
        return this.maxNode.getValue();
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public int[] keysToArray() {
        IAVLNode[] inorderNodes = this.inorder();
        int[] keys = new int[inorderNodes.length];
        for (int i = 0; i < inorderNodes.length; i++) {
            keys[i] = inorderNodes[i].getKey();
        }
        return keys;
    }

    /**
     * public String[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public String[] infoToArray() {
        IAVLNode[] inorderNodes = this.inorder();
        String[] info = new String[inorderNodes.length];
        for (int i = 0; i < inorderNodes.length; i++) {
            info[i] = inorderNodes[i].getValue();
        }
        return info;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        return 422; // to be replaced by student code
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public IAVLNode getRoot() {
        return this.root;
    }

    /**
     * public AVLTree[] split(int x)
     * <p>
     * splits the tree into 2 trees according to the key x.
     * Returns an array [t1, t2] with two AVL trees. keys(t1) < x < keys(t2).
     * <p>
     * precondition: search(x) != null (i.e. you can also assume that the tree is not empty)
     * postcondition: none
     */
    public AVLTree[] split(int x) {
        return null;
    }

    /**
     * public int join(IAVLNode x, AVLTree t)
     * <p>
     * joins t and x with the tree.
     * Returns the complexity of the operation (|tree.rank - t.rank| + 1).
     * <p>
     * precondition: keys(t) < x < keys() or keys(t) > x > keys(). t/tree might be empty (rank = -1).
     * postcondition: none
     */
    public int join(IAVLNode x, AVLTree t) {
        return -1;
    }

    //helping functions

    /**
     * public IAVLNode treePosition(int k)
     * <p>
     * Looks for k in the tree. Returns the last node encountered
     * <p>
     * precondition: none
     * postcondition: null iff tree is empty
     */
    public IAVLNode treePosition(IAVLNode x, int k) {
        IAVLNode y = null;
        while (x != null && x.isRealNode()) {
            y = x;
            if (k == x.getKey()) {
                return x;
            }
            x = (k < x.getKey()) ? x.getLeft() : x.getRight();
        }
        return y;
    }

    /**
     * public void rotationRight(IAVLNode node)
     * <p>
     * Conducts a single right rotation on the tree.
     * <p>
     * precondition: there is a real left son for node
     * precondition: node is not null
     * postcondition: the result is a legal BTS after a single left rotation
     */
    public void rotationRight(IAVLNode node) {
        IAVLNode nodeParent = node.getParent();
        IAVLNode leftSon = node.getLeft();
        IAVLNode leftSonsRightSon = node.getLeft().getRight();

        // adjust node parent related fields

        if (nodeParent != null) {
            if (leftSon.getKey() < nodeParent.getKey()) {
                nodeParent.setLeft(leftSon);
            } else {
                nodeParent.setRight(leftSon);
            }
        } else {
            this.root = leftSon;
        }

        //adjust node related fields
        node.setParent(leftSon);
        node.setLeft(leftSonsRightSon);

        // adjust left son related fields
        leftSon.setParent(nodeParent);
        leftSon.setRight(node);

        // adjust left son's right son related fields
        leftSonsRightSon.setParent(node);

    }

    /**
     * public void rotationLeft(IAVLNode node)
     * Conducts a single left rotation on the tree.
     * precondition: there is a real right son for node
     * precondition: node is not null
     * postcondition: the result is a legal BTS after a single left rotation
     */
    public void rotationLeft(IAVLNode node) {
        IAVLNode rightSon = node.getRight(); //this is the node we will do the rotate with
        IAVLNode rightSonsLeftSon = rightSon.getLeft();
        IAVLNode parent = node.getParent();

        //set parents fields according to rightson
        if (parent != null) {
            if (node == parent.getLeft()) {
                parent.setLeft(rightSon);
            } else { //node == parent.getRight()
                parent.setRight(rightSon);
            }
        } else {
            this.root = rightSon;
        }

        //set rightSonsLefttSon to be right son of node
        node.setRight(rightSonsLeftSon);
        rightSonsLeftSon.setParent(node);

        //set node to be left son of rightSon
        rightSon.setLeft(node);
        node.setParent(rightSon);

        //set parent of rightSon to be the parent of node
        rightSon.setParent(parent);

    }

    /**
     * public IAVLNode predecessor(IAVLNode x)
     * <p>
     * finds the previous node by key value.
     * <p>
     * precondition: x in tree
     * precondition: tree is not empty
     * postcondition: null iff min value
     */
    public IAVLNode predecessor(IAVLNode x) {
        if (x.getLeft().isRealNode()) {
            return maxSub(x.getLeft());
        }
        IAVLNode y = x.getParent();
        while (y != null && x == y.getLeft()) {
            x = y;
            y = x.getParent();
        }
        return y;
    }

    /**
     * public IAVLNode successor(IAVLNode x)
     * finds the next node by key value.
     * precondition: x in tree
     * precondition: tree is not empty
     * postcondition: null iff max value
     */
    public IAVLNode successor(IAVLNode x) {
        if (x.getRight().isRealNode()) {
            return minSub(x.getRight());
        }
        IAVLNode y = x.getParent();
        while (y != null && x == y.getRight()) {
            x = y;
            y = x.getParent();
        }
        return y;
    }

    /**
     * public IAVLNode maxSub(IAVLNode x)
     * <p>
     * finds the maximum in subtree
     * <p>
     * precondition: x in tree
     * precondition: tree is not empty
     * postcondition: none
     */
    public IAVLNode maxSub(IAVLNode x) {
        while (x.getRight().isRealNode()) {
            x = x.getRight();
        }
        return x;
    }

    /**
     * public IAVLNode minSub(IAVLNode x)
     * finds the minimum in subtree
     * precondition: x in tree
     * precondition: tree is not empty
     * postcondition: none
     */
    public IAVLNode minSub(IAVLNode x) {
        while (x.getLeft().isRealNode()) {
            x = x.getLeft();
        }
        return x;
    }

    /**
     * public IAVLNode[] inorder()
     * Returns array of the tree nodes, inorder
     * if the tree is empty returns empty array
     * index is for counting the index of array in recursion
     */
    private static int index = 0;
    public IAVLNode[] inorder() {
        IAVLNode[] treeNodes = {};
        if (this.root != null) {
            treeNodes = new IAVLNode[this.root.getSize()];
            inorder_rec(treeNodes, this.root);
        }
        index = 0;
        return treeNodes;
    }

    /**
     * public void inorder_rec(IAVLNode[] arrToFill, IAVLNode x, int index)
     * fills arrToFill, inplace, where the nodes are from tree with the root x
     */
    public void inorder_rec(IAVLNode[] arrToFill, IAVLNode x) {
        if (x.isRealNode()) {
            inorder_rec(arrToFill, x.getLeft());
            arrToFill[index] = x;
            index++;
            inorder_rec(arrToFill, x.getRight());
        }
    }


    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode {
        public int getKey(); // Returns node's key (for virtual node return -1).

        public String getValue(); // Returns node's value [info], for virtual node returns null.

        public void setLeft(IAVLNode node); // Sets left child.

        public IAVLNode getLeft(); // Returns left child, if there is no left child returns null.

        public void setRight(IAVLNode node); // Sets right child.

        public IAVLNode getRight(); // Returns right child, if there is no right child return null.

        public void setParent(IAVLNode node); // Sets parent.

        public IAVLNode getParent(); // Returns the parent, if there is no parent return null.

        public boolean isRealNode(); // Returns True if this is a non-virtual AVL node.

        public void setHeight(int height); // Sets the height of the node.

        public int getHeight(); // Returns the height of the node (-1 for virtual nodes).

        public int getSize();

        public void setSize(int size);
    }

    /**
     * public class AVLNode
     * <p>
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in another file.
     * <p>
     * This class can and MUST be modified (It must implement IAVLNode).
     */
    public class AVLNode implements IAVLNode {

        int key;
        String info;
        boolean realNode;
        IAVLNode parent;
        IAVLNode left;
        IAVLNode right;
        int rank;
        int size;

        /**
         * public AVLNode(IAVLNode parent)
         * virtual leaves constructor
         */
        public AVLNode(IAVLNode parent) {
            this.key = -1;
            this.info = null;
            this.realNode = false;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.rank = -1;
            this.size = 0;
        }

        /**
         * public AVLNode(int key, String info, IAVLNode parent)
         * real nodes constructor
         */
        public AVLNode(int key, String info, IAVLNode parent) {
            this.key = key;
            this.info = info;
            this.realNode = true;
            this.parent = parent;
            this.left = new AVLNode(this);
            this.right = new AVLNode(this);
            this.rank = 0;
            this.size = 1;
        }

        public int getKey() {
            return this.key;
        }

        public String getValue() {
            return this.info;
        }

        public void setLeft(IAVLNode node) {
            this.left = node;
        }

        public IAVLNode getLeft() {
            return this.left;
        }

        public void setRight(IAVLNode node) {
            this.right = node;
        }

        public IAVLNode getRight() {
            return this.right;
        }

        public void setParent(IAVLNode node) {
            this.parent = node;
        }

        public IAVLNode getParent() {
            return this.parent;
        }

        public boolean isRealNode() {
            return this.realNode;
        }

        public void setHeight(int height) {
            this.rank = height;
        }

        public int getHeight() {
            return this.rank;
        }


        public int getSize() {
            return this.size;
        }

        public void setSize(int size){
            this.size = size;
        }
    }

}
  
