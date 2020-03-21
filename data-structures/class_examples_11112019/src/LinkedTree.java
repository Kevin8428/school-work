// recursion
public class LinkedTree {
	
	private Node root;
	private int count;
	
	public boolean add(Integer data) {
		
		if (root == null) {
			Node newNode = new Node();
			newNode.setData(data);
			root = newNode;
			count = 1;
		} else {
			return recurAdd(root, data);
		}
		
		return true;
	}

	private boolean recurAdd(Node root, Integer data) {
		if (root == null) {
			// We should never get here!!!
			return false;
		}
		
		if (data == root.getData()) {
			// Data already in tree;
			return true;
		}
		
		if (data < root.getData()) {
			if (root.getlChild() == null) {
				Node newNode = new Node();
				newNode.setData(data);
				root.setlChild(newNode);
				return true;
			} else {
				// Recursion time!
				return recurAdd(root.getlChild(), data);
			}
		} else {
			// Data is > root data
			if (root.getrChild() == null) {
				Node newNode = new Node();
				newNode.setData(data);
				root.setrChild(newNode);
				return true;
			} else {
				//Recursion time!!!
				return recurAdd(root.getrChild(), data);
			}
		}
	}
	
	public void preOrder() {
		preOrder(root);
	}
	
	private void preOrder(Node root) {
		if (root == null) {
			return;
		}
		
		System.out.println(root.getData());
		preOrder(root.getlChild());
		preOrder(root.getrChild());
	}
	
	public void inOrder() {
		inOrder(root);
	}
	
	private void inOrder(Node root) {
		if (root == null) {
			return;
		}
		
		inOrder(root.getlChild());
		System.out.println(root.getData());
		inOrder(root.getrChild());
	}
	
	public void postOrder() {
		postOrder(root);
	}
	
	private void postOrder(Node root) {
		if (root == null) {
			return;
		}
		
		postOrder(root.getlChild());
		postOrder(root.getrChild());
		System.out.println(root.getData());
	}
	
	public Integer getLeftMostData() {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		return getLeftMostData(root);
	}
	
	private Integer getLeftMostData(Node root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}
		
		if (root.getlChild() == null) {
			return root.getData();
		} else {
			return getLeftMostData(root.getlChild());
		}
	}
	
	private boolean isLeaf(Node n) {
		if (n == null) {
			return false;
		}
		
		return ((root.getlChild() == null) && (root.getrChild() == null));
	}
	
	
	private int counter;
	private String printTraversal(Node root) {
		if (root == null) {
			return "";
		}
		String rtn = printTraversal(root.getlChild());
		if (counter == 0) {
			rtn += root.getData();
			counter++;
		} else {
			rtn += " " + root.getData();
		}
		//System.out.println("rtn = " + rtn);
		return rtn + printTraversal(root.getrChild());
	}
 	
	public String toString() {
		counter= 0;
		return printTraversal(root);
	}
	
}
