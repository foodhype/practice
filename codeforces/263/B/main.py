from sys import stdin

def main():
  lines = stdin
  n, k = map(int, lines.next().split())

  if k > n:
    print -1
  else:
    arr = map(int, lines.next().split())
    arr.sort(reverse = True)
    print "%d %d" % (arr[k - 1], arr[k - 1])
      

main()

