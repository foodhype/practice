from collections import Counter


def n_gte_n_max(arr):
    counter = Counter()
    for val in arr:
        if val >= len(arr):
            counter[len(arr)] += 1
        elif val > 0:
            counter[val] += 1

    max_val = 0
    for index in reversed(xrange(1, len(arr) + 1)):
        max_val += counter[index]
        if max_val >= index:
            return index
        

def main():
    arr1 = [1, 2, 3, 4]
    assert n_gte_n_max(arr1) == 2
    arr2 = [900, 2, 901, 3, 1000]
    assert n_gte_n_max(arr2) == 3


if __name__ == "__main__":
    main()
