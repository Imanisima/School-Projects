from AVLTree import AVLTree
from AVLTree import AVLNode
from RedBlackTree import RedBlackTree

"""
Lab #: 3
Class: CS2302 1:30

Purpose: Prints all the permutations of each word and checks if that word is a part of the English language.
"""

word_file = "word.txt"
test_file = "test.txt"


# Asks user which tree they would like populated
def main():
    print("Welcome to the Anagram!\nWhat kind of tree would you like to populate?")
    print("1. AVL Tree\n2. RED AND BLACK Tree")
    user_choice = input()

    if user_choice is "1":
        print("You've selected the AVL TREE\nNow Loading\n.\n.\n.")
        insert_into_AVL(word_file)
        print("Tree successfully populated!")

    elif user_choice is "2":
        print("You've selected the RED AND BLACK TREE\nNow Loading\n.\n.\n.")
        insert_into_RBTree(word_file)
        print("Tree successfully populated!")

    else:
        print("Invalid answer. Please try again.")


# Insert text into AVL Tree
def insert_into_AVL(text_file):
    avlTree = AVLTree()

    with open(text_file) as file:
        for line in file:
            if "\n" in line:
                line = line.replace("\n", "")
            node = AVLNode(line.upper())
            avlTree.insert(node)

        return avlTree


# Insert text into Red and Black Tree
def insert_into_RBTree(text_file):
    RBTree = RedBlackTree()

    with open(text_file) as file:
        for line in file:
            if "\n" in line:
                line = line.replace("\n", "")
            node = line.upper()
            RBTree.insert(node)

        return RBTree


def demo():
    word = "spot"
    print("\n~NOW PRINTING RESULTS~")
    print("Testing word: ", word, "\nPrinting Anagrams for \"", word, "\": ", print_anagrams(word))
    print("Number of Anagrams: ", count_anagrams(word))

                    # Word with Most Anagrams #
    print("\nWord with Most Anagrams: \"", top_anagram(test_file), "\"")
    print("\nPrinting anagrams for \"", top_anagram(test_file), "\":")
    print(print_anagrams(top_anagram(test_file)))
    print("\nNumber of Anagrams for above word: ", count_anagrams(top_anagram(test_file)))


# returns the word with the most anagrams
def top_anagram(text_file):
    max_counter = 0
    max_anagram = ""
    with open(text_file) as file:
        for line in file:
            line = line.replace("\n", "")
            current_word = count_anagrams(line)
            if current_word > max_counter:
                max_counter = current_word
                max_anagram = line

        return max_anagram


# Count the number of anagrams per word
def count_anagrams(word, prefix=""):
    counter = 0
    if len(word) <= 1:
        str = prefix + word

        # If anagram is a word, increase counter
        if english_words(str):
            return counter+1
        return counter

    else:
        for i in range(len(word)):
            cur = word[i: i + 1]
            before = word[0: i]
            after = word[i + 1:]
            if cur not in before:
                counter += count_anagrams(before + after, prefix + cur)
        return counter


# Prints all of the anagrams of a given word
def print_anagrams(word, prefix=""):
    if len(word) <= 1:
        str = prefix + word

        if english_words(str):
            print(prefix + word)

    else:
        for i in range(len(word)):
            cur = word[i:i+1]
            before = word[0:i]  # letters before cur
            after = word[i+1:]  # letters after cur

            if cur not in before:  # check if permutations of cur have not been generated
                print_anagrams(before + after, prefix + cur)


# Adds the english words file to a binary search tree
def english_words(word):
    avlTree = AVLTree()

    with open(test_file) as file:
        for line in file:
            if "\n" in line:
                line = line.replace("\n", "")

            # convert all words to uppercase and insert into tree
            node = AVLNode(line.upper())
            avlTree.insert(node)


    # Searching for word
    if avlTree.search(word):
        return True

main()
demo()
