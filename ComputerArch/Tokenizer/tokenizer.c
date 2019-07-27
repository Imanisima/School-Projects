#include <stdio.h>
#include <stdlib.h>
#include "tokenizer.h"
#include "history.h"


/* Counts the number of characters in the string argument. */
int string_length(char *str)
{
    char *counter = str;
    while (*str)
    {
        str++;
    }

    return str - counter;
}

/* Evaluates if the character c is an acceptable character for
   a token. */
char is_valid_character(char c)
{
    if (c > 32)
    {
        return 1;
    }
    return 0;
}

/* Finds the next word in the string. */
char* find_word_start(char *str)
{
    while (!is_valid_character(*str))
    {
        str++;

        if (is_valid_character(*str))
        {
            return str; 
        }
    }
    return str;
}

/* Returns everything after first word. */
char* find_word_end(char *str)
{
    while (is_valid_character(*str))
    {
        str++;

        if (*str == 32) // checks for space
        {
            return str;
        }
    }
    return "\n";
}

/* Counts the number of words in the string argument. */
int count_words(char *str)
{
    int numOfWords = 0;

    if (*str == '\0')
    {
        return 0;
    }

    while (*str != 10) // Check for newline
    {
        str = find_word_start(str);
        numOfWords++;
        str = find_word_end(str);
    }
    return numOfWords;
}

/* Print all tokens */
void print_tokens(char **tokens)
{
    printf("TOKENIZER\n");
    while (*tokens != NULL)
    {
        printf("%s\n", *tokens);	
		tokens++;
    }
	

}

/* Frees all tokens and the array containing tokens */
void free_tokens(char **tokens)
{
    char **freeTokens = tokens;

    while (*freeTokens != NULL)
    {
        free(*freeTokens); // deallocate memory
        freeTokens++;
        
    }
    free(tokens);
}

/* Tokenizer  */
char** tokenize(char *str){
    int numOfWords = count_words(str);
    int findWordLength[numOfWords];
    int pos = 0;

    char* currentWord = str;
    char* newWord = str; // copy of original string so that original one is unchanged
    int word = string_length(find_word_end(currentWord)); // keeps track of end of sentence
    
    char **tokens = (char**)malloc((sizeof(char*) * numOfWords)+3);


      for (int i = 0; i < numOfWords; i++){
        findWordLength[i] = string_length(find_word_start(newWord)) - string_length(find_word_end(newWord));
        newWord = find_word_end(newWord);
        newWord = find_word_start(newWord);
    }

    for (int i = 0; i < numOfWords; i++){
        tokens[i] = malloc((sizeof(char*) * findWordLength[i]) + 1);

        while(pos < findWordLength[i]){
            if(is_valid_character(*str)){
                tokens[i][pos] = *str;
                str++;
                pos++;
            } 
            else {
                str++;
                pos = 0;
            }
        }
        pos = 0;
    }
    return tokens;
}
