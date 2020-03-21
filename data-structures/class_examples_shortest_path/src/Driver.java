
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}
	
	public void doIt() {
		EList graph = new EList(7);
		
		graph.addEdge(0, 1, 5);
		graph.addEdge(0, 2, 2);
		graph.addEdge(0, 5, 5);
		graph.addEdge(1, 4, 2);
		graph.addEdge(1, 5, 4);
		graph.addEdge(2, 4, 3);
		graph.addEdge(3, 2, 3);
		graph.addEdge(3, 6, 4);
		graph.addEdge(4, 6, 2);
		graph.addEdge(5, 6, 3);
		
		//graph.DFTraversal(0);

		//System.out.println("BF Traversal");
		//graph.BFTraversal(0);
		
		int cost[][] = new int[7][];
		
		for (int i = 0; i < graph.size(); i++) {
			cost[i]= graph.shortestPath(i);
		}
		
		for(int x = 0; x < cost[0].length; x++) {
			System.out.print("From V" + x + "\t");
			for (int y = 0; y < cost[0].length; y++) {
				System.out.print("V"+ y + " = " );
				if (cost[x][y] == Integer.MAX_VALUE) {
					System.out.print("\u221E ");
				} else {
					System.out.print(cost[x][y] + " ");
				}
			}
			System.out.println();
		}


	}

}
