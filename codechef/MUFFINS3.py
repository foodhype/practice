import itertools
import sys

num_cases = int(sys.stdin.readline())

arr = []
for _ in itertools.repeat(None, num_cases):
  num_cupcakes = int(sys.stdin.readline())
  arr.append(str((num_cupcakes / 2) + 1))

print "\n".join(arr)
