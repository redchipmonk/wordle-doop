import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Runner class for Wordle.java
 *
 * @author alvin le
 */
public class WordleClient {
    public static void main(String[] args) {

        System.out.println("Welcome to my Wordle Dupe!");
        Scanner scan = new Scanner(System.in);
        //get wordsize between 5 and 8
        int wordSize = 0;
        do {
                try {
                    System.out.print("Please input how many letter words you would like to guess (between 5 and 8): ");
                    wordSize = scan.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("Please enter numerical numbers between 5 and 8.");
                    System.exit(1);
                }
        }
        while (wordSize < 5 || wordSize > 8);
        Wordle wordle = new Wordle(wordSize);
        //player gets one more guess than word size
        int guesses = wordSize + 1;
        boolean won = false;
        System.out.println("You have chosen to guess a " + wordSize + "-letter word. You have " +  guesses +
                " guesses to guess the word.");
        //guesses loop
        for (int i = 1; i < guesses + 1; i++) {
            String guess;
            //get guess
            do {
                System.out.print("Enter a " + wordSize + "-letter word: ");
                guess = scan.next();
            }
            while (guess.length() != wordSize);
            //reset status due to new guess
            wordle.statusReset();
            wordle.checkWord(guess);
            System.out.print("Guess " + i + ": ");
            wordle.print(guess);
            if (guess.equalsIgnoreCase(wordle.getWord())) {
                won = true;
                break;
            }

        }
        if (won) System.out.println("You won!");
        else System.out.println(wordle.getWord() + "\nYou lost :(");
        System.exit(0);
    }
}
