
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



    public void insert(E e)
    {
         
        /**
         * TODO: INSERT YOUR CODE HERE
         *
         * INSERT THE ITEM PASSED AS PARAMETER IN THE TREE .
         * HINT: THE NUMBER OF NODE CAN BE UPDATED IN THE "size" VARIABLE
         *
         */

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

                if (current.getLeft() == null) //Node has or one right child
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
                }
                else if(current.getRight() == null)
                {
                    if(parent == null)
                        root = current.getLeft();
                    else
                    {
                        if (e.compareTo(parent.getElement()) < 0)
                            parent.left = current.right;
                        else
                            parent.right = current.left;
                    }
                }
                else
                {
                    TreeNode<E> itemToDelete = inorderSuccessor(current.getRight());
                    current.setElement(itemToDelete.getElement());

                    if(current.getRight() == itemToDelete)
                        current.setRight(itemToDelete.getRight());
                }

                current = null;
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

        if(blResult)// == true
            size--;
        return blResult;
    }

    //Helper function to find inorder successor
    //Implemented by Jake Lyon
    private TreeNode<E> inorderSuccessor(TreeNode<E> current)
    {
        TreeNode<E> parent = null;

        while(current.getLeft() != null)
        {
            parent = current;
            current = current.getLeft();
        }

        if(parent != null)
            parent.setLeft(current.getRight());

        return current;
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

        //Because size is updated on every insert and delete, this method only needs to return size
        return size;
    }// end getSize

    // (Implement postorder traversal without using recursion)
    // Implemented by Jake Lyon
    public ArrayList<E> postorderNoRecursion()
    {
	    ArrayList<E> nonRecursivePostorder = new ArrayList<>();
	    Stack<TreeNode<E>> stack1 = new Stack<>();
	    Stack<TreeNode<E>> stack2 = new Stack<>();
	    TreeNode<E> current;

	    if (root == null)
	        return nonRecursivePostorder;

	    stack1.push(root);

	    while(!stack1.isEmpty())
        {
            current = stack1.pop();
            stack2.push(current);

            if(current.left != null)
                stack1.push(current.getLeft());
            if(current.right != null)
                stack1.push(current.getRight());
        }

        while(!stack2.isEmpty())
            nonRecursivePostorder.add(stack2.pop().getElement());

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
    public ArrayList<E>  inorderNoRecursion()
    {
	    ArrayList<E> nonRecursiveInorder= new ArrayList<>();

	    /**
	       * TODO: INSERT YOUR CODE HERE
	       * FIND THE IN ORDER TRAVERSAL OF THE TREE WITHOUT USING RECURSION AND RETURN THE RESULT TRAVEL SEQUENCE IN ARRAYLIST nonRecursiveInorder
	       */

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

