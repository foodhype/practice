def primes_upto(limit):
  primes = {}
  for i in xrange(2, limit + 1):
    primes[i] = True
  for i in primes:
    for factor in range(i, limit + 1, i)[1:]:
      primes[factor] = False
  return [i for i in primes if primes[i] == True]

product = 0
for i in primes_upto(2000000):
  product += i

print str(product)
