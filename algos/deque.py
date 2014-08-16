class deque:
  def __init__(self):
    self.head = None
    self.tail = None

  def appendleft(self, value):
    node = Node(value)
    node.next = self.head
    if self.head is None:
      self.tail = node
    else:
      self.head.prev = node
    self.head = node

  def append(self, value):
    node = Node(value)
    node.prev = self.tail
    if self.tail is None:
      self.head = node
    else:
      self.tail.next = node
    self.tail = node

  def popleft(self):
    if self.head is None:
      raise Exception("Empty list")
    new_head = self.head.next
    if new_head is None:
      self.tail = None
    else:
      new_head.prev = None
    self.head = new_head

    return temp.value

  def pop(self):
    if self.tail is None:
      raise Exception("Empty list")
    new_tail = self.tail.prev
    if new_tail is None:
      self.head = None
    else:
      new_tail.next = None
    self.tail = new_tail


class Node:
  def __init__(self, value):
    self.value = value
    self.next = None
    self.prev = None

