
public class Sorting {

	/**
	 * Implement the mergesort function, which should sort the array of
	 * integers in place
	 *
	 * You will probably want to use helper functions here, as described in the lecture recordings
	 * (ex. merge(), a helper mergesort function)
	 * @param arr
	 */
	public static void mergeSort(CompareInt[] arr) {
		mergeSortHelper(arr, 0, arr.length - 1);
	}

	private static void mergeSortHelper(CompareInt[] arr, int low, int high) {
		if (low >= high) return;
		int midPoint = (high + low) / 2;
		mergeSortHelper(arr, low, midPoint);
		mergeSortHelper(arr, midPoint + 1, high);
		merge(arr, low, midPoint + 1, high);
	}

	private static void merge(CompareInt[] arr, int low, int midPoint, int high) {
		int n = high - low + 1;
		CompareInt[] tmp = new CompareInt[n];
		System.arraycopy(arr, low, tmp, 0, n);
		int left = 0, right = midPoint - low;
		for (int i = low; i <= high;) {
			if (tmp[left].compareTo(tmp[right]) < 0) {
				arr[i++] = tmp[left++];
				if (left >= midPoint - low) {
					while (i <= high) arr[i++] = tmp[right++];
				}
			} else {
				arr[i++] = tmp[right++];
				if (right >= n) {
					while (i <= high) arr[i++] = tmp[left++];
				}
			}
		}
	}

	/**
	 * Implement the quickSelect
	 *
	 * Again, you will probably want to use helper functions here
	 * (ex. partition(), a helper quickselect function)
	 */
	public static CompareInt quickSelect(int k, CompareInt[] arr) {
		if (k > arr.length) return null;
		return quickSelectHelper(k, arr, 0, arr.length - 1);
	}

	private static CompareInt quickSelectHelper(int k, CompareInt[] arr, int low, int high) {
		int p = partition(arr, low, high);
		if (k == p + 1) return arr[p];
		if (k <= p) {
			return quickSelectHelper(k, arr, low, p - 1);
		}
		return quickSelectHelper(k, arr, p + 1, high);
	}

	private static int partition(CompareInt arr[], int low, int high) {
		CompareInt pivot = arr[high];  // always partition on last element
		for (int i = low; i < high; i++) {
			if (arr[i].compareTo(pivot) < 0) {
				if (i != low)
					swap(arr, i, low);
				low++;
			}
		}
		swap(arr, low, high);
		return low;
	}

	private static void swap(CompareInt arr[], int i, int j) {
		CompareInt tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
