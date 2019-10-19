
public class ArrayStack<T> {
	
	private T []stack;
	private int top;
	private static final int STACK_SIZE = 32;
	private int stackSize = STACK_SIZE;
	
	public ArrayStack() {
		stack = (T[]) new Object[stackSize];
		top = 0;
	}
	
	public boolean push(T data) {
		if (isFull()) {
			// resize the stack
			stackSize += STACK_SIZE;
			T []newStack = (T[]) new Object[stackSize];
			for (int i = 0; i < top; i++) {
				newStack[i] = stack[i];
			}
			stack = newStack;
			
		}
		
		stack[top++] = data;
		
		return true;
	}
	
	public T pop() {
		if (isEmpty()) {
			return null;
		}
		
		return stack[--top];
	}
	
	public void clear() {
		top = 0;
		
		// If you want to really clear the stack...
		stack = (T[]) new Object[stackSize];
	}
	
	public int size() {
		return top;
	}
	
	public T peek() {
		if (isEmpty()) {
			return null;
		} else {
			return stack[top-1];
		}
	}
	
	public boolean isFull() {
		return (top == stackSize);
	}
	
	public boolean isEmpty() {
		return (top == 0);
	}
	
	public String toString() {
		String rtn = "";
		
		if (isEmpty()) {
			return "<Empty>";
		}
		
		
		for (int i = top-1; i >= 0; i--) {
			if (i == top-1) {
				rtn += " top -> ";
			} else {
				rtn += "        ";
			}
			rtn += stack[i] + "\n";
			
		}
		
		return rtn;
	}

}
