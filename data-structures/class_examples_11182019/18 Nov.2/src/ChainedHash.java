
public class ChainedHash {
	
	private Node table[];
	private static final int TABLE_SIZE = 31;
	
	public ChainedHash() {
		table = new Node[TABLE_SIZE];
		
	}
	
	private int hash(int data) {
		return data % TABLE_SIZE;
	}
	
	public void add(int data) {
		Node n = new Node();
		n.setData(data);
		
		int loc = hash(data);
		
		if (table[loc] == null) {
			table[loc] = n;
			return;
		} else {
			n.setNext(table[loc]);
			table[loc] = n;
		}
	}
	
	public boolean search(int data) {
		int loc = hash(data);
		
		Node tmp = table[loc];
		
		while (tmp != null) {
			if (tmp.getData() == data) {
				return true;
			}
			
			tmp = tmp.getNext();
		}
		
		return false;
	}
	
	public void delete(int data) {
		
		int loc = hash(data);
		
		Node cur = table[loc];
		Node prev = null;

		while (cur != null) {
			if (cur.getData() == data) {
				if (prev == null) {
					table[loc] = table[loc].getNext();
				} else {
					prev.setNext(cur.getNext());
				}
				return;
			}
			
			prev = cur;
			cur = cur.getNext();
		}
		
	}
	
	public String toString() {
		String rtn = "";
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			rtn += "table[" + i + "] = ";
			if (table[i] == null) {
				rtn += "<Empty>\n";
			} else {
				Node n = table[i];
				while (n != null) {
					rtn += " " + n.getData();
					n = n.getNext();
				}
				rtn += "\n";
			}
		}
		
		return rtn;
	}

}
