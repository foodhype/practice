def palindromic_dec_and_octal():
    for number in count():
        if is_palindrome(str(number)) and is_palindrome(str(oct(number))[1:]):
            yield number


def is_palindrome(s):
    for index in xrange(len(s) // 2):
        if s[index] != s[len(s) - index - 1]:
            return False
    return True


def count():
    index = 0
    while True:
        index += 1
        yield index


def main():
    for num in palindromic_dec_and_octal():
        print num
        print oct(num)
        print ""


if __name__ == "__main__":
    main()
