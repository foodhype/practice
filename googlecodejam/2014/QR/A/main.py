from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    answer1 = int(lines.next())
    possible = []
    for n in xrange(4):
      if n == answer1 - 1:
        possible = lines.next().split()
      else:
        lines.next()

    answer2 = int(lines.next())
    for n in xrange(4):
      if n == answer2 - 1:
        possible_answers = [c for c in lines.next().split() if c in possible]
        print "Case #%d: %s" % (i, solve(possible_answers))
      else:
        lines.next()

def solve(possible_answers):
  count = len(possible_answers)
  if count == 0:
    return "Volunteer cheated!"
  elif count == 1:
    return possible_answers.pop()
  else:
    return "Bad magician!"


main()

