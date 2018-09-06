import hashlib
import string
import random

"""
Name: Imani Martin
Lab: 1: Recursion
Date: 4.September.2018
Class: CS2302 - 1:30

Purpose: Demonstrate real world applications of recursion. In this case, password cracking

Questions:
How do  recursively concat wordList + salt_value?
How do I generate the word list without hardcoding the max number of words?


"""

# Creates a file and generate a list of words
def generateWordList(minChars, maxChars, maxNumOfWords, possibleValues):
    word = ""
    wordList = open("wordList.txt", "w")  # create and write to a file that will contain the generated words to be used
    for i in range(0, maxNumOfWords):
        for j in random.sample(possibleValues, random.randint(minChars, maxChars)):
            word += j
        wordList.write(word + "\n")
        word = ""
    wordList.close()

    print("Word list has been successfully generated!")
    return wordList


# Read the file that contains the records
def readFromTxt(username, saltValue, originalHash):
    try:
        f = open("password_file.txt", "r")
    except FileNotFoundError:
        print("Cannot locate file!")

    for line in f:
        # print(line)
        # value = line.split(",")
        username.append(line.strip().split(",")[0])
        saltValue.append(line.strip().split(",")[1])
        originalHash.append(line.strip().split(",")[2])

        # username = value[0]
        # saltValue = value[1]
        # originalHash = value[2]
        # print(originalHash)

    f.close()

# Create a new hash to be compared with original hash
def hash_with_sha256(str):
    hash_object = hashlib.sha256(str.encode("utf-8"))
    hex_dig = hash_object.hexdigit()
    return hex_dig


def generatePasswords(wordList, saltValue):
    return


def main():
    """ generating a word list """
    minChars = int(3)
    maxChars = int(7)
    maxNumOfWords = int(300)
    possibleValues = string.digits

    generateWordList(minChars, maxChars, maxNumOfWords, possibleValues)
    # wordList = open("wordList.txt", "r")
    # print(wordList.read())
    # wordList.close()
    with open("wordList.txt", "r") as f:  # save words to a list
        wordList = [line.strip() for line in f]

    print(wordList)

    """ Read from file """
    username = []
    saltValue = []
    originalHash = []
    readFromTxt(username, saltValue, originalHash)

    """ Generate all possible passwords"""
    # fileLength = len(username)
    generatePasswords(wordList, saltValue)

    """ Hash the string """
    # hex_dig = hash_with_sha256("This is how you hash a string with sha256")
    # print(hex_dig)


main()
