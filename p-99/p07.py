def flatten(arr):
  flattened = []
  for element in arr:
    if hasattr(element, '__iter__'):
      flattened.extend(flatten(element))
    else:
      flattened.append(element)
  return flattened

def main():
  arr = [[1, 1], 2, [3, [5, 8]]]
  assert flatten(arr) == [1, 1, 2, 3, 5, 8]

if __name__ == "__main__":
  main()
