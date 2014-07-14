from sys import stdin

def main():
  lines = stdin
  n, m = map(int, lines.next().split())
  arr = map(int, lines.next().split())
  arr.sort()
  result = float("inf")
  for i in xrange(n - 1, m):
    result = min(result, arr[i] - arr[i - (n - 1)])
  if result == float("inf"):
    print 0
  else:
    print result


main()

