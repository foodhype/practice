
def preorder(root):
  if root is not None:
    preorder(root.left)
    print root.value
    preorder(root.right)

def all_paths(root, path):
  if root is None:
    return []
  paths = []
  new_path = path[:]
  new_path.append(root.value)
  if root.left is None and root.right is None:
    paths.append(new_path)
  else:
    if root.left is not None:
      paths.extend(all_paths(root.left, new_path))
    if root.right is not None:
      paths.extend(all_paths(root.right, new_path))
  return paths

class Node:
  def __init__(self, value):
    self.value = value
    self.left = None
    self.right = None

def main():
  temp = Node(5)
  root = temp
  temp.left = Node(2)
  temp.right = Node(6)
  temp = temp.left
  temp.left = Node(1)
  temp.right = Node(2.5)
  temp = root
  temp = root.right
  temp.left = Node(5.5)
  temp.right = Node(7)

  print all_paths(root, [])
  preorder(root)

if __name__ == "__main__":
  main()
