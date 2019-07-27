#include <stdio.h>
#include <stdlib.h>
#include "tokenizer.h"
#include "history.h"

int main(){
    List* history = init_history();

    while(1){
        printf("Type something or press 'q' to quit\n>>:  ");
        char *input = malloc(sizeof(char) * 300);
        fgets(input, 300, stdin);
        printf("%s\n", input);

        if (*input == '!'){
            int id = atoi(input + 1);
            printf("ID: %d\n", id);
            input = get_history(history, id);
        }

        else if (*input == 'q'){
            printf("Terminating Program!\n");
            exit(0);
        }

        else{
            add_history(history, input);
        }

        char** tokenizeInput = tokenize(input);
        print_tokens(tokenizeInput);
        free_tokens(tokenizeInput);

        printf("\nHISTORY");
        printf("\n");
        print_history(history);
    }
    free(history);
  
}