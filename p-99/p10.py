from p09 import pack

def encode(li):
  return map(lambda x: (len(x), x[0]), (sublist for sublist in pack(li)))  

def main():
  li = ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']
  assert encode(li) == [(4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')]

if __name__ == "__main__":
  main()

