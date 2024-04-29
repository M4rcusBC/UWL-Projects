package Spring2024.CS220.Assignments.Assign04;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * Assign04
 * 
 * This class contains the solutions to the problems in Assignment 4,
 * as well as the main method to test the solutions.
 * 
 * The problems are:
 * 1. Convert a decimal number to octal.
 * 2. Determine if a string is a word palindrome.
 * 3. Calculate the weight on a person in a pyramid.
 * 4. Convert an integer to English.
 * 
 * @author Marcus Clements
 * @since 2024-04-26
 * 
 */
public class Assign04 {

    private static int weight = 200;

    // Pyramid to store the calculated weights
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
        Scanner scan = new Scanner(System.in);
        String result = "";NumberFormat formatter = new DecimalFormat("#0.00000");
        double startTime, duration;

        while (true) {
            System.out.println("Enter a number to convert to English (any other char to exit): ");
            try {
                int num = scan.nextInt();
                startTime = System.currentTimeMillis();
                result = convertToEnglish(num);
                duration = (System.currentTimeMillis() - startTime);
                System.out.println(result);

                System.out.println("Execution time: " + formatter.format((duration) / 1000d) + " seconds\n");
            } catch (InputMismatchException e) {
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
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
            return true; // Base case; if the string is empty or has one word, it is a palindrome
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
        if (row == 0) {
            return 0;
        }
        // Weight supported by the person diagonally above on the left
        double weightLeft = (col > 0) ? weightOn(row - 1, col - 1) / 2 : 0;
        // Weight supported by the person diagonally above on the right
        double weightRight = (col < row - 1) ? weightOn(row - 1, col) / 2 : 0;
        // Total weight on the current person
        return weight + weightLeft + weightRight;
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

    // Constants for the English conversion method. These arrays are used to convert numbers to English words, where the indexes correspond to the number.
    private static final String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private static final String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    /**
     * Converts the given int into an English sentence.
     * num will always be less than one billion.
     *
     * @param num An int greater or equal to zero and less than one billion.
     * @return The English sentence representation of the number.
     */
    public static String convertToEnglish(int num) throws NumberFormatException {
        if (num == 0) {
            return "zero";
        }
        return convertToEnglishHelper(num).trim();
    }

    /**
     * Helper function for convertToEnglish. Recursively converts the given number to English.
     * 
     * @param num The number to convert.
     * @return The English sentence representation of the number.
     */
    private static String convertToEnglishHelper(int num) {
        // Base case: If the number is less than 10, return the corresponding word
        String result = "";
        if (num < -999999999) throw new NumberFormatException("Number is too small.");
        if (num < 0) return "negative " + convertToEnglishHelper(Math.abs(num));
        if (num < 10) {
            result = ones[num];
        } else if (num < 20) { // If the number is less than 20, return the corresponding word
            result = teens[num - 10];
        } else if (num < 100) { // If the number is less than 100, convert the tens place, add a hyphen, then convert the ones place recursively
            result = tens[num / 10] + ((num % 10 == 0) ? "" : "-") + convertToEnglishHelper(num % 10);
        } else if (num < 1000) { // If the number is less than 1000, convert the hundreds place and the rest recursively
            result = ones[num / 100] + " hundred " + convertToEnglishHelper(num % 100);
        } else if (num < 1000000) { // If the number is less than 1000000, convert the thousands place and the rest recursively
            result = convertToEnglishHelper(num / 1000) + " thousand " + convertToEnglishHelper(num % 1000);
        } else if (num < 1000000000) { // If the number is less than 1000000000, convert the millions place and the rest recursively
            result = convertToEnglishHelper(num / 1000000) + " million " + convertToEnglishHelper(num % 1000000);
        } else throw new NumberFormatException("Number is too large.");
        // Return the result
        return result;
    }

}
