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

    elif user_choice is "2":
        print("You've selected the RED AND BLACK TREE\nNow Loading\n.\n.\n.")
        insert_into_RBTree(word_file)

    else:
        print("Invalid answer. Please try again.")


# Insert text into AVL Tree
def insert_into_AVL(text_file):
    avlTree = AVLTree()

    original_file = open(text_file, "r")
    for line in original_file:
        l = line.split("\n")
        node = AVLNode(l[0])
        avlTree.insert(node)

    print("Tree successfully populated!")
    anagrams(avlTree, "spot")  # search and print anagrams

    list_words = []
    demo = open(test_file, "r")
    for line in demo:
        l = line.split("\n")
        list_words.append(l[0])
    # count and find the topmost anagram
    count_anagrams(list_words, avlTree)


# Insert text into Red and Black Tree
def insert_into_RBTree(text_file):
    RBTree = RedBlackTree()

    with open(text_file) as file:
        for line in file:
            l = line.split("\n")
            RBTree.insert(l[0])

    print("Tree successfully populated!")
    # print anagrams
    anagrams(RBTree, "money")

    list_words = []
    test = open(test_file, "r")
    for line in test:
        l = line.split("\n")
        list_words.append(l[0])

    # count number and find the topmost anagram
    count_anagrams(list_words, RBTree)


# Count the number of anagrams per word
def count_anagrams(list_words, tree):
    len_of_anagram = 0
    top_anagram_word = ""

    for word in list_words:
        counter = anagrams(tree, word, "", False)

        if len(counter) > len_of_anagram:
            len_of_anagram = len(counter)
            top_anagram_word = word

    print("Top anagram: ", top_anagram_word, ": ", len_of_anagram)


# Prints all of the anagrams of a given word
def anagrams(tree, word, prefix="", not_empty=True):
    anagram_list = []

    def print_anagrams(word, prefix):
        if len(word) <= 1:
            str = prefix + word

            temp = tree.search(str)
            if temp is not None:
                if not_empty:
                    print(temp.key)
                    anagram_list.append(temp.key)

        else:
            for i in range(len(word)):
                cur = word[i:i+1]
                before = word[0:i]  # letters before cur
                after = word[i+1:]  # letters after cur

                if cur not in before:  # check if permutations of cur have not been generated
                    print_anagrams(before + after, prefix + cur)

    print_anagrams(word, prefix)
    if not_empty:
        print("Number of Anagrams: ", len(anagram_list))
    return anagram_list


main()
