class Node:
  def __init__(self, val = None, next = None):
    self.val = val
    self.next = next

class Deque:
  def __init__(self):
    self.head = None
    self.tail = None

  def push(self, val):
    current = Node(val, self.head)
    self.head = current
    self.tail = current

  def pop(self):
    if self.head is None:
      raise "Empty list"
    else:
      temp = self.head.val
      self.head = self.head.next
      return temp

  def enqueue(self, val):
    if self.head is None:
      self.head = Node(val)
      tail = self.head
    else:
      current = self.head
      while current.next is not None:
        current = current.next
      current.next = Node(val)
      tail = current.next

if __name__ == "__main__":
  ll = Deque()
  for i in xrange(5):
    ll.Deque(i)
  for i in xrange(5):
    print ll.pop()
  for i in xrange(5):
    ll.push(i)
  for _ in xrange(5):
    print ll.pop()
