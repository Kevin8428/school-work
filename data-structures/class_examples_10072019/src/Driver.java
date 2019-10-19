
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver me = new Driver();
		me.doIt2();
	}
	
	public void doIt2() {
		RPNCalc calc = new RPNCalc();
		calc.doIt();
	}
	
	public void doIt() {
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		
		stack.push(12);
		stack.push(13);
		for (int i = 0; i < 32; i++) {
			stack.push(20+i);
		}
		System.out.println(stack);
		
		System.out.println("Stack size = " + stack.size());
		while (!stack.isEmpty()) {
			stack.pop();
		}
		
		System.out.println(stack);
	}

}
