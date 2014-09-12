import sys
from collections import Counter


def longest_matching_words(words, letters):
    word_counters = [Counter(word) for word in words]
    letter_counter = Counter(letters)

    matching_words = []
    max_length = 0
    for (index, word_counter) in enumerate(word_counters):
        match = True
        for letter, count in word_counter.items():
            if count > letter_counter[letter]:
                match = False

        if match:
            if len(words[index]) > max_length:
                matching_words = [words[index]]
            elif len(words[index]) == max_length:
                matching_words.append(words[index])

    return matching_words


def main():
    f = open(sys.argv[1])
    words = [word.rstrip() for word in f.readlines()]
    f.close()
    letters = sys.argv[2:]
    print longest_matching_words(words, letters)


if __name__ == "__main__":
    main()
