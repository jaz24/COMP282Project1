
import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * NAME: abstractTree.java
 * Description: This is the BST class containing all methods for traversing the tree
 * Author: 
 */


public class  BST<E extends Comparable<E>> implements TreeInterface<E>
{

    // Data fields
    public TreeNode<E> root;
    // Store the number of Nodes in this class variables
    public int size = 0;
    //Store the number of Non Leaf nodes in this class variables
    public int nonleaves;
   
    public ArrayList<E> inOrderTraversal = new ArrayList<>();
    public ArrayList<E> preOrderTraversal = new ArrayList<>();
    public ArrayList<E> postOrderTraversal = new ArrayList<>();
    public ArrayList<E> bstTraversal= new ArrayList<>();
  

    // empty constructor
    public BST(){ }
   
    // check if it is empty
    public boolean isEmpty()
    {
        // if the root has nothing then there can be no tree. so True
        if (root == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }// end isEmpty

    // Looks for an item in the tree
    // Implemented by Jazmin Perez
    public boolean search(E e)
    {
        boolean blResult = false;

        TreeNode<E> current = root;
        while (current != null)
        {
            if (e.compareTo(current.element) < 0)
            {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0)
            {
                current = current.right;
            }
            else
            {
                blResult = true;
                return blResult;
            }
        }
        return blResult;
    } // end search method


    // Implemented by Jazmin Perez
    public void insert(E e)
    {
        TreeNode<E> newNode = new TreeNode<>(e);
        root = insert(root, newNode);
        size++;
    }
    public TreeNode<E> insert(TreeNode<E> root, TreeNode<E> newNode){
        if(root == null){
            root = newNode;
            return root;
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root; 
            while(current != null){
                if(newNode.compareTo(current.element) < 0){
                    parent = insert(current.getLeft(), newNode);
                    current.setLeft(parent);
                    return current;
                }else if(newNode.compareTo(current.element) > 0){
                    parent = insert(current.getRight(), newNode);
                    current.setRight(parent);
                    return current;
                }
            }
            if(newNode.compareTo(parent.element) < 0){
                parent.left = newNode;
            }else{
                parent.right = newNode; 
            }
        }
    }


    // Implemented by Jake Lyon
    public boolean delete(E e)
    {
        //Initial check for empty tree.
        if(root == null)
            return false;

        boolean blResult = false;
        TreeNode<E> parent = null;
        TreeNode<E> current = root;

        // While the current node contains a value
        while(current != null)
        {
            // Element we are looking at is the element we want to delete
            if (current.getElement() == e)
            {
                blResult = true;

                if (current.left == null && current.right == null) //Node is a leaf
                {
                    current = null;
                }
                else if (current.getLeft() == null) //Node has one right child
                {
                    if(parent == null) //Parent hasn't been modified, we are at the root, right child becomes root
                    {
                        root = current.getRight();
                    }
                    else //Parent has been modified, we are further down the tree
                    {
                        if(e.compareTo(parent.getElement()) < 0) //If current element
                            parent.left = current.right;
                        else
                            parent.right = current.left;
                    }

                    current = null;
                }
                else if (current.getRight() == null) //Node has one left child
                {
                    parent = current.getLeft();

                    current = null;
                }
                else //Node has two children, get inorder successor
                {
                    while (current.getLeft() != null)
                    {
                        parent = current;
                        current = current.getLeft();
                    }

                    if (parent != null)
                        parent.setLeft(current.getRight());
                }
            }
            else //Element we are looking at is NOT element we want to delete
            {
                parent = current; //Set current node to parent node
                if (e.compareTo(current.getElement()) < 0) //If our element is smaller than current.element
                    current = current.left; //move left
                else //If our element is bigger than current.element
                    current = current.right; //move right
            }

        }

        size--; // In case algorithm is incorrect, break here if size < 0
        return blResult;
    }

    // returns the size of the tree
    public int getSize()
    {
        /**
         * TODO: INSERT YOUR CODE HERE
         * FIND THE NUMBER OF NODES IN THE TREE AND STORE THE VALUE IN CLASS VARIABLE "size"
         * RETURN THE "size" VALUE
         *
         * HINT: THE NUMBER OF NODES CAN BE UPDATED IN THE "size" VARIABLE EVERY TIME A NODE IS INSERTED OR DELETED FROM THE TREE.
         */

        return size;
    }// end getSize

    // (Implement postorder traversal without using recursion)
    // Implemented by Jake Lyon
    public ArrayList<E> postorderNoRecursion()
    {
	    ArrayList<E> nonRecursivePostorder = new ArrayList<>();
	    Stack<TreeNode<E>> nRPStack = new Stack<>();
	    TreeNode<E> current;

	    if (root == null)
	        return nonRecursivePostorder;

	    nRPStack.push(root);

	    while(!nRPStack.isEmpty())
        {
            current = nRPStack.pop();
            nonRecursivePostorder.add(current.getElement());

            if(current.left != null)
                nRPStack.push(current.getLeft());
            if(current.right != null)
                nRPStack.push(current.getRight());
        }

	    return nonRecursivePostorder;
    }

    // get the Number of non-leaves.
    // implemented by Jake Lyon
    public int getNumberofNonLeaves()
    {
	    TreeNode<E> current = root;

	    nonleaves = countNonLeaf(current);

	    return nonleaves;
    }

    // recursive helper function to get total number of non-leaves
    // Implemented by Jake Lyon
    private int countNonLeaf(TreeNode node)
    {
        if(node == null || node.left == null && node.right == null)
            return 0;
        return 1 + countNonLeaf(node.left) + countNonLeaf(node.right);
    }


    //(Implement inorder traversal without using recursion)
    // Implemented by Jazmin Perez
    public ArrayList<E>  inorderNoRecursion()
    {
	    ArrayList<E> nonRecursiveInorder= new ArrayList<>();
	    Stack<TreeNode<E>> nRIStack = new Stack<>();
        TreeNode<E> current = root;

	    if (root == null){
	        return nonRecursiveInorder;
        }

        while(current != null  || !nRIStack.isEmpty()){
            
            while(current != null){
                nRIStack.push(current);
                current = current.left;
            }
            
            current = nRIStack.pop();
            nonRecursiveInorder.add(current.getElement());
            current = current.right; 
        }
	    return nonRecursiveInorder;
    }


    // traversal with recursion
    public ArrayList<E> inorder()
    {
	    /**
	       * TODO: INSERT YOUR CODE HERE
	       * FIND THE IN ORDER TRAVERSAL OF THE TREE AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST inOrderTraversal
	       */

	    return inOrderTraversal;
    }//end of inorder
 
   
    public ArrayList<E> preorder()
    {
	    /**
	       * TODO: INSERT YOUR CODE HERE
	       * FIND THE PRE ORDER TRAVERSAL OF THE TREE AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST preOrderTraversal
	       */

	    return preOrderTraversal;

    }// end preorder


    public ArrayList<E> postorder()
    {
	    /**
	       * TODO: INSERT YOUR CODE HERE
	       * FIND THE POST ORDER TRAVERSAL OF THE TREE AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST postOrderTraversal
	       */
	    return postOrderTraversal;
    }

  

}// end class BST

