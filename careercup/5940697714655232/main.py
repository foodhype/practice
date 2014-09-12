from math import log10
from math import sqrt
from decimal import *


def tilings(n):
    with localcontext() as context:
        context.prec = int(log10(sqrt(5)**n)) + 1
        sqrt_five = Decimal(5).sqrt()
        golden_ratio = (1 + sqrt_five) / 2

        return int((golden_ratio**n - -golden_ratio**-n) / sqrt_five)


def main():
    print tilings(127)


if __name__ == "__main__":
    main()
