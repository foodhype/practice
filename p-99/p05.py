def reverse(li):
  return li[::-1]

def main():
  li = [1, 1, 2, 3, 5, 8]
  assert reverse(li) == [8, 5, 3, 2, 1, 1]

if __name__ == "__main__":
  main()
