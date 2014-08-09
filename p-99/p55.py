from bintree import Node

def c_balanced(c, value):
  if c == 0:
    return None
  root = Node(value)
  left_size = (c - 1) // 2
  right_size = (c - 1) - left_size
  root.left = c_balanced(left_size, value)
  root.right = c_balanced(right_size, value)

  return root

def main():
  tree1 = c_balanced(4, "x")
  tree2 = Node("x")
  tree2.left = Node("x")
  tree2.right = Node("x")
  tree2.right.right = Node("x")

  assert tree1 == tree2

if __name__ == "__main__":
  main()
