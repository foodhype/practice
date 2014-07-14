from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  arr = map(float, lines.next().split())
  print (sum(arr) / (100.0 * len(arr))) * 100.0

main()

