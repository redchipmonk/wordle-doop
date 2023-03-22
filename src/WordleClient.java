import java.io.File;
import java.util.Scanner;

public class WordleClient {
    public static void main(String[] args) {
        System.out.println("Welcome to my Wordle Dupe!");
        Scanner scan = new Scanner(System.in);
        int wordSize;
        do {
            System.out.print("Please input how many letter words you would like to guess: ");
            wordSize = scan.nextInt();
            System.out.println();
        }
        while (wordSize < 5 || wordSize > 8);
        Wordle wordle = new Wordle(wordSize);
        int guesses = wordle.getWordSize() + 1;
        System.out.println("You have chosen to guess a " + wordle.getWordSize() + "-letter word. You have " +  guesses +
                " guesses to guess the word.");
        for (int i = 0; i < guesses; i++) {
            String guess;
            do {
                System.out.print("Input a " + wordle.getWordSize() + "-letter word: ");
                guess = scan.nextLine();
                System.out.println();
            }
            while (guess.length() != wordle.getWordSize());
        }
    }
}
