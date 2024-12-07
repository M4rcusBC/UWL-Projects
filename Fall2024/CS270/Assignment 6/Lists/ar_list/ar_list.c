/*
 * Author: Marcus Clements
 * Date:   12/06/2024
 *
 * Description: a simple array-based list of integers
 */

#include "ar_list.h"

/*
 * Initialize an empty list
 * Sets the data to NULL and size to 0.
 */
void init_list(ar_list * the_list){
  the_list->data = NULL;
  the_list->size = 0;
}

/*
 * Append
 * Extends the size of the array by 1 and writes val into the last position.
 */
void append(ar_list * the_list, int val){
    the_list->data = (int *)realloc(the_list->data, (the_list->size + 1) * sizeof(int));
    if (the_list->data == NULL) {
        // Handle memory allocation failure
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    the_list->data[the_list->size] = val;  // Use current size as the last index
    the_list->size++;                     // Increment size after appending
}


/*
 * Prepend
 * Extends the size of the array by 1, moves all values down by one spot, and
 * writes val into the first position.
 */
void prepend(ar_list * the_list, int val){
  int i;
  the_list->data = (int *)realloc(the_list->data, (the_list->size + 1) * sizeof(int));
  if (the_list->data == NULL) {
    // Handle memory allocation failure
    fprintf(stderr, "Memory allocation failed\n");
    exit(1);
  }
  for(i = the_list->size - 1; i > 0; i--){
    the_list->data[i] = the_list->data[i - 1];
  }
  the_list->data[0] = val;
  the_list->size++;
}

/*
 * Insert a node at location i with value val.  If i >= size,
 * append to the end of the list.  If i < 0, prepend to the beginning.
 */
void insert_at(ar_list * the_list, int val, int i){
  int j;
  if(i >= the_list->size){
    append(the_list, val);
    return;
  }
  if(i < 0){
    prepend(the_list, val);
    return;
  }
  the_list->size++;
  the_list->data = (int *)realloc(the_list->data, the_list->size * sizeof(int));
  for(j = the_list->size - 1; j > i; j--){
    the_list->data[j] = the_list->data[j - 1];
  }
  the_list->data[i] = val;
}

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(ar_list * the_list, int val){
  int i;
  for(i = 0; i < the_list->size; i++){
    if(the_list->data[i] == val){
      return i;
    }
  }
  return -1;
}

/*
 * Delete value
 * Deletes the value from the list, shifting all following elements up one spot.
 * If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(ar_list * the_list, int val){
  int i, j;
  i = index_of(the_list, val);
  if(i == -1){
    return -1;
  }
  for(j = i; j < the_list->size - 1; j++){
    the_list->data[j] = the_list->data[j + 1];
  }
  the_list->size--;
  the_list->data = (int *)realloc(the_list->data, the_list->size * sizeof(int));
  return 0;
}

/*
 * Delete a node at location i.  If i >= size or i < 0,
 * do nothing.
 */
void delete_at(ar_list * the_list, int i){
  int j;
  if(i >= the_list->size || i < 0){
    return;
  }
  for(j = i; j < the_list->size - 1; j++){
    the_list->data[j] = the_list->data[j + 1];
  }
  the_list->size--;
  the_list->data = (int *)realloc(the_list->data, the_list->size * sizeof(int));
}

/*
 * Set item at location idx to value val.
 * Returns 0 on success, -1 on not found.
 */
int set(ar_list * the_list, int idx, int val){
  if(idx < 0 || idx >= the_list->size){
    return -1;
  }
  the_list->data[idx] = val;
  return 0;
}

/*
 * Print
 */
void print_list(ar_list * the_list){
  printf("data -> ");
  int i;
  if(the_list->size == 0){
    printf("{}\nSize: 0\n");
    return;
  }
  printf("{");
  for(i = 0; i < the_list->size - 1; i++){
    printf("%d, ", the_list->data[i]);
  }
  printf("%d}\nSize: %d\n", the_list->data[i], the_list->size);
}
