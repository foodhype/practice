import itertools
import sys

num_lines = int(sys.stdin.readline())

arr = []
for _ in itertools.repeat(None, num_lines):
  palindrome_length = int(sys.stdin.readline())
  if palindrome_length % 2 == 0:
    arr.append(str(palindrome_length))
  else:
    arr.append(str(palindrome_length - 1))

print "\n".join(arr)
