import java.util.Scanner;

public class RPNCalc {


	public void doIt() {
		Scanner kbd = new Scanner(System.in);
		ArrayStack<Double> stack = new ArrayStack<Double>();

		boolean done = false;
		String input;
		while (!done) {
			System.out.println("Enter command, data or ? for help");
			System.out.print("-> ");
			input = kbd.nextLine();

			String mappedInput = input.toUpperCase().trim();

			switch(mappedInput) {
				case "?":
					System.out.println("Welcome to help!");
					System.out.println("Nothing to see here, move along");
				break;

				case "Q":
					done = true;
				break;

				case "D":
					System.out.println(stack);
				break;
				
				case "P":
				case "=":
					System.out.println(stack.peek());
					break;
					
				case "C":
					stack.clear();
					break;
				
				case "+":
				case "PLUS":
					if (stack.size() < 2) {
						System.out.println("ERROR: Insufficient values on stack");
					} else {
						Double a = stack.pop();
						Double b = stack.pop();
						Double c = a + b;
						stack.push(c);
					}
					break;

				case "-":
					if (stack.size() < 2) {
						System.out.println("ERROR: Insufficient values on stack");
					} else {
						Double a = stack.pop();
						Double b = stack.pop();
						Double c = b - a;
						stack.push(c);
					}
					break;

				case "*":
					if (stack.size() < 2) {
						System.out.println("ERROR: Insufficient values on stack");
					} else {
						Double a = stack.pop();
						Double b = stack.pop();
						Double c = a * b;
						stack.push(c);
					}
					break;

				case "/":
					if (stack.size() < 2) {
						System.out.println("ERROR: Insufficient values on stack");
					} else {
						Double a = stack.pop();
						Double b = stack.pop();
						
						if (a == 0.0) {
							System.out.println("ERROR: Division by 0");
							stack.push(b);
							stack.push(a);
						} else {
							Double c = b / a;
							stack.push(c);
						}
					}
					break;

				default: 
				{
					Double val;

					try {
						val = Double.parseDouble(mappedInput);
						stack.push(val);
					} catch (NumberFormatException e) {
						System.out.println("Invalid command (" + input + ")");
					}
					break;
				}
			}
		}
	}

}
