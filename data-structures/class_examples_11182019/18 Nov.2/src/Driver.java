
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt2();
	}
	
	public void doIt() {
		
		OAHash table = new OAHash();
		
		System.out.println(table);
		table.add(12);
		table.add(12+31);
		table.add(12+31+31);
		System.out.println(table);
		
		table.delete(12+31);
		System.out.println(table);
		
		table.add(12+31+31+31);
		System.out.println(table);
		
		System.out.println(table.search(105));
		System.out.println(table.search(15));
	}
	
	public void doIt2() {
		ChainedHash table = new ChainedHash();
		
		System.out.println(table);
		table.add(7);
		table.add(7+31);
		table.add(7+31+31);
		System.out.println(table);
		
		table.delete(7+31);
		System.out.println(table);
	}

}
