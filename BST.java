
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
        
        //while current containas a value: 
        while (current != null)
        {   
            //compare if the comparison between element e and current element is less than zero
            if (e.compareTo(current.element) < 0){   
                //if previous comparison is true, make the current Treenode equal to its left 
                current = current.left;
            }
            //other wise: compare if the comparison between element e and current element is greater than zero
            else if (e.compareTo(current.element) > 0){
                //if comparison is greater than zero, make the current treenode equal to its right child
                current = current.right;
            }
            else{
                //if all else fails, then the element e is in the BST
                blResult = true;
                return blResult;
            }
        }
        return blResult;
    } // end search method


    // Implemented by Jazmin Perez
    public void insert(E e)
    {
        //make a new node that is being inserted into the tree
        TreeNode<E> newNode = new TreeNode<>();
        //set its value to the e parameter, which is the element being inserted
        newNode.setElement(e);
        root = insert(root, newNode);
        //increment the size of the BST
        size++;
    }
    //helper function for insert method, using recursion 
    public TreeNode<E> insert(TreeNode<E> root, TreeNode<E> newNode){
        //if the root is null, the root has no children
        if(root == null){
            root = newNode;
            return root;
        }
        //otherwise, insertion must go through the following cases: 
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root; 
            //while current has no value
            while(current != null){
                //if the comparison of the new element is less than zero: 
                //insert new child to the left
                if(newNode.getElement().compareTo(current.element) < 0){
                    parent = insert(current.getLeft(), newNode);
                    current.setLeft(parent);
                    return current;
                }
                //if the comparison of the new element is greater than zero: 
                //insert new child to the right
                else if(newNode.getElement().compareTo(current.element) > 0){
                    parent = insert(current.getRight(), newNode);
                    current.setRight(parent);
                    return current;
                }
                //if all else fails, return root
                else{
                    return root; 
                }
            }
            //if comparison between the element and parent element is less than zero, make the parent.left the new Node
            if(newNode.getElement().compareTo(parent.element) < 0){
                parent.left = newNode;
                return current; 
            }
            //if not, if comparison between the element and parent element is less than zero, make the parent.left the new Node
            else{
                parent.right = newNode; 
                return current; 
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
    // Implemented by Jazmin Perez
    public ArrayList<E>  inorderNoRecursion()
    {
        //making new Arraylist and Stack 
	    ArrayList<E> nonRecursiveInorder= new ArrayList<>();
	    Stack<TreeNode<E>> nRIStack = new Stack<>();
        TreeNode<E> current = root;

        //if the root is null, then there are no children, and that is the only element in the BST
	    if (root == null){
            //returns the Arraylist nonRecursiveInorder
	        return nonRecursiveInorder;
        }

        //if the Stack is not empty, do the following: 
        while(!nRIStack.isEmpty()){
            //while current has a value, add the value to the stack
            while(current != null){
                nRIStack.push(current);
                //then move down to the left child
                current = current.left;
            }
            //if current is now null, set the deleted element to current 
            current = nRIStack.pop();
            //add the deleted value to the Arraylist
            nonRecursiveInorder.add(current.getElement());
            //move down to the right child 
            current = current.right; 
        }
        //in the end, return the Arraylist with all values in inOrder 
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

