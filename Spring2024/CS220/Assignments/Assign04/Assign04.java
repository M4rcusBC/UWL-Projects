package Spring2024.CS220.Assignments.Assign04;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Assign04 {

    private static int weight = 200;

    private static double[][] pyramid = {
            {0},
            {0, 0},
            {0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {

        System.out.println("** OCTAL CONVERSION **");
        System.out.println("0 in octal:" + octalConversion(0));
        System.out.println("1 in octal:" + octalConversion(1));
        System.out.println("5 in octal:" + octalConversion(5));
        System.out.println("7 in octal:" + octalConversion(7));
        System.out.println("8 in octal:" + octalConversion(8));
        System.out.println("9 in octal:" + octalConversion(9));
        System.out.println("15 in octal:" + octalConversion(15));
        System.out.println("16 in octal:" + octalConversion(16));
        System.out.println("17 in octal:" + octalConversion(17));
        System.out.println("255 in octal:" + octalConversion(255));

        System.out.println("\n** WORD PALINDROME **");
        String line = "Bores are people that say that people are bores.";
        System.out.println(line+": "+isWordPalindrome(line));
        line = "";
        System.out.println(line+"\t\t\t\t\t\t: "+isWordPalindrome(line));
        line = "say";
        System.out.println(line+"\t\t\t\t\t\t: "+isWordPalindrome(line));
        line = "FALL LEAVES AFTER LEAVES FALL";
        System.out.println(line+"\t\t\t: "+isWordPalindrome(line));
        line = "taco cat";
        System.out.println(line+"\t\t\t\t\t\t: "+isWordPalindrome(line));

        System.out.println("\n** WEIGHT ON **");
        System.out.println("0, 0: " + weightOn(0,0));
        System.out.println("1, 0: " + weightOn(1,0));
        System.out.println("2, 1: " + weightOn(2,1));
        System.out.println("4, 2: " + weightOn(4,2));
        System.out.println();

        System.out.println("\n** WEIGHT ON MEMO**");
        System.out.println("0, 0: " + weightOnMemo(0,0, pyramid));
        System.out.println("1, 0: " + weightOnMemo(1,0, pyramid));
        System.out.println("2, 1: " + weightOnMemo(2,1, pyramid));
        System.out.println("4, 2: " + weightOnMemo(4,2, pyramid));
        System.out.println();

        System.out.println("\n** INT TO ENGLISH **");
        System.out.println(convertToEnglish(3));
        System.out.println(convertToEnglish(33));
        System.out.println(convertToEnglish(333));
        System.out.println(convertToEnglish(3333));
        System.out.println(convertToEnglish(33333));
        System.out.println(convertToEnglish(333333));
        System.out.println(convertToEnglish(3333333));
        System.out.println(convertToEnglish(33333333));
        System.out.println(convertToEnglish(333333333));
    }

    /**
     * Converts an int from base 10 (decimal) to a string in base 8 (octal).
     *
     * @param n The base 10 number to convert.
     * @return The octal string value.
     */
    private static String octalConversion(int n) {
        if (n == 0) {
            return ""; // Base case; if the number is 0, return an empty string
        }
        /*
         * The recursive case works by taking the remainder of the number divided by 8
         * and concatenating it to the result of the number divided by 8. This is
         * pursuant to the assignment description.
         */
        return octalConversion(n / 8) + n % 8;
    }

    /**
     * Determines whether the given string is a word palindrome.
     *
     * @param line The given string.
     * @return True if it's a word palindrome, false if not.
     */
    private static boolean isWordPalindrome(String line) {
        if (line.isEmpty() || line.split(" ").length == 1) {
            return true; // Base case; if the string is empty or has one character, it is a palindrome
        }

        line = line.replaceAll("\\p{Punct}", "").toLowerCase().trim(); // Convert the string to lowercase and remove all non-alphabetic characters
        ArrayList<String> words = new ArrayList<>(Arrays.asList(line.split(" ")));

        /*
         * The recursive case works by checking if the first and last words in the
         * String are equal. If they are, the function is called recursively with
         * a modified string with the first and last words removed.
         * If they are not, the function returns false.
         */

        // Using null-safe object.equals to compare the first and last words
        if (!Objects.equals(words.getFirst(), words.getLast())) return false;

        // Remove the first and last words, then check any remaining words
        words.removeFirst();
        words.removeLast();
        return isWordPalindrome(words.toString());
    }

    /**
     * Calculates the weight being carried by the person at row, col.
     *
     * @param row The row position of the person.
     * @param col The column position of the person.
     * @return A double representing the amount of weight the person at row col is carrying.
     */
    private static double weightOn(int row, int col) {
        // Base case: If it's the top person, they bear no weight
        if (row == 0 && col == 0) {
            return 0;
        }
        // If the person is out of bounds of the pyramid, they bear no weight
        if (col < 0 || col > row) {
            return -1;
        }


        
    }

    /**
     * Calculates the weight being carried by the person at row, col using memoization.
     *
     * @param row  The row position of the person.
     * @param col  The column position of the person.
     * @param memo A 2D array to store the calculated weights.
     * @return A double representing the amount of weight the person at row col is carrying.
     */
    private static double weightOnMemo(int row, int col, double[][] memo) {
        if (row == 0) {
            return 0; // Base case
        }

        if (memo[row][col] != 0.0) {
            return memo[row][col]; // Already calculated, return from memo
        }

        double weight = weightOn(row, col); // Calculate weight without memo
        memo[row][col] = weight; // Store the calculated weight in memo
        return weight;
    }



    /**
     * Converts the given int into an English sentence.
     * num will always be less than one billion.
     *
     * @param num An int greater or equal to zero and less than one billion.
     * @return
     */
    private static String convertToEnglish(int num) {

    }

}
