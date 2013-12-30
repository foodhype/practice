import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int k = ir.nextInt();
    OutputWriter ow = new OutputWriter(System.out);

    while (k != 0) {
      String a = ir.next();
      String b = ir.next();
      int result = SAMERO8D.lcsk(a, b, k);
      ow.println(result);
      k = ir.nextInt();
    }

    ow.close();
  }
}

class SAMERO8D {
  private static final int MAX = 1001; // Necessary to pass tests on SPOJ when using this method
  private static int[][] commonSegments = new int[MAX][MAX];
  private static int[][] dp = new int[MAX][MAX];

  // Return the longest common subsequence where each common segment has a length of at least k
  public static int lcsk(String a, String b, int k) {
    int aLength = a.length();
    int bLength = b.length();

    for (int i = 1; i <= aLength; i++) {
      for (int j = 1; j <= bLength; j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          commonSegments[i][j] = commonSegments[i - 1][j - 1] + 1;
        }

        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        if (commonSegments[i][j] >= k) {
          for (int z = k; z <= commonSegments[i][j]; z++) {
            dp[i][j] = Math.max(dp[i][j], dp[i - z][j - z] + z);
          }
        }
      }
    }

    int result = dp[aLength][bLength];
    clear(aLength, bLength);

    return result;
  }

  private static void clear(int aLength, int bLength) {
    for (int i = 1; i <= aLength; i++) {
      for (int j = 1; j <= bLength; j++) {
        commonSegments[i][j] = 0;
        dp[i][j] = 0;
      }
    }
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

