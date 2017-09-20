
import java.util.*;

public class GreedyDynamicAlgorithms {

  /**
   * Goal: find the smallest number of red intervals to select, such that
   * every blue interval overlaps with at least one of the selected red intervals.
   * Output this number
   *
   * @param red - the list of red intervals
   * @param blue - the list blue intervals
   * @return
   */
  public static int optimalIntervals(ArrayList<Interval> red, ArrayList<Interval> blue) {
    //TODO
    return -1;
  }

  /**
   * Goal: find any path of lowest cost from the top-left of the grid (grid[0][0])
   * to the bottom right of the grid (grid[m-1][n-1]).  Output this sequence of directions
   *
   * @param grid - the 2d grid containing the cost of each location in the grid.
   * @return
   */
  public static List<Direction> optimalGridPath(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] optCosts = new int[m][n];
    Direction[][] optSteps = new Direction[m][n];
    optCosts[0][0] = grid[0][0];
    for (int i = 1; i < m; i++) {
      optCosts[i][0] = optCosts[i - 1][0] + grid[i][0];
      optSteps[i][0] = Direction.DOWN;
    }
    for (int j = 1; j < n; j++) {
      optCosts[0][j] = optCosts[0][j - 1] + grid[0][j];
      optSteps[0][j] = Direction.RIGHT;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        int downCost = optCosts[i - 1][j] + grid[i][j];
        int rightCost = optCosts[i][j - 1] + grid[i][j];
        if (downCost < rightCost) {
          optCosts[i][j] = downCost;
          optSteps[i][j] = Direction.DOWN;
        } else {
          optCosts[i][j] = rightCost;
          optSteps[i][j] = Direction.RIGHT;
        }
      }
    }
    LinkedList<Direction> path = new LinkedList<Direction>();
    int i = m - 1, j = n - 1;
    while (i > 0 || j > 0) {
      Direction nextStep = optSteps[i][j];
      path.addFirst(nextStep);
      if (nextStep == Direction.DOWN) {
        i--;
      } else {
        j--;
      }
    }
    return path;
  }

  /**
   * A simple Direction enum
   * directions can be either DOWN or RIGHT
   * You will output a list of these in the grid-path problem
   */
  public static enum Direction {
    DOWN, RIGHT
  }

  /**
   * A private Interval class to help with the interval question
   */
  public static class Interval {

    int start;
    int finish;

    public Interval(int start, int finish) {
      this.start = start;
      this.finish = finish;
    }

    /**
     * sorts a list of intervals by start time, you are free to use this on the first question
     */
    public static void sortByStartTime(ArrayList<Interval> l) {
      Collections.sort(l, new Comparator<Interval>() {
        public int compare(Interval o1, Interval o2) {
          Interval i1 = (Interval) o1;
          Interval i2 = (Interval) o2;
          return i1.start - i2.start;
        }
      });
    }

    /**
     * sorts a list of intervals by finish time, you are free to use this on the first question
     */
    public static void sortByFinishTime(ArrayList<Interval> l) {
      Collections.sort(l, new Comparator<Interval>() {
        public int compare(Interval o1, Interval o2) {
          Interval i1 = (Interval) o1;
          Interval i2 = (Interval) o2;
          return i1.finish - i2.finish;
        }
      });
    }
  }

}
