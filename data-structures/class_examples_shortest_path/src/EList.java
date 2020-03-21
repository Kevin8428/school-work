
public class EList {

	
	private Vertex []vertices;
	
	public EList(int count) {
		vertices = new Vertex[count];
		for (int i = 0; i < count; i++) {
			vertices[i] = new Vertex();
			vertices[i].setLabel("V" + i);
		}
	}

	
	public void addEdge(int source, int target, int cost) {
		// TODO Auto-generated method stub

		if (isEdge(source, target)) {
			return;
		}
		
		Edge e = new Edge();
		e.setVertex(target);
		e.setCost(cost);
		e.setNext(vertices[source].getList());
		
		vertices[source].setList(e);
	}

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

	public void setLabel(int source, String label) {
		vertices[source].setLabel(label);
	}

	public String getLabel(int source) {
		return vertices[source].getLabel();
	}

	public int size() {
		return vertices.length;
	}

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
	
	public int getCost(int source, int target) {
		Edge e = vertices[source].getList();
		while(e != null) {
			if (e.getVertex() == target) {
				return e.getCost();
			}
			e = e.getNext();
		}
		return Integer.MAX_VALUE;
	}

	public int[] shortestPath(int start) {
		
		
		int []distance = new int[this.vertices.length];
		boolean []unvisited = new boolean[this.vertices.length];
		
		// Initialize everybody to be unvisited, and of infinite cost
		for (int i = 0; i < distance.length; i++) {
			distance[i] = Integer.MAX_VALUE;
			unvisited[i] = true;
		}
		
		// Set the start/current vertex to cost 0
		int current = start;
		distance[start] = 0;
		
		// Loop until all processable vertices are processed
		boolean done = false;
		while(!done) {
			// Set tentative distances
			int neighbors[] = this.neighbors(current);
			
			// See if a better cost exists, and the vertex is unvisited.
			// if there is a better cost, use it.
			for (int i = 0; i < neighbors.length; i++) {
				if (unvisited[neighbors[i]] == true) {
					int newValue = distance[current] + this.getCost(current, neighbors[i]);
					if (newValue < distance[neighbors[i]]) {
						distance[neighbors[i]] = newValue;
					}
				}
			}
			
			
			// The current node is visited, remove from unvisited set
			// This means we have processed the current node to the fullest possible extent
			unvisited[current] = false;
			
			// select a new current
			// This will be the smallest unvisited vertex
			int curSmall = Integer.MAX_VALUE;
			
			for (int i = 0; i < distance.length; i++) {
				if (unvisited[i]) {
					if (distance[i] < curSmall) {
						curSmall = distance[i];
						current = i;
					}
				}
			}
			
			// See if we should stop
			// We will only stop if there are no more unvisited nodes that contain infinity as a cost
			done = true;
			for (int i = 0; i < distance.length; i++) {
				if (unvisited[i] && (distance[i] != Integer.MAX_VALUE)) {
					// Keep going
					done = false;
				}
			}
		}
		
		return distance;
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
