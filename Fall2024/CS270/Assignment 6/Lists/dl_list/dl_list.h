/*
 * Author: [Your Name]
 * Date:   [Date]
 *
 * Description: A doubly-linked list of integers using sentinel nodes.
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * Globals
 */
extern int line_count;

/*
 * First we need some data structures for our list.
 */

/*
 * Node struct
 * Stores a value and a pointer to the next thing in the list.
 */
struct node {
  // data
  int value;
  // links
  struct node * next;
  struct node * prev;
};
typedef struct node node;

/*
 * List struct
 * Doubly-linked list with sentinel nodes for head and tail.
 */
struct dl_list {
  node * head;
  node * tail;
  int size;
};
typedef struct dl_list dl_list;

/******************************************************************/

/*
 * Now we need some operations that are performed on lists.
 */

/*
 * Create a node with value val, next = NULL and tail = NULL.
 */
node * create(int val);

/*
 * Initialize an empty list
 * Allocate space for sentinel nodes and set their links.
 * Initialize size to 0.
 */
void init_list(dl_list * the_list);

/*
 * Append
 * Creates a new node with value val and adds it to the end of the list.
 */
void append(dl_list * the_list, int val);

/*
 * Prepend
 * Creates a new node with value val and adds it to the beginning of the list.
 */
void prepend(dl_list * the_list, int val);

/*
 * Insert a node at location i with value val.  If i >= size,
 * append to the end of the list.  If i < 0, prepend to beginning.
 */
void insert_at(dl_list * the_list, int val, int i);

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(dl_list * the_list, int val);

/*
 * Delete value
 * Deletes the value from the list.  If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(dl_list * the_list, int val);

/*
 * Delete a node at location i.  If i >= size or i < 0, do nothing.
 */
void delete_at(dl_list * the_list, int i);

/*
 * Set item at location i to value val.
 */
int set(dl_list * the_list, int idx, int val);

/*
 * Print
 */
void print_list(dl_list * the_list);
