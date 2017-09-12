import java.util.*;

/*
 * SD2x Homework #6
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class GraphUtils {

	public static int minDistance(Graph graph, String src, String dest) {
		if (graph == null || src == null || dest == null) return -1;
		if (!graph.containsElement(src)) return -1;
		return new BreadthFirstSearch(graph).bfs(graph.getNode(src), dest);
	}

	public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
		if (graph == null || src == null || distance < 1 || !graph.containsElement(src)) return null;
		Set<String> nodes = new HashSet<String>();
		Map<Node, Integer> marked = new HashMap<Node, Integer>();
		Queue<Node> toExplore = new LinkedList<Node>();
		Node start = graph.getNode(src);
		marked.put(start, 1);
		toExplore.add(start);
		while (!toExplore.isEmpty()) {
			Node current = toExplore.remove();
			Integer dist = marked.get(current);
			for (Node neighbor : graph.getNodeNeighbors(current)) {
				if (!marked.containsKey(neighbor)) {
					nodes.add(neighbor.getElement());
					marked.put(neighbor, dist + 1);
					if (dist < distance) toExplore.add(neighbor);
				}
			}
		}
		return nodes;
	}

	public static boolean isHamiltonianPath(Graph g, List<String> values) {
		if (g == null || values == null || values.isEmpty() ||
			  values.size() != g.getNumNodes() + 1) return false;
		String start = values.remove(0);
		if (!g.containsElement(start)) return false;
		Node current = g.getNode(start);
		String value = null;
		while (!values.isEmpty()) {
			value = values.remove(0);
			if (!g.containsElement(value)) return false;
			Node node = g.getNode(value);
			if (!g.getNodeNeighbors(current).contains(node)) return false;
			current = node;
		}
		return value == start;
	}

}
