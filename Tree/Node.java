/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tmtuan.tree;

/**
 *
 * @author tmtuan
 */
public class Node {
    
    // Instance variables
    private int data;
    private Node parent;        // a reference to the parent node
    private Node left;          // a reference to the left child
    private Node right;         // a reference to the right child
    
    // Constructor with given value, parent node, left node, and child node if any
    public Node(int value, Node parent, Node leftChild, Node rightChild) {
        data = value;
        this.parent = parent;
        this.left = leftChild;
        this.right = rightChild;
    }
    
    // accessor method
    public int getValue() { return data; }
    public Node getParent() { return parent; }
    public Node getLeftChild() { return left; }
    public Node getRightChild() { return right; }
    
    // update methods
    public void setValue(int value) { data = value; }
    public void setParent(Node parent) { this.parent = parent; }
    public void setLeftChild(Node left) { this.left = left; }
    public void setRightChild(Node right) { this.right = right; }    
}
