import Compressor
import Decompressor

def main():
  test_input = "Eerie eyes seen near lake."
  print test_input
  print Decompressor.uncompress(*Compressor.compress(test_input))

if __name__ == "__main__":
  main()
