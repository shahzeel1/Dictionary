/**
 * @Programmer Zeel Shah
 * @Date Nov 18 2018
 * @Student No 250970094
 * 
 * This class represents a node in the ordered dictionary. Each node holds a 
 * reference to the record object and the n node, parent, left and right child.
 */


public class Node {
	private Node left;
	private Node right;
	private Node parent;
	private Record record;
	
	/**
	 * The constructor creates a single node with a record, not attatched to anything
	 * @param rec
	 */
	public Node(Record rec)
	{
		record = rec;
		left = null;
		right = null;
		parent= null;
	}
	

	/**
	 * @return left child
	 */
	public Node getLeft()
	{
		return left;
	}
	
	/**
	 * 
	 * @return right child
	 */
	public Node getRight()
	{
		return right;
	}
	
	/**
	 * @return parent
	 */
	public Node getParent()
	{
		return parent;
	}
	
	/**
	 * @return record stored in the node
	 */
	public Record getRecord()
	{
		return record;
	}
	
	/**
	 * Sets the left child
	 * @param leftChild
	 */
	public void setLeft(Node leftChild)
	{
		left = leftChild;
	}
	
	/**
	 * Sets the right child
	 * @param rightChild
	 */
	public void setRight(Node rightChild)
	{
		right = rightChild;
	}
	
	/**
	 * Sets the parent
	 * @param parent
	 */
	public void setParent(Node parent)
	{
		this.parent = parent;
	}
	
	/**
	 * set the record in the node
	 * @param rec
	 */
	public void setRecord(Record rec)
	{
		record = rec;
	}
	
	
}
