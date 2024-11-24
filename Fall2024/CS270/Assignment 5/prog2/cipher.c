/*
 * Author: TODO
 * Date:   TODO
 * Description: TODO
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// buffer size
#define MAX_LINE_LENGTH 1024

void encrypt(char *string, int offset);

char *decrypt(char *string, int offset);

// ASCII Table: https://www.lookuptables.com/

void encrypt(char *string, int offset) {

    for (int i = 0; i < strlen(string); i++) {
        if (isalpha(string[i])) {
            const int ascii_value = int(string[i]);
            const int dup = offset;

            if (ascii_value + offset > 122) {
                offset -= (122 - ascii_value);
                offset = offset % 26;
                string[i] = toupper(char(96 + offset));
            } else {
                string[i] = toupper(char(ascii_value + offset));
            }
            offset = dup;
        }
    }

}

int main(int argc, char **argv) {
    char *fgets_rtn = NULL; // variable to capture return code
    char buffer[MAX_LINE_LENGTH]; // where we are reading the data into
    int line = 1;

    // pre-loop control flow; determine encrypt/decrypt and offset

    do {
        // read data (up to MAX_LINE_LEN chars) into buffer from stdin
        fgets_rtn = fgets(buffer, MAX_LINE_LENGTH, stdin);
        if (fgets_rtn != NULL) {
            // fgets was successful!
            if ('\n' == buffer[strlen(buffer) - 1]) {
                buffer[strlen(buffer) - 1] = '\0';
            }

            //loop control flow

            line++;
        }
    } while (fgets_rtn != NULL);

    return 0;
}
