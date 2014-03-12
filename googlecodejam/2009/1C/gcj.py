from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    message = lines.next().rstrip()
    print "Case #%d: %s" % (i, solve(message))

def solve(message):
  base = max(len(set(message)), 2)

  digit_map = {}
  digit_map[message[0]] = 1
  min_seconds = 1
  current = 1
  for i in xrange(1, len(message)):
    digit = message[i]
    min_seconds *= base
    if digit not in digit_map:
      if current == 1:
        digit_map[digit] = 0
      else:
        digit_map[digit] = current
      current += 1
    min_seconds += digit_map[digit]

  return min_seconds

main()

