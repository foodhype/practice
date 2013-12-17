result = 0
a, b = 0, 1
while a <= 4000000:
  if a % 2 == 0:
    result += a
  a, b = b, a + b

print result
