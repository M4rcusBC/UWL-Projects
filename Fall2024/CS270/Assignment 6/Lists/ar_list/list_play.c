/*
 * Author: Dipankar Mitra
 * Date:   05/01/2022
 * Description: Program to play with lists.
 */

#include "ar_list.h"

void display_autolab_score();

int main(int argc, char** argv){
  ar_list * a_list = NULL;
  int i;

  a_list = (ar_list *) malloc(sizeof(ar_list));
  if(a_list == NULL){
    return -1;
  }

  init_list(a_list);

  for( i = 0; i < 10; i++){
    append(a_list, i);
  }

  print_list(a_list);

  for(i = 5; i < 10; i += 4){
    // call the function
    set(a_list, i, 55);
    // print line count
    printf("Set element %d to 55.\n", i);
  }

  // add more tests for the rest of the functions

  return 0;
}

