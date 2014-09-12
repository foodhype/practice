def print_in_order(prefix, N):
    if int(prefix) <= N:
        print prefix
        for number in xrange(0, 10):
            print_in_order(prefix + str(number), N)


def main():
    N = 1000
    for prefix in xrange(1, 10):
        print_in_order(str(prefix), N)


if __name__ == "__main__":
    main()
