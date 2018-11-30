import numpy as np

"""
Name: Imani M.
Lab: 7
Class: 2302 1:30

Purpose: Implement Edit Distance algorithm
"""


def edit_distance(s1, s2):
    len_s1 = len(s1)
    len_s2 = len(s2)

    # create a table of length n+1 for s1 and length m+1 for s2 to account for empty string
    m = [[0 for x in range(len_s2 + 1)] for x in range(len_s1 + 1)]

    for i in range(len_s1 + 1):
        for j in range(len_s2 + 1):
            # Case 1: subset of string is empty
            if i == 0:  # insert if s1 is empty
                m[i][j] = j  # number of operations
            elif j == 0:  # remove rest of chars from second string if s2 is empty
                m[i][j] = i  # min ops = i

            # Case 2: Chars are equal
            elif s1[i-1] == s2[j-1]:
                m[i][j] = m[i-1][j-1]
            # Case 3: Chars are different
            else:
                m[i][j] = 1 + min(m[i][j-1],
                                  m[i-1][j],
                                  m[i-1][j-1])

    print("\nPrinting matrix: \n", np.array(m))
    return m[len_s1][len_s2]


def main():
    s1 = "miners"
    s2 = "money"

    print("Comparing the following strings: \n"
          "str1: %s\n"
          "str2: %s" % (s1, s2))

    print("\nEdit Distance: %d" % (edit_distance(s1, s2)))


if __name__ == '__main__':
    main()
