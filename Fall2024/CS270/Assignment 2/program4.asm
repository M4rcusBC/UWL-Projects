    # MIPS Program to display 12 values in descending order, decreasing by 5 each time
    # Marcus Clements
    # This is an assembly program to display a sequence of numbers, starting with a value provided by the user

    # begin data segment
.data
count:      .word   12                          # number of values to display
dec:        .word   5                           # decrement value
space:      .asciiz " "                         # space to insert between numbers
            .align  2                           # align data segment after each string
usrwlcm:    .asciiz "Please enter an integer: " # User welcome message
            .align  2                           # align data segment after each string
newline:    .asciiz "\n"                        # newline character
            .align  2                           # align data segment after each string


    # end of data segment
    #=======================================================================
    # begin text segment
.text
    # instructions start here
MAIN:
    # Prompt user for starting value
    li      $v0,    4                           # syscall for print string
    la      $a0,    usrwlcm                     # load address of usrwlcm
    syscall                                     # print usrwlcm

    li      $v0,    5                           # syscall for read integer
    syscall                                     # read integer from user
    move    $t0,    $v0                         # move the input value to $t0
    li      $v0,    4                           # syscall for print string
    la      $a0,    newline                     # load address of newline
    syscall                                     # print newline

    la      $s1,    count                       # load the address of count
    lw      $t1,    0($s1)                      # load the count value
    la      $s2,    dec                         # load the address of decrement
    lw      $t2,    0($s2)                      # load the decrement value
    la      $s3,    space                       # load the address of space

LOOP:
    beq     $t1,    $zero,      END             # if count = 0, end the loop
    add     $a0,    $zero,      $t0             # put current value in $a0
    addi    $v0,    $zero,      1               # setup int print
    syscall                                     # print int
    add     $a0,    $zero,      $s3             # put space in $a0 - can overwrite $a0 from previous syscall
    addi    $v0,    $zero,      4               # setup print string
    syscall                                     # print string
    sub     $t0,    $t0,        $t2             # decrement the value
    addi    $t1,    $t1,        -1              # decrement the count
    j       LOOP                                # jump back to loop

END:
    addi    $v0,    $zero,      10              # system call for exit
    syscall                                     # clean termination of program