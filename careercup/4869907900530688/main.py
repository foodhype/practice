def next_largest(number):
    digits = []
    while number > 0:
        digit = number % 10
        digits.append(digit)
        number /= 10
    digits.reverse()

    for index in reversed(xrange(1, len(digits))):
        if digits[index - 1] < digits[index]:
            temp = digits[index - 1]
            digits[index - 1] = digits[-1]
            digits[-1] = temp
            digits[index:] = sorted(digits[index:])
            break

    result = 0
    degree = 0
    for digit in reversed(digits):
        result += digit * 10**degree
        degree += 1

    return result


def main():
    assert next_largest(25468) == 25486
    assert next_largest(21765) == 25167
    assert next_largest(54321) == 54321


if __name__ == "__main__":
    main()
