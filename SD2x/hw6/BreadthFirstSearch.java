import java.util.*;

/*
 * SD2x Homework #6
 * This is an implementation of Breadth First Search (BFS) on a graph.
 * You may modify and submit this code if you'd like.
 */

public class BreadthFirstSearch {
	protected Map<Node, Integer> marked;
	protected Graph graph;

	public BreadthFirstSearch(Graph graphToSearch) {
		marked = new HashMap<Node, Integer>();
		graph = graphToSearch;
	}

	/**
	 * This method was discussed in the lesson
	 */
	public int bfs(Node start, String elementToFind) {
		if (!graph.containsNode(start)) {
				return -1;
		}
		if (start.getElement().equals(elementToFind)) {
			return 0;
		}
		Queue<Node> toExplore = new LinkedList<Node>();
		marked.put(start, 0);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			Integer dist = marked.get(current);
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.containsKey(neighbor)) {
					if (neighbor.getElement().equals(elementToFind)) {
						return dist + 1;
					}
					marked.put(neighbor, dist + 1);
					toExplore.add(neighbor);
				}
			}
		}
		return -1;
	}


}
