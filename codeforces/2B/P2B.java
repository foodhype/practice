import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.StringBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;

public class P2B {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int n = ir.nextInt();
    int[][] matrix = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        matrix[i][j] = ir.nextInt();
      }
    }

    LeastRoundWay lrw = new LeastRoundWay(matrix, n);
    String way = lrw.findWay();
    int minTrailingZeros = lrw.getMinTrailingZeros();

    OutputWriter ow = new OutputWriter(System.out);
    ow.println(String.valueOf(minTrailingZeros));
    ow.println(way);
    ow.close();
  }
}

class LeastRoundWay {
  private int[][] matrix;
  private int size;
  private int minTrailingZeros;
  private int[][] memo;

  public LeastRoundWay(int[][] matrix, int size) {
    this.matrix = matrix;
    this.size = size;
    minTrailingZeros = 0;
    memo = new int[size][size];
  }

  public String findWay() {
    for (int[] row: memo)
      Arrays.fill(row, -1);
    int minTwos = findWay(0, 0, 2);
    String path = tracePath();
    for (int[] row: memo)
      Arrays.fill(row, -1);
    int minFives = findWay(0, 0, 5);
    if (minTwos < minFives) {
      minTrailingZeros = minTwos;
      return path;
    } else {
      minTrailingZeros = minFives;
      return tracePath();
    }
  }

  public String tracePath() {
    StringBuilder sb = new StringBuilder();
    int row = 0;
    int col = 0;
    while (row + 1 < size && col + 1 < size) {
      if (memo[row + 1][col] <= memo[row][col + 1]) {
        sb.append("D");
        row++;
      } else {
        sb.append("R");
        col++;
      }
    }
    
    while (row + 1 < size) {
      sb.append("D");
      row++;
    }
    
    while (col + 1 < size) {
      sb.append("R");
      col++;
    }

    return sb.toString();
  }

  private int findWay(int row, int col, int target) {
    int count = 0;
    int current = matrix[row][col];
    while (current % target == 0) {
      count++;
      current /= target;
    }

    int upCount = 0;
    int downCount = 0;
    if (row + 1 < size && col + 1 < size) {
      if (memo[row + 1][col] != -1) {
        downCount = memo[row + 1][col];
      } else {
        downCount = findWay(row + 1, col, target);
      }

      if (memo[row][col + 1] != -1) {
        upCount = memo[row][col + 1];
      } else {
        upCount = findWay(row, col + 1, target);
      }

      count += Math.min(downCount, upCount);
    } else if (row + 1 < size) {
      if (memo[row + 1][col] != -1) {
        count += memo[row + 1][col];
      } else {
        count += findWay(row + 1, col, target);
      }
    } else if (col + 1 < size) {
      if (memo[row][col + 1] != -1) {
        count += memo[row][col + 1];
      } else {
        count += findWay(row, col + 1, target);
      }
    }

    memo[row][col] = count;

    return count;
  }

  public int getMinTrailingZeros() {
    return minTrailingZeros;
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

