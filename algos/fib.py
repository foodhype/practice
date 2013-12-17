def fib(n):
  """Return nth fibonacci number"""
  golden_ratio = (1 + 5**0.5) / 2
  return int((golden_ratio**n - (-golden_ratio)**(-n)) / 5**0.5)

def fibs(n):
  """Generator for first n fibonacci numbers"""
  a, b = 0, 1
  for _ in xrange(n):
    yield a
    a, b = b, a + b

def fibs_xrange(stop):
  """Generator for fibonacci numbers up to stop"""
  a, b = 0, 1
  while a < stop:
    yield a
    a, b = b, a + b
