def gcd(a, b):
  while b != 0:
    a, b = b, a % b
  return a

def lcm(a, b):
  return (a * b) / gcd(a, b)

result = 1
for i in xrange(1, 20):
  result = lcm(result, i)

print str(result)
