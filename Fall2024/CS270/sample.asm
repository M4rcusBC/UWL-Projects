    #MIPS Hello World
    # Your Name
    # We are doing an assembly programming for "hello world!"
    # begin data segment
.data
hi:     .asciiz "Hello World!\n"        #create a null-terminated string "Hello world"
        .align  2                       # align data segment after each string
num:    .word   5                       # reserve space and initialize
i:      .space  4                       # counter variable
space:  .asciiz " "                     # space to insert between numbers
        .align  2                       # align data segment after each string
    # end of data segment
    #=======================================================================
    # begin text segment
.text
    # instructions start here
MAIN:       la      $s0,    hi          # load the address of string hi
    add     $a0,    $zero,  $s0         # put address of hi in $a0
    addi    $v0,    $zero,  4           # printing a string
    syscall                             # print
    la      $s1,    num                 # load address of num for use
    lw      $s1,    0($s1)              # load value of num
    la      $s2,    space               # load address of
    addi    $s3,    $zero,  0           # set i to 0
LOOP:       slt     $t0,    $s3,    $s1 # i < num
    beq     $t0,    $zero,  END         # go to end when done
    add     $a0,    $zero,  $s3         # put i in $a0
    addi    $v0,    $zero,  1           # setup int print
    syscall                             # print int
    add     $a0,    $zero,  $s2         # put space in $a0
    addi    $v0,    $zero,  4           # setup print string
    syscall                             # print string
    addi    $s3,    $s3,    1           # i++
    j       LOOP                        # loop!
END:        addi    $v0,    $zero,  10  # system call for exit
    syscall                             # clean termination of program