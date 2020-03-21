
public class AMatrix implements MyGraph {
	
	boolean [][]graph;
	String []labels;
	
	public AMatrix(int vertices) {
		graph = new boolean[vertices][vertices];
		labels = new String[vertices];
		for (int i = 0; i < vertices; i++) {
			labels[i] = "V"+i;
		}
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int source, int target) {
		graph[source][target] = true;
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#isEdge(int, int)
	 */
	@Override
	public boolean isEdge(int source, int target) {
		return graph[source][target];
	}

	/* (non-Javadoc)
	 * @see MyGraph#removeEdge(int, int)
	 */
	@Override
	public void removeEdge(int source, int target) {
		graph[source][target] = false;
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#setLabel(int, java.lang.String)
	 */
	@Override
	public void setLabel(int source, String label) {
		labels[source] = label;
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#getLabel(int)
	 */
	@Override
	public String getLabel(int source) {
		return labels[source];
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#size()
	 */
	@Override
	public int size() {
		return labels.length;
	}
	
	/* (non-Javadoc)
	 * @see MyGraph#neighbors(int)
	 */
	@Override
	public int[] neighbors(int vertex) {
		int count = 0;
		for (int i = 0; i < labels.length; i++) {
			if (graph[vertex][i] == true) {
				count++;
			}
		}
		
		int []result = new int[count];
		
		count = 0;
		for (int i = 0; i < labels.length; i++) {
			if (graph[vertex][i] == true) {
				result[count++] = i;
			}
		}
		
		return result;
	}
	
	private boolean []visited;
	public void DFTraversal(int start) {
		visited = new boolean[labels.length];
		for (int i = 0; i < labels.length; i++) {
			visited[i] = false;
		}
		
		recDFTraversal(start);
	}
	
	private void recDFTraversal(int start) {
		// stop if already visited
		if (visited[start]) {
			return;
		}
		
		// mark ourselves as visited
		visited[start] = true;
		System.out.println("Visited " + getLabel(start));
		
		int []neighbors = neighbors(start);
		for (int i = 0; i < neighbors.length; i++) {
//			if (!visited[neighbors[i]]) {
				recDFTraversal(neighbors[i]);
	//		}
		}
		
	}
	
	public String toString() {
		String rtn = "";
		
		for (int vCount = 0; vCount < labels.length; vCount++) {
			rtn += labels[vCount] + " = <";
			for (int eCount = 0; eCount < labels.length; eCount++) {
				if (graph[vCount][eCount] == true) {
					rtn += eCount + " ";
				}
			}
			rtn += ">\n";
		}
		
		return rtn;
	}
}
