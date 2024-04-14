/**
 * CS 220: Lab 08 Template for Exercise 1
 * DESCRIPTION OF THE PROGRAM HERE
 *
 * @author A. Sauppe, YOUR NAME HERE
 * Last Modified: DATE LAST MODIFIED HERE
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
    		return "";
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
     *
     * In addition to selection logic and return statements,
     * use only the following functionality in your solution:
     *  - System.out.println()
     *  - System.out.print()
     *  - String's .charAt(int idx) method
     *
     * @param str The full string to display
     * @param idx The current index to display
     */
    private void displayReverseHelper(String str, int idx) {
        // TODO Write me
    }

}
