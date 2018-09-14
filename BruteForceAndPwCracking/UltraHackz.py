import hashlib
import string
import random

"""
Name: Imani Martin
Lab: 1: Recursion
Date: 4.September.2018
Class: CS2302 - 1:30

Purpose: Using recursion, generate all possible passwords.

About: A txt file is uploaded containing 100 records with information on 100 system accounts. For each record, there is a username, salt value, and hashed password. Generate an equivalent hash to the real account's password.

Comments: Passwords successfully generated recursively. Drawback: Because there are so many passwords and accounts, it takes over 5 minutes to generate.

"""

def main():
    """ generating a word list """
    minChars = int(3)  # Min number of characters required for the password
    maxChars = int(7)  # Max number of characters required for the password

    username = []
    saltValue = []
    originalHash = []

    # Read original texts
    readFromTxt(username, saltValue, originalHash)
    # print(username)
    # print(saltValue)
    # print(originalHash)

    # Generating possible passwords
    generatePossiblePw(username, minChars, maxChars, saltValue, originalHash)


# Reads the file containing records, and splits columns
def readFromTxt(username, saltValue, originalHash):
    try:
        f = open("password_file.txt", "r")
    except FileNotFoundError:
        print("Cannot locate file!")

    for line in f:
        username.append(line.rstrip().split(",")[0])
        saltValue.append(line.rstrip().split(",")[1])
        originalHash.append(line.rstrip().split(",")[2])

    f.close()


# Generate a list of words
def generatePossiblePw2(username, minChars, saltValue, originalHash):
    passwords = []

    print("genPw2 Method")
    minChars = 3
    for i in range(1000):
        print("i is now: ", i)
        for j in range(len(originalHash)):
            i = str(i).format(i).zfill(minChars) # acheives the 000 - 9999chars
            print("salt value: ", saltValue[j]) # testing
            password = compareHash(i, saltValue[j], originalHash[j])
            passwords.append(password) # testing
            print(passwords) # testing
    return passwords


def generatePossiblePw(username, minChars, maxChars, saltValue, originalHash):
    print("genpw method")
    if minChars < maxChars:
        password = generatePossiblePw2(username, minChars+1, saltValue, originalHash)
        return password + generatePossiblePw(username, minChars+1, maxChars, saltValue, originalHash)
    else:
        return []


# Comparing hash from the records to password generated
def compareHash(password, saltValue, originalHash):
    print("Compare Hash Method")
    for i in range(len(originalHash)):
        print("Original Hash: ", originalHash) # testing
        newStr = password + saltValue  # concating password and salt value
        print("newStr: ", newStr) # testing
        newHash = hash_with_sha256(newStr)  # hashing the new pw
        print("newHash: ", newHash) # testing

        if newHash == originalHash:  # password equivalent found
            print("Password found!")
            return password

        print("Password not found")
        return -1


# Create a new hash to be compared with original hash
def hash_with_sha256(str):
    hash_object = hashlib.sha256(str.encode("utf-8"))
    hex_dig = hash_object.hexdigest()
    return hex_dig


main()
