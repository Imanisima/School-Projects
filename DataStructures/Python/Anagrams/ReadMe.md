LAB 3

An anagram is a permutation of the letters of a word to produce another word. It generates all permutations of the letters in the word and, for each permutation, it checks if it is a valid word in the English language.  For example, if you call print_anagrams(”spot”), the method should print the following words: spot, stop, post, pots, opts, and tops.

 We will implement this data structure using a binary search tree. To populate engish_words, we will use a text file called words.txt that contains 354,984 English words. You can download words.txt from the following URL: https://github.com/dwyl/english-words/​. Once you have downloaded words.txt, write a function that reads the file and populates the binary search tree with all the English words contained in the file.  Ask the user what type of binary search tree he/she wants to use (AVL Tree or Red-Black Tree).
 
 Write another function called count_anagrams that does not produce output, but returns the number of anagrams that a given word has. For example, count_anagrams(”spot”) should return 6. Finally, write another function that reads another file that contains words (feel free to create it yourself) and finds the word in the file that has the greatest number of anagrams. 
