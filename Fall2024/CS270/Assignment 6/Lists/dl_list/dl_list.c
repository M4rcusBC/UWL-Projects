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
node *create(int val)
{
  node *new_node = (node *)malloc(sizeof(node));
  new_node->value = val;
  new_node->next = NULL;
  new_node->prev = NULL;
  return new_node;
}

/*
 * Initialize an empty list
 * Allocate space for sentinel nodes and set their links.
 * Initialize size to 0.
 */
void init_list(dl_list *the_list)
{
  the_list->head = create(0);
  the_list->tail = create(0);
  the_list->head->next = the_list->tail;
  the_list->tail->prev = the_list->head;
  the_list->size = 0;
}

/*
 * Append
 * Creates a new node with value val and adds it to the end of the list.
 */
void append(dl_list *the_list, int val)
{
  node *new_node = create(val);      // create a new node for the value
  node *last = the_list->tail->prev; // get the last node in the list

  last->next = new_node;           // set the last node's next to the new node
  new_node->prev = last;           // set the new node's prev to the last node
  new_node->next = the_list->tail; // set the new node's next to the tail
  the_list->tail->prev = new_node; // set the tail's prev to the new node

  // increment the size of the list
  the_list->size++;
}

/*
 * Prepend
 * Creates a new node with value val and adds it to the beginning of the list.
 */
void prepend(dl_list *the_list, int val)
{
  node *new_node = create(val);       // create a new node for the value
  node *first = the_list->head->next; // get the first node in the list

  first->prev = new_node;          // set the first node's prev to the new node
  new_node->next = first;          // set the new node's next to the first node
  new_node->prev = the_list->head; // set the new node's prev to the head
  the_list->head->next = new_node; // set the head's next to the new node

  // increment the size of the list
  the_list->size++;
}

/*
 * Insert at location i.  If i >= size, append to end of list.
 * If i < 0, prepend to beginning.
 */
void insert_at(dl_list *the_list, int val, int i)
{
  // if i is greater than or equal to the size, append to the end
  if (i >= the_list->size)
  {
    append(the_list, val);
  }
  // if i is less than or equal to 0, prepend to the beginning
  else if (i <= 0)
  {
    prepend(the_list, val);
  }
  // otherwise, insert at the specified location; 1 <= i < size
  else
  {
    node *new_node = create(val);         // create a new node for the value
    node *cur_pos = the_list->head->next; // start at the beginning

    // move to the correct location
    for (int j = 0; j < i; j++)
    {
      cur_pos = cur_pos->next;
    }

    // insert the new node
    new_node->next = cur_pos;       // set the new node's next to the current position
    new_node->prev = cur_pos->prev; // set the new node's prev to the current position's prev
    cur_pos->prev->next = new_node; // set the current position's prev's next to the new node
    cur_pos->prev = new_node;       // set the current position's prev to the new node

    // increment the size of the list
    the_list->size++;
  }
}

/*
 *Index of - returns the index of val in the list.  If not found, -1 is returned.
 */
int index_of(dl_list *the_list, int val)
{
  node *cur_pos = the_list->head->next; // start at the beginning
  int idx = 0;                          // start at index 0

  // while we are not at the end...
  while (cur_pos != the_list->tail)
  {
    // if we find the value, return the index
    if (cur_pos->value == val)
    {
      return idx;
    }
    // move on to the next element
    cur_pos = cur_pos->next;
    idx++;
  }
  return -1; // value not found
}

/*
 * Delete value
 * Deletes the value from the list.  If not found, list unchanged.
 * Returns 0 on success, -1 on not found.
 */
int delete_from_list(dl_list *the_list, int val)
{
  node *cur_pos = the_list->head->next; // start at the beginning

  // while we are not at the end...
  while (cur_pos != the_list->tail)
  {
    // if we find the value...
    if (cur_pos->value == val)
    {
      cur_pos->prev->next = cur_pos->next; // set the previous node's next to the current node's next
      cur_pos->next->prev = cur_pos->prev; // set the next node's prev to the current node's prev
      free(cur_pos);                       // free the current node
      the_list->size--;                    // decrement the size of the list
      return 0;                            // return success
    }
    // move on to the next element
    cur_pos = cur_pos->next;
  }
  // If the above while-loop is skipped, we know the list is empty, so we can return -1 anyways.
  return -1; // value not found
}

/*
 * Delete element at location i.  If i >= size or i < 0, do nothing.
 */
void delete_at(dl_list *the_list, int i)
{
  // Check if i is out of bounds
  if (i >= the_list->size || i < 0)
  {
    return;
  }

  node *cur_pos = the_list->head->next; // start at the beginning

  // Move to the i-th node
  for (int j = 0; j < i; j++)
  {
    cur_pos = cur_pos->next;
  }

  // Remove the node
  cur_pos->prev->next = cur_pos->next;
  cur_pos->next->prev = cur_pos->prev;
  free(cur_pos); // free the current node

  // Decrement the size of the list
  the_list->size--;
}

/*
 * Set the value of the element at location idx to the value val.
 */
int set(dl_list *the_list, int idx, int val)
{
  // Check if idx is out of bounds
  if (idx >= the_list->size || idx < 0)
  {
    return -1;
  }

  node *cur_pos = the_list->head->next; // start at the beginning

  // Move to the idx-th node
  for (int j = 0; j < idx; j++)
  {
    cur_pos = cur_pos->next;
  }

  // Set the value
  cur_pos->value = val;

  return 0;
}

/*
 * Print
 */
void print_list(dl_list *the_list)
{
  // start at the beginning
  node *cur_pos = the_list->head->next;

  printf("head -> {");
  // while we are not at the end...
  while (cur_pos != the_list->tail)
  {
    if (cur_pos->next != the_list->tail)
    {
      // not the last value
      printf("%d, ", cur_pos->value);
    }
    else
    {
      // last value
      printf("%d", cur_pos->value);
    }
    // move on to the next element
    cur_pos = cur_pos->next;
  }
  printf("} <- tail\nLength: %d\n", the_list->size);
}
