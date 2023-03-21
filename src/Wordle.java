import java.io.File;
import java.util.Scanner;

public class Wordle {
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    public static void main(String[] args) {
        System.out.println("Welcome to my Wordle Dupe!");
        Scanner scan = new Scanner(System.in);
        int wordSize = 0;
        do {
            System.out.print("Please input how many letter words you would like to guess: ");
            wordSize = scan.nextInt();
            System.out.println();
        }
        while (wordSize < 5 || wordSize > 8);
        int guess = wordSize + 1;
        String fileName = wordSize + "-letter-words.txt";
        File inFile = new File(fileName);
        System.out.println("You have chosen to guess a " + wordSize + "-letter word. You have " + guess +
                "guesses to guess the word.");





    }
}