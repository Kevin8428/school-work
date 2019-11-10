
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}

	
	public void doIt() {
		
		System.out.println(fact2(5));
		printNum(1, 5);
		// System.out.println(fib(3));
		
	}
	
	public int fact(int n) {
		if ((n == 0) || (n == 1))
			return 1;
		
		return n * fact(n-1);
	}

	public int fact2(int n) {
		if ((n == 0) || (n == 1)) {
			System.out.println(n);
			return 1;
		}
		
		System.out.println("Before: " + n);
		int x = n * fact2(n-1);
		System.out.println("After: " + n);
		return x;
	}
	
	public void printNum(int cur, int end) {
		if (cur == end) {
			System.out.println("Base: " + (cur-1));
			return;
		}
		System.out.println("Before: " + cur);
		printNum(cur+1, end);
		System.out.println("After: " + cur);
		
	}
	
	public int fib(int n) {
		if ((n == 1) || (n == 2)) {
			return 1;
		}
		
		return fib(n-1) + fib(n-2);
	}
}
 