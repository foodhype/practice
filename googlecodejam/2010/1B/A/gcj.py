from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for i in xrange(1, t + 1):
    n, m = map(int, lines.next().split())
    old = []
    new = []
    for _ in xrange(n):
      old.append(lines.next().rstrip())
    for _ in xrange(m):
      new.append(lines.next().rstrip())
    print "Case #%d: %d" % (i, solve(old, new))

def solve(old, new):
  root = {}
  old_paths = [path.split("/") for path in old]
  new_paths = [path.split("/") for path in new]

  for old_path in old_paths:
    current = root
    for part in old_path:
      if part not in current:
        current[part] = {}
      current = current[part]

  if '' not in root:
    root[''] = {}

  count = 0

  for new_path in new_paths:
    current = root
    for part in new_path:
      if part not in current:
        current[part] = {}
        count += 1
      current = current[part]

  return count

main()

