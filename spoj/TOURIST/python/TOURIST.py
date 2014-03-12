import sys


def main():
  dp = [[[0 for _ in xrange(100)] for _ in xrange(100)] for _ in xrange(200)]

  t = int(sys.stdin.readline())

  # input
  results = []
  for _ in xrange(t):
    matrix = []
    w, h = map(int, sys.stdin.readline().split())
    for _ in xrange(h):
      matrix.append(list(sys.stdin.readline().strip()))
  
    # calculate
    results.append(max_interesting_locations(matrix, dp))
    clear_cache(dp, h, w)

  # output
  for result in results:
    print str(result)


def max_interesting_locations(matrix, dp):
  rows = len(matrix)
  cols = len(matrix[0])

  """We can only move down and right on the trip to the destination, and can only move up and 
     left on the trip back: under those constraints, a trip from the NW corner to the SE corner 
     and back is identical to doing two successive trips from the NW corner to the SE corner, 
     since the reachable paths are identical. Thus we can still view the problem as a directed 
     acyclic graph and do dynamic programming. We can count the maximum number of interesting 
     locations that two successive tourists could have visited at each row and column at a given
     step until we reach the SE corner."""
  for step in xrange(rows + cols - 1):
    for r1 in xrange(min(rows, step + 1)):
      for r2 in xrange(r1 + 1):
        c1 = step - r1
        c2 = step - r2

        if c1 >= cols or c2 >= cols or matrix[r1][c1] == '#' or matrix[r2][c2] == '#':
          dp[step][r1][r2] = 0
          dp[step][r2][r1] = 0
        else:
          count = 0
          if step > 0:
            count = max(count, dp[step - 1][r1][r2])
            if r1 > 0:
              count = max(count, dp[step - 1][r1 - 1][r2])
            if r2 > 0:
              count = max(count, dp[step - 1][r1][r2 - 1])
            if r1 > 0 and r2 > 0:
              count = max(count, dp[step - 1][r1 - 1][r2 - 1])
  
          if matrix[r1][c1] == '*':
            count += 1
          if r1 != r2 and matrix[r2][c2] == '*':
            count += 1

          dp[step][r1][r2] = count
          dp[step][r2][r1] = count

  return dp[rows + cols - 2][rows - 1][rows - 1]

def clear_cache(dp, rows, cols):
  for step in xrange(rows + cols - 1):
    for r1 in xrange(min(rows, step + 1)):
      for r2 in xrange(r1 + 1):
        dp[step][r1][r2] = 0

main()
