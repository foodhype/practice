from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  arr = map(int, lines.next().split())
  arr.sort(reverse = True)
  count = 0
  current = 0
  rem = sum(arr)
  for coin in arr:
    current += coin
    rem -= coin
    count += 1
    if current > rem:
      print count
      break

  
    

main()

