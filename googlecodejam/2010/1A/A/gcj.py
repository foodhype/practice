from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    n, k = map(int, lines.next().split())
    board = []
    for _ in xrange(n):
      row = [c for c in lines.next().rstrip()]
      board.append(row)
    print "Case #%d: %s" % (i, solve(board, k))

def count(board, target, i, j, iStep, jStep):
  if i >= 0 and j >= 0 and i < len(board) and j < len(board[0]) and board[i][j] == target:
    return 1 + count(board, target, i + iStep, j + jStep, iStep, jStep)
  else:
    return 0

def connectK(board, target, k, i, j):
  maxConnected = max(count(board, target, i - 1, j, -1, 0) + 1 + count(board, target, i + 1, j, 1, 0),
      count(board, target, i, j - 1, 0, -1) + 1 + count(board, target, i, j + 1, 0, 1),
      count(board, target, i - 1, j - 1, -1, -1) + 1 + count(board, target, i + 1, j + 1, 1, 1),
      count(board, target, i + 1, j - 1, 1, -1) + 1 + count(board, target, i - 1, j + 1, -1, 1))

  return maxConnected >= k

def solve(board, k):
  for i in xrange(len(board)):
    row = [cell for cell in board[i] if cell == 'R' or cell == 'B']
    board[i] = ['.' for _ in board[i]]
    for l in xrange(len(row)):
      board[i][len(board[i]) - l - 1] = row[len(row) - l - 1]

  redWin = False
  blueWin = False
  for i in xrange(len(board)):
    row = board[i]
    for j in xrange(len(row)):
      cell = row[j]
      if cell == 'R' and connectK(board, 'R', k, i, j):
        redWin = True
      if cell == 'B' and connectK(board, 'B', k, i, j):
        blueWin = True

  if redWin and blueWin:
    return "Both"
  elif redWin:
    return "Red"
  elif blueWin:
    return "Blue"
  else:
    return "Neither"

main()

