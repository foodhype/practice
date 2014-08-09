import HuffmanTree

def uncompress(encoded, huffman_tree):
  result = ""
  root = huffman_tree

  current = root
  for c in encoded:
    if current.value is None:
      if c == "0":
        current = current.left
      elif c == "1":
        current = current.right
      if current.value is not None:
        result += current.value
        current = root

  return result

