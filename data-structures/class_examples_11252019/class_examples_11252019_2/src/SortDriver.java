
public class SortDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortDriver me = new SortDriver();
		//me.doIt();
		me.doIt2();		
	}
	
	public void doIt() {
		
		int [] array = {23, 12, 45, 67, 2, 98, 89, 1, 129, 132, 138};
		//int [] array = {12, 23, 67, 34, 45};
//		int [] a2 = array.clone();
		int [] a2 = new int[10];
		int num = a2.length;
		for (int i = 0; i < a2.length; i++) {
			a2[i] = num--;
		}
		a2 = array;
		
		re = 0;
		System.out.println(pa(a2, 0, a2.length));
		System.out.println("sorting");
		//quickSort(a2, 0, a2.length-1);
		mergesort(a2, 0, a2.length);
		System.out.println("sorted");
		System.out.println(pa(a2, 0, a2.length));
		
		int [] a3 = {1, 5, 6, 4, 7, 8};

		//mergesort(a3, 0, a3.length);
		//System.out.println(re);
	}
	
	private void doIt2() {
		int []array = new int[3200000];
		int cnt = array.length-1;
		for (int i = 0; i < array.length; i++) {
			array[cnt--] = i+5;
		}
		
		System.out.println("Array is built.");
		long start = System.currentTimeMillis();
		//quickSort(array, 0, array.length-1);
		mergesort(array, 0, array.length);
		long end = System.currentTimeMillis();
		
		System.out.println("Sort took " + (end-start) + " milliseconds");
		
	}

	
	int re = 0;
	public void mergesort(int data[], int first, int n) {
		int n1;
		int n2;
		re++;
		
		if (n > 1) {
			n1 = n / 2;
			n2 = n - n1;
			
			//System.out.println(pa(data, first, n1));
			//System.out.println(pa(data, first+n1, n2));
			mergesort(data, first, n1);
			mergesort(data, first+n1, n2);

			merge(data, first, n1, n2);
		}
	}
	
	private void merge(int[] data, int first, int n1, int n2) {
		
		int [] temp = new int[n1+n2];
		int copied = 0;
		int copied1 = 0;
		int copied2 = 0;
		int i;
//		System.out.println("Merging...");
//		System.out.println(pa(data, first, n1));
//		System.out.println((pa(data, first+n1, n2)));
//		System.out.println();
		
		while ((copied1 < n1) && (copied2 < n2)) {
			if (data[first+copied1] < data[first + n1 + copied2]) 
				temp[copied++] = data[first + (copied1++)];
			else
				temp[copied++] = data[first + n1 + (copied2++)];
		}
		
		// Copy all the left remaining elements.  We don't need to
		// copy the right side, because they are already in in the right places.
		while (copied1 < n1) {
			temp[copied++] = data[first + (copied1++)];
		}
		
		for (i = 0; i < copied; i++) {
			data[first+i] = temp[i];
		}
		
		//System.out.println(pa(data, first, n1+n2));
	}
	
	private String pa(int[] array, int first, int len) {
		String rtn = "";
		
		for (int i = first; i < first+len; i++) {
			if (i < array.length)
				rtn += "[" + i + "] = " + array[i] + ", ";
			if ((i > 0) && ((i % 15) == 0)) {
				rtn += "\n";
			}
 		}
		rtn += "\n";
		return rtn;
	}
	
	public int partition(int arr[], int left, int right)

	{

	      int i = left, j = right;
	      int tmp;
	      int pivot = arr[(left + right) / 2];

	      //System.out.println("Pivot is " + pivot);
	      while (i <= j) {
	            while (arr[i] < pivot)
	                  i++;
	            while (arr[j] > pivot)
	                  j--;

	            if (i <= j) {
	                  tmp = arr[i];
	                  arr[i] = arr[j];
	                  arr[j] = tmp;
	                  i++;
	                  j--;
	            }

	      }

	      return i;

	}

	 

	void quickSort(int arr[], int left, int right) {
		//System.out.println("left " +left + " right " + right);
	      int index = partition(arr, left, right);
	      //System.out.println(pa(arr, left, arr.length));
	      if (left < index - 1)
	            quickSort(arr, left, index - 1);

	      //System.out.println(pa(arr, left, arr.length ));
	      if (index < right)
	            quickSort(arr, index, right);

	}


}
