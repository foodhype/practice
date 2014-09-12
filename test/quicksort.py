import random

def qsort(li):
  if len(li) <= 1:
    return li
  else:
    less = []
    equal = []
    greater = []
    pivot = random.choice(li)
    for x in li:
      if x < pivot:
        less.append(x)
      elif x == pivot:
        equal.append(x)
      else:
        greater.append(x)
    return qsort(less) + equal + qsort(greater)

def main():
  arr = [5, 4, 6, 4, 5, 6, 3, 2]
  print qsort(arr)

if __name__ == "__main__":
  main()
