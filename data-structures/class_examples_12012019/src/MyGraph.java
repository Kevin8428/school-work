
public interface MyGraph {

	void addEdge(int source, int target);

	boolean isEdge(int source, int target);

	void removeEdge(int source, int target);

	void setLabel(int source, String label);

	String getLabel(int source);

	int size();

	int[] neighbors(int vertex);

}