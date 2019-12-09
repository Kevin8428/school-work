
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}
	
	public void doIt() {
		EList graph = new EList(7);
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(1, 3);
		graph.addEdge(2, 0);
		graph.addEdge(3, 0);
		graph.addEdge(3, 5);
		graph.addEdge(3, 6);
		graph.addEdge(6, 1);
		
		graph.DFTraversal(0);

		System.out.println("BF Traversal");
		graph.BFTraversal(0);
//		System.out.println(graph);
//		for (int a:graph.neighbors(3)) {
//			System.out.println(a);
//		}
////		graph.removeEdge(3, 5);
//		System.out.println(graph);
	}

}
