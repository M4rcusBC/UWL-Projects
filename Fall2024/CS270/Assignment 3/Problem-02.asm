    # Marcus Clements
    # Bubble Sort Implementation in MIPS
    # CS 270 - Assignment 3, Problem 2

.data
array:      .word   0:20                                            # Array to store up to 20 integers
size:       .word   0                                               # Variable to store array size

prompt1:    .asciiz "Enter the number of elements (1-20): "
            .align  2                                               # Align data segment after each string
prompt2:    .asciiz "Enter element: "
            .align  2                                               # Align data segment after each string
output:     .asciiz "The elements sorted in ascending order are: "
            .align  2
comma:      .asciiz ", "
            .align  2

.text
            .globl  main

main:
    # Get array size from user
    li      $v0,            4                                       # Print string system call
    la      $a0,            prompt1
    syscall

    li      $v0,            5                                       # Read integer system call
    syscall
    sw      $v0,            size                                    # Store size

    # Read array elements
    la      $t0,            array                                   # Array base address
    lw      $t1,            size                                    # Counter for loop
    li      $t2,            0                                       # Index counter

input_loop:
    beq     $t2,            $t1,        sort_array                  # If we've read all elements, start sorting. Opted for a pre-test loop again here.

    # Print prompt for element
    li      $v0,            4                                       # Print string system call
    la      $a0,            prompt2
    syscall

    # Read integer
    li      $v0,            5                                       # Read integer system call
    syscall

    # Store in array
    sll     $t3,            $t2,        2                           # Multiply index by 4 for word alignment
    add     $t3,            $t0,        $t3                         # Calculate address
    sw      $v0,            ($t3)                                   # Store value

    addi    $t2,            $t2,        1                           # Increment counter
    j       input_loop

sort_array:
    # Call bubble sort
    la      $a0,            array                                   # First argument - array address
    lw      $a1,            size                                    # Second argument - array size
    jal     bubble_sort

    # Print sorted array
    li      $v0,            4                                      # Print string system call
    la      $a0,            output                                 # Load address of output string
    syscall

    # Print array elements
    la      $t0,            array                                   # Array base address
    lw      $t1,            size                                    # Size
    li      $t2,            0                                       # Counter

print_loop:
    beq     $t2,            $t1,        exit                        # If we've printed all elements, exit. Pre-test loop again.

    # Load and print current element
    sll     $t3,            $t2,        2
    add     $t3,            $t0,        $t3
    lw      $a0,            ($t3)
    li      $v0,            1
    syscall

    # Print comma if not last element
    addi    $t4,            $t1,        -1                          # size - 1
    beq     $t2,            $t4,        print_loop_end              # Skip comma if last element
    li      $v0,            4
    la      $a0,            comma
    syscall

print_loop_end:
    addi    $t2,            $t2,        1
    j       print_loop

exit:
    li      $v0,            10
    syscall

    # Bubble Sort Function
    # Parameters:
    #   $a0 - array address
    #   $a1 - array size
bubble_sort:
    # Save registers
    addi    $sp,            $sp,        -4
    sw      $ra,            ($sp)

    move    $t0,            $a0                                     # Array address
    addi    $t1,            $a1,        -1                          # Outer loop bound (size - 1)
    li      $t2,            0                                       # i = 0

outer_loop:
    beq     $t2,            $t1,        bubble_sort_end

    li      $t3,            0                                       # j = 0
    sub     $t4,            $t1,        $t2                         # size - 1 - i

inner_loop:
    beq     $t3,            $t4,        outer_loop_end

    # Calculate addresses of arr[j] and arr[j+1]
    sll     $t5,            $t3,        2
    add     $t5,            $t0,        $t5                         # address of arr[j]
    lw      $t6,            ($t5)                                   # value of arr[j]
    lw      $t7,            4($t5)                                  # value of arr[j+1]

    # Compare and swap if needed
    slt     $t8,            $t7,        $t6                         # $t8 = 1 if $t7 < $t6
    beq     $t8,            $zero,      no_swap                     # branch if $t7 >= $t6

    # Swap elements
    sw      $t7,            ($t5)
    sw      $t6,            4($t5)

no_swap:                                                            # Continue inner loop
    addi    $t3,            $t3,        1                           # j++
    j       inner_loop

outer_loop_end:
    addi    $t2,            $t2,        1                           # i++
    j       outer_loop

bubble_sort_end:
    # Restore registers
    lw      $ra,            ($sp)
    addi    $sp,            $sp,        4
    jr      $ra