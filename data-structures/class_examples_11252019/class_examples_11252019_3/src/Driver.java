
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver me = new Driver();
		//me.doIt();
		me.doIt2();
	}
	
	private int []data = {99, 12, 3, 5, 18, 23, 15, 1, 9, 13, 14, 51, 82, 8, 66}; 
	public void doIt() {
		System.out.println("Bubble Sort\n");
		bubbleSort(data);
		System.out.println("More efficient Bubble Sort\n");
		bubbleSortMoreEfficient(data);
		System.out.println("Selection Sort \n");
		selectionSort(data);
		System.out.println("Insertion Sort\n");
		insertionSort(data);
	}
	
	private int[] cloneArray(int [] old) {
		int []newArray = new int[old.length];
		
		for (int i = 0; i < old.length; i++) {
			newArray[i] = old[i];
		}
		
		return newArray;
	}
	
	private void doIt2() {
		int []array = new int[320000];
		int cnt = array.length-1;
		for (int i = 0; i < array.length; i++) {
			array[cnt--] = i+5;
		}
		
		System.out.println("Array is built.");
		long start = System.currentTimeMillis();
		largeBubbleSort(array);
		long end = System.currentTimeMillis();
		
		System.out.println("Sort took " + (end-start) + " milliseconds");
		
	}
	
	private void printArray(int []array) {
		for (int i = 0; i < array.length-1; i++) {
			System.out.print(array[i] + ", " );
		}
		System.out.println(array[array.length-1]);
	}
	
	public void bubbleSortMoreEfficient(int []data) {
		int array[] = cloneArray(data);
		printArray(data);
		System.out.println();
		
		int count = 0;
		boolean swapsOccurred = false;
		
		for (int i = 0; i < array.length; i++) {
			swapsOccurred = false;
			for (int j = 0; j < array.length-1-i; j++) {
				count++;
				if (array[j] > array[j+1]) {
					// swap them
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
					swapsOccurred = true;
				}
			}
			
			if (!swapsOccurred) {
				break;
			}
			printArray(array);
		}
		
		System.out.println();
		printArray(array);
		System.out.println("Bubble Sort took " + count + " comparisons");
		
		
	}
	
	public void largeBubbleSort(int []array) {
		//int array[] = cloneArray(data);
		//printArray(data);
		//System.out.println();
		
		long count = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-1; j++) {
				count++;
				if (array[j] > array[j+1]) {
					// swap them
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
			
			//printArray(array);
		}
		
		//System.out.println();
		//printArray(array);
		System.out.println("Bubble Sort took " + count + " comparisons");
		
		
	}

	
	public void bubbleSort(int []data) {
		int array[] = cloneArray(data);
		printArray(data);
		System.out.println();
		
		int count = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-1; j++) {
				count++;
				if (array[j] > array[j+1]) {
					// swap them
					int tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
			
			printArray(array);
		}
		
		System.out.println();
		printArray(array);
		System.out.println("Bubble Sort took " + count + " comparisons");
		
		
	}
	

	public void selectionSort(int []data) {
		int array[] = cloneArray(data);
		printArray(data);
		System.out.println();
		int biggestIndex = 0;
		int swapee = array.length-1;
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			
			biggestIndex = 0;
			
			for (int j = 0; j < array.length-i; j++) {
				count++;
				if (array[j] > array[biggestIndex]) {
					biggestIndex = j;
				}
			}
			// Swap them
			int tmp = array[biggestIndex];
			array[biggestIndex] = array[swapee];
			array[swapee] = tmp;
			swapee--;
		}
		System.out.println();
		printArray(array);
		System.out.println("Selection Sort took " + count + " comparisons");

	}
	
	
	public void insertionSort(int []data) {
		int array[] = cloneArray(data);
		int []newArray = new int[data.length];
		printArray(data); System.out.println();
		int biggestIndex = 0;
		int swapee = array.length-1;
		int count = 0;
		int smallestIndex = 0;
		
		for (int i = 0; i < array.length; i++) {
			
			biggestIndex = 0;
			
			for (int j = 0; j < array.length; j++) {
				count++;
				if (array[j] > array[biggestIndex]) {
					biggestIndex = j;
				}
			}
			
			// insert this into the new array
			newArray[swapee] = array[biggestIndex];
			// make this guy unattractive to the algorithm.
			array[biggestIndex] = Integer.MIN_VALUE;
			swapee--;
		}
		
		System.out.println();
		printArray(newArray);
		System.out.println("Insertion Sort took " + count + " comparisons");

	}

}
