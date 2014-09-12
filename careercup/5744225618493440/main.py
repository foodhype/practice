import itertools


def triplesSummingToValue(numbers, d):
    triples = []
    for a, b, c in itertools.combinations(numbers, 3):
        if a + b + c <= d:
            triples.append((a, b, c))

    return triples


def main():
    numbers = [1, 2, 3, 4, 5, 6, 7]
    print triplesSummingToValue(numbers, 7)


if __name__ == "__main__":
    main()
