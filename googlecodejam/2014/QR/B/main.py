from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    c, f, x = map(float, lines.next().split())
    print "Case #%d: %s" % (i, solve(c, f, x))


def solve(c, f, x):
  time_elapsed = 0
  cookies = 0
  rate = 2

  while cookies < x:
    if cookies >= c:
      if ((x - cookies) / rate) < ((x - cookies + c) / (rate + f)):
        time_elapsed += ((x - cookies) / rate)
        cookies = x
      else:
        cookies -= c
        rate += f
    else:
      if x - cookies <= c:
        time_elapsed += ((x - cookies) / rate)
        cookies = x
      else:
        time_elapsed += ((c - cookies) / rate)
        cookies += c
    
  return '{0:.7f}'.format(time_elapsed)
  

main()

