from sys import stdin

def main():
  lines = stdin
  n, k = map(int, lines.next().split())
  arr = map(int, lines.next().split())
  score = arr[k - 1]
  count = 0
  for c in arr:
    if c >= score and c > 0:
      # optimization would be to enumerate backwards, take n - k, and break
      count += 1
  
  print count
    

main()

