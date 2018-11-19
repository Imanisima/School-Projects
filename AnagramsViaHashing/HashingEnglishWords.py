from Hash_node import *

"""
Class: CS2302 1:30
Lab: 4

Purpose: Implement an algorithm to find the anagrams of a word using hash tables instead of a binary search tree. This program will also compute the average number of comparisons required to perform retrieval and compute the load factor.

"""
file = "word.txt"


def main():
    hash_table = create_table(file)
    print("Hash table successfully generated!")
    print("Load factor: ", load_factor(hash_table))
    print("Average number of comparisons: ", avg_comparisons(hash_table, file))


# Creating the hash table
def create_table(file):
    # Get the total number of words in the file to determine the size of the table
    num_of_words = total_words(file)
    hash_table = [None] * num_of_words

    with open(file) as f:
        for line in f:
            line = line.rstrip()
            word = line.lower() # convert all words to same ascii
            pos = determine_hash_value(word, num_of_words)
            hash_table[pos] = hash_node(line, hash_table[pos])

    return hash_table


# Determines the size of the file
def total_words(file):
    num_of_words = 0
    with open(file) as f:
        for line in f:
            num_of_words += 1
    f.close()

    return num_of_words


# Find the position of the word within the hash table
def determine_hash_value(word, num_of_words):
    int_value = base_26(word)
    return int_value % num_of_words


# Retrieves the average comparisons for the words
def avg_comparisons(hash_table, file):
    counter = 0
    num_of_comparisons = 0
    num_of_words = total_words(file)

    temp = dict()
    with open(file) as f:
        for line in f:
            line = line.rstrip()
            word = line.lower()
            pos = determine_hash_value(word, num_of_words)
            temp[word] = num_comparisons_per_word(hash_table, pos)

    for value in temp:
        num_of_comparisons += temp[value]
        counter += 1
    return round((num_of_comparisons / counter), 3)


# Determines the number of comparisons necessary for each word
def num_comparisons_per_word(hash_table, pos):
    counter = 0
    temp = hash_table[pos]

    while temp is not None:
        counter += 1
        temp = temp.next

    return counter


# Converting string from base-26 to a integer because each word within English is a base-26 integer
def base_26(word):
    word = word.lower()
    if word == "" or len(word) == 0:
        return
    if len(word) == 1:
        # returns an integer representing the unicode
        return ord(word)-96
    else:
        return base_26(word[1:]) + (26**(len(word)-1))*(ord(word[0])-96)


# Returns the load factor of the hash table
def load_factor(hash_table):
    num_elements = 0

    for i in range(len(hash_table)):
        temp = hash_table[i]

        while temp is not None:
            num_elements += 1
            temp = temp.next

    return num_elements/len(hash_table)


main()
