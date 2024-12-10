/*
 * Author: Marcus Clements, D. Mitra
 * Date:   12/06/2024
 * Description: Program to play with lists.
 */

#include "ar_list.c"

void display_autolab_score();

int main(int argc, char **argv) {
    // Initialize the array-based list
    ar_list* my_list = (ar_list*)malloc(sizeof(ar_list)); // Allocate space for ar_list

    init_list(my_list);
    printf("Initialized empty list:\n");
    print_list(my_list);
    printf("-------------------------\n");

    // Test append
    printf("\nTesting append (10, 20, 30):\n");
    append(my_list, 10);
    print_list(my_list);
    append(my_list, 20);
    print_list(my_list);
    append(my_list, 30);
    print_list(my_list);
    printf("-------------------------\n");

    // Test prepend
    printf("\nTesting prepend (5, 1, 16):\n");
    prepend(my_list, 5);
    print_list(my_list);
    prepend(my_list, 1);
    print_list(my_list);
    prepend(my_list, 16);
    print_list(my_list);
    printf("-------------------------\n");

    // Test insert_at
    printf("\nTesting insert_at (val, idx),((15, 2), (35, 10), (57, 4), (0, -1)):\n");
    insert_at(my_list, 15, 2); // Insert 15 at index 2
    print_list(my_list);
    insert_at(my_list, 35, 10); // Insert 35 at the end
    print_list(my_list);
    insert_at(my_list, 57, 4);
    print_list(my_list);
    insert_at(my_list, 0, -1); // Insert 0 at the beginning
    print_list(my_list);
    printf("-------------------------\n");

    // Test index_of
    printf("\nTesting index_of:\n");
    print_list(my_list);
    printf("Index of 15: %d\n", index_of(my_list, 15));
    printf("Index of 10: %d\n", index_of(my_list, 10));
    printf("Index of 0: %d\n", index_of(my_list, 0));
    printf("Index of 100 (not in list): %d\n", index_of(my_list, 100));
    printf("-------------------------\n");

    // Test delete_from_list
    printf("\nTesting delete_from_list (15, 5, 100 (non-existent)):\n");
    delete_from_list(my_list, 15); // Delete existing value
    print_list(my_list);
    delete_from_list(my_list, 5);
    print_list(my_list);
    delete_from_list(my_list, 100); // Try to delete non-existent value
    print_list(my_list);
    printf("-------------------------\n");

    // Test delete_at
    printf("\nTesting delete_at(0, my_list.size - 1, 100):\n");
    delete_at(my_list, 0); // Delete first element
    print_list(my_list);
    delete_at(my_list, my_list->size - 1); // Delete last element
    print_list(my_list);
    delete_at(my_list, 100); // Out-of-bounds index
    print_list(my_list);
    printf("-------------------------\n");

    // Test set
    printf("\nTesting set (idx, val)[(1, 99), (-1, 50), (4, 68), (100, 50)]:\n");
    print_list(my_list);
    set(my_list, 1, 99); // Update value at index 1
    print_list(my_list);
    set(my_list, -1, 50); // Out-of-bounds index
    print_list(my_list);
    set(my_list, 4, 68); // Update value at index 4
    print_list(my_list);
    set(my_list, 100, 50); // Out-of-bounds index
    print_list(my_list);
    printf("-------------------------\n");

    // Final list state
    printf("\nFinal state of the list:\n");
    print_list(my_list);

    // Clean up memory
    printf("\nCleaning up memory:\n");
    while (my_list->size > 0) {
        delete_at(my_list, 0);
        print_list(my_list);
    }
    free(my_list);
    printf("-------------------------\n");

    return 0;
}
