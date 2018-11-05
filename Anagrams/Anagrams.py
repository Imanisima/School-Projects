from AVLTree import AVLTree
from AVLTree import Node
from RedBlackTree import RedBlackTree

"""
Name: Imani Martin
Lab #: 3
Class: CS2302 1:30

Purpose: Prints all the permutations of each word and checks if that word is a part of the English language.
"""


# Populates the english words using a binary search tree
def english_words(BSTree):
    file = open("word.txt", "r")
    for line in file:
        l = line.split("\n")
        print(line)
        if isinstance(BSTree, AVLTree):
            BSTree.insert(Node(l[0]))
        else:
            BSTree.insert(l[0])

    file.close()


# Prints all of the anagrams of a given word
def print_anagrams(word, prefix=""):
    if len(word) <= 1:
        str = prefix + word

        if str in english_words:
            print(prefix + word)

    else:
        for i in range(len(word)):
            cur = word[i:i+1]
            before = word[0:i]  # letters before cur
            after = word[i+1:]  # letters after cur

            if cur not in before:  # check if permutations of cur have not been generated
                print_anagrams(before + after, prefix + cur)


# Asks user which tree they would like populated
def main():
    print("Welcome to the Anagram!\nWhat kind of tree would you like to populate?")
    print("1. AVL TREE\n2. Red-Black Tree")
    user_choice = input()

    if user_choice is "1":
        print("You've selected the AVL TREE")
        BSTree = AVLTree()

    elif user_choice is "2":
        print("You've selected the RED AND BLACK TREE")
        BSTree = RedBlackTree()

    else:
        print("Invalid answer. Please try again.")

    english_words(BSTree)
    print_anagrams(BSTree, "love")


main()
