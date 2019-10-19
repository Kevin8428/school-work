
public class LinkedStack implements MyStack {

	private Node top;
	private int count;
	
	public LinkedStack() {
		clearStack();	
	}
	
	private void clearStack() {
		top = null;
		count = 0;
	}
	
	@Override
	public boolean push(Integer data) {
		Node n = new Node();
		n.setData(data);
		n.setNext(top);
		top = n;
		count++;
		return true;
	}

	@Override
	public Integer pop() {
		if (isEmpty()) {
			return null;
		}
		
		Integer rtn = top.getData();
		top = top.getNext();
		count--;
		return rtn;
	}

	@Override
	public void clear() {
		clearStack();
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Integer peek() {
		if (isEmpty()) {
			return null;
		} else {
			return top.getData();
		}
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (count == 0);
		// return (top == null);
	}

	
	public String toString() {
		String rtn = "";
		
		if (isEmpty()) {
			return "<Empty>";
		}
		
		Node tmp = top;
		while(tmp != null) {
			if (tmp == top) {
				rtn += " top -> ";
			} else {
				rtn += "        ";
			}
			rtn += tmp.getData() + "\n";
			tmp = tmp.getNext();
		}
		
		return rtn;
	}

}
