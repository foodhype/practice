


def fibo(n):
    dp = {}
    dp[0] = 0
    dp[1] = 1
    for index in xrange(1, n + 1):
        dp[index] = dp[index - 1] + dp[index - 2]

    return dp[n]


memo = {}

def fibo(n):
    if n in memo.keys():
        return memo[n]

    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        memo[n] = fibo(n - 1) + fibo(n - 2)
        return memo[n]
