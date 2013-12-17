# maximum contiguous sum
def kadanes(a):
  max_here = 0
  max_sum = 0
  for i in a:
    max_here = max(0, max_here + i)
    max_sum = max(max_sum, max_here)
  return max_sum

print kadanes([1, -3, 4, -1, 2, 1, -5, 4]) # output: 6
