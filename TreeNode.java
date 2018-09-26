/**
 * NAME: TreeNode.java
 * Description: creates the node with root, left and right children defined
 * 
 */


class TreeNode<E>
{
   E element;
   TreeNode<E> left;
   TreeNode<E> right;


   public TreeNode(){
    
   }


	public E getElement()
	{
		return element;
	}


	public void setElement(E element) {
	this.element = element;
}


	public TreeNode<E> getLeft() {
	return left;
}


	public void setLeft(TreeNode left) {
	this.left = left;
}


	public TreeNode<E> getRight() {
	return right;
}


	public void setRight(TreeNode right) {
	this.right = right;
}
   
   
   // end TreeNode

}// end class TreeNode
