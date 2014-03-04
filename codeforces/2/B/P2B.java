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

    int min[][] = LeastRoundWay.leastRoundWay(matrix);
    int minTrailingZeros = LeastRoundWay.getMinTrailingZeros(min);
    String path = LeastRoundWay.traceLeastRoundPath(min);

    OutputWriter ow = new OutputWriter(System.out);
    ow.println(String.valueOf(minTrailingZeros));
    ow.println(path);
    ow.close();
  }
}

class LeastRoundWay {
  public static int[][] leastRoundWay(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] minTwos = leastRoundWay(matrix, 2);
    int[][] minFives = leastRoundWay(matrix, 5);

    if (minTwos[rows - 1][cols - 1] < minFives[rows - 1][cols - 1])
      return minTwos;
    else
      return minFives;
  }

  private static int[][] leastRoundWay(int[][] matrix, int factor) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    int[][] min = new int[rows][cols];

    min[0][0] = countFactors(matrix[0][0], factor);
    for (int j = 1; j < cols; j++)
      min[0][j] = min[0][j - 1] + countFactors(matrix[0][j], factor);
    for (int i = 1; i < rows; i++)
      min[i][0] = min[i - 1][0] + countFactors(matrix[i][0], factor);
    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < cols; j++) {
        int count = countFactors(matrix[i][j], factor);
        int upCount = min[i - 1][j];
        int leftCount = min[i][j - 1];
        min[i][j] = count + Math.min(upCount, leftCount);
      }
    }

    if (min[rows - 1][cols - 1] > 1) {
      boolean zeroFound = false;
      int rowOfZero = 0;
      int colOfZero = 0;
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          if (matrix[i][j] == 0) {
            zeroFound = true;
            rowOfZero = i;
            colOfZero = j;
            break;
          }
        }
        if (zeroFound)
          break;
      }

      if (zeroFound) {
        for (int[] row: min)
          Arrays.fill(row, Integer.MAX_VALUE);

        int row = 0;
        int col = 0;
        while (row < rowOfZero) {
          min[row][col] = 1;
          row++;
        }
        while (col < colOfZero) {
          min[row][col] = 1;
          col++;
        }
        while (row < rows) {
          min[row][col] = 1;
          row++;
        }
        row  = rows - 1;
        while (col < cols) {
          min[row][col] = 1;
          col++;
        }
      }
    }

    return min;
  }

  private static int countFactors(int n, int factor) {
    int count = 0;
    while (n > 1 && n % factor == 0) {
      count++;
      n /= factor;
    }

    return count;
  }

  public static String traceLeastRoundPath(int[][] min) {
    StringBuilder sb = new StringBuilder();
    int row = min.length - 1;
    int col = min[0].length - 1;

    while (row - 1 >= 0 && col - 1 >= 0) {
      if (min[row - 1][col] < min[row][col - 1]) {
        sb.append("D");
        row--;
      } else {
        sb.append("R");
        col--;
      }
    }
    
    while (row - 1 >= 0) {
      sb.append("D");
      row--;
    } 

    while (col - 1 >= 0) {
      sb.append("R");
      col--;
    }   

    return sb.reverse().toString();
  }

  public static int getMinTrailingZeros(int[][] min) {
    int rows = min.length;
    int cols = min[0].length;
    return min[rows - 1][cols - 1];
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

