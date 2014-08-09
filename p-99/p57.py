from bintree import Node
from p56 import is_symmetric

def add_value(value, tree = None):
  if tree is None:
    return Node(value)
  if value <= tree.value:
    if tree.left == None:
      tree.left = Node(value)
    else:
      add_value(value, tree.left)
  else:
    if tree.right == None:
      tree.right = Node(value)
    else:
      add_value(value, tree.right)

  return tree

def from_list(li):
  root = add_value(li[0])
  for value in li[1:]:
    add_value(value, root)
  return root

def main():
  tree1 = add_value(2)
  tree2 = Node(2)
  assert tree1 == tree2

  add_value(3, tree1)
  tree2.right = Node(3)
  assert tree1 == tree2

  add_value(0, tree1)
  tree2.left = Node(0)
  assert tree1 == tree2

  tree1 = from_list([3, 2, 5, 7, 1])
  tree2 = Node(3)
  tree2.left = Node(2)
  tree2.right = Node(5)
  tree2.right.right = Node(7)
  tree2.left.left = Node(1)
  assert tree1 == tree2

  assert is_symmetric(from_list([5, 3, 18, 1, 4, 12, 21]))
  assert not is_symmetric(from_list([3, 2, 5, 7, 4]))

if __name__ == "__main__":
  main()
