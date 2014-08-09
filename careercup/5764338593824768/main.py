import random

def rand_max(arr):
  max_val = float("-inf")
  count = 0
  for num in arr:
    if num > max_val:
      count = 0
      max_val = num
    elif num == max_val:
      count += 1

  r = random.randint(0, count)

  count = 0
  for (index, num) in enumerate(arr):
    if num == max_val:
      if count == r:
        return index
      count += 1

def main():
  arr = [1,4,9,7,3,9,4,7,2,7,3,0,1,2,9,6,5,7,8,9]
  print rand_max(arr)

main()

