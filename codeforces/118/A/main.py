from sys import stdin

def main():
  lines = stdin
  word = ""
  vowels = {'a', 'e', 'i', 'o', 'u', 'y'}
  print '.' + '.'.join([c for c in lines.next().rstrip().lower() if c not in vowels])


main()

