package cn.net.bhe.introductiontoalgorithms.datastructures.tree.redblacktree;

public class RedBlackTree<T extends Comparable<T>> implements Iterable<T> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public class Node {

        public boolean color = RED; // 结点颜色

        public T value;

        public Node left, right, parent;

        public Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public Node root;

    private int nodeCount = 0;

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 查找
     * @param value
     * @return
     */
    public boolean contains(T value) {

        Node node = root;

        if (node == null || value == null)
            return false;

        while (node != null) {

            int cmp = value.compareTo(node.value);

            // 往左子树查
            if (cmp < 0)
                node = node.left;

            // 往右子树查
            else if (cmp > 0)
                node = node.right;
            
            // 找到
            else
                return true;
        }
        return false;
    }

    /**
     * 新增
     * @param value
     * @return
     */
    public boolean insert(T value) {
        if (value == null)
            throw new IllegalArgumentException();
        if (root == null) {
            root = new Node(value, null);
            insertionRelabel(root); 
            nodeCount++;
            return true;
        }
        // 插入新节点后需要重新调整颜色，以满足红黑性质
        for (Node node = root;;) {
            int cmp = value.compareTo(node.value);
            // 在左子树找位置插入
            if (cmp < 0) {
                if (node.left == null) {
                    node.left = new Node(value, node);
                    insertionRelabel(node.left);
                    nodeCount++;
                    return true;
                }
                node = node.left;
            }
            // 在右子树找位置插入
            else if (cmp > 0) {
                if (node.right == null) {
                    node.right = new Node(value, node);
                    insertionRelabel(node.right);
                    nodeCount++;
                    return true;
                }
                node = node.right;
            }
            // 已经存在要插入的值
            else
                return false;
        }
    }

    /**
     * 重新调整颜色，以满足红黑性质
     * 调整的操作包括变色、左旋、右旋，这些操作都能满足性质5，所以回溯过程中只需要考虑性质4
     * 1、每个结点或是红色的，或是黑色的。
     * 2、根结点是黑色的。
     * 3、每个叶节点(NIL)是黑色的。
     * 4、如果一个结点是红色的，则它的两个子节点都是黑色的。
     * 5、对每个结点，从该节点到其所有后代叶节点的简单路径上，均包含相同数目的黑色结点。
     * @param node
     */
    private void insertionRelabel(Node node) { 

        Node parent = node.parent;

        // 没有根节点时，考虑性质1
        if (parent == null) {
            node.color = BLACK;
            root = node;
            return;
        }
        
        // 父结点是黑色时，由性质2和4可知不用处理
        Node grandParent = parent.parent;
        if (grandParent == null)
            return;
        if (parent.color == BLACK || node.color == BLACK)
            return;

        // 父结点是红色时，考虑性质4，分多种情况处理
        boolean nodeIsLeftChild = (parent.left == node);
        boolean parentIsLeftChild = (parent == grandParent.left);
        Node uncle = parentIsLeftChild ? grandParent.right : grandParent.left;
        boolean uncleIsRedNode = (uncle == null) ? BLACK : uncle.color;
        // 情况1，叔结点是红色
        // 将父、叔结点都变为黑色，祖父结点变为红色
        if (uncleIsRedNode) {
            parent.color = BLACK;
            grandParent.color = RED;
            uncle.color = BLACK;
        }
        // 情况2，叔结点是黑色
        // 又细分多种情况：
        // 1、父结点是左子树且当前结点是左子树
        // 2、父结点是左子树且当前结点是右子树
        // 3、父结点是右子树且当前结点是左子树
        // 4、父结点是右子树且当前结点是右子树
        else {
            if (parentIsLeftChild) {
                // 父结点是左子树且当前结点是左子树，右旋即可
                if (nodeIsLeftChild) {
                    grandParent = leftLeftCase(grandParent);
                }
                // 父结点是左子树且当前结点是右子树，先左旋再右旋
                else {
                    grandParent = leftRightCase(grandParent);
                }
            }
            else {
                // 父结点是右子树且当前结点是左子树，先右旋再左旋
                if (nodeIsLeftChild) {
                    grandParent = rightLeftCase(grandParent);

                }
                // 父结点是右子树且当前结点是右子树，左旋即可
                else {
                    grandParent = rightRightCase(grandParent);
                }
            }
        }
        insertionRelabel(grandParent); // 继续向上回溯，再根据遇到的情况处理
    }

    private void swapColors(Node a, Node b) {
        boolean tmpColor = a.color;
        a.color = b.color;
        b.color = tmpColor;
    }

    private Node leftLeftCase(Node node) {
        node = rightRotate(node);
        swapColors(node, node.right);
        return node;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotate(node.left);
        return leftLeftCase(node);
    }

    private Node rightRightCase(Node node) {
        node = leftRotate(node);
        swapColors(node, node.left);
        return node;
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotate(node.right);
        return rightRightCase(node);
    }

    private Node rightRotate(Node parent) {

        Node grandParent = parent.parent;
        Node child = parent.left;

        parent.left = child.right;
        if (child.right != null)
            child.right.parent = parent;

        child.right = parent;
        parent.parent = child;

        child.parent = grandParent;
        updateParentChildLink(grandParent, parent, child);

        return child;
    }

    private Node leftRotate(Node parent) {

        Node grandParent = parent.parent;
        Node child = parent.right;

        parent.right = child.left;
        if (child.left != null)
            child.left.parent = parent;

        child.left = parent;
        parent.parent = child;

        child.parent = grandParent;
        updateParentChildLink(grandParent, parent, child);

        return child;
    }
    
    private void updateParentChildLink(Node parent, Node oldChild, Node newChild) {
        if (parent != null) {
            if (parent.left == oldChild) {
                parent.left = newChild;
            } else {
                parent.right = newChild;
            }
        }
    }

    /**
     * 查找最小值
     * @param node
     * @return
     */
    public Node findMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }

    /**
     * 查找最大值
     * @param node
     * @return
     */
    public Node findMax(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    @Override
    public java.util.Iterator<T> iterator() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount)
                    throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNodeCount != nodeCount)
                    throw new java.util.ConcurrentModificationException();

                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Example usage of RB tree:
    public static void main(String[] args) {

        int[] values = { 5, 8, 1, -4, 6, -2, 0, 7 };
        RedBlackTree<Integer> rbTree = new RedBlackTree<>();
        for (int v : values)
            rbTree.insert(v);

        System.out.printf("RB tree contains %d: %s\n", 6, rbTree.contains(6));
        System.out.printf("RB tree contains %d: %s\n", -5, rbTree.contains(-5));
        System.out.printf("RB tree contains %d: %s\n", 1, rbTree.contains(1));
        System.out.printf("RB tree contains %d: %s\n", 99, rbTree.contains(99));
    }
}
