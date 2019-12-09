
public class EList implements MyGraph{

	
	private Vertex []vertices;
	
	public EList(int count) {
		vertices = new Vertex[count];
		for (int i = 0; i < count; i++) {
			vertices[i] = new Vertex();
			vertices[i].setLabel("V" + i);
		}
	}

	
	@Override
//	public void addEdge(int source, int target) {
//		intAddEdge(source, target);
//		intAddEdge(target, source);
//		
//	}
//	
	public void addEdge(int source, int target) {
		// TODO Auto-generated method stub

		if (isEdge(source, target)) {
			return;
		}
		
		Edge e = new Edge();
		e.setVertex(target);
		e.setNext(vertices[source].getList());
		
		vertices[source].setList(e);
	}

	@Override
	public boolean isEdge(int source, int target) {
		boolean edgeExists = false;
		Edge e = vertices[source].getList();
		while(e != null) {
			if (e.getVertex() == target) {
				edgeExists = true;
				return edgeExists;
			}
			e = e.getNext();
		}
		return edgeExists;
	}

	@Override
	public void removeEdge(int source, int target) {
		if (isEdge(source, target)) {
			Edge prev = null;
			Edge cur = vertices[source].getList();
			while (cur != null) {
				if (cur.getVertex() == target) {
					// This is it...
					if (prev == null) {
						// First or only in list
						vertices[source].setList(cur.getNext());
						return;
					} else {
						prev.setNext(cur.getNext());
					}
				}
				prev = cur;
				cur = cur.getNext();
			}
		}
	}

	@Override
	public void setLabel(int source, String label) {
		vertices[source].setLabel(label);
	}

	@Override
	public String getLabel(int source) {
		return vertices[source].getLabel();
	}

	@Override
	public int size() {
		return vertices.length;
	}

	@Override
	public int[] neighbors(int vertex) {
		int count = 0;
		Edge e = vertices[vertex].getList();
		while (e != null) {
			count++;
			e = e.getNext();
		}
		
		int []result = new int[count];
		
		count = 0;
		e = vertices[vertex].getList();
		while (e != null) {
			result[count++] = e.getVertex();
			e = e.getNext();
		}
		return result;
	}
	
	private boolean []visited;
	public void DFTraversal(int start) {
		visited = new boolean[vertices.length];
		for (int i = 0; i < vertices.length; i++) {
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
	
	public void BFTraversal(int start) {
		for (int i = 0; i < vertices.length; i++) {
			visited[i] = false;
		}
		
		Queue q = new Queue();
		
		// Process the start
		visited[start] = true;
		q.add(start);

		while (!q.isEmpty()) {
			// remove the head element
			Integer hd = q.remove();
			
			int []neighbors = this.neighbors(hd);
			
			for (int i = 0; i < neighbors.length; i++) {
				if (visited[neighbors[i]]) {
					continue;
				} else {
					visited[neighbors[i]] = true;
					System.out.println("Adding " + neighbors[i] + " to the queue");
					q.add(neighbors[i]);
				}
			}
		}
	}
	

	
	public String toString() {
		String rtn = "";
		for (int i = 0; i < vertices.length; i++) {
			rtn += vertices[i].getLabel() + " = <";
			Edge e = vertices[i].getList();
			while(e != null) {
				rtn += e.getVertex() + " ";
				e = e.getNext();
			}
			rtn += ">\n";
		}
		return rtn;
	}
	
}
