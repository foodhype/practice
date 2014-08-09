def pack(li):
  packed = []
  running_elem = None
  current_run = None
  for element in li:
    if element == running_elem:
      current_run.append(element)
    else:
      running_elem = element
      current_run = [running_elem]
      packed.append(current_run)

  return packed

def main():
  li = ['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']
  assert pack(li) == [['a', 'a', 'a', 'a'], ['b'], ['c', 'c'], ['a', 'a'], ['d'], ['e', 'e', 'e', 'e']]

if __name__ == "__main__":
  main()
