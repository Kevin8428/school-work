
public class LinkedQueue {

	private Node head;
	private Node tail;
	private int count;
	
	public boolean add(Integer data) {
		
		Node n = new Node(data);
		
		if (count == 0) {
			// The queue is empty
			head = tail = n;
		} else {
			tail.setNext(n);
			tail = n;
		}
		
		count++;
		return true;
	}
	
	public Integer remove() {
		Integer tmp;
		if (isEmpty()) {
			return null;
		} else {
			tmp = head.getData();
			if (count == 1) {
				head = tail = null;
			} else {
				head = head.getNext();
			}

			count--;
		}
		
		
		return tmp;
	}
	
	public Node getHeadElement() {
		if (isEmpty()) {
			return null;
		} else {
			return head;
		}
	}
 	
	public boolean isEmpty() {
		return count == 0;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public int size() {
		return count;
	}
	
	public String toString() {
		String rtn = "";

		if (isEmpty()) {
			return "<empty>";
		}
		
		Node tmp = head;
		while(tmp != null) {
			rtn += tmp.getData();
			tmp = tmp.getNext();
			if (tmp != null) {
				rtn += " -> ";
			}
		}
		return rtn;
	}
}
