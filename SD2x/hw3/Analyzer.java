import java.io.*;
import java.util.*;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {

  /*
   * Implement this method in Part 1
   */
  public static List<Sentence> readFile(String filename) {
    List<Sentence> list = new LinkedList<Sentence>();
    if (filename == null) return list;
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      for(String line; (line = br.readLine()) != null; ) {
        line = line.trim();
        if (line.length() == 0) continue;
        String arr[] = line.split(" ", 2);
        Integer score;
        try {
          score = Integer.parseInt(arr[0]);
        } catch (NumberFormatException e) {
          continue;
        }
        if (score < -2 || score > 2) continue;
        String text = arr[1].trim();
        if (text.length() == 0) continue;
        list.add(new Sentence(score, text));
      }
    }
    catch (Exception e) {
        return list;
    }

    return list;

  }

  /*
   * Implement this method in Part 2
   */
  public static Set<Word> allWords(List<Sentence> sentences) {
    if (sentences == null) return new HashSet<Word>();
    Map<String, Word> wordsMap = new HashMap<String, Word>();
    for (Sentence sentence : sentences) {
        if (sentence == null) continue;
        String text = sentence.getText();
        if (text == null) continue;
        StringTokenizer st = new StringTokenizer(text);
        while (st.hasMoreTokens()) {
          String token = st.nextToken().toLowerCase();
          char c = token.charAt(0);
          if (c < 'a' || c > 'z') continue;
          if (!wordsMap.containsKey(token)) {
              wordsMap.put(token, new Word(token));
          }
          Word word = wordsMap.get(token);
          word.increaseTotal(sentence.getScore());
        }
    }
    return new HashSet<Word>(wordsMap.values());
  }

  /*
   * Implement this method in Part 3
   */
  public static Map<String, Double> calculateScores(Set<Word> words) {
      Map<String, Double> scoreMap = new HashMap<String, Double>();
      if (words == null) return scoreMap;
      for (Word word : words) {
          if (word == null) continue;
          scoreMap.put(word.getText(), word.calculateScore());
      }
      return scoreMap;
  }

  /*
   * Implement this method in Part 4
   */
  public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
      if (wordScores == null  || sentence == null) return 0.0;
      Double accScore = 0.0;
      Integer wordCount = 0;
      StringTokenizer st = new StringTokenizer(sentence);
      while (st.hasMoreTokens()) {
          String token = st.nextToken().toLowerCase();
          char c = token.charAt(0);
          if (c < 'a' || c > 'z') continue;
          wordCount++;
          if (wordScores.containsKey(token)) {
              accScore += wordScores.get(token);
          }
      }
      if (wordCount == 0) return 0.0;
      return accScore / wordCount;
  }

  /*
   * This method is here to help you run your program. Y
   * You may modify it as needed.
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please specify the name of the input file");
      System.exit(0);
    }
    String filename = args[0];
    System.out.print("Please enter a sentence: ");
    Scanner in = new Scanner(System.in);
    String sentence = in.nextLine();
    in.close();
    List<Sentence> sentences = Analyzer.readFile(filename);
    Set<Word> words = Analyzer.allWords(sentences);
    Map<String, Double> wordScores = Analyzer.calculateScores(words);
    double score = Analyzer.calculateSentenceScore(wordScores, sentence);
    System.out.println("The sentiment score is " + score);
  }
}
