from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  for i in xrange(1, n + 1):
    words = lines.next().split()
    words.reverse()
    print "Case #%d: %s" % (i, " ".join(words))

main()

