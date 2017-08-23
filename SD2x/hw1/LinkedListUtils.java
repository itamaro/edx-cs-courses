import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list == null) {
				return;
		}
		if (list.size() == 0 || list.getFirst() >= value) {
			list.addFirst(value);
		} else {
			for (ListIterator<Integer> iter = list.listIterator(); iter.hasNext(); ) {
				Integer element = iter.next();
				if (element >= value) {
					iter.previous();
					iter.add(value);
					return;
				}
			}
			list.addLast(value);
		}

	}

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		if (list == null) {
				return;
		}
		while (N-- > 0) {
						if (list.size() == 0) {
								return;
						}
			String maxElement = list.getFirst();
			for (ListIterator<String> iter = list.listIterator(); iter.hasNext(); ) {
				String element = iter.next();
				if (element.compareTo(maxElement) > 0) {
					maxElement = element;
				}
			}
			list.removeAll(Collections.singleton(maxElement));
		}

	}

	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || one.size() == 0) {
			return false;
		}
		if (two == null || two.size() == 0) {
			return false;
		}
		for (int i = 0; i <= one.size() - two.size(); ++i) {
			boolean result = true;
			for (int j = 0; j < two.size(); ++j) {
				if (one.get(i + j) != two.get(j)) {
					result = false;
					break;
				}
			}
			if (result) {
				return true;
			}
		}
		return false;
	}
}
