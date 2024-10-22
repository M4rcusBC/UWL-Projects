    # MIPS Program to calculate and display the sum of integers from 0 to 9
    # Marcus Clements
    # This is an assembly program to calculate and display the sum of integers

    # begin data segment
.data
sum:    .word   0                       # initialize sum to 0
limit:  .word   10                      # limit for the loop
space:  .asciiz " "                     # space to insert between numbers
        .align  2                       # align data segment after each string

    # end of data segment
    #=======================================================================
    # begin text segment
.text
    # instructions start here
MAIN:       la      $s0,    sum         # load the address of sum
    lw      $t0,    0($s0)              # load the initial sum value
    la      $s1,    limit               # load the address of limit
    lw      $t1,    0($s1)              # load the limit value
    addi    $s2,    $zero,  0           # initialize i to 0
    la      $s3,    space               # load the address of space

LOOP:       slt     $t2,    $s2,    $t1 # if i < limit
    beq     $t2,    $zero,  END         # if not, end the loop
    add     $t0,    $t0,    $s2         # sum += i
    sw      $t0,    0($s0)              # store updated sum back to memory
    add     $a0,    $zero,  $t0         # put current sum in $a0
    addi    $v0,    $zero,  1           # setup int print
    syscall                             # print int
    add     $a0,    $zero,  $s3         # put space in $a0
    addi    $v0,    $zero,  4           # setup print string
    syscall                             # print string
    addi    $s2,    $s2,    1           # i++
    j       LOOP                        # jump back to loop

END:        addi    $v0,    $zero,  10  # system call for exit
    syscall                             # clean termination of program