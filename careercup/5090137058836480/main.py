def divide(a, b):
    real, rem = divmod(a, b)
    decimal = []
    remainders = {}
    index = 0
    while rem != 0:
        if rem in remainders:
            decimal.insert(remainders[rem], "(")
            break
        else:
            remainders[rem] = index

        rem *= 10
        digit, rem = divmod(rem, b)
        decimal.append(str(digit))
        index += 1

    if rem == 0:
        decimal.append("(0)")
    else:
        decimal.append(")")

    return "%d.%s" % (real, "".join(decimal))


def main():
    print divide(1, 3)
    print divide(2, 4)
    print divide(22, 7)


if __name__ == "__main__":
    main()
