from sys import stdin

def main():
  lines = stdin
  n, m = map(int, lines.next().split())    
  arr = map(int, lines.next().split())
  location = 1
  count = 0
  for a in arr:
    if location != a:
      if location < a:
        count += (a - location)
      else:
        count += (n - location) + a
      location = a

  print count


main()

