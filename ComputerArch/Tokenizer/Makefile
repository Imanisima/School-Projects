run: main.o history.o tokenizer.o
	gcc main.o tokenizer.o history.o -o run

main.o: main.c
	gcc -c main.c

tokenizer.o: tokenizer.c tokenizer.h
	gcc -c tokenizer.c

history.o: history.c history.h
	gcc -c history.c

clean:
	rm *.o run