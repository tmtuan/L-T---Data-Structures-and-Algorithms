/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tmtuan.tree;

/**
 *
 * @author tmtuan
 */
public class BinarySearchTree {
    
    // instance variable
    private Node root;      // the root, null if the tree is empty
    private int size;    // the number of nodes existing 
    
    // Constructor
    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    
    // assessor method
    public Node getRoot() { return root; }
    public int size() { return this.size; }
    // Update methods
    public void insert(int value) {
       
        Node curr = root;
        while (curr != null) {  // Traverse tree to find a proper place for inserting value
            if (value < curr.getValue()) 
                if (curr.getLeftChild() == null) {
                    curr.setLeftChild(new Node(value, curr, null, null));
                    size++;
                    break;
                }
                else 
                    curr = curr.getLeftChild();
               
            if (value > curr.getValue()) 
                if(curr.getRightChild() == null) {
                    curr.setRightChild(new Node(value, curr, null, null));
                    size++;
                    break;
                }
                else    
                    curr = curr.getRightChild();
        } 

        if (root == null) {
            root = new Node(value, null, null, null);
            size++;
        }
    }   
    
    
    public void delete(Node p) {
        if (p.getLeftChild() == null && p.getRightChild() == null) {
            if(p.getParent()!= null) 
                if(p.getParent().getLeftChild() == p) p.getParent().setLeftChild(null);
                else p.getParent().setRightChild(null);
            p = null;
            size--;
        }
        else if (p.getLeftChild() != null) {
            Node pred = predecessor(p);
            swap(p, pred);
            delete(pred);
        }
        else if (p.getRightChild() != null) {
            Node succ = successor(p);
            swap(p, succ);
            delete(succ);
        } 
    }
    /**
     * Swap value of p and q
     * @param p
     * @param q 
     */
    public void swap(Node p, Node q) {
        int temp = p.getValue();
        p.setValue(q.getValue());
        q.setValue(temp);
    }
    
    public Node predecessor(Node p) {
        
        Node curr = p.getLeftChild();
        Node result = curr;
        if (curr != null) {
            while ((curr = curr.getRightChild()) != null) {
                if (curr.getRightChild() == null) {
                    result = curr;
                }
            }
        }
        return result;    
    }
    
    public Node successor(Node p) {
        
        Node curr = p.getRightChild();
        Node result = curr;
        if (curr != null) {
            while((curr = curr.getLeftChild()) != null) {
                if (curr.getLeftChild() == null) {
                    result = curr;
                }
            }
        }
        return result;
    }
    
    /**
     * Starting from node p, search node with key 
     * @param p
     * @param key
     * @return 
     */
    public Node search(Node p, int key) {
        if (p == null) return null;
        else if (key == p.getValue()) return p;
        else if (key < p.getValue()) return search(p.getLeftChild(), key);
        else return search(p.getRightChild(), key);
    }
    // Traverse methods
    /**
     * Visit p, then left child, finally right child (NLR)
     * @param p initialized with root 
     */
    public void preorder(Node p) {
        if (p == null) return;
        System.out.print(p.getValue() + " ");
        preorder(p.getLeftChild());
        preorder(p.getRightChild());
    }
    /**
     * Visit left child, then p, finally right child (LNR)
     * @param p initialized with root
     */
    public void inorder(Node p) {
        if (p == null) return;
        inorder(p.getLeftChild());
        System.out.print(p.getValue() + " ");
        inorder(p.getRightChild());
    }
    /**
     * Visit left child, then right child, finally p (LRN
     * @param p 
     */
    public void postorder(Node p) {
        if (p == null) return;
        postorder(p.getLeftChild());
        postorder(p.getRightChild());
        System.out.print(p.getValue() + " ");
        
    }
    
    public static void main(String[] args) {
        
        int[] a = {4, 6, 1, 3, 10, 2, 20};
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("Currently, the tree has the size of " + tree.size());
       
        for(int i = 0; i < a.length; i++) {
            tree.insert(a[i]);
        }
       System.out.println("After inserted with several values, the tree has the size of " + tree.size());
       
        System.out.println("\nTraverse the tree by preorder");
        tree.preorder(tree.getRoot());
       
       System.out.println("\nTraverse the tree by inorder");
       tree.inorder(tree.getRoot());
       
       System.out.println("\nTraverse the tree by postorder");
       tree.postorder(tree.getRoot());
       
       System.out.println("\nSearching 6");
       Node search = tree.search(tree.getRoot(), 6);
       if(search != null) {
           System.out.println("Found " + search.getValue());
            tree.delete(search);
            tree.inorder(tree.getRoot());
       }
       else
           System.out.println("Not found");
       
       System.out.println("\nSearch 100");
       search = tree.search(tree.getRoot(), 100);
       if(search != null) {
           System.out.println("found");
         
       }
       else
           System.out.println(("Not found"));
           
     
       System.out.println();
               
    }
}
