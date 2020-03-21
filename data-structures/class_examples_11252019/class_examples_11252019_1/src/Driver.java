
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		//me.doIt();
		me.doIt();
	}
	
	public void doIt() {

		MyHeap heap = new MyHeap();
		
		heap.add(34);
		heap.add(36);
		heap.add(3);
		heap.add(4);
		heap.add(345);
		heap.add(22);
		heap.add(311);
		heap.add(56);
		heap.add(6);
		heap.add(120);
		heap.add(145);
		heap.add(567);
		heap.add(888);
		heap.add(3999);
		
		System.out.println("Heap..." + heap);
		
		while (!heap.isEmpty()) {
			System.out.println(heap.remove());
		}

	}

	public void doIt2() {

		MyHeap heap = new MyHeap();

		int []array = new int[3200000];
		int cnt = array.length-1;
		System.out.println("Array is built.");
		long start = System.currentTimeMillis();
		for (int i = 0; i < array.length; i++) {
			//array[cnt--] = i+5;
			heap.add(i+5);
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Sort took " + (end-start) + " milliseconds");

		
//		while (!heap.isEmpty()) {
		for (int i = 0; i < 100; i++) {
			System.out.println(heap.remove());
		}

	}

}
