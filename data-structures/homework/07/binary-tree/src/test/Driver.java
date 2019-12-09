package test;
import edu.bu.met.cs342a1.TextParser;

public class Driver {
	public static void main(String[] args) {
		Tree tree = new Tree();
		TextParser parser = new TextParser(); 
		parser.openFile("dracula.txt");
		String cur = "";
		while (cur != null) {
			cur = parser.getNextWord();
			
			if (cur != null) {
				tree.add(cur);				
			}
		};
		System.out.println("text contains: " + String.valueOf(tree.count())+" total words.");
		
		tree.find("transylvania");
		tree.find("harker");
		tree.find("renfield");
		tree.find("vampire");
		tree.find("expostulate");
		tree.find("fang");
		
		tree.frequentest();
		System.out.println("number of unique words: " + String.valueOf(tree.unique()));
		System.out.println("word at root is: '" + String.valueOf(tree.rootWord())+"'");
		System.out.println("tree is: " + tree.depth()+" nodes deep");
		System.out.println("total word count: " + String.valueOf(tree.count()));
		System.out.println("most frequent word is '" + tree.frequentest.getWord() + "'; used " +String.valueOf(tree.frequentest.getCount()) + " times.");
		
		System.out.print("First 20 words pre order words: ");
		tree.preOrder();
		System.out.println();
		System.out.print("First 20 words post order words: ");
		tree.postOrder();
		System.out.println();
		System.out.print("First 20 words in order words: ");
		tree.inOrder();
		System.out.println();
    }
}
