
# memoized recursive solution that I used before
# 1 + 3 is different from 3 + 1
dp = {}
def count_ways_summing_to_k(nums, k):
  """Count the number of ways that numbers can sum to k. All numbers are 
     positive integers, and each number can be added multiple times."""
  count = 0
  for n in nums:
    diff = k - n
    if diff == 0:
      count += 1
    elif diff > 0:
      if diff in dp:
        count += dp[diff]
      else:
        count += count_ways_summing_to_k(nums, diff)
  dp[k] = count
  return count

