def penultimate(li):
  return li[-2]

def main():
  li = [1, 1, 2, 3, 5, 8]
  assert penultimate(li) == 5

if __name__ == "__main__":
  main()
