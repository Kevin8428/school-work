
public class MyHeap {
	
	private int[] tree = new int[3200001];
	
	public final static int UNUSED = -1;
	private int numElements = 0;
	
	public MyHeap() {
		for (int i = 0; i < tree.length; i++) {
			tree[i] = UNUSED;
		}
	}
	
	public void add(int data) {
		//System.out.println("Adding " + data);
		tree[numElements++] = data;
		

		int element = numElements-1;
		if (element == 0) { 
			// root of tree
			return;
		}
		
		int parent = (element-1) / 2;
		
		while(tree[element] > tree[parent]) {
			int temp = tree[element];
			tree[element] = tree[parent];
			tree[parent] = temp;
			
			element = parent;
			parent = (element-1) / 2;
		}
	}
	
	public boolean isEmpty() {
		return numElements == 0;
	}
	
	public int remove() {
		int removee = tree[0];
		//System.out.println("Copying " + tree[numElements-1] + " over " + tree[0]);
		tree[0] = tree[--numElements];
		tree[numElements+1] = UNUSED;
		
		int parent = 0;
		int lchild = parent*2 + 1;
		int rchild = parent*2 + 2;
		while((tree[parent] < tree[lchild]) || (tree[parent] < tree[rchild])) {
			int swapee = 0;
			if (tree[lchild] > tree[rchild]) {
				swapee = lchild;
			} else {
				swapee = rchild;
			}
			//System.out.println("Swapping " + tree[swapee] + " and " + tree[parent]);
			int temp = tree[swapee];
			tree[swapee] = tree[parent];
			tree[parent] = temp;
			//System.out.println("After swapping " + this);
		
			parent = swapee;
			lchild = parent*2 + 1;
			rchild = parent*2 + 2;
		}
		
		return removee;
	}
	
	public String toString() {
		if (numElements == 0)
			return "<Empty Heap>";
		
		String rtn = "";
		
		for (int i = 0; i < numElements; i++) {
			rtn += tree[i] + " ";
		}
		
		return rtn;
	}
	

}
