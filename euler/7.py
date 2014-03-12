def prime(n):
  counter = 0
  current = 2
  while counter < n:
    if is_prime(current):
      counter += 1
    current += 1
  current -= 1
  return current

def is_prime(n):
  if n == 2:
    return True
  if n % 2 == 0:
    return False
  i = 3
  while i <= n**0.5 + 1:
    if n % i == 0:
      return False
    i += 2
  return True

print str(prime(10001))
