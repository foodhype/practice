class Node:
  def __init__(self, value):
    self.value = value
    self.left = None
    self.right = None

  def __str__(self):
    return "T(" + str(self.value) + " " + str(self.left) + " " + str(self.right) + ")"

  def __eq__(self, other):
    if other == None:
      return False

    return self.value == other.value and self.left == other.left and self.right == other.right

  def __neq__(self, other):
    return not self.__eq__(other)
