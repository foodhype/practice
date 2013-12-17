def fib(n):
  """Generator for first n fibonacci numbers"""
  a, b = 0, 1
  for _ in xrange(n):
    yield a
    a, b = b, a + b

def fib_xrange(stop):
  """Generator for fibonacci numbers up to stop"""
  a, b = 0, 1
  while a < stop:
    yield a
    a, b = b, a + b
