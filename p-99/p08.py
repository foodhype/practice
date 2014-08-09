def compress(li):
  visited = set()
  compressed = []
  for element in li:
    if element not in visited:
      compressed.append(element)
      cache.add(element)

  return compressed

def main():
  li = ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']
  assert compress(li) == ['a', 'b', 'c', 'd', 'e']

if __name__ == "__main__":
  main()
