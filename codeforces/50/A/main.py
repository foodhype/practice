from sys import stdin

def main():
  lines = stdin
  m, n = map(int, lines.next().split())
  print (m * n) // 2

main()

