import math

def factors(n):
  result = []
  factor_pairs = ([i, n//i] for i in xrange(
      1, int(math.sqrt(n)) + 1) if n % i == 0)
  for factor in factor_pairs:
    result += factor
  return result

def isprime(n):
  if n == 2:
    return True
  elif n % 2 == 0:
    return False
  i = 3
  while i <= math.sqrt(n) + 1:
    if n % i == 0:
      return False
    i += 2
  return True

def primefactors(n):
  return [p for p in factors(n) if isprime(p)]

print str(max(primefactors(600851475143)))
