import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Keyboard {
    private int[] keyboard = new int[26];
    private int[] indexes = new int[26];
    final char[] keys = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
            'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    final String GREEN = "\u001b[42m";
    final String YELLOW = "\u001b[43m";
    final String RESET = "\u001b[0m";
    final String GRAY = "\u001b[100m";

    public Keyboard() {
        Arrays.fill(keyboard, -1);
        for (int i = 0; i < 26; i++) {
            indexes[(int)(keys[i]) - 97] = i;
        }
    }

    public void update(String guess, int @NotNull [] status) {
        for (int i = 0; i < status.length; i++) {
            int letter = indexes[(int)(guess.charAt(i)) - 97];
            if (status[i] == 1 && keyboard[letter] != 2) keyboard[letter] = 1;
            else if (status[i] == 2) keyboard[letter] = 2;
            else if (status[i] == 0 && keyboard[letter] == -1) keyboard[letter] = 0;
        }
    }


    public void display() {
        System.out.println("Letters used: ");
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == 1) System.out.print(YELLOW + keys[i] + RESET);
            else if (keyboard[i] == 2) System.out.print(GREEN + keys[i] + RESET);
            else if (keyboard[i] == 0) System.out.print(GRAY + keys[i] + RESET);
            else System.out.print(keys[i]);
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
