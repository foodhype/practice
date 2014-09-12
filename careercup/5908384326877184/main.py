import bisect


def sum_less_than_or_equal(numbers, k):
    numbers.sort()

    pairs = []
    start = None
    for index, number in enumerate(reversed(numbers)):
        if number + numbers[0] <= k:
            start = index

    if start is None:
        return pairs

    for index in xrange(start, 0, -1):
        number = numbers[index]
        target = k - number
        target_index = bisect.bisect(numbers, target, 0, index)
        if target_index:
            for pair_index in xrange(target_index):
                pairs.append((number, numbers[pair_index]))

    return pairs


def main():
    numbers = [1, 3, 5, 9, 20, 28, 30, 37, 60, 100]
    k = 50
    print sum_less_than_or_equal(numbers, k)


if __name__ == "__main__":
    main()
