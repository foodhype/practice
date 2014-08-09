from p10 import encode

def encode_modified(li):
  return map(lambda x: x if x[0] > 1 else x[1], (sublist for sublist in encode(li)))  

def main():
  li = ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']
  assert encode_modified(li) == [(4, 'a'), 'b', (2, 'c'), (2, 'a'), 'd', (4, 'e')]

if __name__ == "__main__":
  main()

