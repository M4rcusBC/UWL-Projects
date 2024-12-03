#
# A simple makefile
#

CC=gcc
CFLAGS=-g -O0 -Wall

PROGS=list_play

all: $(PROGS)


list_play: list_play.c dl_list.o
	$(CC) list_play.c dl_list.o -o list_play $(CFLAGS)

dl_list.o: dl_list.c dl_list.h
	$(CC) dl_list.c -c -o dl_list.o $(CFLAGS)

clean:
	rm -f $(PROGS)
	rm -rf *.o *.dSYM *~

