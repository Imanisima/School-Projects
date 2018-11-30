import numpy as np

"""
Name: Imani M.
Lab: 7
Class: 2302 1:30

Purpose: Implement Edit Distance algorithm
"""


def edit_distance(s1, s2):
    s1 = list(s1)
    s2 = list(s2)
    # print(s1)  # proof pf list of chars
    # m = [[s1], [s2]]
    # print(m)

    m = np.array([[s1], [s2]])
    print(m)


def main():
    s1 = "miners"
    s2 = "money"

    print("Comparing the following strings: \n"
          "str1: %s\nstr2: %s" % (s1, s2))
    print()
    edit_distance(s1, s2)


if __name__ == '__main__':
    main()


"""
Notes for me:
m[1] -> 2nd row
m[1][2] -> 3rd element of 2nd row
m[0][-1] -> last element of the 1st row
m[-1][-1] -> Last element of matrix

"""