
public class OAHash {
	
	private HashObject table[];
	private static final int TABLE_SIZE = 31;
	int count;
	
	public OAHash() {
		table = new HashObject[TABLE_SIZE];
		count = 0;
	}
	
	private int hash(int data) {
		return data % TABLE_SIZE;
	}
	
	private int probe(int loc) {
		return (loc + 1) % TABLE_SIZE;
	}
	
	private boolean tableFull() {
		return count == table.length;
	}
	
	public boolean add(int data) {
		if (intSearch(data) != -1) {
			return true;
		}
		
		if (tableFull()) {
			return false;
		}
		
		int loc = hash(data);
		
		boolean done = false;
		while (!done) {
			if ((table[loc] == null) || (table[loc].isPrevUsed())) {
				// put the data here
				HashObject d = new HashObject();
				d.setData(data);
				table[loc] = d;
				count++;
				done = true;
			}
			
			loc = probe(loc);
		}
		
		return true;
		
	}
	
	public boolean search(int data) {
		return intSearch(data) != -1;
	}
	
	private int intSearch(int data) {
		
		int loc = hash(data);
		
		int cnt = 0;
		
		while ((table[loc] != null) && (cnt < TABLE_SIZE)) {

			cnt++;
			
			if (table[loc].isPrevUsed()) {
			  loc = probe(loc);
			  continue;
			}
			
			if (table[loc].getData() == data) {
				return loc;
			}
			
			loc = probe(loc);
			
		}
		
		return -1;
		
	}
	
	public void delete(int data) {
		int loc = intSearch(data);
		
		if (loc != -1) {
			table[loc].setPrevUsed(true);
			count--;
		}
	}
	
	public String toString() {
		String rtn = "";
		
		for (int i = 0; i < TABLE_SIZE; i++) {
			rtn += "table[" + i + "] = ";
			if (table[i] == null) {
				rtn += "<Never Used> \n";
			} else if (table[i].isPrevUsed()) {
				rtn += "<Prev Used>\n";
			} else {
				rtn += table[i].getData() + "\n";
			}
		}
		
		return rtn;
	}

}
