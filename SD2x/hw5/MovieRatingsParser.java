/*
 * SD2x Homework #5
 * Implement the method below according to the specification in the assignment description.
 * Please be sure not to change the method signature!
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class MovieRatingsParser {

	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {
		TreeMap<String, PriorityQueue<Integer>> ratingsMap = new TreeMap<String, PriorityQueue<Integer>>();
    if (allUsersRatings == null) return ratingsMap;
    for (UserMovieRating userRating : allUsersRatings) {
      if (userRating == null) continue;
      String movie = userRating.getMovie();
      Integer rating = userRating.getUserRating();
      if (movie == null || movie.Length() == 0 || rating < 0) continue;
      movie = movie.toLower();
      if (!ratingsMap.containsKey(movie)) {
        ratingsMap.put(movie, new PriorityQueue<Integer>());
      }
      ratingsMap.get(movie).add(rating);
    }
		return ratingsMap;
	}

}
