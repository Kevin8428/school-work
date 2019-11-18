
public class ArrayQueue {
	
	private int head;
	private int tail;
	private int count;
	private Integer []queue;
	
	private static final int QUEUE_SIZE = 16;
	
	public ArrayQueue() {
		queue = new Integer[QUEUE_SIZE];
		head = tail = count = 0;
	}
	
	public ArrayQueue(int size) {
		queue = new Integer[size];
		head = tail = count = 0;
	}
	
	public boolean add(Integer data) {
		if (isFull()) {
			return false;
		}
		
		queue[tail] = data;
		tail++;
		
		if (tail == queue.length) {
			tail = 0;
		}
		
		count++;
		return true;
	}
	
	public Integer remove() {
		if (isEmpty()) {
			return null;
		}
		
		Integer tmp = queue[head];
		head++;
		
		if (head == queue.length) {
			head = 0;
		}
		
		count--;
		return tmp;
		
	}
	
	public boolean isFull() {
		return (count == queue.length);
	}
	
	public boolean isEmpty() {
		return (count == 0);
	}
	
	public int size() {
		return count;
	}
	
	public String toString() {
		String rtn = "";
		rtn += "count = " + count + "\n";
		rtn += "head  = " + head + "\n";
		rtn += "tail  = " + tail + "\n";
		
		if (isEmpty()) {
			return "<empty>";
		}
		int tmp = head;
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				rtn += "head -> ";
			} else {
				if (i == count-1) {
					rtn += "tail -> ";
				} else {
					rtn += "        ";
				}
			}
			
			rtn += queue[tmp++] + "\n";
			
			if (tmp == queue.length) {
				tmp = 0;
			}
			
		}
		
		return rtn;
	}

}
