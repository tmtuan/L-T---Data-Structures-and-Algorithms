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
    /**
     * Insert a node then balance the tree 
     * @param value 
     */
    public void balanceInsert(int value) {
       
        Node curr = root;
        while (curr != null) {  // Traverse tree to find a proper place for inserting value
            if (value < curr.getValue()) 
                if (curr.getLeftChild() == null) {
                    curr.setLeftChild(new Node(value, curr, null, null));
                    size++;
                    restructure(curr.getLeftChild());
                    break;
                }
                else 
                    curr = curr.getLeftChild();
               
            if (value > curr.getValue()) 
                if(curr.getRightChild() == null) {
                    curr.setRightChild(new Node(value, curr, null, null));
                    size++;
                    restructure(curr.getRightChild());
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
    /**
     * Delete a node
     * @param p 
     */
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
     * Delete a node, then balance the tree
     * @param p 
     */
    public void balancedDelete(Node p) {
        if (p.getLeftChild() == null && p.getRightChild() == null) {
            if(p.getParent()!= null) 
                if(p.getParent().getLeftChild() == p) p.getParent().setLeftChild(null);
                else p.getParent().setRightChild(null);
            restructure(p.getParent());
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
    /**
     * The predecessor of p which is the largest on the left of p
     * @param p
     * @return 
     */
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
    
    /**
     * The successor of p which is the smallest on the right of p
     * @param p
     * @return 
     */
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
    /**
     * Height of a tree
     * @param p
     * @return 
     */
    public int height(Node p) {
        if (p == null) return 0;
        else return  1 + max(height(p.getLeftChild()),
                             height(p.getRightChild()));
    }
    /**
     * The max of a and b
     * @param a
     * @param b
     * @return 
     */
    public int max(int a, int b) {
        return a > b ? a : b;
    }
    /**
     * Given y is parent of x and x is left child of y
     * A right rotation at x turns x into a parent of y and right subtree of x becomes left subtree of y
     * @param x 
     */
    public void rightRotation(Node x) {
        
        Node y = x.getParent();
        y.setLeftChild(x.getRightChild());
        x.setRightChild(y);
        x.setParent(y.getParent());
        y.setParent(x);
    }
    
    /**
     * Given y is parent of x and x is right child of y
     * A left rotation at x turns x into parent of y and left subtree of x becomes right subtree of y
     * @param x 
     */
    public void leftRotation(Node x) {
        
        Node y = x.getParent();
        y.setRightChild(x.getLeftChild());
        x.setLeftChild(y);
        x.setParent(y.getParent());
        y.setParent(x);
        
    }
    /**
     * 
     * @param x 
     */
    public void doubleRotation(Node x) {
        
    }
    /**
     * AVL tree has every node with its left sub-tree and right sub-tree different by 1 height
     * @param p
     * @return 
     */
    public boolean isAVL(Node p) {
        Boolean avl;
        if (p == null) 
            avl = true;
        else 
            avl = Math.abs(height(p.getLeftChild()) - height(p.getRightChild())) == 1; 
        return avl;
    }
    /**
     * Calibrate to balance tree from node x
     * @param x 
     */
    public void balance(Node x) {
     
        // if x's right child, named y, is 2 height higher than left child, then,
        // if x's right child is right heavy or balanced, then leftRotate at y
        if(x==null) return; 
        else if ((height(x.getLeftChild()) - height(x.getRightChild())) < -1 ) {
            if ((height(x.getRightChild().getLeftChild()) - height(x.getRightChild().getRightChild())) < - 1)
                leftRotation(x.getRightChild());
            else {
                rightRotation(x.getRightChild().getLeftChild());
                leftRotation(x.getRightChild());   
            }
        }
        // if x's left child, name y is 2 height higher than right child, then,
        // if x's left child is left heavy or balance, then rightRotate at y
        else if ((height(x.getLeftChild()) - height(x.getRightChild())) > 1) {
            if ((height(x.getLeftChild().getLeftChild()) - height(x.getLeftChild().getRightChild())) > 1) 
                rightRotation((x.getLeftChild()));
            else {
                leftRotation(x.getLeftChild().getRightChild());
                rightRotation(x.getLeftChild());
            }
        }
        
                
    }
    /**
     * Restructure a tree from node x up to root to balance the whole tree
     * @param x 
     */
    public void restructure(Node x) {
        
        if (x == null) return;
        else {
            balance(x);
            balance(x.getParent());
        }
    }
    
    /**
     * main
     * @param args 
     */
    public static void main(String[] args) {
        
        int[] a = {4, 6, 1, 3, 10, 2, 20};
        BinarySearchTree tree = new BinarySearchTree();
        System.out.println("Currently, the tree has the size of " + tree.size());
       
        for(int i = 0; i < a.length; i++) {
            tree.insert(a[i]);
        }
       System.out.println("After inserted with several values, the tree has the size of " + tree.size());
       System.out.println("Tree height = " + tree.height(tree.getRoot()));
       System.out.println("Height of left subtree = " + tree.height(tree.getRoot().getLeftChild()));
       System.out.println("Height of right subtree = " + tree.height(tree.getRoot().getRightChild()));
       
        System.out.println("\nTraverse the tree by preorder");
        tree.preorder(tree.getRoot());
       
       System.out.println("\nTraverse the tree by inorder");
       tree.inorder(tree.getRoot());
       
       System.out.println("\nTraverse the tree by postorder");
       tree.postorder(tree.getRoot());
       
       if (tree.isAVL(tree.getRoot()))
           System.out.println("\nIs AVL");
       else 
           System.out.println("\nIs not AVL");
       
       System.out.println("\nSearching 6");
       Node search = tree.search(tree.getRoot(), 6);
       if(search != null) {
           System.out.println("Found " + search.getValue());
            tree.balancedDelete(search);
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
           
       System.out.println("Height of left subtree = " + tree.height(tree.getRoot().getLeftChild()));
       System.out.println("Height of right subtree = " + tree.height(tree.getRoot().getRightChild()));
       
       System.out.println("\nTraverse the tree by inorder");
       tree.inorder(tree.getRoot());
       if (tree.isAVL(tree.getRoot()))
           System.out.println("Is AVL");
       else 
           System.out.println("Is not AVL");
       System.out.println();
               
    }
}
