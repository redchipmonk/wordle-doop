import java.io.*;

public class Wordle {
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    private int wordSize;
    private String word;
    private int[] status;
    public Wordle(int wordSize) {
        this.wordSize = wordSize;
        String fileName = wordSize + "-letter-words.txt";
        File inFile = new File(fileName);
        word = generate(inFile).toLowerCase();
        status = new int[wordSize];

    }

    public String getWord() {
        return word;
    }

    public void checkWord(String guess) {
        for (int i = 0; i < wordSize; i++) {
                if (word.substring(i, i + 1).equals(guess.substring(i, i + 1))) {
                        status[i] = 2;
                }
                else {
                    for (int j = 0; j < wordSize; j++) {
                        if (i != j && word.substring(i, i + 1).equals(guess.substring(j, j +1))) {
                            status[j] = 1;
                            break;
                    }
                }
            }
        }
    }

    public void statusReset() {
        for (int i = 0; i < wordSize; i++) {
            status[i] = 0;
        }
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

    public void print(String guess) {
        for (int i = 0; i < wordSize; i++) {
            if (status[i] == 2) System.out.print(GREEN + guess.substring(i, i + 1) + RESET);
            else if (status[i] == 1) System.out.print(YELLOW + guess.substring(i, i + 1) + RESET);
            else System.out.print(guess.substring(i, i + 1));
        }
        System.out.println();
    }
}