
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt2();
	}

	public void doIt() {
		
		LinkedQueue q = new LinkedQueue();
		
		q.add(10);
		q.add(20);
		q.add(30);
		q.add(40);
		q.add(50);
		q.add(60);
		System.out.println(q);
		q.remove();
		q.remove();
		q.remove();
		System.out.println(q);
		q.add(70);
		System.out.println(q);
		q.add(80);
		System.out.println(q);
	}
	
	public void doIt2() {
		GrocerySimulation sim = new GrocerySimulation();
		
		sim.setup();
		sim.run();
	}
}
