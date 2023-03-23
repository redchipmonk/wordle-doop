import java.util.Arrays;

/**
 * Keyboard class that represents a qwerty keyboard and displays letters that are chosen by guess and their status
 *
 * @author alvin le
 */
public class Keyboard {
    //status array of letters
    private final int[] keyboard = new int[26];
    //array of the indexes of letters on a keyboard
    private final int[] indexes = new int[26];
    final char[] keys = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    final String GRAY = "\u001b[100m";

    /**
     * Default constructor that sets keyboard array to -1, indicating unused letter as well as declaring indexes array
     */
    public Keyboard() {
        Arrays.fill(keyboard, -1);
        for (int i = 0; i < 26; i++) {
            //index ascii value of keys array
            indexes[(int)(keys[i]) - 97] = i;
        }
    }

    /**
     * Method to update keyboard array based on a guess and status
     * @param guess String guess
     * @param status int[] status array of guess
     */
    public void update(String guess, int[] status) {
        for (int i = 0; i < status.length; i++) {
            //index of a keyboard based on alphabet - ex: a is the 0th of the alphabet, but 10th of qwerty keyboard
            int letter = indexes[(int)(guess.charAt(i)) - 97];
            //if letter is not in right spot and has not already been correct, update
            if (status[i] == 1 && keyboard[letter] != 2) keyboard[letter] = 1;
            else if (status[i] == 2) keyboard[letter] = 2;
            //if letter is incorrect and has not been guessed, gray out
            else if (status[i] == 0 && keyboard[letter] == -1) keyboard[letter] = 0;
        }
    }

    /**
     * Display method to print keyboard to console
     */
    public void display() {
        System.out.println("Letters used: ");
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == 1) System.out.print(YELLOW + keys[i] + RESET);
            else if (keyboard[i] == 2) System.out.print(GREEN + keys[i] + RESET);
            else if (keyboard[i] == 0) System.out.print(GRAY + keys[i] + RESET);
            //not guessed yet
            else System.out.print(keys[i]);
            //if letter is at p or l, the ends of a keyboard, new line and spaces
            if (i == 9 || i == 18) {
                System.out.println();
                if (i == 9) System.out.print(" ");
                else System.out.print("  ");
            }
        }
        System.out.println();
        System.out.println();
    }
}
