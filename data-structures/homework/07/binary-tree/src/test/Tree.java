package test;

/*
Tree contains methods for adding, getting and printing nodes from the tree
It is assumed that the text is already stripped of punctuation.
It is assumed all text is lowercase already.
The tree is a binary search tree. 
'frequentest' is the most frequently used word.
'count' is the total count of words used.
 */
public class Tree {
	private Node root;
	private int count;
	private int unique;
	public Node frequentest;
	private int nToTwenty;
	
	// getter
	public int count() {
		return count;
	}
	
	// getter
	public int unique() {
		return unique;
	}

	// getter
	public Node root() {
		return root;
	}
	
	// getter
	public String rootWord() {
		return root.getWord();
	}
	
	// add node to tree
	public boolean add(String word) {
		if (root == null) {
			Node node = new Node();
			node.setWord(word);
			root = node;
			count = 1;
		} else {
			count++;
			return rAdd(root, word);
		}
		
		return true;
	}
	
	// traverse left then right side of tree
	private boolean rAdd(Node root, String word) {
		if (root == null) {
			return false;
		}
		if (root.getWord().compareTo(word) == 0) {
			root.increment();
			return true;
		}
		 
		
		if (root.isAfterthisWord(word)) {
			// alphabetically, root comes after word
			if (root.getlChild() == null) {
				Node node = new Node();
				node.setWord(word);
				root.setlChild(node);
				unique++;
				return true;
			} else {
				return rAdd(root.getlChild(), word);
			}
		} else {
			// alphabetically, root comes before word
			if (root.getrChild() == null) {
				Node node = new Node();
				node.setWord(word);
				root.setrChild(node);
				unique++;
				return true;
			} else {
				return rAdd(root.getrChild(), word);
			}
		}
	}
	
	// getter
	public int depth() {
		return depth(root);
	}
	
	// get depth of tree, Traverse left then right side
	private int depth(Node node) {
		if (node == null) {
			return 0;
		}
		int lDepth = depth(node.getlChild());
		int rDepth = depth(node.getrChild());
		if (lDepth > rDepth) {
			return lDepth + 1;
		}
		return rDepth + 1;
	}
	
	public Node frequentest() {
		return frequentest(root);
	}
	
	private Node frequentest(Node root){
		if (root == null) {
			return null;
		}
		
		if (frequentest == null) {
			frequentest = root;
		}
		
		if (root.getCount() > frequentest.getCount()) {
			frequentest = root;
		}
		frequentest(root.getlChild());
		return frequentest(root.getrChild());
	}
	
	public int find(String word) {
		Node node = find(root, word);
		if (node == null) {
			return 0;
		}
		return node.getCount();
	}
	
	private Node find(Node root, String word) {
		if (root == null) {
			return null;
		}
		if (root.getWord().compareTo(word) == 0) {
			System.out.println(word +  " occurs " + root.getCount()+ " times.");
			return root;
		}
		find(root.getlChild(), word);
		return find(root.getrChild(), word);
	}
	
	public void postOrder() {
		nToTwenty = 0;
		postOrder(root);
	}
	
	private void postOrder(Node root) {
		if (root == null) {
			return;
		}
		
		if (nToTwenty > 19) {
			return;
		}
		
		nToTwenty++;
		postOrder(root.getlChild());
		postOrder(root.getrChild());
		System.out.print(root.getWord() + " ");
	}
	
	public void inOrder() {
		nToTwenty = 0;
		inOrder(root);
	}
	
	private void inOrder(Node root) {
		if (root == null) {
			return;
		}
		
		if (nToTwenty > 19) {
			return;
		}
		
		nToTwenty++;
		inOrder(root.getlChild());
		System.out.print(root.getWord() + " ");
		inOrder(root.getrChild());
	}
	
	public void preOrder() {
		nToTwenty = 0;
		preOrder(root);
	}
	
	private void preOrder(Node root) {
		if (root == null) {
			return;
		}
		
		if (nToTwenty > 19) {
			return;
		}
		
		nToTwenty++;
		System.out.print(root.getWord() + " ");
		preOrder(root.getlChild());
		preOrder(root.getrChild());
	}
}


















