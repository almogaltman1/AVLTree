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
        //else, tree is not empty and parentToNewNode is not null
        if (parentToNewNode.getKey() == k) {
            //we already have this key in the tree
            return -1;
        }

        //else - insert (tree is not empty, and key is valid)
        IAVLNode nodeForRebalance = insertBeforeRebalance(newNode, parentToNewNode);

        //rebalnce
        return rebalanceAfterInsertOrJoin(nodeForRebalance);
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
        // empty tree
        if (this.empty()) {
            return -1;
        }
        IAVLNode nodeToDelete = treePosition(this.root, k);
        // node not in tree
        if (k != nodeToDelete.getKey()) {
            return -1;
        }

        // update min and max fields in the tree
        updateMinMaxFieldsInDelete(nodeToDelete);

        // delete the node and find from which node to start the rebalance from
        IAVLNode nodeForRebalance = deleteBeforeRebalance(nodeToDelete);

        return rebalanceAfterDelete(nodeForRebalance);
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
        if (this.root == null) {
            return 0;
        }
        return this.root.getSize();
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
        //min and max of subtrees will not br correct until end
        AVLTree t1 = new AVLTree();
        AVLTree t2 = new AVLTree();
        AVLTree temp = new AVLTree();
        IAVLNode nodex = treePosition(this.getRoot(), x);
        IAVLNode sonToTree;
        if (nodex.getLeft().isRealNode())
        {
            IAVLNode leftx = nodex.getLeft();
            t1.fromNodeToTree(leftx);
        }
        if (nodex.getRight().isRealNode())
        {
            IAVLNode rightx = nodex.getRight();
            t2.fromNodeToTree(rightx);
        }
        nodex = nodex.getParent();
        IAVLNode parentx;
        while (nodex != null) {
            parentx = nodex.getParent();
            if (nodex.getKey() < x)
            {
                sonToTree = nodex.getLeft();
                if (sonToTree.isRealNode()){
                    temp.fromNodeToTree(sonToTree);
                }
                nodex.setParent(null);
                nodex.setRight(new AVLNode(nodex));
                nodex.setLeft(new AVLNode(nodex));
                nodex.setHeight(0);
                nodex.setSize(1);
                t1.join(nodex,temp);
            }
            else { //nodex is right child
                sonToTree = nodex.getRight();
                if (sonToTree.isRealNode()){
                    temp.fromNodeToTree(sonToTree);
                }
                nodex.setParent(null);
                nodex.setRight(new AVLNode(nodex));
                nodex.setLeft(new AVLNode(nodex));
                nodex.setHeight(0);
                nodex.setSize(1);
                t2.join(nodex, temp);
            }
            nodex = parentx;
        }
        //now we have t1 and t2, but min and max are wrong
        if (!t1.empty()){
            t1.updateMinMaxForJoin();
        }
        if (!t2.empty()){
            t2.updateMinMaxForJoin();
        }
        AVLTree[] splited = {t1,t2};
        return splited;
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
        if (this.empty() || t.empty()){
            int ret = 1;
            if (this.empty())
            {
                if (!t.empty()){
                    ret += t.getRoot().getHeight() + 1;
                    this.root = t.getRoot();
                    this.minNode = t.minNode;
                    this.maxNode = t.maxNode;
                }
            }
            else if (t.empty()){
                ret += this.getRoot().getHeight() + 1;
            }
            this.insert(x.getKey(), x.getValue());
            return ret;
        }
        AVLTree t1, t2;
        IAVLNode min, max, newRoot;
        if (t.getRoot().getHeight() < this.getRoot().getHeight()){
            t1 = t;
            t2 = this;
            newRoot = this.getRoot();
        }
        else {
            t1 = this;
            t2 = t;
            newRoot = t.getRoot();
        }
        int low = t1.getRoot().getHeight();
        int high = t2.getRoot().getHeight();
        IAVLNode a = t1.getRoot();
        IAVLNode b = t2.getRoot();
        boolean goLeft = b.getKey() > x.getKey();
        while (b.getHeight() > low) {
            if (goLeft){
                b = b.getLeft();
            }
            else {
                b = b.getRight();
            }

        }
        a.setParent(x);
        IAVLNode c = b.getParent();
        b.setParent(x);
        if (a.getKey() < b.getKey())
        {
            x.setLeft(a);
            x.setRight(b);
            min = t1.minNode;
            max = t2.maxNode;
        }
        else
        {
            x.setLeft(b);
            x.setRight(a);
            min = t2.minNode;
            max = t1.maxNode;
        }
        x.setHeight(a.getHeight() + 1);
        x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
        this.minNode = min;
        this.maxNode = max;
        if (c == null) //same heights
        {
            x.setParent(null);
            this.root = x;
        }
        else {
            if (x.getKey() < c.getKey()){
                c.setLeft(x);
            }
            else {
                c.setRight(x);
            }
            x.setParent(c);
            this.root = newRoot;
            rebalanceAfterInsertOrJoin(c);
        }
        return high - low + 1;
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
     * <p>
     * Conducts a single left rotation on the tree.
     * <p>
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
     * <p>
     * finds the next node by key value.
     * <p>
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
     * <p>
     * finds the minimum in subtree
     * <p>
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
     * <p>
     * Returns array of the tree nodes, inorder
     * if the tree is empty returns empty array
     */

    public IAVLNode[] inorder() {
        IAVLNode[] treeNodes = {};
        if (this.root != null) {
            treeNodes = new IAVLNode[this.root.getSize()];
            int[] index = {0};
            inorder_rec(treeNodes, this.root, index);
        }
        return treeNodes;
    }

    /**
     * public void inorder_rec(IAVLNode[] arrToFill, IAVLNode x, int[] index)
     * <p>
     * fills arrToFill, inplace, where the nodes are from tree with the root x
     * index array is to count the index to insert in the array in the recursion
     */
    public void inorder_rec(IAVLNode[] arrToFill, IAVLNode x, int[] index) {
        if (x.isRealNode()) {
            inorder_rec(arrToFill, x.getLeft(), index);
            arrToFill[index[0]] = x;
            index[0] += 1;
            inorder_rec(arrToFill, x.getRight(), index);
        }
    }


    //helping function for both delete and insert

    /**
     * public void updateSizeForAncestors(IAVLNode node)
     * <p>
     * updated all sized of ancestors after a deletion was made
     * precondition: none
     */
    public void updateSizeForAncestors(IAVLNode node) {
        while(node != null){
            node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
            node = node.getParent();
        }
    }


    //helping insert functions

    /**
     * public IAVLNode insertBeforeRebalance(IAVLNode node, IAVLNode parentToNewNode)
     * <p>
     * Inserts node to the tree and returns the node from which we need to start the rebalancing
     * <p>
     * precondition: tree is not empty and node.getkey() is not in tree
     */
    public IAVLNode insertBeforeRebalance(IAVLNode newNode, IAVLNode parentToNewNode) {
        //replace min or max if needed
        if (newNode.getKey() < this.minNode.getKey()) {
            this.minNode = newNode;
        } else if (newNode.getKey() > this.maxNode.getKey()) {
            this.maxNode = newNode;
        }
        //chose right or left son, no need to update parent of newNode (we do it in the initial creation of the node)
        if (newNode.getKey() < parentToNewNode.getKey()) {
            parentToNewNode.setLeft(newNode);
        } else {
            parentToNewNode.setRight(newNode);
        }
        return parentToNewNode; //from here we may need rebalance
    }

    /**
     * public int rebalanceAfterInsert(IAVLNode node)
     * <p>
     * rebalances the tree after an insert of a node
     */
    public int rebalanceAfterInsertOrJoin(IAVLNode node) {
        int numOperations = 0;
        int[] diffs = diffBetweenParentAndChildes(node);
        int diffLeft = diffs[0];
        int diffRight = diffs[1];
        while (node != null && (diffLeft == 0 || diffRight == 0)) {

            if ((diffLeft == 0 && diffRight == 1) || (diffLeft == 1 && diffRight == 0)) {
                //case 1 or case 1 symmetry:
                node = case1InsertRebalance(node);
                numOperations += 1;
            } else if ((diffLeft == 0 && diffRight == 2)) {
                IAVLNode leftSon = node.getLeft();
                int leftLeftDiff = leftSon.getHeight() - leftSon.getLeft().getHeight();
                int leftRightDiff = leftSon.getHeight() - leftSon.getRight().getHeight();

                if (leftLeftDiff == 1 && leftRightDiff == 2) {
                    //case 2 isLeft == true
                    node = case2InsertRebalance(node, true);
                    numOperations += 2;
                } else if (leftLeftDiff == 2 && leftRightDiff == 1) {
                    //case 3 isLeft == true
                    node = case3InsertRebalance(node, true);
                    numOperations += 5;
                }
                //for join
                else if (leftLeftDiff == 1 && leftRightDiff == 1) {
                    //case 4 isLeft == true
                    node = case4JoinRebalance(node, true);
                    numOperations += 2; //dosen't matter
                }
            } else if (diffLeft == 2 && diffRight == 0) {
                IAVLNode rightSon = node.getRight();
                int rightLeftDiff = rightSon.getHeight() - rightSon.getLeft().getHeight();
                int rightRightDiff = rightSon.getHeight() - rightSon.getRight().getHeight();

                if (rightLeftDiff == 2 && rightRightDiff == 1) {
                    //case 2 isLeft == false
                    node = case2InsertRebalance(node, false);
                    numOperations += 2;
                } else if (rightLeftDiff == 1 && rightRightDiff == 2) {
                    //case 3 isLeft == false
                    node = case3InsertRebalance(node, false);
                    numOperations += 5;
                }
                //for join
                else if (rightLeftDiff == 1 && rightRightDiff == 1) {
                    //case 4 isLeft == false
                    node = case4JoinRebalance(node, false);
                    numOperations += 2; //dosen't matter
                }
            }
            //update before checking the while condition
            if (node != null) {
                diffs = diffBetweenParentAndChildes(node);
                diffLeft = diffs[0];
                diffRight = diffs[1];
            }
        }
        updateSizeForAncestors(node);
        return numOperations;
    }

    /**
     * public IAVLNode case1InsertRebalance(IAVLNode node)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case:
     *          z
     * 0 (or 1)/ \1 (or 0)
     *        x   y
     */
    public IAVLNode case1InsertRebalance(IAVLNode node) {
        node.setHeight(node.getHeight() + 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node.getParent();
    }

    /**
     * public IAVLNode case2InsertRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *     z
     *   0/ \2
     *   x   y
     * 1/ \2
     * a   b
     * and in this case if isLeft == false:
     *   z
     * 2/ \0
     * x   y
     *   2/ \1
     *   a   b
     */
    public IAVLNode case2InsertRebalance(IAVLNode node, boolean isLeft) {
        if (isLeft) {
            rotationRight(node);
        } else {
            rotationLeft(node);
        }
        node.setHeight(node.getHeight() - 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        node = node.getParent();
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node.getParent();
    }

    /**
     * public IAVLNode case3InsertRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *     z
     *   0/ \2
     *   x   y
     * 2/ \1
     * a   b
     *    / \
     *   c   d
     * and in this case if isLeft == false:
     *   z
     * 2/ \0
     * x   y
     *   1/ \2
     *   a   b
     *  / \
     * c   d
     */
    public IAVLNode case3InsertRebalance(IAVLNode node, boolean isLeft) {
        IAVLNode tempOtherSon;
        if (isLeft) {
            rotationLeft(node.getLeft());
            rotationRight(node);
            tempOtherSon = node.getParent().getLeft();

        } else {
            rotationRight(node.getRight());
            rotationLeft(node);
            tempOtherSon = node.getParent().getRight();
        }
        node.setHeight(node.getHeight() - 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        tempOtherSon.setHeight(tempOtherSon.getHeight() - 1);
        tempOtherSon.setSize(tempOtherSon.getLeft().getSize() + tempOtherSon.getRight().getSize() + 1);
        node = node.getParent();
        node.setHeight(node.getHeight() + 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node.getParent();
    }

    /**
     * public IAVLNode case4JoinRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *     z
     *   0/ \2
     *   x   y
     * 1/ \1
     * a   b
     * and in this case if isLeft == false:
     *   z
     * 2/ \0
     * x   y
     *   1/ \1
     *   a   b
     */
    public IAVLNode case4JoinRebalance(IAVLNode node, boolean isLeft) {
        if (isLeft) {
            rotationRight(node);
        } else {
            rotationLeft(node);
        }
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        node = node.getParent();
        node.setHeight(node.getHeight() + 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node.getParent();
    }

    /**
     * public int diffBetweenParentAndChildes(IAVLNode parent)
     * <p>
     * rebalances the tree after an insert of a node
     * precondition: none
     */
    public int[] diffBetweenParentAndChildes(IAVLNode parent) {
        int[] diffs = new int[2];
        diffs[0] = parent.getHeight() - parent.getLeft().getHeight();
        diffs[1] = parent.getHeight() - parent.getRight().getHeight();
        return diffs;
    }


    //helping functions for delete

    /**
     * public void updateMinMaxFields(IAVLNode node)
     * <p>
     * Updates min and max fields of the AVLTree
     * <p>
     * precondition: tree is not empty
     */
    public void updateMinMaxFieldsInDelete(IAVLNode node) {
        if (this.minNode.getKey() == node.getKey()) {
            IAVLNode successor = successor(node);
            this.minNode = successor;
        }

        if (this.maxNode.getKey() == node.getKey()) {
            IAVLNode predecessor = predecessor(node);
            this.maxNode = predecessor;
        }
    }

    /**
     * public IAVLNode deleteBeforeRebalance(IAVLNode node)
     * <p>
     * Deletes node from tree and return the node from which we need to start the rebalancing
     * <p>
     * precondition: tree is not empty and node.getkey() is in tree
     */
    public IAVLNode deleteBeforeRebalance(IAVLNode node) {
        if (!node.getRight().isRealNode() || !node.getLeft().isRealNode()) {
            // case when leaf
            if (!node.getRight().isRealNode() && !node.getLeft().isRealNode()) {
                return deleteBeforeRebalanceLeaf(node);
            }
            // case when unary node
            else {
                return deleteBeforeRebalanceUnary(node);
            }
        }
        // case when binary node
        IAVLNode successor = successor(node);

        // successor is either leaf or unary node
        boolean directChild = node.getRight() == successor;
        IAVLNode nodeForRebalance = deleteBeforeRebalance(successor);

        // set successor's fields
        successor.setParent(node.getParent());
        successor.setRight(node.getRight());
        successor.setLeft(node.getLeft());
        successor.setHeight(node.getHeight());
        successor.setSize(node.getSize());

        // set node sons' fields
        node.getRight().setParent(successor);
        node.getLeft().setParent(successor);

        if (this.root == node) {
            this.root = successor;
        } else {
            if (node.getParent().getRight() == node) {
                node.getParent().setRight(successor);
            } else {
                node.getParent().setLeft(successor);
            }
        }
        return directChild ? successor: nodeForRebalance;
    }

    /**
     * public IAVLNode deleteBeforeRebalanceLeaf(IAVLNode node)
     * <p>
     * Deletes leaf from tree and return the node from which we need to start the rebalancing
     * <p>
     * precondition: node is leaf
     */
    public IAVLNode deleteBeforeRebalanceLeaf(IAVLNode node) {
        if (node == this.root) {
            this.root = null;
            return null;
        }
        IAVLNode parent = node.getParent();
        if (parent.getRight() == node) {
            parent.setRight(new AVLNode(parent));
        } else {
            parent.setLeft(new AVLNode(parent));
        }
        return parent;
    }

    /**
     * public IAVLNode deleteBeforeRebalanceUnary(IAVLNode node)
     * <p>
     * Deletes unary node from tree and return the node from which we need to start the rebalancing
     * <p>
     * precondition: node is unary
     */
    public IAVLNode deleteBeforeRebalanceUnary(IAVLNode node) {

        IAVLNode son = null;
        IAVLNode parent = node.getParent();
        if (node.getLeft().isRealNode()) {
            son = node.getLeft();
        } else {
            son = node.getRight();
        }

        son.setParent(parent);
        if (this.root == node) {
            this.root = son;
        } else {
            if (parent.getLeft() == node) {
                parent.setLeft(son);
            } else {
                parent.setRight(son);
            }
        }

        return parent;
    }

    /**
     * public int rebalanceAfterDelete(IAVLNode node)
     * <p>
     * rebalances the tree after a deletion of a node
     * <p>
     * precondition: none
     */
    public int rebalanceAfterDelete(IAVLNode node) {
        int numOperations = 0;
        int diff;
        while (node != null &&
                (diff = Math.abs(node.getLeft().getHeight() - node.getRight().getHeight())) != 1) {

            int leftDiff = node.getHeight() - node.getLeft().getHeight();
            int rightDiff = node.getHeight() - node.getRight().getHeight();

            if (diff == 0) {
                //case 1:
                node = case1DeleteRebalance(node);
                numOperations += 1;
            } else if (leftDiff == 3 && rightDiff == 1) {

                IAVLNode rightSon = node.getRight();
                int rightLeftDiff = rightSon.getHeight() - rightSon.getLeft().getHeight();
                int rightRightDiff = rightSon.getHeight() - rightSon.getRight().getHeight();

                if (rightLeftDiff == 1 && rightRightDiff == 1) {
                    //case 2, isLeft == true
                    node = case2DeleteRebalance(node, true);
                    numOperations += 3;
                    break; //this is a terminate case
                } else if (rightLeftDiff == 2 && rightRightDiff == 1) {
                    //case 3, isLeft == true
                    node = case3DeleteRebalance(node, true);
                    numOperations += 3;
                } else if (rightLeftDiff == 1 && rightRightDiff == 2) {
                    //case 4, isLeft == true
                    node = case4DeleteRebalance(node, true);
                    numOperations += 6;
                }
            } else if (leftDiff == 1 && rightDiff == 3) {
                IAVLNode leftSon = node.getLeft();
                int leftLeftDiff = leftSon.getHeight() - leftSon.getLeft().getHeight();
                int leftRightDiff = leftSon.getHeight() - leftSon.getRight().getHeight();
                if (leftLeftDiff == 1 && leftRightDiff == 1) {
                    //case 2, isLeft == false
                    node = case2DeleteRebalance(node, false);
                    numOperations += 3;
                } else if (leftLeftDiff == 1 && leftRightDiff == 2) {
                    //case 3, isLeft == false
                    node = case3DeleteRebalance(node, false);
                    numOperations += 3;
                } else if (leftLeftDiff == 2 && leftRightDiff == 1) {
                    //case 4, isLeft == false
                    node = case4DeleteRebalance(node, false);
                    numOperations += 6;
                }
            }
        }
        updateSizeForAncestors(node);
        return numOperations;
    }

    /**
     * public IAVLNode case1DeleteRebalance(IAVLNode node)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case:
     *   z
     * 2/ \2
     * x   y
     */
    public IAVLNode case1DeleteRebalance(IAVLNode node) {
        node.setHeight(node.getHeight() - 1);
        node.setSize(node.getSize() - 1);
        return node.getParent();
    }

    /**
     * public IAVLNode case2DeleteRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *   z
     * 3/ \1
     * x   y
     *   1/ \1
     *   a   b
     * and in this case if isLeft == false:
     *     z
     *   1/ \3
     *   x   y
     * 1/ \1
     * a   b
     */
    public IAVLNode case2DeleteRebalance(IAVLNode node, boolean isLeft) {
        if (isLeft) {
            rotationLeft(node);
        } else {
            rotationRight(node);
        }
        node.setHeight(node.getHeight() - 1);
        node.getParent().setHeight(node.getParent().getHeight() + 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        IAVLNode tempParent = node.getParent();
        tempParent.setSize(tempParent.getLeft().getSize() + tempParent.getRight().getSize() + 1);
        return tempParent.getParent();
    }

    /**
     * public IAVLNode case3DeleteRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *   z
     * 3/ \1
     * x   y
     *   2/ \1
     *   a   b
     * and in this case if isLeft == false:
     *     z
     *   1/ \3
     *   x   y
     * 1/ \2
     * a   b
     */
    public IAVLNode case3DeleteRebalance(IAVLNode node, boolean isLeft) {
        if (isLeft) {
            rotationLeft(node);
        } else {
            rotationRight(node);
        }
        node.setHeight(node.getHeight() - 2);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        IAVLNode tempParent = node.getParent();
        tempParent.setSize(tempParent.getLeft().getSize() + tempParent.getRight().getSize() + 1);
        return tempParent.getParent();
    }

    /**
     * public IAVLNode case4DeleteRebalance(IAVLNode node, boolean isLeft)
     * <p>
     * rebalance local and return the next node that need to rebalance
     * <p>
     * precondition: we are in this case if isLeft == true:
     *    z
     *  3/ \1
     *  x   y
     *   1/ \2
     *   a   b
     *  / \
     * c   d
     * and in this case if isLeft == false:
     *     z
     *   1/ \3
     *   x   y
     * 2/ \1
     * a   b
     *    / \
     *   c   d
     */
    public IAVLNode case4DeleteRebalance(IAVLNode node, boolean isLeft) {
        IAVLNode tempOtherSon;
        if (isLeft) {
            rotationRight(node.getRight());
            rotationLeft(node);
            tempOtherSon = node.getParent().getRight();
        } else {
            rotationLeft(node.getLeft());
            rotationRight(node);
            tempOtherSon = node.getParent().getLeft();
        }
        node.setHeight(node.getHeight() - 2);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        tempOtherSon.setHeight(tempOtherSon.getHeight() - 1);
        tempOtherSon.setSize((tempOtherSon.getLeft().getSize() + tempOtherSon.getRight().getSize() + 1));
        node = node.getParent();
        node.setHeight(node.getHeight() + 1);
        node.setSize(node.getLeft().getSize() + node.getRight().getSize() + 1);
        return node.getParent();
    }

    /**
     * private void fromNodeToTree(IAVLNode node)
     * <p>
     * adjusts the tree fields to contain the given node
     * tree is not with valid min and max after this function!
     * use only in split.
     */
    private void fromNodeToTree(IAVLNode node) {
        node.setParent(null);
        this.root = node;
        this.minNode = node;
        this.maxNode = node;
    }

    /**
     * private void updateMinMaxForJoin()
     * <p>
     * find the correct min and max in tree, and update fields
     */
    private void updateMinMaxForJoin()
    {
        IAVLNode currNode = this.root;
        while (currNode.getLeft().isRealNode())
        {
            currNode = currNode.getLeft();
        }
        this.minNode = currNode;
        currNode = this.root;
        while (currNode.getRight().isRealNode())
        {
            currNode = currNode.getRight();
        }
        this.maxNode = currNode;
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
         * <p>
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
         * <p>
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

        public void setSize(int size) {
            this.size = size;
        }
    }

}
  
