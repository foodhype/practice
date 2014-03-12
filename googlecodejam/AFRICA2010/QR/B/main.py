from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  for i in xrange(1, n + 1):
    cost = int(lines.next())
    num_items = int(lines.next())
    prices = [int(p) for p in lines.next().split()]
    result = solve(cost, prices)
    print "Case #%d: %d %d" % (i, result[0], result[1])

def solve(cost, prices):
  dp = {}
  for i in xrange(1, len(prices) + 1):
    p = prices[i - 1]
    if cost - p in dp:
      return (dp[cost - p], i)
    else:
      dp[p] = i

main()

