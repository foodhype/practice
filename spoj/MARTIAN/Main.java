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
    OutputWriter ow = new OutputWriter(System.out);
    int n = ir.nextInt();
    int m = ir.nextInt();
    while (!(n == 0 && m == 0)) {
      int [][] yeyenum = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          yeyenum[i][j] = ir.nextInt();
        }
      }

      int [][] bloggium = new int[n][m];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          bloggium[i][j] = ir.nextInt();
        }
      }

      ow.println(MARTIAN.maxMinerals(yeyenum, bloggium));
      n = ir.nextInt();
      m = ir.nextInt();
    }

    ow.close();
  }
}

class MARTIAN {
  public static int maxMinerals(int[][] yeyenum, int[][] bloggium) {
    int rows = yeyenum.length;
    int cols = yeyenum[0].length;
    int [][] y = new int[rows + 1][cols + 1];
    int [][] b = new int[rows + 1][cols + 1];
    int [][] dp = new int[rows + 1][cols + 1];

    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        y[i][j] = yeyenum[i - 1][j - 1] + y[i][j - 1];
        b[i][j] = bloggium[i - 1][j - 1] + b[i - 1][j];
        dp[i][j] = Math.max(dp[i - 1][j] + y[i][j], dp[i][j - 1] + b[i][j]);
      }
    }

    return dp[rows][cols];
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

