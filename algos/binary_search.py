from bisect import bisect_left

def binary_search(li, target):
  pos = bisect_left(li, target)
  return pos if pos != len(li) and li[pos] == target else -1

def main():
  li = [1, 1, 2, 3, 5, 8]
  print li
  print binary_search(li, 3)

if __name__ == "__main__":
  main()
