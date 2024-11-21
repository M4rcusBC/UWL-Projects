#
# A simple makefile
#

CC=gcc
CFLAGS=-g -O0 -Wall

PROGS=palindrome

all: $(PROGS)

extract: palindrome.c
	$(CC) palindrome.c -o palindrome $(CFLAGS)

clean:
	rm -f $(PROGS)
	rm -rf *.o *.dSYM *~
