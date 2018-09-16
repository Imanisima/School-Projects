import hashlib

"""
Lab: 1: Recursion
Date: 4.September.2018
Class: CS2302 - 1:30

Purpose: Using recursion, generate all possible passwords.

About: A txt file is uploaded containing 100 records with information on 100 system accounts. For each record, there is a username, salt value, and hashed password. Generate an equivalent hash to the real account's password.


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


# Sends values to be compared, and returns password if found to below method
def generatePossiblePw2(username, minChars, saltValue, originalHash):
    passwordFound = ""
    for j in range(len(originalHash)):
        for i in range((len(originalHash))**2):
            i = str(i).format(i).zfill(minChars)
            passwordFound = compareHash(i, saltValue[j], originalHash[j], username[j])

    return passwordFound

# Recursively calls on the above method to generate passwords, and saves them to a list
def generatePossiblePw(username, minChars, maxChars, saltValue, originalHash):
    passwords = []

    if minChars < maxChars:
        password = generatePossiblePw2(username, minChars, saltValue, originalHash)
        passwords.append(password)
        return passwords + generatePossiblePw(username, minChars+1, maxChars, saltValue, originalHash)
    else:
        return []


# Comparing hash from the records to password generated
def compareHash(password, saltValue, originalHash, username):
    # Concating salt value and password to create new hash
    newStr = password + saltValue
    newHash = hash_with_sha256(newStr)

    # password equivalent found
    if newHash == originalHash:
        # Save new password to a file
        with open("found_Pws.txt", "a+") as pwList:
            pwList.write(username + ": " + password + "\n")
        # pwList.close()

        print(username + "\n Original Hash: %s\n Compared Hash: %s\n Equivalent password: %s" % (originalHash, newHash, password))
        return password

    return -1


# Create a new hash to be compared with original hash
def hash_with_sha256(str):
    hash_object = hashlib.sha256(str.encode("utf-8"))
    hex_dig = hash_object.hexdigest()
    return hex_dig


main()
