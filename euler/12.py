import math

def triangularNumber(n):
  return (n * (n + 1)) / 2

def factors(n):
  result = []
  factor_pairs = [[i, n // i] for i in xrange(
      1, int(math.sqrt(n)) + 1) if n % i == 0]
  for pair in factor_pairs:
    result += pair
  return result

n = 1
triangular_number = 0
num_divisors = 0
while num_divisors <= 500:
  triangular_number = triangularNumber(n)
  num_divisors = len(factors(triangular_number))
  n += 1

print str(triangular_number)
