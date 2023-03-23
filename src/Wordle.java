import java.io.*;

/**
 * Wordle class that generates random word, checks guesses against that word, and displays the word and a keyboard
 * through composition.
 *
 * @author alvin le
 */
public class Wordle {
    //ANSI colors
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    final String GRAY = "\u001b[100m";
    private final int wordSize;
    private final String word;
    //array that contains values that indicate if a letter in a guess is valid
    private final int[] status;
    private final Keyboard keyboard;

    /**
     * Constructor that generates random word based on word size
     * @param wordSize int word size
     */
    public Wordle(int wordSize) {
        this.wordSize = wordSize;
        String fileName = wordSize + "-letter-words.txt";
        File inFile = new File(fileName);
        word = generate(inFile).toLowerCase();
        status = new int[wordSize];
        keyboard = new Keyboard();

    }
    /**
     * Getter method of chosen word
     * @return String word
     */
    public String getWord() {
        return word;
    }

    /**
     * Method to compare a guess to chosen word and updates status array if a letter matches or if a letter is in the
     * word but not at the right place
     * @param guess String guess
     */
    public void checkWord(String guess) {
        for (int i = 0; i < wordSize; i++) {
            if (word.substring(i, i + 1).equals(guess.substring(i, i + 1))) {
                //exact place
                status[i] = 2;
            }
            else {
                for (int j = 0; j < wordSize; j++) {
                    if (i != j && word.substring(i, i + 1).equals(guess.substring(j, j +1))) {
                        //in the word but not at the right place
                        status[j] = 1;
                        break;
                    }
                }
            }
        }
        //update keyboard based on results
        keyboard.update(guess, status);
    }

    /**
     * Method to reset status array to default
     */
    public void statusReset() {
        for (int i = 0; i < wordSize; i++) {
            status[i] = 0;
        }
    }

    /**
     * Method to choose random word from text files
     * @param inFile File to choose from
     * @return String chosen word
     */
    public String generate(File inFile) {
        try {
            RandomAccessFile file = new RandomAccessFile(inFile, "r");
            long location = (long) (Math.random() * file.length());
            file.seek(location);
            file.readLine();
            String randomLine = file.readLine();
            file.close();
            return randomLine;
        }
        catch (IOException fnfe) {
            System.out.println("File not found: " + inFile.getName());
        }
        return "File not found: " + inFile.getName();
    }

    /**
     * Method to print a guess with color to indicate status along with keyboard to show status of letters
     * @param guess String guess
     */
    public void print(String guess) {
        for (int i = 0; i < wordSize; i++) {
            if (status[i] == 2) System.out.print(GREEN + guess.charAt(i) + RESET);
            else if (status[i] == 1) System.out.print(YELLOW + guess.charAt(i) + RESET);
            else System.out.print(GRAY + guess.charAt(i) + RESET);
        }
        System.out.println();
        keyboard.display();
    }
}