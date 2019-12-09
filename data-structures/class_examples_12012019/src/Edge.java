
public class Edge {
	private int vertex; 
	
	public int getVertex() {
		return vertex;
	}
	public void setVertex(int vertex) {
		this.vertex = vertex;
	}
	public Edge getNext() {
		return next;
	}
	public void setNext(Edge next) {
		this.next = next;
	}
	private Edge next;
}
