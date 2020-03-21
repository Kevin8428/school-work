
public class Edge {
	private int vertex; 
	private int cost;
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
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
