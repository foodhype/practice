import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Main {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    OutputWriter ow = new OutputWriter(System.out);

    int t = ir.nextInt();
    for (int i = 0; i < t; i++) {
      int w = ir.nextInt();
      int h = ir.nextInt();
      String[] grid = new String[h];
      for (int k = 0; k < h; k++) {
        grid[k] = ir.next();
      }
      int result = TOURIST.maxInterestingLocations(grid);
      ow.println(result);
    }

    ow.close();
  }
}

class TOURIST {
  private static int[][][] dp = new int[200][100][100];

  public static int maxInterestingLocations(String[] grid) {
    int rows = grid.length;
    int cols = grid[0].length();
    char[][] matrix = new char[rows][cols];
    for (int i = 0; i < rows; i++) {
      matrix[i] = grid[i].toCharArray();
    }
    
    /* We can only move down and right on the trip to the destination, and can only move up and 
       left on the trip back: under those constraints, a trip from the NW corner to the SE corner 
       and back is identical to doing two successive trips from the NW corner to the SE corner, 
       since the reachable paths are identical. Thus we can still view the problem as a directed 
       acyclic graph and do dynamic programming. We can count the maximum number of interesting 
       locations that two successive tourists could have visited at each row and column at a given
       step until we reach the SE corner. */
    for (int step = 0; step <= rows + cols - 2; step++) {
      for (int r1 = 0; r1 <= step && r1 < rows; r1++) { // First tourist
        for (int r2 = 0; r2 <= r1; r2++) { // Second tourist
          // The row and step imply the column
          int c1 = step - r1;
          int c2 = step - r2; 

          if (c1 >= cols || c2 >= cols || matrix[r1][c1] == '#' || matrix[r2][c2] == '#') {
            dp[step][r1][r2] = 0;
            dp[step][r2][r1] = 0;
          } else {
            int count = 0;
            if (step > 0) {
              // max for both tourists coming from left of current positions
              count = Math.max(count, dp[step - 1][r1][r2]);
              // max for first coming from above and second coming from left
              if (r1 > 0)
                count = Math.max(count, dp[step - 1][r1 - 1][r2]);
              // max for first coming from left and second coming from above
              if (r2 > 0)
                count = Math.max(count, dp[step - 1][r1][r2 - 1]);
              // max for both coming from above
              if (r1 > 0 && r2 > 0)
                count = Math.max(count, dp[step - 1][r1 - 1][r2 - 1]);
            }

            if (matrix[r1][c1] == '*') {
              count++;
            }

            if (r1 != r2 && matrix[r2][c2] == '*') {
              count++; 
            }

            dp[step][r1][r2] = count;
            dp[step][r2][r1] = count;
          }
        }
      }
    }

    int result = dp[rows + cols - 2][rows - 1][rows - 1];
    clearCache(rows, cols);

    return result;
  }

  public static void clearCache(int rows, int cols) {
    for (int step = 0; step <= rows + cols - 2; step++) {
      for (int r1 = 0; r1 <= step && r1 < rows; r1++) {
        for (int r2 = 0; r2 <= r1; r2++) {
          dp[step][r1][r2] = 0;
        }
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

