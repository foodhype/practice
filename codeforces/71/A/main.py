from sys import stdin

def main():
  lines = stdin
  t = int(lines.next())
  for _ in xrange(t):
    word = lines.next().rstrip()
    if len(word) <= 10:
      print word
    else:
      print word[0] + str(len(word) - 2) + word[-1]
    

main()

