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
How do I generate the word list recursively? Do I really need the dictionary?

Comments: Still messy. Problems finding the right hash.

"""

def main():
    """ generating a word list """
    minChars = int(3)  # Min number of characters required for the password
    maxChars = int(7)  # Max number of characters required for the password
    maxNumOfWords = int(1000)
    possibleValues = string.digits

    generateWordList(minChars, maxChars, maxNumOfWords, possibleValues)

    # wordList = open("wordList.txt", "r")  # Testing
    # print(wordList.read())  # Testing
    # wordList.close()  # Testing

    with open("wordList.txt", "r") as f:  # save words to a list
        wordList = [line.strip() for line in f]  # .strip() removes trailing characters

    # print(wordList)  # Testing

    """ Read from file """
    username = []
    saltValue = []
    originalHash = []

    # Read original texts
    readFromTxt(username, saltValue, originalHash)

    # Crack the password
    crackPassword(username, saltValue, originalHash, wordList)

# Creates a file and generate a list of words
def generateWordList(minChars, maxChars, maxNumOfWords, possibleValues):
    word = ""
    with open("wordList.txt", "w") as wordList:  # create and write to a file that will contain the generated passwords
        for i in range(0, maxNumOfWords):
            for j in random.sample(possibleValues, random.randint(minChars, maxChars)):
                word += j
            wordList.write(word + "\n")
            word = ""
    wordList.close()

    print("Possible passwords have been successfully generated!")
    return wordList


# Reads the file containing records, and splits columns
def readFromTxt(username, saltValue, originalHash):
    try:
        f = open("password_file.txt", "r")
    except FileNotFoundError:
        print("Cannot locate file!")

    for line in f:
        username.append(line.strip().split(",")[0])
        saltValue.append(line.strip().split(",")[1])
        originalHash.append(line.strip().split(",")[2])

    f.close()

def crackPassword(username, saltValue, originalHash, wordList):
    # for user in username:
    #     print(user)

    # for guess in wordList:
    for i in range(len(wordList)):
        for j in range(len(originalHash)):
            print("Original hash: ", originalHash[i])
            newStr = str(wordList[i]) + saltValue[j]
            # print("Concat finished: " + newStr)
            newHash = hash_with_sha256(newStr)
            print("New hash " + newHash)

            if newHash == originalHash[j]:
                print("Password found: ", newHash)
                break
            # else:
            #     print("Comparing... ")
            elif newHash != originalHash[j]:
                print("Comparing... ")


# Create a new hash to be compared with original hash
def hash_with_sha256(str):
    hash_object = hashlib.sha256(str.encode("utf-8"))
    hex_dig = hash_object.hexdigest()
    return hex_dig


main()
