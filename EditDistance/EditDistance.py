import sys
import time
import subprocess
import os
import numpy as np

"""
Name: Imani M.
Lab: 7
Class: 2302 1:30

Purpose: Implement Edit Distance algorithm
"""


def display_example():
    print("-" * 35)
    print("\t\t~`~\tEXAMPLE\t~`~")
    print("-" * 35)

    s1 = "money"
    s2 = "miners"

    print("str1: %s\n"
          "str2: %s" % (s1, s2))

    print("\nEdit Distance: %d" % (edit_distance(s1, s2)))


def edit_distance(s1, s2):
    len_s1 = len(s1)
    len_s2 = len(s2)

    # create a matrix of length n+1 for s1 and length m+1 for s2 to account for empty string
    m = [[0 for x in range(len_s2 + 1)] for x in range(len_s1 + 1)]

    for i in range(len_s1 + 1):
        for j in range(len_s2 + 1):
            # ~~~~ Case 1: subset of string is empty ~~~~
            # --------------------------------------------#

            # if s1 is empty but not s2 then insert
            if i == 0:
                m[i][j] = j  # number of operations equates to s2

            # if s2 is empty but not s1 then remove
            elif j == 0:
                m[i][j] = i  # min number of operations equates to s1

            # ~~~~~ Case 2: last chars are equal ~~~~~
            # ------------------------------------#
            elif s1[i-1] == s2[j-1]:
                m[i][j] = m[i-1][j-1]  # Change nothing and shrink string

            # ~~~~ Case 3: last chars are different ~~~~
            # -------------------------------------#
            else:
                m[i][j] = 1 + min(m[i][j-1],  # insert
                                  m[i-1][j],  # delete
                                  m[i-1][j-1])  # replace

    print("\nPrinting matrix: \n", np.array(m))

    return m[len_s1][len_s2]


def main():
    print("EDIT DISTANCE")
    """ ----------------------------
    |           Example            |
    -------------------------------- """
    display_example()
    time.sleep(4)

    """ ---------------------------- 
    |           User Input         |
    -------------------------------- """
    print("-" * 35)
    print("\t\t~`~\tUSER INPUT\t~`~")
    print("-"*35)

    yes = 'Y'.lower()
    while yes == 'Y'.lower():

        user_choice = input("Would you like to compare your own pair of strings? [y/n]")
        if user_choice != yes:
            user_choice2 = input("Would you like to look at the example again?[y/n]")
            if user_choice2 == 'Y'.lower():
                display_example()
                time.sleep(2)
                sys.exit()
            sys.exit()
        else:
            s1 = input("-> Please type in a string to compare: ")
            s2 = input("-> Please type in another string to compare: ")

            print("s1: %s\ns2: %s" % (s1, s2))
            print("Edit Distance: %d" % (edit_distance(s1, s2)))


if __name__ == '__main__':
    main()
