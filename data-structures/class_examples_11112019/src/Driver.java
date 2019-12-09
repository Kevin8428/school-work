
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}
	
	public void doIt() {
		
		LinkedTree tree = new LinkedTree();
		
		tree.add(50);
		tree.add(75);
		tree.add(25);
		tree.add(12);
		tree.add(37);
		tree.add(100);
		
		//tree.inOrder();
		System.out.println(tree);
		System.out.println(tree.getLeftMostData());
	}
}