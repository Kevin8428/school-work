
public class Queue {
	
	private Node head;
	private Node tail;
	private int count = 0;
	
	public void add(Integer vertex) {
		Node n = new Node();
		n.setVertex(vertex);
		
		if (count == 0) {
			head = tail = n;
			count = 1;
			return;
		} else {
			tail.setNext(n);
			count++;
			tail = n;
		}
	}
	
	public Integer remove() {
		
		if (count == 0) {
			return null;
		}
		
		Integer rtn = head.getVertex();
		
		head = head.getNext();
		count--;
		
		if (count == 0) {
			tail = head = null;
		}
		
		return rtn;
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}
}
