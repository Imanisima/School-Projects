import heap

"""
Name: Imani M.
Lab: 5
Class: CS2302 1:30

Purpose: Implement the Min-Heap data structure and heapsort then test using ONE of the following methods:

    * Read a file with a list of numbers separated by commas and printing the sorted sequence
    * Create a separate file where heapsort is called using hard-coded lists of numbers
"""


# Arranges list into min-heap ~ Testing purposes only
def testing():
    h = heap.MinHeap()
    test = [10, 2, 5, 18, 22]
    print(test, "\n------------------------\n")

    for num in test:
        h.insert(num)
        print(" ---> array: %s\n" % h.heap_array)


#
def max_heap_percolate_down(node, list, size):
    child_index = (2*node)+1
    element = list[node]

    while child_index < size:
        # Find the max among the node and all the node's children
        max_value = element
        max_index = -1
        i = 0

        while i < 2 and i + child_index < size:
            if list[i + child_index] > max_value:
                max_value = list[i + child_index]
                max_index = i + child_index
            i = i + 1

        if max_value == element:
            return

        # swap current node index with max index
        temp = list[node]
        list[node] = list[max_index]
        list[max_index] = temp

        node = max_index
        child_index = 2 * node + 1


# Sort the list in ascending order
def heap_sort(list):
    i = len(list) // 2 - 1
    while i >= 0:
        max_heap_percolate_down(i, list, len(list))
        i = i - 1

    i = len(list) - 1
    while i > 0:
        # swap
        temp = list[0]
        list[0] = list[i]
        list[i] = temp

        max_heap_percolate_down(0, list, i)
        i = i - 1


# Reads a file and adds each row to it's own list
def read_file():
    heap_list = []
    file = open("heap_file.txt", "r")
    line = file.read().split(",")

    for num in line:
        heap_list.append(num)

    heap_list = list(map(int, heap_list))

    return heap_list


def main():
    print("Testing the following list: ")
    testing()
    print("The above list is now a MIN-HEAP!\n---------------------------------------\n")

    print("HEAP SORT IMPLEMENTATION")
    # HARD CODED
    print("*Hardcoded values")
    list = [4, 6, 3, 2, 9]
    print("\nUNSORTED: ", list)
    heap_sort(list)
    print("NOW SORTED: ", list)

    # FILE READER
    print("\n*Reading from a file")
    read_list = read_file()

    print("\nUNSORTED: ", read_list)
    heap_sort(read_list)
    print("NOW SORTED: ", read_list)


main()
