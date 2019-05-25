## Lab Project 1: Building a Tokenizer :hammer:
1. Ensure that all of the required files are in the same folder: tokenizer.c, tokenizer.h, main.c, main.h, history.c, history.h, Makefile :file_folder:

2. Compile Code :computer:
- Using the terminal, type...
  * ``` make clean ``` (to ensure the project is indeed clean of previously built .o files)
  * ``` make run ```
  * ``` ./run ```

3. Enter any word or phrase when indicated by ``` >> ```. Your input will then be tokenized and printed. With each input **HISTORY** is updated and printed. 
4. At anytime to terminate the program, press the ``` 'Q' ``` key.

Note: You can **recall your history** by press ```!(number beside printed history)```.

***FOR EXAMPLE**
```
HISTORY
1) hello
2) how are you doing?
3) adfhk adfljl dja
4) foo

>> !3

RETRIEVED HISTORY
3) adfhk adfljl dja
```
