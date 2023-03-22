import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WordleClient {
    public static void main(String[] args) {

        System.out.println("Welcome to my Wordle Dupe!");
        Scanner scan = new Scanner(System.in);
        int wordSize = 0;
        do {
                System.out.print("Please input how many letter words you would like to guess: ");
                wordSize = scan.nextInt();
        }
        while (wordSize < 5 || wordSize > 8);
        Wordle wordle = new Wordle(wordSize);
        int guesses = wordSize + 1;
        boolean won = false;
        System.out.println("You have chosen to guess a " + wordSize + "-letter word. You have " +  guesses +
                " guesses to guess the word.");
        for (int i = 1; i < guesses + 1; i++) {
            String guess = "";
            do {
                System.out.print("Enter a " + wordSize + "-letter word: ");
                guess = scan.next();
            }
            while (guess.length() != wordSize);
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
    }
}
