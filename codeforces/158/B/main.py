from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  groups = map(int, lines.next().split())
  groups.sort(reverse = True)
  start = 0
  end = len(groups) - 1
  count = 0
  while start <= end:
    count += 1
    capacity = 4 - groups[start]
    while groups[end] <= capacity and start <= end:
      capacity -= groups[end]
      end -= 1
    start += 1

  print count

main()

