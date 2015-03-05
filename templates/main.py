from sys import stdin


def main():
    t = read(int)
    for _ in xrange(t):
        

def read_line(t, delim=" "):
    """Read delim-separated line of elements of type t from stdin."""
    return map(t, stdin.next().rstrip().split(delim))


def read(t):
    """Read line containing element of type t."""
    return t(stdin.next().rstrip())


main()

