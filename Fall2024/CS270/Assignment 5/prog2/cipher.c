/*
 * Author: Marcus Clements, with code from Dr. Mitra
 * Date:   11/24/2024
 * Description: Simple Caesar cipher program that
 * reads in text from stdin, encrypts/decrypts it
 * by moving each char over the alphabet by an
 * offset, then writes the result to stdout.
 *
 * Decryption offset will always be positive for
 * consistency; negative values are converted to
 * their absolute value.
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MAX_LINE_LENGTH 1024
#define ALPHABET_SIZE 26

void encrypt(char *string, int offset);
void decrypt(char *string, int offset);

void encrypt(char *string, const int offset) {
    for (int i = 0; i < strlen(string); i++) {
        if (isalpha(string[i])) {
            const char base = islower(string[i]) ? 'a' : 'A';
            string[i] = (char) toupper(base + (string[i] - base + offset) % ALPHABET_SIZE);
        }
    }
}

void decrypt(char *string, int offset) {
    for (int i = 0; i < strlen(string); i++) {
        if (isalpha(string[i])) {
            /*
             * Determine the base character to use based on the case of the current character,
             * allowing for the offset to be applied to the correct ascii range.
             */
            const char base = islower(string[i]) ? 'a' : 'A';
            string[i] = (char) (base + (string[i] - base - offset + ALPHABET_SIZE) % ALPHABET_SIZE);
        }
    }
}

int main(int argc, char **argv) {
    char *fgets_rtn = NULL; // variable to capture return code
    char buffer[MAX_LINE_LENGTH]; // where we are reading the data into
    int line = 1;


    // Check for valid encryption/decryption directive in argv[1]
    int enc_dec;
    if (argv[1] != NULL) {
        char enc[] = "-e", dec[] = "-d";
        if (strcmp(argv[1], enc) == 0) {
            enc_dec = 0; // Encrypt
        } else if (strcmp(argv[1], dec) == 0) {
            enc_dec = 1; // Decrypt
        } else {
            fprintf(
                stderr,
                "\"%s\" is an unrecognized argument.\nPlease provide a valid encryption or decryption argument (\'-e\' or \'-d\')\n",
                (char *) argv[1]);
            return -1;
        }
    } else {
        fprintf(
            stderr, "Please provide an encryption or decryption directive (\'-e\' or \'-d\') as the first argument.\n");
        return -1;
    }

    // Check for valid offset value in argv[2]
    int cipher_offset;
    if (argv[2] != NULL) {
        if (strtol(argv[2], NULL, 10) == 0) {
            fprintf(stderr, "Please provide a valid offset value.\n");
            return -1;
        } else {
            cipher_offset = abs(strtol(argv[2], NULL, 10));
        }
    } else {
        fprintf(stderr, "Please provide an offset value as a positive integer (whole number) for the second argument.\n");
        return -1;
    }

    do {
        // read data (up to MAX_LINE_LEN chars) into buffer from stdin
        fgets_rtn = fgets(buffer, MAX_LINE_LENGTH, stdin);
        if (fgets_rtn != NULL) {
            // fgets was successful!
            if ('\n' == buffer[strlen(buffer) - 1]) {
                buffer[strlen(buffer) - 1] = '\0';
            }

            // Loop control flow; encrypt/decrypt each line according to args and print to stdout
            if (enc_dec == 0) {
                encrypt(buffer, cipher_offset);
                fprintf(stdout, "%s\n", buffer);
            } else {
                decrypt(buffer, cipher_offset);
                fprintf(stdout, "%s\n", buffer);
            }
            line++;
        }
    } while (fgets_rtn != NULL);

    return 0;
}
