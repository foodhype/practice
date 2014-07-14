from sys import stdin
import Queue

def sums_to(n):
  queue = Queue.Queue()
  for i in xrange(1, (n / 2) + 1):
    sum_list = [i, n - i]
    queue.put(sum_list)
  while not queue.empty():
    sum_list = queue.get()
    print sum_list
    for i in xrange(sum_list[len(sum_list) - 2], (sum_list[len(sum_list) - 1] // 2) + 1):
      a = []
      for j in xrange(len(sum_list) - 1):
        a.append(sum_list[j])
      a.append(i)
      a.append(sum_list[len(sum_list) - 1] - i)
      queue.put(a)


def main():
  lines = stdin
  n = int(lines.next())
  sums_to(n)

main()

