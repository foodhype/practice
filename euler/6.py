sum_of_squares = sum([i*i for i in xrange(1, 101)])
square_of_sum = sum([i for i in xrange(1, 101)])**2
print str(square_of_sum - sum_of_squares)
