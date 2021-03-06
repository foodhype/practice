def is_palindrome(n):
  s = str(n)
  for i in xrange(len(s) // 2):
    if s[i] != s[len(s) - i - 1]:
      return False
  return True

largest_palindrome = 0

for i in xrange(100, 1000):
  for j in xrange(100, 1000):
    product = i * j
    if is_palindrome(product):
      largest_palindrome = max(product, largest_palindrome)

print str(largest_palindrome)
