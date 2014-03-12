from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  for i in xrange(1, n + 1):
    num_entries = int(lines.next())
    vectorA = [int(x) for x in lines.next().split()]
    vectorB = [int(x) for x in lines.next().split()]
    print "Case #%d: %d" % (i, solve(sorted(vectorA), sorted(vectorB)))

def solve(vectorA, vectorB):
  result = 0
  bMax = len(vectorB) - 1
  for a in vectorA:
    result += a * vectorB[bMax]
    bMax -= 1

  return result  

main()

