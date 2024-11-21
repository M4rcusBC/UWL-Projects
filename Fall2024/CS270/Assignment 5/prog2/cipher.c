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

int main(int argc, char **argv)
{
  char *fgets_rtn = NULL;       // variable to capture return code
  char buffer[MAX_LINE_LENGTH]; // where we are reading the data into
  int line = 1;

  do
  {
    // read data (up to LINELEN chars) into buffer from stdin
    fgets_rtn = fgets(buffer, MAX_LINE_LENGTH, stdin);
    if (fgets_rtn != NULL)
    {
      // fgets was successful!
      if ('\n' == buffer[strlen(buffer) - 1])
      {
        buffer[strlen(buffer) - 1] = '\0';
      }
      printf("Echo: \"%s\" (%d)\n", buffer, line);
      line++;
    }
  } while (fgets_rtn != NULL);

  return 0;
}
