# We are counting the number of unique arrangements of 20 downs and 20 rights,
# which is 40! / (20! * 20!) to find the number of paths from the top-left
# corner to the bottom-right corner. No matrix traversal needed.

result = 1
for i in xrange(21, 40 + 1): # we have canceled out some terms
  result *= i
for i in xrange(1, 20 + 1):
  result /= i

print str(result)
