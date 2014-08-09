def is_palindrome(arr):
  for i in xrange(len(arr) // 2):
    if arr[i] != arr[len(arr) - i - 1]:
      return False
  return True

def main():
  arr1 = [1, 2, 3, 2, 1]
  arr2 = [1, 2, 2, 1]
  arr3 = [1, 1, 2, 3, 5, 8]
  assert is_palindrome(arr1)
  assert is_palindrome(arr2)
  assert not is_palindrome(arr3)

if __name__ == "__main__":
  main()
