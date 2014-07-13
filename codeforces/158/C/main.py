from sys import stdin

def main():
  lines = stdin
  n = int(lines.next())
  cur_path_name = "/"
  for _ in xrange(n):
    command = lines.next().rstrip().split()
    if command[0] == "pwd":
      if cur_path_name == "/":
        print cur_path_name
      else:
        print "/" + cur_path_name + "/"
    elif command[0] == "cd":
      cur_path = cur_path_name.split("/")
      arg_path = command[1].split("/")

      if arg_path[0] == "":
        cur_path = arg_path
      else:
        cur_path += arg_path

      cur_path = [p for p in cur_path if p != ""]

      op_stack = []
      path_stack = []
      cur_path.reverse()

      index = 0
      while index < len(cur_path):
        if cur_path[index] == "..":
          op_stack.append("..")
        elif len(op_stack) > 0:
          op_stack.pop()
        else:
          path_stack.append(cur_path[index])
        index += 1

      path_stack.reverse()
      cur_path_name = "/" if len(path_stack) == 0 else "/".join(path_stack)


main()

