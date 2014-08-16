"""module for generating fibonacci numbers"""

def fib(count):
    """Return nth fibonacci number"""
    golden_ratio = (1 + 5**0.5) / 2
    return int((golden_ratio**count - (-golden_ratio)**(-count)) / 5**0.5)

def fibs(count):
    """Generator for first n fibonacci numbers"""
    current_fib, next_fib = 0, 1
    for _ in xrange(count):
        yield current_fib
        current_fib, next_fib = next_fib, current_fib + next_fib

def fibs_xrange(stop):
    """Generator for fibonacci numbers up to stop"""
    current_fib, next_fib = 0, 1
    while current_fib < stop:
        yield current_fib
        current_fib, next_fib = next_fib, current_fib + next_fib
