class Node:
    def __init__(self, key):
        self.key = key
        self.parent = None
        self.left = None
        self.right = None
        self.height = 0

    # Calculate balance factor
    def get_balance(self):
        left_height, right_height = -1

        # get current height of left subtree
        if self.left is not None:
            left_height = self.left.height

        # current height of right subtree
        if self.right is not None:
            right_height = self.right.height

        # balance factor
        return left_height - right_height

    def update_height(self):
        # recalculate the current height of the subtree
        left_height, right_height = -1
        if self.left is not None:
            left_height = self.left.height

        if self.right is not None:
            right_height = self.right.height

        self.height = max(left_height, right_height) + 1

    # Determine where to place the newest child
    def set_child(self, new_child, child):
        if new_child != "left" and new_child != "right":
            return False

        if new_child == "left":
            self.left = child
        else:
            self.right = child

        # assign parent data of the new child
        if child is not None:
            child.parent = self

        # update height of the subtree
        self.update_height()
        return True

    # replace current child with new child
    def replace_child(self, current_child, new_child):
        if self.left is current_child:
            return self.set_child("left", new_child)
        elif self.right is current_child:
            return self.set_child("right", new_child)

        return False


class AVLTree:
    def __init__(self):
        self.root = None

    # left rotation
    def rotate_left(self, node):
        right_left_child = node.right.left

        if node.parent is not Node:
            node.parent.replace_child(node, node.right)
        else:
            self.root = node.right
            self.root.parent = None

        node.right.self_child("left", node)
        node.set_child("right", right_left_child)

        return node.parent

    # Right rotation
    def rotate_right(self, node):
        left_right_child = node.left.right

        if node.parent is not None:
            node.parent.replace_child(node, node.left)
        else:  # node is root
            self.root = node.left
            self.root.parent = None

        node.left.set_child('right', node)

        node.set_child('left', left_right_child)

        return node.parent

    # Updates the given node's height and rebalances the subtree
    def rebalance(self, node):

        # update the height of this node.
        node.update_height()

        # Check for an imbalance.
        if node.get_balance() == -2:

            # The subtree is too big to the right.
            if node.right.get_balance() == 1:
                # Double rotation case. First do a right rotation
                # on the right child.
                self.rotate_right(node.right)

            # A left rotation will now make the subtree balanced.
            return self.rotate_left(node)

        elif node.get_balance() == 2:

            # The subtree is too big to the left
            if node.left.get_balance() == -1:
                # Double rotation case. First do a left rotation
                # on the left child.
                self.rotate_left(node.left)

            # A right rotation will now make the subtree balanced.
            return self.rotate_right(node)

        # No imbalance, so just return the original node.
        return node

    def insert(self, node):

        # Special case: if the tree is empty, just set the root to
        # the new node.
        if self.root is None:
            self.root = node
            node.parent = None

        else:
            # Step 1 - do a regular binary search tree insert.
            current_node = self.root
            while current_node is not None:
                # Choose to go left or right
                if node.key < current_node.key:
                    # Go left. If left child is None, insert the new
                    # node here.
                    if current_node.left is None:
                        current_node.left = node
                        node.parent = current_node
                        current_node = None
                    else:
                        # Go left and do the loop again.
                        current_node = current_node.left
                else:
                    # Go right. If the right child is None, insert the
                    # new node here.
                    if current_node.right is None:
                        current_node.right = node
                        node.parent = current_node
                        current_node = None
                    else:
                        # Go right and do the loop again.
                        current_node = current_node.right

            # Step 2 - Rebalance along a path from the new node's parent up
            # to the root.
            node = node.parent
            while node is not None:
                self.rebalance(node)
                node = node.parent

    def remove_node(self, node):
        # Base case:
        if node is None:
            return False

        # Parent needed for rebalancing.
        parent = node.parent

        # Case 1: Internal node with 2 children
        if node.left is not None and node.right is not None:
            # Find successor
            successor_node = node.right
            while successor_node.left is not None:
                successor_node = successor_node.left

            # Copy the value from the node
            node.key = successor_node.key

            # Recursively remove successor
            self.remove_node(successor_node)

            # Nothing left to do since the recursive call will have rebalanced
            return True

        # Case 2: Root node (with 1 or 0 children)
        elif node is self.root:
            if node.left is not None:
                self.root = node.left
            else:
                self.root = node.right

            if self.root is not None:
                self.root.parent = None

            return True

        # Case 3: Internal with left child only
        elif node.left is not None:
            parent.replace_child(node, node.left)

        # Case 4: Internal with right child only OR leaf
        else:
            parent.replace_child(node, node.right)

        node = parent
        while node is not None:
            self.rebalance(node)
            node = node.parent
        return True

    # Searches for a node with a matching key.
    def search(self, key):
        current_node = self.root
        while current_node is not None:
            # Compare the current node's key with the target key.
            if current_node.key.lower() == key.lower():
                return current_node
            elif current_node.key < key:
                current_node = current_node.right
            else:
                current_node = current_node.left
        return None

    # Attempts to remove a node with a matching key. If no node has a matching key
    # then nothing is done and False is returned; otherwise the node is removed and
    # True is returned.
    def remove_key(self, key):
        node = self.search(key)
        if node is None:
            return False
        else:
            return self.remove_node(node)

