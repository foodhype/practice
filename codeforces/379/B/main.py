from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  arr = map(int, lines.next().split())
  coins_left = sum(arr)
  ops = []
  while sum(arr) > 0:
    index = 0
    for _ in xrange(len(arr) - 1):
      a = arr[index]
      if a > 0:
        ops.append("P")
        arr[index] -= 1
      ops.append("R")
      index += 1
  
    for _ in xrange(len(arr) - 1): 
      a = arr[index]
      if a > 0:
        ops.append("P")
        arr[index] -= 1
      ops.append("L")
      index -= 1

  print "".join(ops)
    

main()

