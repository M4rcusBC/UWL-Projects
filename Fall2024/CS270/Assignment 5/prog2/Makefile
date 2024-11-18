#
# A simple makefile
#

CC=gcc
CFLAGS=-g -O0 -Wall

PROGS=cipher

all: $(PROGS)

cipher: cipher.c
	$(CC) cipher.c -o cipher $(CFLAGS)

clean:
	rm -f $(PROGS)
	rm -rf *.o *.dSYM *~
