/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.*;


public class MovieRatingsProcessor {

	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {
		List<String> movies = new LinkedList<String>();
		if (movieRatings == null) return movies;
		for (String movie : movieRatings.keySet()) {
			movies.add(movie);
		}
		return movies;
	}

	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		List<String> movies = new LinkedList<String>();
		if (movieRatings == null) return movies;
		for (Map.Entry<String, PriorityQueue<Integer>> entry : movieRatings.entrySet()) {
			if (entry.getValue().peek() > rating)
				movies.add(entry.getKey());
		}
		return movies;
	}

	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {
		TreeMap<String, Integer> removedRatings = new TreeMap<String, Integer>();
		if (movieRatings == null) return removedRatings;
		Iterator<Map.Entry<String, PriorityQueue<Integer>>> iter = movieRatings.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, PriorityQueue<Integer>> entry = iter.next();
			int numRemoved = 0;
			PriorityQueue<Integer> ratings = entry.getValue();
			while (!ratings.isEmpty() && ratings.peek() < rating) {
				ratings.remove();
				numRemoved++;
			}
			if (ratings.isEmpty()) iter.remove();
			if (numRemoved > 0) removedRatings.put(entry.getKey(), numRemoved);
		}
		return removedRatings;
	}

}
