
def get_model(words):
  start = {}
  for word in words:
    start[word[0]] = start.get(word[0], 0.0) + 1.0
  for key in start.keys():
    start[key] /= len(words)

  end = {}
  graph = {}
  count = {}
  for word in words:
    for (index, letter) in enumerate(word):
      if index < len(word) - 1:
        count[letter] = count.get(letter, 0.0) + 1.0
        if letter in graph.keys():
          graph[letter][word[index + 1]] = graph[letter].get(word[index + 1], 0.0) + 1.0
        else:
          graph[letter] = {word[index + 1] : 1.0}
      else:
        end[letter] = end.get(letter, 0.0) + 1.0

  for letter in graph.keys():
    for successor in graph[letter]:
      graph[letter][successor] /= count[letter]

  for letter in end.keys():
    end[letter] /= len(words)

  return (start, graph, end)

def main():
  words = [
      "APPLE",
      "ALPHABETICAL",
      "FRANCE",
      "STUPDENDOUS",
      "ABBA",
      "MANIACAL",
      "EXISTENTIAL"]

  print words

  model = get_model(words)
  print "start: " + str(model[0])
  print "graph: " + str(model[1])
  print "end: " + str(model[2])

if __name__ == "__main__":
  main()
