import java.util.*;

public class Wordle {

    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    static ReadFile rf = new ReadFile();
    static ArrayList<String> wordList = rf.getWords();
    static ArrayList<String> guesses = new ArrayList<>();
    static final String ANSI_RESET = "\u001B[0m";
    static final String ANSI_YELLOW = "\u001B[43m";
    static final String ANSI_GREEN = "\u001B[42m";

    public static void main(String[] args) {
        game();
    }

    public static void game() {
        String word = wordList.get(random.nextInt(wordList.size()));
        // String word = "urges";

        for (int i = 0; i < 6; i++) {
            System.out.print("\033\143");
            System.out.println(word);
            System.out.println("Guess the word: ");

            for (int j = 0; j < guesses.size(); j++) {
                System.out.println(guesses.get(j));
            }
            String wordGuess = input.nextLine();
            if (!wordList.contains(wordGuess)) {
                i--;
                continue;
            }
            compareWords(word, wordGuess);
            if (word.equals(wordGuess)) {
                System.out.print("\033\143");
                for (int j = 0; j < guesses.size(); j++) {
                    System.out.println(guesses.get(j));
                }
                System.out.println("Congratulations! It took you " + (i + 1) + " guesses!");
                return;
            }
        }
    }

    public static String compareWords(String word, String wordGuess) {
        String guess = "";
        ArrayList<Character> letters = new ArrayList<>(5);
        for (int i = 0; i < word.length(); i++) {
            letters.add(word.charAt(i));
        }

        for (int i = 0; i < word.length(); i++) {
            if (letters.contains(wordGuess.charAt(i))) {
                if (word.charAt(i) == wordGuess.charAt(i)) {
                    guess = guess + ANSI_GREEN + wordGuess.charAt(i) + ANSI_RESET;
                    letters.remove(Character.valueOf(wordGuess.charAt(i)));
                } else {
                    guess = guess + ANSI_YELLOW + wordGuess.charAt(i) + ANSI_RESET;
                }
            } else {
                guess = guess + wordGuess.charAt(i);
            }

            System.out.println();
        }
        guesses.add(guess);
        return guess;

    }
}