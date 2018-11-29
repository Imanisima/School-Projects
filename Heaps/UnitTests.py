import unittest
from MinHeap import MinHeap
from HeapSort import heap_sort


class HeapsTestCase(unittest.TestCase):
    """Tests for MinHeap.py"""
    def setUp(self):
        pass

    # Return False if heap is not empty
    def test_is_empty(self):
        h = MinHeap()
        h.heap_array = [1, 5, 12, 8, 2]
        self.assertFalse(MinHeap.is_empty(h), msg="[1, 5, 12, 8, 2] is not empty")

    """Tests for HeapSort.py"""
    # def test_heap_sort(self):
    #     h = MinHeap()
    #     h.heap_array = [5, , 8, 12,]
    #     m = []
    #
    #     self.assertListEqual(heap_sort(h), heap_sort(h))


if __name__ == '__main__':
    unittest.main()
