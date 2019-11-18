import java.util.Random;
import java.util.Scanner;

public class GrocerySimulation {

	private LinkedQueue line[];
	private int count;
	private int transactionMax = 20;
	private int maxArrivalTime = 2;
	private int duration = 600;
	private int longest = 0;
	private int custCount = 0;
	
	public void setup() {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Welcome to the store simulator");
		System.out.println();
		System.out.print("Enter number of cashiers -> ");
		count = kbd.nextInt();
		kbd.nextLine();
		
		line = new LinkedQueue[count];
		for (int i = 0; i < line.length; i++) {
			line[i] = new LinkedQueue();
		}
	}
	
	public void run() {
		
		int nextArrivalTime;
		Random gen = new Random();
		
		nextArrivalTime = gen.nextInt(maxArrivalTime) + 1;
		
		for (int time = 0; time < duration; time++) {
			
			// Decrement the timers
			for (int i = 0; i < line.length; i++) {
				if (line[i].isEmpty()) {
					continue;
				} else {
					
					if (line[i].size() > longest) {
						longest = line[i].size();
					}
					
					Node n = line[i].getHeadElement();
					n.setData(n.getData()-1);
					if (n.getData() <= 0) {
						line[i].remove();
					}
				}
			}
			
			if (nextArrivalTime == time) {
				custCount++;
				
				// calculate when the next person will show up
				nextArrivalTime = gen.nextInt(maxArrivalTime) + 1 + time;
				
				// find the shortest queue
				int shortest = 0;
				for (int i = 0; i < line.length; i++) {
					if (line[i].size() < line[shortest].size()) {
						shortest = i;
					}
				}
				
				line[shortest].add(gen.nextInt(transactionMax) + 1);
			}
		}
		
		// all done print out stats
		System.out.println("simulation ended");
		System.out.println("Total customers = " + custCount);
		System.out.println("Longest line was " + longest + " customers");
	}
}
