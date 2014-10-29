import random


def generate_integer_file(filename):
    with open(filename, "w") as f:
        for _ in xrange(2**23):
            f.write(str(random.randint(0, 2**20 - 1)) + "\n")


def find_integer_not_in_file(filename):
    """Find an integer that does NOT exist in a file with no more than 2^32 integers."""
    lower_bound = 0
    upper_bound = 2**32
    buckets = [0 for _ in xrange(2**16)]
    mask = 0xFFFF0000
    shift = 16
    with open(filename) as f:
        for line in f:
            integer = int(line)
            if lower_bound <= integer < upper_bound:
                buckets[(integer & mask) >> shift] += 1

    for bucket in buckets:
        if bucket != 2**16:
            bucket_lower_bound = bucket << shift
            bucket_upper_bound = bucket_lower_bound + 2**16
            bucket_integers = set()
            with open(filename) as f:
                for line in f:
                    integer = int(line)
                    if lower_bound <= integer < upper_bound:
                        if (integer & mask) >> shift == bucket:
                            bucket_integers.add(integer)

            for integer in xrange(bucket_lower_bound, bucket_upper_bound):
                if integer not in bucket_integers:
                    return integer


print "Generating file containing random integers..."
generate_integer_file("integers.txt")
print "Integers generated."

print "Finding integer not contained in file..."
missing_no = find_integer_not_in_file("integers.txt")
print "Missing number: %d" % (missing_no)

print "Checking..."
with open("integers.txt") as f:
    for line in f:
        integer = int(line)
        if integer == missing_no:
            print "Crap, it wasn't actually missing."
    print "Good, at least it wasn't in the file."
