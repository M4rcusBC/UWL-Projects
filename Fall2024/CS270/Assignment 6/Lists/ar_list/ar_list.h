/*
 * Author: Dipankar Mitra
 * Date:   05/01/2022
 *
 * Description: a simple array-based list of integers
 */

#include <stdio.h>
#include <stdlib.h>


/*
 * First we need some data structures for our list.
 */

/*
 * List struct
 * Stores the list as an array in data and keeps track of its size.
 */
struct ar_list {
  int * data;
  int size;
};
typedef struct ar_list ar_list;

/******************************************************************/

/*
 * Now we need some operations that are performed on lists.
 */


/*
 * Initialize an empty list
 * Sets the data to NULL and size to 0.
 */
void init_list(ar_list * the_list);

/*
 * Append
 * Extends the size of the array by 1 and writes val into the last position.
 */
void append(ar_list * the_list, int val);

/*
 * Prepend
 * Extends the size of the array by 1, moves all values down by one spot, and
 * writes val into the first position.
 */
void prepend(ar_list * the_list, int val);

/*
 * Insert a node at location i with value val.  If i >= size,
 * append to the end of the list.  If i < 0, prepend to the beginning.
 */
void insert_at(ar_list * the_list, int val, int i);

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(ar_list * the_list, int val);

/*
 * Delete value
 * Deletes the value from the list, shifting all following elements up one spot.
 * If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(ar_list * the_list, int val);

/*
 * Delete a node at location i.  If i >= size or i < 0,
 * do nothing.
 */
void delete_at(ar_list * the_list, int i);

/*
 * Set item at location idx to value val.
 */
int set(ar_list * the_list, int idx, int val);

/*
 * Print
 */
void print_list(ar_list * the_list);
