
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}
	
	public void doIt() {
		
		MazeSolver s = new MazeSolver();
		
		s.solve(0, 0, MazeNode.NORTH);
	}

}
