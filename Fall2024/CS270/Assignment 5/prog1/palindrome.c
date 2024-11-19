/*
 * Author: Marcus Clements, Dr. Dipankar Mitra
 * Date:   11/20/2024
 * Description: TODO
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// buffer size
#define LINE_LEN 1024

int main(int argc, char **argv)
{
  char *fgets_rtn = NULL; // variable to capture return code
  char buffer[LINE_LEN];  // where we are reading the data into
  int line = 1;

  do
  {
    // read data (up to LINELEN chars) into buffer from stdin
    fgets_rtn = fgets(buffer, LINE_LEN, stdin);
    if (fgets_rtn != NULL)
    {
      // fgets was successful!
      if ('\n' == buffer[strlen(buffer) - 1])
      {
        printf("Remove newline\n");
        buffer[strlen(buffer) - 1] = '\0';
      }
      printf("Echo: \"%s\" (%d)\n", buffer, line);
      line++;
    }
  } while (fgets_rtn != NULL);

  return 0;
}
