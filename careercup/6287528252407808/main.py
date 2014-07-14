from sys import stdin

def k_palindrome(a, k):
  b = a[::-1]

  dp = [[0 for _ in xrange(len(a) + 1)] for _ in xrange(len(b) + 1)]

  for i in xrange(len(a)):
    dp[i][0] = i
    dp[0][i] = i

  for i in xrange(1, len(a) + 1):
    for j in xrange(1, len(b) + 1):
      noop_min = dp[i - 1][j - 1] if a[i - 1] == b[j - 1] else float("inf")
      delete_min = dp[i - 1][j] + 1
      insert_min = dp[i][j - 1] + 1
      dp[i][j] = min(noop_min, delete_min, insert_min)

  return dp[len(a)][len(b)] <= 2 * k

def main():
  lines = stdin
  a = lines.next().rstrip()
  k = int(lines.next())
  print "YES" if k_palindrome(a, k) else "No"

main()
