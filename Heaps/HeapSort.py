import MinHeap

"""
Name: Imani M.
Lab: 5
Class: CS2302 1:30

Purpose: Implement the Min-Heap data structure and heapsort then test using ONE of the following methods:

    * Unit Tests
    * Read a file with a list of numbers separated by commas and printing the sorted sequence - Done
    * Create a separate file where heapsort is called using hard-coded lists of numbers - Done
"""


# Arranges list into min-heap ~ Testing purposes only
def testing():
    h = MinHeap.MinHeap()
    test = [5, 32, 1, 0, 6]
    print(test, "\n------------------------\n")

    for num in test:
        h.insert(num)
        print(" --> new array: %s\n" % h.heap_array)

    print("FINAL MIN-HEAP: \n\t", h.heap_array)


# Sort the list in ascending order ~ O(n log n)
def heap_sort(heap_list):
    h = MinHeap.MinHeap()

    i = len(heap_list) // 2 - 1
    while i >= 0:
        h.percolate_down(i, heap_list, len(heap_list))
        i = i - 1

    i = len(heap_list) - 1
    while i > 0:
        # swap
        temp = heap_list[0]
        heap_list[0] = heap_list[i]
        heap_list[i] = temp

        h.percolate_down(0, heap_list, i)
        i = i - 1


# Reads a file and adds each row to it's own list ~ O(n)
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
    print("---------------------------------------\n")

    print("HEAP SORT IMPLEMENTATION")

    # HARD-CODED
    print("~*~Hardcoded values~*~")

    hard_list = [12, 6, 54, 2, 20]
    print("\nUNSORTED: ", hard_list)
    heap_sort(hard_list)
    print("NOW SORTED: ", hard_list)

    # FILE READER
    print("\n~*~Reading from a file~*~")

    read_list = read_file()

    print("\nUNSORTED: ", read_list)
    heap_sort(read_list)
    print("NOW SORTED: ", read_list)


main()
