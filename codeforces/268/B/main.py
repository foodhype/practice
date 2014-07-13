from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  count = 0
  depth = 0
  
  while n > 0:
    count += n
    n -= 1
    count += n * depth
    depth += 1

  print count

main()

