from sys import stdin
import itertools

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    n, l = map(int, lines.next().split())
    outletFlows = [int(flow, 2) for flow in lines.next().split()]
    deviceFlows = [int(flow, 2) for flow in lines.next().split()]
    print "Case #%d: %s" % (i, solve(n, l, outletFlows, deviceFlows))

def solve(n, l, outletFlows, deviceFlows):
  check = set(deviceFlows)
  minFlips = float("inf")
  flipStrings = [flow ^ deviceFlows[0] for flow in outletFlows]
  for flipString in flipStrings:
    if all(flow ^ flipString in check for flow in outletFlows):
      minFlips = min(minFlips, bin(flipString).count('1'))
  if minFlips == float("inf"):
    return "NOT POSSIBLE"
  else:
    return str(minFlips)


main()

