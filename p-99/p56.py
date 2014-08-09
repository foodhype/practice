from bintree import Node

def is_mirror(treeA, treeB):
  if treeA == None and treeB == None:
    return True
  elif treeA == None or treeB == None:
    return False
  else:
    return is_mirror(treeA.left, treeB.right) and is_mirror(treeA.right, treeB.left)

def is_symmetric(tree):
  return is_mirror(tree.left, tree.right)

def main():
  tree = Node("a")
  tree.left = Node("b")
  tree.right = Node("c")
  assert is_symmetric(tree)
  tree.left.left = Node("x")
  assert not is_symmetric(tree)

if __name__ == "__main__":
  main()
