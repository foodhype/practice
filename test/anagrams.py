def anagrams(words):
  anagram_lists = {}
  for word in words:
    key = "".join(sorted(word))
    if key in anagram_lists:
      anagram_lists[key].append(word)
    else:
      anagram_lists[key] = [word]

  return anagram_lists.values()

def main():
  f = open("/usr/share/dict/words")
  words = [line.rstrip() for line in f.readlines()]
  f.close()
  print anagrams(words)


if __name__ == "__main__":
  main()
