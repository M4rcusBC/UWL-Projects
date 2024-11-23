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

int is_pal(char* string);
char* remove_punc_to_lower(char* string);

char* remove_punc_to_lower(char* string) {
  const int len = (int)strlen(string);
  char* result = (char*)malloc(len + 1);

  int j = 0;

  for (int i = 0; i < len; i++) {
    if (isalnum(string[i])) {
      result[j++] = (char)tolower(string[i]);
    }
  }

  result[j] = (char)"\0"; // Ensures returned strings will always be null-terminated, even if empty
  return result;
}

int is_pal(char* string) {
  char* alpha_num_string = remove_punc_to_lower(string);

  const int len = (int)strlen(alpha_num_string);
  for (int i = 0; i < ((len) / 2); i++) {
    if (alpha_num_string[i] != alpha_num_string[len - i - 1]) {
      free(alpha_num_string);
      return 0;
    }
  }
  free(alpha_num_string);
  return 1;

}

int main(int argc, char **argv)
{
  char *fgets_rtn = NULL;       // variable to capture return code
  char buffer[MAX_LINE_LENGTH]; // where we are reading the data into
  int line = 1, pal_count = 0;

  printf("Output:\n");

  do
  {
    // read data (up to MAX_LINE_LEN chars) into buffer from stdin
    fgets_rtn = fgets(buffer, MAX_LINE_LENGTH, stdin);
    if (fgets_rtn != NULL) {
      // fgets was successful!
      if ('\n' == buffer[strlen(buffer) - 1]) {
        buffer[strlen(buffer) - 1] = '\0';
      }
      if (is_pal(buffer)) {
        pal_count++;
        printf("[Palindrome!]: %s\n", buffer);
      } else {
        printf("[Regular String]: %s\n", buffer);
      }
      line++;
    }
  } while (fgets_rtn != NULL);

  printf("Summary:\nTotal Palindromes: %d (out of %d strings)", pal_count, (line - 1));

  return 0;
}
