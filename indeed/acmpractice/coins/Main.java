import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.InputMismatchException;

public class Main {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int c = ir.nextInt();
    int[] result = new int[c];
    for (int i = 0; i < c; i++) {
        int n = ir.nextInt();
        int[] coins = new int[n];
        int innerSum = 0;
        for (int j = 0; j < n; j++) {
            coins[j] = ir.nextInt();
            innerSum += coins[j];
        }
        int[][] cache = new int[coins.length][coins.length];
        result[i] = Solution.minimax(coins, 0, coins.length - 1, innerSum, cache);
    }


    OutputWriter ow = new OutputWriter(System.out);
    for (int r: result) {
        ow.println(r);
    }

    ow.close();
  }
}

class Solution {
    public static int minimax(int[] coins, int left, int right, int innerSum, int[][] cache) {
        if (left <= right) {
            if (cache[left][right] > 0) {
                return cache[left][right];
            }

            int leftSum = innerSum - minimax(coins, left + 1, right, innerSum - coins[left], cache);
            int rightSum = innerSum - minimax(coins, left, right - 1, innerSum - coins[right], cache);
            int maxSum = Math.max(leftSum, rightSum);
            cache[left][right] = maxSum;

            return maxSum;
        }

        return innerSum;
    }
}

class InputReader {
  private InputStream stream;
  private byte[] buf = new byte[1024];
  private int curChar;
  private int numChars;

  public InputReader(InputStream stream) {
    this.stream = stream;
  }

  public int read() {
    if (numChars == -1)
      throw new InputMismatchException();
    if (curChar >= numChars) {
      curChar = 0;
      try {
        numChars = stream.read(buf);
      } catch (IOException e) {
        throw new InputMismatchException();
      }
      if (numChars <= 0)
        return -1;
    }
    return buf[curChar++];
  }

  public int nextInt() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
    int sgn = 1;
    if (c == '-') {
      sgn = -1;
      c = read();
    }
    int res = 0;
    do {
      if (c < '0' || c > '9')
        throw new InputMismatchException();
      res *= 10;
      res += c & 15;
      c = read();
    } while (!isSpaceChar(c));

    return res * sgn;
  }

  public String next() {
    int c = read();
    while (isSpaceChar(c))
      c = read();
      StringBuilder res = new StringBuilder();
    do {
      res.appendCodePoint(c);
      c = read();
    } while (!isSpaceChar(c));

    return res.toString();
  }

  public static boolean isSpaceChar(int c) {
    return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
  }
}

class OutputWriter {
  private PrintWriter writer;

  public OutputWriter(OutputStream stream) {
    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)));
  }

  public OutputWriter(OutputStream stream, int size) {
    writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream), size));
  }

  public OutputWriter(Writer writer) {
    this.writer = new PrintWriter(writer);
  }

  public void println(int x) {
    writer.println(x);
  }

  public void println(String s) {
    writer.println(s);
  }

  public void println(char[] c) {
    writer.println(c);
  }

  public void print(int x) {
    writer.print(x);
  }

  public void printSpace() {
    writer.print(" ");
  }

  public void close() {
    writer.close();
  }
}

