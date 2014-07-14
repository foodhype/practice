from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  arr = map(int, list(lines.next().rstrip()))
  first_half = arr[:n]
  second_half = arr[n:]
  first_half.sort()
  second_half.sort()

  if (all(first_half[i] > second_half[i] for i in xrange(n)) or
      all(first_half[i] < second_half[i] for i in xrange(n))):
    print "YES"
  else:
    print "NO"


main()

