package test;

public class Node {
	private String word;
	private Node lChild;
	private Node rChild;
	private int count;
	
	public String getWord() {
		return word;
	}
	
	public int getCount() {
		return count;
	}

	public void increment() {
		this.count++;
	}
	
	public void setWord(String word) {
		this.word = word;
		this.count++;
	}
	
	public void setrChild(Node word) {
		this.rChild = word;
	}
	
	public void setlChild(Node word) {
		this.lChild = word;
	}
	
	public Node getlChild() {
		return lChild;
	}
	
	public Node getrChild() {
		return rChild;
	}
	
	public boolean isAfterthisWord(String nextWord) {
		return word.compareTo(nextWord) > 0;
	}
}
