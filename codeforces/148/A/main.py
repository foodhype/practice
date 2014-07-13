from sys import stdin

def main():
  lines = stdin
  k = int(lines.next())
  l = int(lines.next())
  m = int(lines.next())
  n = int(lines.next())
  d = int(lines.next())
  sieve = [False for _ in xrange(d)]

  ki = -1
  li = -1
  mi = -1
  ni = -1
  while ki < d or li < d or mi < d or ni < d:
    ki += k
    li += l
    mi += m
    ni += n
    if ki < d:
      sieve[ki] = True
    if li < d:
      sieve[li] = True
    if mi < d:
      sieve[mi] = True
    if ni < d:
      sieve[ni] = True

  count = 0
  for dragon in sieve:
    if dragon:
      count += 1

  print count



main()

