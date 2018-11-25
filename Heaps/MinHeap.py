class MinHeap:

    def __init__(self):
        self.heap_array = []

    # Insert a new node into the heap
    def insert(self, k):
        # print("Insert(%d):" % k)
        self.heap_array.append(k)

        # percolate up from the last index to restore heap property
        self.percolate_up(len(self.heap_array) - 1)

    # Swap largest node with smallest
    def percolate_up(self, node):
        while node > 0:
            # find parent of current node
            parent_node = (node - 1) // 2

            # check if max heap is present
            if self.heap_array[node] >= self.heap_array[parent_node]:
                return
            else:
                print("Percolate_up() swap: %d <-> %d" %(self.heap_array[parent_node], self.heap_array[node]))
                temp = self.heap_array[node]
                self.heap_array[node] = self.heap_array[parent_node]
                self.heap_array[parent_node] = temp

                node = parent_node


    def is_empty(self):
        return len(self.heap_array) == 0
