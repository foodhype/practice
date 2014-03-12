chain_length_cache = {}

num_with_longest_chain = 1
max_collatz_chain_length = 0
for i in xrange(1, 1000000):
  chain_length = 1
  n = i
  if n in chain_length_cache:
    chain_length = chain_length_cache[n]
  else:
    while n != 1:
      if n in chain_length_cache:
        chain_length += chain_length_cache[n] - 1
        break
      else:
        if n % 2 == 0:
          n //= 2
        else:
          n = 3 * n + 1
      chain_length += 1
    chain_length_cache[i] = chain_length

  if chain_length > max_collatz_chain_length:
    max_collatz_chain_length = chain_length
    num_with_longest_chain = i
  print "iteration %d of 1000000" % (i + 1)

print str(num_with_longest_chain)
