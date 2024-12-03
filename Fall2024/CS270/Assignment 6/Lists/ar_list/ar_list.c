/*
 * Author: Dipankar Mitra
 * Date:   05/01/2022
 *
 * Description: a simple array-based list of integers
 */

#include "ar_list.h"

/*
 * Initialize an empty list
 * Sets the data to NULL and size to 0.
 */
void init_list(ar_list * the_list){
}

/*
 * Append
 * Extends the size of the array by 1 and writes val into the last position.
 */
void append(ar_list * the_list, int val){
}

/*
 * Prepend
 * Extends the size of the array by 1, moves all values down by one spot, and
 * writes val into the first position.
 */
void prepend(ar_list * the_list, int val){
}

/*
 * Insert a node at location i with value val.  If i >= size,
 * append to the end of the list.  If i < 0, prepend to the beginning.
 */
void insert_at(ar_list * the_list, int val, int i){
}

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(ar_list * the_list, int val){
  return -1;
}

/*
 * Delete value
 * Deletes the value from the list, shifting all following elements up one spot.
 * If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(ar_list * the_list, int val){
  return -1;
}

/*
 * Delete a node at location i.  If i >= size or i < 0,
 * do nothing.
 */
void delete_at(ar_list * the_list, int i){
}

/*
 * Set item at location idx to value val.
 * Returns 0 on success, -1 on not found.
 */
int set(ar_list * the_list, int idx, int val){
  return 0;
}

/*
 * Print
 */
void print_list(ar_list * the_list){
  int i;
  if(the_list->size == 0){
    printf("{}\n");
    return;
  }
  printf("{");
  for(i = 0; i < the_list->size - 1; i++){
    printf("%d, ", the_list->data[i]);
  }
  printf("%d}\n", the_list->data[i]);
}
