package cn.net.bhe.introductiontoalgorithms.datastructures.tree.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class BinaryTree {
    
    public class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }

    Node root;
 
    /**
     * 新增
     * @param value
     */
    public void add(int value) {
        root = addRecursive(root, value);
    }
    
    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
     
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }
     
        return current;
    }
    
    @Test
    public void createBinaryTree() {
        BinaryTree bt = new BinaryTree();
     
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
    }
    
    /**
     * 查找
     * @param value
     * @return
     */
    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }
    
    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        } 
        if (value == current.value) {
            return true;
        } 
        return value < current.value
          ? containsNodeRecursive(current.left, value)
          : containsNodeRecursive(current.right, value);
    }
    
    @Test
    public void givenABinaryTree_WhenAddingElements_ThenTreeContainsThoseElements() {
        BinaryTree bt = new BinaryTree();
        
        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
     
        System.out.println(bt.containsNode(4));
        System.out.println(bt.containsNode(40));
    }
    
    /**
     * 删除
     * @param value
     */
    public void delete(int value) {
        root = deleteRecursive(root, value);
    }
    
    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        } 
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
    
    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }
    
    @Test
    public void givenABinaryTree_WhenDeletingElements_ThenTreeDoesNotContainThoseElements() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        System.out.println(bt.containsNode(9));
        bt.delete(9);
        System.out.println(bt.containsNode(9));
    }
    
    /**深度优先遍历Depth-First Search Start**************************************************************************/
    public void traverseInOrder(Node node) { // 中序遍历
        if (node != null) { 
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
    }
    
    public void traversePreOrder(Node node) { // 前序遍历
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    
    public void traversePostOrder(Node node) { // 后续遍历
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }
    /**Depth-First Search End**************************************************************************/
    
    /**
     * 广度优先遍历Breadth-First Search
     */
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
     
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
     
        while (!nodes.isEmpty()) {
     
            Node node = nodes.remove();
     
            System.out.print(" " + node.value);
     
            if (node.left != null) {
                nodes.add(node.left);
            }
     
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
    }
    
    @Test
    public void traverseTest() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);
        
        bt.traverseInOrder(bt.root);
        System.out.println();
        bt.traversePreOrder(bt.root);
        System.out.println();
        bt.traversePostOrder(bt.root);
        System.out.println();
        
        bt.traverseLevelOrder();
    }
}
