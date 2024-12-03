#
# A simple makefile
#

CC=gcc
CFLAGS=-g -O0 -Wall

PROGS=list_play

all: $(PROGS)


list_play: list_play.c ar_list.o
	$(CC) list_play.c ar_list.o -o list_play $(CFLAGS)

ar_list.o: ar_list.c ar_list.h
	$(CC) ar_list.c -c -o ar_list.o $(CFLAGS)

clean:
	rm -f $(PROGS)
	rm -rf *.o *.dSYM *~
