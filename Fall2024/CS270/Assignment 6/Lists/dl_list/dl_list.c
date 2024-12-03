/*
 * Author: [Your Name]
 * Date:   [Date]
 *
 * Description: A doubly-linked list of integers using sentinel nodes.
 */

#include "dl_list.h"

/*
 * Create a node with value val, next = NULL and tail = NULL.
 */
node * create(int val){
  return NULL;
}

/*
 * Initialize an empty list
 * Allocate space for sentinel nodes and set their links.
 * Initialize size to 0.
 */
void init_list(dl_list * the_list){
}

/*
 * Append
 * Creates a new node with value val and adds it to the end of the list.
 */
void append(dl_list * the_list, int val){
}

/*
 * Prepend
 * Creates a new node with value val and adds it to the beginning of the list.
 */
void prepend(dl_list * the_list, int val){
}

/*
 * Insert at location i.  If i >= size, append to end of list.
 * If i < 0, prepend to beginning.
 */
void insert_at(dl_list * the_list, int val, int i){
}

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(dl_list * the_list, int val){
  return -1;
}

/*
 * Delete value
 * Deletes the value from the list.  If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(dl_list * the_list, int val){
  return 0;
}


/*
 * Delete element at location i.  If i >= size or i < 0, do nothing.
 */
void delete_at(dl_list * the_list, int i){
}

/*
 * Set the value of the element at location idx to the value val.
 */
int set(dl_list * the_list, int idx, int val){
  return 0;
}

/*
 * Print
 */
void print_list(dl_list * the_list){
  // start at the beginning
  node * cur_pos = the_list->head->next;

  printf("{");
  // while we are not at the end...
  while(cur_pos != the_list->tail){
    if(cur_pos->next != the_list->tail){
      // not the last value
      printf("%d, ", cur_pos->value);
    }
    else{
      // last value
      printf("%d", cur_pos->value);
    }
    // move on to the next element
    cur_pos = cur_pos->next;
  }
  printf("}\n");
}
