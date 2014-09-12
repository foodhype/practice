from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  for _ in xrange(n):
      numbers = map(int, lines.next().split())
      vertex = numbers[0]
      if vertex == 0:

      adjacent = numbers[1:]
  print vertex
  print adjacent

main()

