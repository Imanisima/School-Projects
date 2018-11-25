In 2015, Mark Burnett released 10 million passwords people use to access online accounts. Your job is to download the passwords file from ​this link​, and write a Python 3 program that finds the 20 most used password.  
 
To accomplish this, your program must create a linked list to store all of the passwords contained in the file. Your linked list must not contain any duplicates, so each node must store the following values: 
 
○ A unique password extracted from the file (string) 
 
○ The number of times the password appears in the file (integer) 
 
○ A reference called ‘next’ that points to the next node in the linked list 
 
You are required to code two different solutions to create this list: 
 
○ Solution A:  Every time you read a password from the file, check (using a loop) if that password has already been added to the linked list. That is, you need to traverse the linked list to see if that password has been added already. If the password is already in your linked list, update the number of times the password has been seen in the file. Otherwise, add a the password to the linked list.   
 
○ Solution B: This is a variation of Solution A. Instead of traversing the linked list to check if a password has been seen before, we will be using what is called ​a dictionary​. Read the following code snippet to understand how to use a dictionary in a similar context: 
 
list_with_duplicates = [​"utep"​​, ​"go"​​, ​"utep"​​, ​"utep"​​, ​"miners"​​, ​"go"​​, ​"miners"​​] 
 
dict = {} 
 
for ​​item ​in ​​list_with_duplicates:    ​if ​​item ​in ​​dict: ​# You can assume this operation takes O(1)        dict[item] = dict[item] + ​1    ​else​​:        dict[item] = ​1 
 print​(dict[​"go"​​]) ​# 2 print​(dict[​"utep"​​]) ​# 3 
print​(dict[​"miners"​​]) ​# 2 
 
 
Once the linked list has been created, implement the following solutions to find the 20 passwords that appear the most in the file: 
 
○ Solution A: Sort the list (in descending order) using bubble sort, and print the 20 most used passwords along with the number of times they appear in the file. 
 
○ Solution B: Sort the list (in descending order) using merge sort, and print the 20 most used passwords along with the number of times they appear in the file
