/**
 * @Programmer Zeel Shah
 * @Date Nov 18 2018
 * @Student No 250970094
 * This class implements an ordered dictionary using a binary search tree. The tree will be made up of linked nodes.
 * All the leaf nodes have null records and internal nodes have values for the record
 */
public class OrderedDictionary implements OrderedDictionaryADT {

	private Node rootOfTree; // initializes the tree
	public OrderedDictionary()
	{
		rootOfTree = new Node(null);// set the record to null
	}

	/**
	 * get the Record stored by associated with the key
	 * @param key 
	 * @ return Record associated with key
	 */
	public Record get (Pair key)
	{
		Pair compareKey;
		Node p = rootOfTree;// pointer node

		// while the node is internal compare the given key with the key in the record, traverse until it is found (if it is in the bst)
		while(p.getRecord()!=null)
		{
			compareKey = p.getRecord().getKey();
			if (key.compareTo(compareKey) == 0)
				return p.getRecord();
			else if (key.compareTo(compareKey)==-1)
				p=p.getLeft();
			else
				p=p.getRight();
		}
		// if the key does not exist return null
		return null;
	}

	/**
	 * @param record that needs to be inserted
	 * @throws Dictionaru Exception
	 */
	public void put(Record rec) throws DictionaryException
	{
		// check to see if the node exists

		Record p = get(rec.getKey());
		
		Node current,leftChild, rightChild;

		// if the node already exists in the tree throw an error
		if (p!=null)
			throw new DictionaryException("Record already exists");

		//else
		if (p == null)
		{
			// initialize nodes
			current = new Node (rec);
			leftChild = new Node(null);
			rightChild = new Node (null);
			// set the current nodes children
			current.setLeft(leftChild);
			current.setRight(rightChild);
			//set the current nodes children's parents to itself
			leftChild.setParent(current);
			rightChild.setParent(current);
			// if the tree is empty add the current node
			if(rootOfTree.getRecord()==null)
			{
				rootOfTree=current;
			}
			// link the new node with the tree
			else
				rootOfTree = link(current,rootOfTree);

		}
	}

	/**
	 * @param key associated with the record that needs to be removed
	 * @throws Dictionary Exception
	 */
	public void remove (Pair k) throws DictionaryException
	{
		// find the node with the pair k
		Node p = find(k);//pointer
		Node c; // child of the current node
		char dir; // left or right child
		// create leaf children
		Node leftChild = new Node(null); 
		Node rightChild = new Node(null);

		// if the pointer is null it means the record is not in the dictionary
		if (p==null)
		{
			throw new DictionaryException("Record not found");
		}

		
		// if the left child is a leaf and the right child is a leaf: 
		if(p.getLeft().getRecord()==null && p.getRight().getRecord()==null)
		{
			
			// if the node that needs to be removed is the root set everything to null ( remove it)
			if(p==rootOfTree)
			{
				rootOfTree.setRecord(null);
				rootOfTree.setLeft(null);
				rootOfTree.setRight(null);
			}
			//set the parent of p's children to leaf nodes
			else
			{
				p.getParent().setLeft(leftChild);
				p.getParent().setRight(rightChild);
				while(p!=rootOfTree)
				{
					p=p.getParent();// set the new pointer to the parent of p until the root is reached
				}
				rootOfTree = p; // set the new root of the tree to the pointer
			}

		}


		// if only one of the child nodes is a leaf:
		else if (p.getLeft().getRecord()==null || p.getRight().getRecord()==null)
		{
			// if only the left child is a leaf 
			if(p.getLeft().getRecord()==null)
			{
				// get the right child of p
				c=p.getRight();
				dir ='r'; // remember which child it was
			}
			 // same as left but this time it's the right child
			else
			{
				c=p.getRight();
				dir ='l';
			}

			// if the pointer is the root
			if (p.equals(rootOfTree))
			{
				c.setParent(null); // set the child's parent to null
				rootOfTree = c; // set the new root of the tree to the child

			}
			
			
			else 
			{
				// set the child's pointer to the parent of p
				c.setParent(p.getParent());
				// set the new parent's child back to  c
				if (dir == 'r')
					c.getParent().setRight(c);
				else 
					c.getParent().setLeft(c);
			}
		}
		
		// if it is a inner node get the smallest node set the record of the pointer to the record of the smallest element in the sub tree
		else 
		{
			Record s = smallest();
			p.setRecord(s);		
		}	
	}


	/**
	 * get the successor of the key given
	 * @param key
	 * @return record of the successor
	 */
	public Record successor (Pair k)
	{
		Node parentOfP;

		// if the tree is empty return null
		if (rootOfTree.getRecord()==null)
		{
			return null;
		}

		else
		{
			// see where the key is in the dictionary
			Node p = find(k);
			//check to see if p is an internal node and the right child is also internal.. if it is then...
			if(p!=null) 
			{
				if (p.getRecord()!=null && p.getRight().getRecord()!=null)
				{
					// find the smallest node on the right side of p
					p = p.getRight();

					while (p.getRecord()!=null)
					{
						p=p.getLeft();
					}
					return p.getParent().getRecord();

				}

				else 
				{
					//get the parent of p
					parentOfP = p.getParent() ;
					while (p != rootOfTree && parentOfP.getRight() == p )
					{
						p=parentOfP;
						parentOfP = p.getParent();
					}
					if (p == rootOfTree)
						return null;
					else 
						return parentOfP.getRecord();				
				}
			}
			 
			// key is not in the dictionary to traverse until you find the succ.
			else
			{
				// compare key with each node until you find a successor
				Pair compareKey;
				p = rootOfTree;
				Node succ = null;
				while(p.getRecord()!=null)
				{
					compareKey = p.getRecord().getKey();


					if (k.compareTo(compareKey)== -1)
					{
						succ = p;
						p=p.getLeft();						
					}
					else
					{						
						p=p.getRight();
					}
				}
				// if there is a successor return it
				if (succ != null)
				{
					return succ.getRecord();
				}
				//else return null
				else 
					return null;

			}
		}

	}





	/**
	 * @param the key
	 * @ return the predecessor record of the key
	 */
	public Record predecessor (Pair k)
	{
		Node parentOfP;

		// if the tree is empty return null
		if (rootOfTree.getRecord()==null)
		{
			return null;
		}

		else
		{
			// see where the key is in the dictionary
			Node p = find(k);
			//check to see if p is an internal node and the right child is also internal.. if it is then...
			if (p!=null)
			{
				if (p.getRecord()!=null && p.getLeft().getRecord()!=null)
				{
					// find the largest node on the left side of p
					p = p.getLeft();

					while (p.getRecord()!=null)
					{
						p=p.getRight();
					}
					return p.getParent().getRecord();

				}

				else 
				{
					//get the parent of p
					parentOfP = p.getParent() ;
					while (p != rootOfTree && parentOfP.getLeft() == p )
					{
						p=parentOfP;
						parentOfP = p.getParent();
					}
					if (p == rootOfTree)
						return null;
					else 
						return parentOfP.getRecord();				
				}

			}
			// key is not in the dictionary so traverse until the pred is found
			else
			{
				// compare k with each node until you find a predessor
				Pair compareKey;
				p = rootOfTree;
				Node pred = null;
				while(p.getRecord()!=null)
				{
					compareKey = p.getRecord().getKey();


					if (k.compareTo(compareKey)== -1)
					{
						p=p.getLeft();						
					}
					else
					{

						pred=p;
						p=p.getRight();
					}
				}
				// if the pred is found return it
				if (pred != null)
				{
					return pred.getRecord();
				}
				else 
					return null;

			}
		}
	}
	
	/**
	 * @return the smallest record in the tree ( alphabetically first)
	 */
	public Record smallest ()
	{
		// start at the root
		Node p = rootOfTree;
		
		// check if the root is a leaf
		if (rootOfTree.getRecord()==null)
		{
			return null;
		}
		// if the root is not a leaf then keep going left until smallest record is found
		else
		{
			while (p.getRecord()!=null)
			{
				p=p.getLeft(); // loop until a leaf is reached, it's parent must be the smallest node
			}
			// return the smallest record
			return p.getParent().getRecord();
		}
	}

	/**
	 * @ return the largest node in the tree, ( lexigraphically last)
	 */
	public Record largest ()
	{
		// method is the same as the smallest method but go right instead of left
		Node p = rootOfTree;
		if (rootOfTree.getRecord()==null)
		{
			return null;
		}
		else
		{
			while (p.getRecord()!=null)
			{
				p=p.getRight();
			}
			return p.getParent().getRecord();
		}
	}


	/**
	 * find the node associated with the key
	 * @param key
	 * @return
	 */
	private Node find(Pair k)
	{
		
		Pair compareKey;
		Node p = rootOfTree; // start at the root

		// while the node is not a leaf:
		while(p.getRecord()!=null)
		{
			// compare the given key with the key linked to the record
			compareKey = p.getRecord().getKey();
			if (k.compareTo(compareKey) == 0)
				return p; // return the node if keys match
			
			// keep traversing until a key matches or we reach the end of the tree
			else if (k.compareTo(compareKey)==-1)
				p=p.getLeft();
			else
				p=p.getRight();
		}
		// key is not in the dictionary
		return null;
	}
	
	/**
	 * This method links a node and it's descendants with the rest of the tree
	 * @param subTree
	 * @param compareNode
	 * @return the final tree
	 */
	private Node link(Node subTree, Node compareNode)
	{
		//compare to the root
		Pair key1, key2;

		// if the node is a leaf, link the sub tree with it
		if (compareNode.getRecord()==null)
		{
			subTree.setParent(compareNode);
			compareNode.setLeft(subTree);
			return compareNode;
		}

		// get the two keys at the nodes
		key1=subTree.getRecord().getKey();
		key2 = compareNode.getRecord().getKey();

		// if the root of the subtree is smaller than the other tree go left
		if (key1.compareTo(key2)==-1)
		{
			// link the left node of the bigger tree with the sub tree if the left child is a leaf
			if (compareNode.getLeft().getRecord()==null)
			{
				subTree.setParent(compareNode);
				compareNode.setLeft(subTree);
				return compareNode;
			}
			// if not keep going through the bigger tree
			else
				return link(subTree, compareNode.getLeft()).getParent();
		}
		// go right and do the same as above
		else
		{
			if (compareNode.getRight().getRecord()==null)
			{
				subTree.setParent(compareNode);
				compareNode.setRight(subTree);
				return compareNode;
			}
			else
				return link(subTree, compareNode.getRight()).getParent();
		}
	}
}




