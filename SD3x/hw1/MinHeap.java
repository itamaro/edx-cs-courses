

/**
 * A Heap implementation class
 *
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {

	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}

	/**
	 * Adds an element to the heap
	 *
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
		if (size >= heap.length - 1) throw new IllegalArgumentException();
		heap[++size] = val;
		swim();
	}

	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
		CompareInt ret = heap[1];
		heap[1] = heap[size--];
		sink();
		return ret;
	}

	private void swim() {
		int cur = size, parent = size / 2;
		while (parent > 0) {
			if (heap[parent].compareTo(heap[cur]) <= 0) return;
			swap(parent, cur);
			cur = parent;
			parent = cur / 2;
		}
	}

	private void sink() {
		int cur = 1;
		while (cur < size) {
			int minChild = getMinChild(cur);
			if (minChild == 0 || heap[cur].compareTo(heap[minChild]) <= 0) return;
			swap(cur, minChild);
			cur = minChild;
		}
	}

	private void swap(int i, int j) {
		CompareInt tmp = heap[i];
		heap[i] = heap[j];
		heap[j] = tmp;
	}

	private int getMinChild(int index) {
		int left = 2 * index, right = 2 * index + 1;
		if (left > size) return 0;
		if (right > size) return left;
		return (heap[left].compareTo(heap[right]) < 0 ? left : right);
	}

}
