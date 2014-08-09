class HuffmanTree:
  def __init__(self, priority, timestamp, value):
    self.priority = priority
    self.timestamp = timestamp
    self.value = value
    self.left = None
    self.right = None

  def __cmp__(self, other):
    if other == None:
      return -1
    return cmp((self.priority, self.timestamp),
        (other.priority, other.timestamp))

  def __str__(self):
    indentation = 0
    return self.formatted(self, 0)

  def formatted(self, root, indentation):
    indent = " " * indentation
    if root == None:
      return indent + "None\n"
    else:
      root_str = "Priority: %d Value: %s\n" % (root.priority, str(root.value))
      left_str = self.formatted(root.left, indentation + 2)
      right_str = self.formatted(root.right, indentation + 2)
      return indent + root_str + left_str + right_str

