"""Double-ended queue implementation."""

class Deque(object):
    """Double-ended queue class."""

    def __init__(self):
        self.head = None
        self.tail = None

    def appendleft(self, value):
        """Append value to the front of the queue."""
        node = Node(value)
        node.next = self.head
        if self.head is None:
            self.tail = node
        else:
            self.head.prev = node
        self.head = node

    def append(self, value):
        """Append value to the back of the queue."""
        node = Node(value)
        node.prev = self.tail
        if self.tail is None:
            self.head = node
        else:
            self.tail.next = node
        self.tail = node

    def popleft(self):
        """Remove and return value from the front of the queue."""
        if self.head is None:
            raise Exception("Empty list")
        temp = self.head
        new_head = self.head.next
        if new_head is None:
            self.tail = None
        else:
            new_head.prev = None
        self.head = new_head

        return temp.value

    def pop(self):
        """Remove and return value at the back of the queue."""
        if self.tail is None:
            raise Exception("Empty list")
        temp = self.tail
        new_tail = self.tail.prev
        if new_tail is None:
            self.head = None
        else:
            new_tail.next = None
        self.tail = new_tail

        return temp.value

class Node(object):
    """Doubly-linked list node class."""

    def __init__(self, value):
        self.value = value
        self.next = None
        self.prev = None

