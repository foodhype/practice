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

    int t = ir.nextInt();
    int[] result = new int[t];
    for (int i = 0; i < t; i++) {
        int n = ir.nextInt();
        int m = ir.nextInt();
        int[][] grid = new int[n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                grid[r][c] = ir.nextInt()
            }
        }

        result[i] = Solution.solve(grid, n, m);
    }

    OutputWriter ow = new OutputWriter(System.out);
    for (int i = 0; i < t; i++) {
        ow.println(result[i]);
    }


    ow.close();
  }
}

class Solution {
    public static int solve(int[][] grid, int n, int m) {
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 2; i < n; i++) {
            if ((grid[i][0] > grid[i - 1][0] && grid[i - 1][0] > grid[i - 2][0]) ||
                (grid[i][0] < grid[i - 1][0] && grid[i - 1][0] < grid[i - 2][0])) {
                dp[i][0] = dp[i - 1][0];
            } else if {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }

        for (int i = 2; i < m; i++) {
            if ((grid[0][i] > grid[0][i - 1] && grid[0][i - 1] > grid[0][i - 2]) ||
                (grid[0][i] < grid[0][i - 1] && grid[0][i - 1] < grid[0][i - 2])) {
                dp[0][i] = dp[0][i - 1];
            } else {
                dp[0][i] = dp[0][i - 1] + 1;
            }
        }


        for (int i = 2; i < n; i++) {
            for (int j = 2; j < m; j++) {
                dp[]
            }
            if (grid[i] > grid[i - 1]) {
                dp[i][0] = grid[i];
            } else {
                dp[i][0] = -grid[i];
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

