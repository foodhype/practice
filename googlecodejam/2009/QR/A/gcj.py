from sys import stdin
import re

def main():
  lines = stdin
  l, d, n = map(int, lines.next().split())

  words = []
  for _ in xrange(d):
    words.append(lines.next())

  for i in xrange(1, n + 1):
    pattern = lines.next()
    pattern = re.sub('\(', '[', pattern)
    pattern = re.sub('\)', ']', pattern)
    count = 0
    for word in words:
      if re.match(pattern, word):
        count += 1
    print "Case #%d: %d" % (i, count)

main()

