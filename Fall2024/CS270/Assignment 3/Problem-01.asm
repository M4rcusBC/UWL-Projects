.data
A:          .word   0                                   # First number (multiplicand)
B:          .word   0                                   # Second number (multiplier)
C:          .word   0                                   # Will store the result of A × B
promptA:    .asciiz "Enter the first number (A): "      # Prompt for input A
promptB:    .asciiz "Enter the second number (B): "     # Prompt for input B
resultMsg:  .asciiz "The product of A and B is: "       # Message to display the result of A * B

.text
            .globl  main

main:
    # Prompt user for input A
    li      $v0,                    4                   # System call code for print string
    la      $a0,                    promptA             # Load address of promptA
    syscall                                             # Print promptA

    li      $v0,                    5                   # System call code for read integer
    syscall                                             # Read integer into $v0
    sw      $v0,                    A                   # Store the input value in A

    # Prompt user for input B
    li      $v0,                    4                   # System call code for print string
    la      $a0,                    promptB             # Load address of promptB
    syscall                                             # Print promptB

    li      $v0,                    5                   # System call code for read integer
    syscall                                             # Read integer into $v0
    sw      $v0,                    B                   # Store the input value in B

    # Load values from memory
    lw      $t0,                    A                   # Load A into $t0
    lw      $t1,                    B                   # Load B into $t1
    li      $t2,                    0                   # Initialize i = 0 (counter)
    li      $t3,                    0                   # Initialize C = 0 (result)

multiplication_loop:
    beq     $t2,                    $t1,        done    # If i == B, exit loop. Opted for a pre-test loop.
    add     $t3,                    $t3,        $t0     # C = C + A
    addi    $t2,                    $t2,        1       # i = i + 1

    j       multiplication_loop                         # Repeat loop

done:
    # Store result in memory
    sw      $t3,                    C                   # Store final result in C

    # Print the result
    li      $v0,                    4                   # System call code for print string
    la      $a0,                    resultMsg           # Load address of resultMsg
    syscall                                             # Print resultMsg

    lw      $a0,                    C                   # Load the result from C
    li      $v0,                    1                   # System call code for print integer
    syscall                                             # Print the result

    # Exit program
    li      $v0,                    10                  # System call code for exit
    syscall                                             # Execute exit