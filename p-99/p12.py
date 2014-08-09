def decode(li):
  decoded = []
  for count, element in li:
    for _ in xrange(count):
      decoded.append(element)

  return decoded

def main():
  li = [(4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')]
  assert decode(li) == ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']

if __name__ == "__main__":
  main()
