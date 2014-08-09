import Queue

def rotate(k, li):
  dq = Queue.deque(li)
  dq.rotate(-k)
  return list(dq)

def main():
  li = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k']
  assert rotate(3, li) == ['d', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'a', 'b', 'c']
  assert rotate(-2, li) == ['j', 'k', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i']

if __name__ == "__main__":
  main()
