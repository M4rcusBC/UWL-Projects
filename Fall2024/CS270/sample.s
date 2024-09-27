.data
num1: .word 5
num2: .word 10
result: .word 0

.text
.globl main

main:
    # Load the first number into register $t0
    lw $t0, num1

    # Load the second number into register $t1
    lw $t1, num2

    # Add the two numbers and store the result in register $t2
    add $t2, $t0, $t1

    # Store the result back into memory
    sw $t2, result

    # Exit the program
    li $v0, 10
    syscall