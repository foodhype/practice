import itertools
import sys

num_lines = int(sys.stdin.readline())

arr = []
for _ in itertools.repeat(None, num_lines):
  sum = 0
  p = int(sys.stdin.readline())
  i = 1
  for _ in itertools.repeat(None, 11):
    if p & i:
      sum += 1
    i <<= 1
  sum += (p / 2048)
  arr.append(str(sum))

print "\n".join(arr)
