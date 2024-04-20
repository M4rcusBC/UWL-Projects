package Spring2024.CS220.Assignments.Assign04;

public class Recursion {

    public static void main(String[] args) {

        System.out.println("-- Testing octalConversion(255) --\n");
        System.out.println("Expected: 377");
        System.out.println("Returned: " + octalConversion(255));

        System.out.println("\n-- Testing octalConversion(8) --\n");
        System.out.println("Expected: 10");
        System.out.println("Returned: " + octalConversion(8));

        System.out.println("\n-- Testing octalConversion(16) --\n");
        System.out.println("Expected: 20");
        System.out.println("Returned: " + octalConversion(16));

        System.out.println("\n-- Testing isPalindrome(\"racecar\") --\n");
        System.out.println("Expected: true");
        System.out.println("Returned: " + isPalindrome("racecar"));

        System.out.println("\n-- Testing isPalindrome(\"\") --\n");
        System.out.println("Expected: true");
        System.out.println("Returned: " + isPalindrome(""));

        System.out.println("\n-- Testing isPalindrome(\"Bores are people that say that people are bores.\") --\n");
        System.out.println("Expected: true");
        System.out.println("Returned: " + isPalindrome("Bores are people that say that people are bores."));

        System.out.println("\n-- Testing isPalindrome(\"Fall leaves after leaves fall!\") --\n");
        System.out.println("Expected: true");
        System.out.println("Returned: " + isPalindrome("Fall leaves after leaves fall!"));


    }

    static String octalConversion(int i) {
        if (i == 0) {
            return ""; // Base case; if the number is 0, return an empty string
        }
        /*
         * The recursive case works by taking the remainder of the number divided by 8
         * and concatenating it to the result of the number divided by 8. This is
         * pursuant to the assignment description.
         */
        return octalConversion(i / 8) + i % 8;
    }

    static boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true; // Base case; if the string is empty or has one character, it is a palindrome
        }

        s = s.replaceAll("\\p{Punct}", "").toLowerCase(); // Convert the string to lowercase and remove all non-alphabetic characters
        String[] line = s.split(" ");


        /*
         * The recursive case works by checking if the first and last words in the
         * String are equal. If they are, the function is called recursively with the
         * substring of the original string with the first and last words removed.
         * If they are not, the function returns false.
         * The below recursive implementation is equivalent to:
         *
         */

        return line[0].equals(line[line.length - 1]) && isPalindrome(s.substring(1, s.length() - 1));


    }

}
