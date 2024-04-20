package Spring2024.CS220.Labs.Lab06;

/**
 * CS 220: Lab 08 Template for Exercise 1
 *
 * This class includes two recursive methods: noX and displayReverse.
 * These methods are designed to remove all instances of the character
 * 'x' from a string, and display a string in reverse, respectively.
 *
 * @author A. Sauppe, M. Clements
 * Last Modified: 4/16/2024
 */
public class StringPlayground {

    /**
     * Main method for kick-starting the program.
     *
     * @param args Command-line arguments (not used here)
     */
    public static void main(String[] args) {
        StringPlayground play = new StringPlayground();

        System.out.println("Testing noX():");
        System.out.println("-----------------");
        System.out.println(play.noX("ab"));
        System.out.println(play.noX("axb"));
        System.out.println(play.noX("axxb"));
        System.out.println(play.noX("xab"));
        System.out.println(play.noX("abx"));
        System.out.println(play.noX("xaxbx"));
        System.out.println(play.noX("xxaxxbxx"));
        System.out.println(play.noX("xabxcdx"));
        System.out.println(play.noX("xxabxxcdxx"));


        System.out.println("Testing displayReverse():");
        System.out.println("----------------------------");
        play.displayReverse("Hello");
        play.displayReverse("This is a test!");
        play.displayReverse("?racecar!");

        System.out.println();
    }

    /**
     * Constructor to initialize the program
     */
    public StringPlayground() {
        // Nothing to do
    }

    /**
     * Recursively removes all xs contained in str
     *
     * @param str String to remove xs from
     * @return
     */
    public String noX(String str) {

        if (!str.contains("x")) {
            return str; // Base case; if the string does not contain an x, return the string
        }

        if (str.charAt(0) == 'x') { // If the first character is an x, remove it
            /* Call the function, passing in a substring of the original string with the first
            character removed. This essentially removes the first character from the string. */
            return noX(str.substring(1));
        }

        return str.charAt(0) + noX(str.substring(1)); // If the first character is not an x, concatenate it to the result of the function.

    }

    /**
     * Displays the string in reverse using the recursive helper method
     * displayReverseHelper.
     *
     * @param str String to display in reverse
     */
    public void displayReverse(String str) {
        displayReverseHelper(str, str.length() - 1);
    }

    /**
     * A recursive helper method to display a string in reverse.
     * <p>
     * In addition to selection logic and return statements,
     * use only the following functionality in your solution:
     * - System.out.println()
     * - System.out.print()
     * - String's .charAt(int idx) method
     *
     * @param str The full string to display
     * @param idx The current index to display
     */
    private void displayReverseHelper(String str, int idx) {
        // Base case

        if (idx < 0) {
            System.out.println();
            return;
        }
        // Recursive case
        System.out.print(str.charAt(idx--));
        displayReverseHelper(str, idx);

    }

}
