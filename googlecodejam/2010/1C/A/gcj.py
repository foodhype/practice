from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    n = int(lines.next())
    wires = []
    for j in xrange(n):
      wire = tuple(map(int, lines.next().split()))
      wires.append(wire)
    print "Case #%d: %d" % (i, solve(wires))

def solve(wires):
  count = 0
  while len(wires) > 0:
    a1, b1 = wires.pop()
    for a2, b2 in wires:
      if (a2 < a1 and b2 > b1) or (a2 > a1 and b2 < b1):
        count += 1
  return count

main()

