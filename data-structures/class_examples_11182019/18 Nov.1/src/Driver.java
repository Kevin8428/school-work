// linear serach, binary search
public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Driver me = new Driver();
		me.doIt();
	}

	static final int ARRAY_SIZE = 1000000000;
	public int array[];
	
	public Driver() {
		array = new int[ARRAY_SIZE];
		for (int i = 0; i < array.length; i++) {
			array[i] = i*2;
		}
	}

	public void doIt() {
//		System.out.println(linearSearch(array, 24));
//		System.out.println(binSrch(array, 24));
//		System.out.println(recBinSrch(array, 24, 0, array.length-1));

		long start = System.currentTimeMillis();
		System.out.println(linearSearch(array, ARRAY_SIZE/2-1));
		long stopOne = System.currentTimeMillis();
		System.out.println(binSrch(array, ARRAY_SIZE/2-1));
		long stopTwo = System.currentTimeMillis();
		
		System.out.println("Iter took " + (stopOne-start) + " Recur took " + (stopTwo - stopOne));
//		System.out.println(recBinSrch(array, ARRAY_SIZE/2-1, 0, array.length-1));
}


	public int linearSearch(int array[], int data) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == data) {
				return i;
			}
		}
		return -1;
	}

	public int binSrch(int array[], int data) {
		int lo = 0;
		int hi = array.length-1;
		int mid;

		while (lo <= hi) {
			mid = (lo + hi) /2;

			if (array[mid] == data) {
				return mid;
			}

			if (data > array[mid]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		return -1;
	}


	public int recBinSrch(int array[], int data, int lo, int hi) {
		if (lo > hi) {
			return -1;
		}

		int mid = (lo + hi) / 2;

		if (array[mid] == data) {
			return mid;
		}

		if (data > array[mid]) {
			return recBinSrch(array, data, mid + 1, hi);
		} else {
			return recBinSrch(array, data, lo, mid - 1);
		}

	}

}
