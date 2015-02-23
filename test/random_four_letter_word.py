import random


def random_four_letter_word(target=None):
    with open("/usr/share/dict/words") as f:
        count = 0
        for line in f:
            word = line.rstrip()
            if len(word) == 4:
                if count == target:
                    return word
                count += 1

    return random_four_letter_word(random.randint(0, count - 1))


print random_four_letter_word()
