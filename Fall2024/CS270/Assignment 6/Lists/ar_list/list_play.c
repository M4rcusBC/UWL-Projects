/*
 * Author: Marcus Clements, D. Mitra
 * Date:   12/06/2024
 * Description: Program to play with lists.
 */

#include "ar_list.c"

void display_autolab_score();

int main(int argc, char** argv){
  // Initialize the array-based list
    ar_list my_list;
    init_list(&my_list);
    printf("Initialized empty list:\n");
    print_list(&my_list);

    // Test append
    printf("\nTesting append:\n");
    append(&my_list, 10);
    print_list(&my_list);
    append(&my_list, 20);
    print_list(&my_list);
    append(&my_list, 30);
    print_list(&my_list);

    // Test prepend
    printf("\nTesting prepend:\n");
    prepend(&my_list, 5);
    print_list(&my_list);
    prepend(&my_list, 1);
    print_list(&my_list);

    // Test insert_at
    printf("\nTesting insert_at:\n");
    insert_at(&my_list, 15, 2);  // Insert 15 at index 2
    print_list(&my_list);
    insert_at(&my_list, 35, 10); // Insert 35 at the end
    print_list(&my_list);
    insert_at(&my_list, 0, -1);  // Insert 0 at the beginning
    print_list(&my_list);

    // Test index_of
    printf("\nTesting index_of:\n");
    print_list(&my_list);
    printf("Index of 15: %d\n", index_of(&my_list, 15));
    printf("Index of 100 (not in list): %d\n", index_of(&my_list, 100));

    // Test delete_from_list
    printf("\nTesting delete_from_list:\n");
    delete_from_list(&my_list, 15);  // Delete existing value
    print_list(&my_list);
    delete_from_list(&my_list, 100); // Try to delete non-existent value
    print_list(&my_list);

    // Test delete_at
    printf("\nTesting delete_at:\n");
    delete_at(&my_list, 0); // Delete first element
    print_list(&my_list);
    delete_at(&my_list, my_list.size - 1); // Delete last element
    print_list(&my_list);
    delete_at(&my_list, 100); // Out-of-bounds index
    print_list(&my_list);

    // Test set
    printf("\nTesting set:\n");
    print_list(&my_list);
    set(&my_list, 1, 99);  // Update value at index 1
    print_list(&my_list);
    set(&my_list, -1, 50); // Out-of-bounds index
    print_list(&my_list);
    set(&my_list, 100, 50); // Out-of-bounds index
    print_list(&my_list);

    // Final list state
    printf("\nFinal state of the list:\n");
    print_list(&my_list);

    // Clean up memory
    printf("\nCleaning up memory:\n");
    while (my_list.size > 0) {
        delete_at(&my_list, 0);
	print_list(&my_list);
    }

  return 0;
}

