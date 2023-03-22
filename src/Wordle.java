import java.io.*;
import java.util.Scanner;

public class Wordle {
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    private int wordSize;
    private String word;
    public Wordle(int wordSize) {
        this.wordSize = wordSize;
        String fileName = wordSize + "-letter-words.txt";
        File inFile = new File(fileName);
        word = generate(inFile).toLowerCase();

    }

    public int getWordSize() {
        return wordSize;
    }

    public String getWord() {
        return word;
    }

    public int checkWord(String guess, int status[]) {
        int score = 0;
        for (int i = 0; i < wordSize - 1; i++) {
            for (int j = 0; j < wordSize - 1; j++) {
                if (word.substring(i, i + 1).equals(guess.substring(j, j + 1))) {
                    if (i == j) {
                        score += 2;
                        status[j] = 2;
                        break;
                    }
                    score++;
                    status[j] = 1;
                }
            }
        }
        return score;
    }


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
            System.out.println("File not found: " + inFile.toString());
        }
        return "File not found: " + inFile.toString();
    }
}