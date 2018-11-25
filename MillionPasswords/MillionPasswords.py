class Node:
    password = ""
    count = 1
    next = None
    prev = None

    def __init__(self, password):
        self.password = password


class LinkedList:
    head = None
    tail = None
    size = 0

    # Insert a node only if it is not a duplicate ~ O(n)
    def insert(self, node):
        if self.head is None:
            self.head = node
            self.tail = node

        else:
            # Check if duplicate
            duplicate = self.is_duplicate(node)
            if duplicate is not None:
                duplicate.count += 1

            else:
                curr = self.head
                node.next = curr
                curr.prev = node
                self.head = node
                self.size += 1

    # Checking for duplicates ~ O(n)
    def is_duplicate(self, node):
        temp = self.head
        while temp is not None:
            if temp.password == node.password:
                return temp
            temp = temp.next
        return None

    # print password ~ O(n)
    def print_password(self):
        curr = self.head
        counter = 0
        while curr is not None:
            counter += 1
            print("(%d) Password: %s" % (counter, curr.password))
            curr = curr.next

    # Sort the list using Bubble Sort ~ O(n^2)
    def bubble_sort(self):
        for i in range(self.size):
            curr = self.head
            adj_curr = curr.next
            while adj_curr is not None:
                if curr.count < adj_curr.count:
                    curr, adj_curr = self.swap(curr, adj_curr)
                curr = curr.next
                adj_curr = adj_curr.next

    # merge sort ~ O(n log n) FIX THIS!!!
    # def merge_sort(self, node):
    #     if node is None or node.next is None:
    #         return node
    #
    #     # Find the middle using pivot point
    #     list_length = find_length(node)
    #     pivot = list_length // 2
    #     list1 = self.head
    #
    #     for i in range(pivot - 1):
    #         list1 = list1.next
    #
    #     # Split into two lists
    #     list2 = list1.next
    #     list1.next = None
    #
    #     lower_half = merge_sort(list1)
    #     upper_half = merge_sort(list2)
    #
    #     # Sort and traverse
    #     if lower_half.password > upper_half.password:
    #         sorted_list = lower_half
    #         lower_half = lower_half.next
    #     else:
    #         sorted_list = upper_half
    #         upper_half = upper_half.next
    #
    #     list1 = sorted_list
    #
    #     while lower_half is not None and upper_half is not None:
    #         if lower_half.password > upper_half.password:
    #             list1.next = lower_half
    #             lower_half = lower_half.next
    #         else:
    #             list1.next = upper_half
    #             upper_half = upper_half.next
    #         list1 = list1.next
    #
    #     # merge (consolidate) the two lists
    #     while lower_half is not None:
    #         list1.next = lower_half
    #         lower_half = lower_half.next
    #         list1 = list1.next
    #
    #     while upper_half is not None:
    #         list1.next = upper_half
    #         upper_half = upper_half.next
    #         list1 = list1.ext
    #
    #     return sorted_list

    # swap is used for switch two elements in the above algorithms ~ O(1)
    def swap(self, curr, adj_curr):
        curr.next = adj_curr.next
        if adj_curr.next is not None:
            adj_curr.next.prev = curr

        else:
            self.tail = curr

        adj_curr.next = curr
        adj_curr.prev = curr.prev

        if adj_curr.prev is not None:
            adj_curr.prev.next = adj_curr
        else:
            self.head = adj_curr

        return adj_curr, curr

    def find_length(self, node):
        counter = 0
        while node is not None:
            counter += 1
            node = node.next
        return counter


def solution_Bubble():
    print("\n\t~*~BUBBLE SORT~*~")
    linked_list = LinkedList()
    file = open("test.txt", "r")
    for line in file:
        col = line.split()
        password = Node(col[1])
        linked_list.insert(password)

    linked_list.bubble_sort()
    linked_list.print_password()
    print("\n\t~*~TOP PASSWORDS~*~")
    top_passwords(linked_list, 20)

# Finds the top items ~ Create a method that calls merge sort
def solution_ListFreq():
    print("\n\t~*~Most Used Passwords~*~")
    temp = dict()
    file = open("test.txt", "r")
    for line in file:
        col = line.split()
        password = col[1]

        if password in temp:
            temp[password] += 1

        else:
            temp[password] = 1

    for word, count in temp.items():
        print("Password: %s\t----> %d" % (word, count))


def top_passwords(linked_list, max):
    curr = linked_list.head
    for i in range(max):
        if curr is None:
            break
        print("(%d) %s" % (i+1, curr.password))
        curr = curr.next

solution_Merge()
solution_Bubble()
