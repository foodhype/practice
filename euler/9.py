def is_triple(a, b, c):
  return a * a + b * b == c * c

result = 0
for k in xrange(1, 1001):
  for j in xrange(k):
    for i in xrange(j):
      if i < j < k and i + j + k == 1000 and is_triple(i, j, k):
        result = i * j * k
  print "iteration %d of 1000" % (k)

print str(result)
