/*
 * Author: Marcus B Clements, with code from Dr. Mitra
 * Date:   11/24/2024
 * Description: Program that reads in text from stdin,
 * checks if it is a palindrome, then writes the result
 * to stdout.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// buffer size
#define MAX_LINE_LENGTH 1024

// Declare functions
int is_pal(const char *string);
char *remove_punc_to_lower(const char *string);


// Function definition to remove punctuation and convert to lowercase
char *remove_punc_to_lower(const char *string) {
    const int len = (int)strlen(string);
    char *result = malloc(sizeof(char) * (len + 1));

    int j = 0;

    for (int i = 0; i < len; i++) {
        // If the character is alphanumeric, add it to the result string
        if (isalnum(string[i])) {
            result[j++] = (char) tolower(string[i]);
        }
    }

    result[j] = '\0'; // Ensures returned strings will always be null-terminated, even if empty
    return result;
}

// Function definition to check if a string is a palindrome, ignoring case, punctuation, and spaces
int is_pal(const char *string) {
    // Convert buffer to lowercase alphanumeric string
    char *alpha_num_string = remove_punc_to_lower(string);

    const int len = (int) strlen(alpha_num_string);
    for (int i = 0; i < ((len) / 2); i++) {
        if (alpha_num_string[i] != alpha_num_string[len - i - 1]) {
            // If any symmetrical character does not match, string is not a palindrome
            free(alpha_num_string);
            return 0;
        }
    }
    // Free this string regardless since it isn't used outside of this scope
    free(alpha_num_string);
    return 1;
}

int main(int argc, char **argv) {
    char *fgets_rtn = NULL; // variable to capture return code
    char buffer[MAX_LINE_LENGTH]; // where we are reading the data into
    int line = 1, pal_count = 0;

    printf("Output:\n");

    do {
        // read data (up to MAX_LINE_LEN chars) into buffer from stdin
        fgets_rtn = fgets(buffer, MAX_LINE_LENGTH, stdin);
        if (fgets_rtn != NULL) {
            // fgets was successful!
            if ('\n' == buffer[strlen(buffer) - 1]) {
                buffer[strlen(buffer) - 1] = '\0';
            }

            // If the buffer is a palindrome, add 1 to the count and print as such; else print regular string
            if (is_pal(buffer)) {
                pal_count++;
                printf("[Palindrome!]: %s\n", buffer);
            } else {
                printf("[Regular String]: %s\n", buffer);
            }
            line++;
        }
    } while (fgets_rtn != NULL);

    // Print summary message after buffer is closed
    printf("Summary:\nTotal Palindromes: %d (out of %d strings)\n", pal_count, (line - 1));

    return 0;
}
