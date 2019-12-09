
public class ABTree {

	
	Integer tree[] = new Integer[32];
	int nodeCount;
	
	public ABTree() {
		nodeCount = 0;
	}
	
	public boolean add(Integer data) {
		// TODO Auto-generated method stub
		if (tree[0] == null) {
			tree[0] = data;
			nodeCount++;
			return true;
		}
		
		int tmp = 0;
		boolean done = false;
		while (! done) {
			if (tmp >= tree.length) {
				return false;
			}
			
			if (data > tree[tmp]) {
				if (tree[tmp*2+2] == null) {
					tree[tmp*2+2] = data;
					nodeCount++;
					return true;
				} else {
					tmp = tmp*2+2;
				}
			} else {
				if (tree[tmp*2+1] == null) {
					tree[tmp*2+1] = data;
					nodeCount++;
					return true;
				} else {
					tmp = tmp * 2 + 1;				}
				
			}
		}

		
		return false;
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}

	public void preOrder() {
		// TODO Auto-generated method stub
		preOrderTraversal(0);
		System.out.println();
	}
	
	private void preOrderTraversal(int root) {
		if (tree[root] == null) {
			return;
		}
		System.out.print(tree[root] + " ");
		preOrderTraversal(root*2+1);
		preOrderTraversal(root*2+2);
	}

	public void inOrder() {
		// TODO Auto-generated method stub
		inOrderTraversal(0);
		System.out.println();
	}
	
	private void inOrderTraversal(int root) {
		if (tree[root] == null) {
			return;
		}
		inOrderTraversal(root*2+1);
		System.out.print(tree[root] + " ");
		inOrderTraversal(root*2+2);
	}


	public void postOrder() {
		// TODO Auto-generated method stub
		postOrderTraversal(0);
		System.out.println();
	}
	
	private void postOrderTraversal(int root) {
		if (tree[root] == null) {
			return;
		}
		postOrderTraversal(root*2+1);
		postOrderTraversal(root*2+2);
		System.out.print(tree[root] + " ");
	}


	public boolean search(Integer num) {
		return bsearch(0, num);
	}
	
	private boolean bsearch(int root, Integer num) {
		if (tree[root] == null) {
			return false;
		}
		
		if (tree[root] == num) {
			return true;
		} else {
			if (num < tree[root]) {
				return bsearch(root*2+1, num);
			} else {
				return bsearch(root*2+2, num);
			}
		}
	}

}
