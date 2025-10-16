package wordle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

import project20280.hashtable.ChainHashMap;

public class Wordle {

    String fileName = "wordle/resources/dictionary.txt";
    //String fileName = "wordle/resources/extended-dictionary.txt";
    List<String> dictionary = null;
    final int num_guesses = 5;
    final long seed = 42;
    //Random rand = new Random(seed);
    Random rand = new Random();

    static final String winMessage = "CONGRATULATIONS! YOU WON! :)";
    static final String lostMessage = "YOU LOST :( THE WORD CHOSEN BY THE GAME IS: ";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";

    Wordle() {

        this.dictionary = readDictionary(fileName);

        System.out.println("dict length: " + this.dictionary.size());
        System.out.println("dict: " + dictionary);

    }

    public void play(String target) {
        for(int i = 0; i < num_guesses; ++i) {
            String guess = getGuess();
    
            // Check if user guess matches the target word
            if(guess.equals(target)) {
                win(target);
                return;
            }
    
            // Create a map to store the count of each letter in the target word
            Map<Character, Integer> letterCount = new HashMap<>();
            for (char c : target.toCharArray()) {
                letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
            }
    
            // Initialize hint array
            String[] hint = new String[5];
    
            // Loop through each character in the guess
            for (int k = 0; k < 5; k++) {
                char currentChar = guess.charAt(k);
                if (currentChar == target.charAt(k)) { // if character is at the correct position
                    hint[k] = ANSI_GREEN_BACKGROUND + "+" + ANSI_RESET; // mark as correct position
                    letterCount.put(currentChar, letterCount.get(currentChar) - 1); // reduce the remaining occurrences
                }
            }
    
            // Loop again to handle incorrect letters and correct letters in wrong positions
            for (int k = 0; k < 5; k++) {
                char currentChar = guess.charAt(k);
                if (hint[k] == null) { // If the hint has not been set for this position
                    if (target.indexOf(currentChar) != -1 && letterCount.get(currentChar) > 0) {
                        // Check if the current character exists in the target
                        if (target.indexOf(currentChar) != k) {
                            hint[k] = ANSI_YELLOW_BACKGROUND + "o" + ANSI_RESET; // Correct letter in wrong position
                        } else {
                            hint[k] = ANSI_GREY_BACKGROUND + "_" + ANSI_RESET; // Incorrect letter
                        }
                        letterCount.put(currentChar, letterCount.get(currentChar) - 1);
                    } else {
                        hint[k] = ANSI_GREY_BACKGROUND + "_" + ANSI_RESET; // Incorrect letter
                    }
                }
            }
    
            // after setting the yellow and green positions, the remaining hint positions must be "_"
            System.out.println("hint: " + Arrays.toString(hint));
    
            // check for a win
            int num_green = 0;
            for(int k = 0; k < 5; ++k) {
                if(hint[k].equals(ANSI_GREEN_BACKGROUND + "+" + ANSI_RESET)) num_green += 1;
            }
            if(num_green == 5) {
                win(target);
                return;
            }
        }
    
        lost(target);
    }
    
        

    public void lost(String target) {
        System.out.println();
        System.out.println(lostMessage + target.toUpperCase() + ".");
        System.out.println();

    }

    public void win(String target) {
        System.out.println(ANSI_GREEN_BACKGROUND + target.toUpperCase() + ANSI_RESET);
        System.out.println();
        System.out.println(winMessage);
        System.out.println();
    }

    public String getGuess() {
        Scanner myScanner = new Scanner(System.in, StandardCharsets.UTF_8.displayName());  // Create a Scanner object
        System.out.println("Guess:");

        String userWord = myScanner.nextLine();  // Read user input

        // check the length of the word and if it exists
        while ((userWord.length() != 5) || !(dictionary.contains(userWord))) {
            if ((userWord.length() != 5)) {
                System.out.println("The word " + userWord + " does not have 5 letters.");
            } else {
                System.out.println("The word " + userWord + " is not in the word list.");
            }
            
            System.out.println("Please enter a new 5 letter word.");
            userWord = myScanner.nextLine();
        }
        return userWord;
    }

    // get hints for unit tests
    public String[] getHints(String target, String guess) {
        String[] hints = new String[5];
        Map<Character, Integer> letterCount = new HashMap<>();

        // Initialize letterCount map with counts of each character in the target
        for (char c : target.toCharArray()) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
        }

        // Loop through each character in the user's guess
        for (int k = 0; k < 5; k++) {
            char currentChar = guess.charAt(k);
            if (currentChar == target.charAt(k)) {
                hints[k] = ANSI_GREEN_BACKGROUND + "+" + ANSI_RESET; // Highlight correct letter in correct position
                letterCount.put(currentChar, letterCount.get(currentChar) - 1);
            }
        }

        // Loop again to find incorrect letters and correct letters in the wrong positions
        for (int k = 0; k < 5; k++) {
            char currentChar = guess.charAt(k);
            if (hints[k] == null) { // If the hint has not been set for this position
                if (target.indexOf(currentChar) != -1 && letterCount.get(currentChar) > 0) {
                    // Check if the current character exists in the target and is available
                    if (target.indexOf(currentChar) != k) {
                        hints[k] = ANSI_YELLOW_BACKGROUND + "o" + ANSI_RESET; // Correct letter in wrong position
                    } else {
                        hints[k] = ANSI_GREY_BACKGROUND + "_" + ANSI_RESET; // Incorrect letter
                    }
                    letterCount.put(currentChar, letterCount.get(currentChar) - 1);
                } else {
                    hints[k] = ANSI_GREY_BACKGROUND + "_" + ANSI_RESET; // Incorrect letter
                }
            }
        }

        return hints;
    }

    public String getRandomTargetWord() {
        // generate random values from 0 to dictionary size
        return dictionary.get(rand.nextInt(dictionary.size()));
    }

    public List<String> readDictionary(String fileName) {
        List<String> wordList = new ArrayList<>();

        try {
            // Open and read the dictionary file
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
            assert in != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String strLine;

            //Read file line By line
            while ((strLine = reader.readLine()) != null) {
                wordList.add(strLine.toLowerCase());
            }
            //Close the input stream
            in.close();

        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return wordList;
    }

    public static void main(String[] args) {
        Wordle game = new Wordle();

        String target = game.getRandomTargetWord();

        System.out.println("target: " + target);

        //int asciiBitLength = Huffman.bitLength(game.dictionary);
        //System.out.println("Total bits required to encode using ASCII: " + asciiBitLength);

        game.play(target);

    }

}