/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import java.util.Scanner;

/**
 *
 * @author apple
 */
// AvlTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate
/**
 * Implements an AVL tree. Note that all "matching" is based on the compareTo
 * method.
 *
 * @author Mark Allen Weiss
 * @param <Key>
 */
public class AvlTree<Key extends Comparable<? super Key>> {

    /**
     * Construct the tree.
     */
    public AvlTree() {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     *
     * @param x the item to insert.
     */
    public void add(Key x) {
        root = AvlTree.this.add(x, root);
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     *
     * @param x the item to remove.
     */
    public void remove(Key x) {
        System.out.println("Sorry, remove unimplemented");
    }

    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public Key min()  {
        
        return min(root).element;
    }

    /**
     * Find the largest item in the tree.
     *
     * @return the largest item of null if empty.
     */
    public Key max()  {
       
        return max(root).element;
    }

    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if x is found.
     */
    public boolean contains(Key x) {
        return contains(x, root);
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty() {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private Node<Key> add(Key x, Node<Key> t) {
        if (t == null) {
            return new Node<>(x, null, null);
        }

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = AvlTree.this.add(x, t.left);
            if (height(t.left) - height(t.right) == 2) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild(t);
                } else {
                    t = doubleWithLeftChild(t);
                }
            }
        } else if (compareResult > 0) {
            t.right = AvlTree.this.add(x, t.right);
            if (height(t.right) - height(t.left) == 2) {
                if (x.compareTo(t.right.element) > 0) {
                    t = rotateWithRightChild(t);
                } else {
                    t = doubleWithRightChild(t);
                }
            }
        } else
            ;  // Duplicate; do nothing
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the smallest item.
     */
    private Node<Key> min(Node<Key> t) {
        if (t == null) {
            return t;
        }

        while (t.left != null) {
            t = t.left;
        }
        return t;
    }

    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the tree.
     * @return node containing the largest item.
     */
    private Node<Key> max(Node<Key> t) {
        if (t == null) {
            return t;
        }

        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the tree.
     * @return true if x is found in subtree.
     */
    private boolean contains(Key x, Node<Key> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);

            if (compareResult < 0) {
                t = t.left;
            } else if (compareResult > 0) {
                t = t.right;
            } else {
                return true;    // Match
            }
        }

        return false;   // No match
    }

    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the tree.
     */
    private void printTree(Node<Key> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    /**
     * Return the height of node t, or -1, if null.
     */
    private int height(Node<Key> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * Rotate binary tree node with left child. For AVL trees, this is a single
     * rotation for case 1. Update heights, then return new root.
     */
    private Node<Key> rotateWithLeftChild(Node<Key> k2) {
        Node<Key> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /**
     * Rotate binary tree node with right child. For AVL trees, this is a single
     * rotation for case 4. Update heights, then return new root.
     */
    private Node<Key> rotateWithRightChild(Node<Key> k1) {
        Node<Key> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    /**
     * Double rotate binary tree node: first left child with its right child;
     * then node k3 with new left child. For AVL trees, this is a double
     * rotation for case 2. Update heights, then return new root.
     */
    private Node<Key> doubleWithLeftChild(Node<Key> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Double rotate binary tree node: first right child with its left child;
     * then node k1 with new right child. For AVL trees, this is a double
     * rotation for case 3. Update heights, then return new root.
     */
    private Node<Key> doubleWithRightChild(Node<Key> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private static class Node<AnyType> {

        // Constructors

        Node(AnyType theElement) {
            this(theElement, null, null);
        }

        Node(AnyType theElement, Node<AnyType> lt, Node<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
            height = 0;
        }

        AnyType element;      // The data in the node
        Node<AnyType> left;         // Left child
        Node<AnyType> right;        // Right child
        int height;       // Height
    }
    
    
    public String getPath(Key key) {
        StringBuilder sb = new StringBuilder();
        Node<Key> node = root;
        while (node != null) {
            if (node.element.compareTo(key) == 0) {
                break;
            } else if (node.element.compareTo(key) < 0) {
                node = node.right;
                if (node != null) {
                    sb.append('R');
                }
            } else {
                node = node.left;
                if (node != null) {
                    sb.append('L');
                }
            }

        }
        return sb.toString();
    }

    public Iterable<Key> keys()  {
        return keys(min(), max());
    }

    // the keys between lo and hi, as an Iterable
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
        keys(root, queue, lo, hi);
        return queue;
    }

    // add the keys between lo and hi in the subtree rooted at x
    // to the queue
    private void keys(Node<Key> x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.element);
        int cmphi = hi.compareTo(x.element);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.element);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }
    
    
    /**
     * The tree root.
     */
    private Node<Key> root;

    // Test program
    public static void main(String[] args) throws UnderflowException {
        AvlTree<Integer> t = new AvlTree<>();
        Scanner in = new Scanner(System.in);

        for (int i = 0; i < 10; i++) {
            t.add(in.nextInt());
        }

        t.printTree();

    }

    private static class UnderflowException extends Exception {

        public UnderflowException() {
        }
    }
}
