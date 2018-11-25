class MinHeap:

    def __init__(self):
        self.heap_array = []

    # O(log n)
    def insert(self, k):
        # print("Insert(%d):" % k)
        self.heap_array.append(k)

        # percolate up from the last index to restore heap property
        self.percolate_up(len(self.heap_array) - 1)

    # Swap largest node with smallest ~ O(log n)
    def percolate_up(self, node):
        while node > 0:
            # find parent of current node
            parent_node = (node - 1) // 2

            # check if max heap is present
            if self.heap_array[node] >= self.heap_array[parent_node]:
                return
            else:
                print("Percolate_up() swap: %d <-> %d" % (self.heap_array[parent_node], self.heap_array[node]))
                temp = self.heap_array[node]
                self.heap_array[node] = self.heap_array[parent_node]
                self.heap_array[parent_node] = temp

                node = parent_node

    # finds the max value and swaps with min value ~ O(log n)
    def percolate_down(self, node, heap_list, size):
        child_index = (2 * node) + 1
        element = heap_list[node]

        while child_index < size:
            # Find the max among the node and all the node's children
            max_value = element
            max_index = -1
            i = 0

            while i < 2 and i + child_index < size:
                if heap_list[i + child_index] > max_value:
                    max_value = heap_list[i + child_index]
                    max_index = i + child_index
                i = i + 1

            if max_value == element:
                return

            # swap current node index with max index
            temp = heap_list[node]
            heap_list[node] = heap_list[max_index]
            heap_list[max_index] = temp

            node = max_index
            child_index = 2 * node + 1

    def is_empty(self):
        return len(self.heap_array) == 0
