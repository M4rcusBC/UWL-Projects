    # MIPS Program to display 12 values in descending order, decreasing by 5 each time
    # Marcus Clements
    # This is an assembly program to display a sequence of numbers

    # begin data segment
.data
start:  .word   850                     # starting value
count:  .word   12                      # number of values to display
dec:    .word   5                       # decrement value
space:  .asciiz " "                     # space to insert between numbers
        .align  2                       # align data segment after each string

    # end of data segment
    #=======================================================================
    # begin text segment
.text
    # instructions start here
MAIN:       la      $s0,    start       # load the address of start
    lw      $t0,    0($s0)              # load the starting value
    la      $s1,    count               # load the address of count
    lw      $t1,    0($s1)              # load the count value
    la      $s2,    dec                 # load the address of decrement
    lw      $t2,    0($s2)              # load the decrement value
    la      $s3,    space               # load the address of space

LOOP:       beq     $t1,    $zero,  END # if count = 0, end the loop
    add     $a0,    $zero,  $t0         # put current value in $a0
    addi    $v0,    $zero,  1           # setup int print
    syscall                             # print int
    add     $a0,    $zero,  $s3         # put space in $a0 - can overwrite $a0 from previous syscall
    addi    $v0,    $zero,  4           # setup print string
    syscall                             # print string
    sub     $t0,    $t0,    $t2         # decrement the value
    subi    $t1,    $t1,    1           # decrement the count
    j       LOOP                        # jump back to loop

END:        addi    $v0,    $zero,  10  # system call for exit
    syscall                             # clean termination of program