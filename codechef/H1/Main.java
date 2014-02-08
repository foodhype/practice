import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.InputMismatchException;

public class Main {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int n = ir.nextInt();
    int[] result = new int[n];
    H1.findShortestPaths();
    for (int k = 0; k < n; k++) {
      int[] matrix = new int[9];
      for (int i = 0; i < 9; i++) {
        matrix[i] = ir.nextInt();
      }

      result[k] = H1.minSteps(matrix);
    }

    OutputWriter ow = new OutputWriter(System.out);
    for (int k = 0; k < n; k++) {
      ow.println(result[k]);
    }
    ow.close();
  }
}

class H1 {
  private static final int [][] swaps = {{0, 1}, {0, 3}, {1, 2}, {1, 4},
      {2, 5}, {3, 4}, {3, 6}, {4, 5}, {4, 7}, {5, 8}, {6, 7}, {7, 8}};
  private static HashMap<Board, Integer> visited = new HashMap<Board, Integer>();

  public static int minSteps(int[] matrix) {
    Board b = new Board(matrix);
    if (visited.containsKey(b)) {
      return visited.get(b);
    } else {
      return -1;
    }
  }

  public static void findShortestPaths() {
    int[] goal = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    Board goalState = new Board(goal);
    Queue<Board> fringe = new ArrayDeque<Board>();
    visited.put(goalState, 0);
    fringe.add(goalState);

    while (!fringe.isEmpty()) {
      Board current = fringe.remove();

      for (int[] swap: swaps) {
        int sum = current.matrix[swap[0]] + current.matrix[swap[1]];
        if (sum == 3 || sum == 5 || sum == 7 || sum == 11 || sum == 13 || sum == 17) {
          Board adjacent = current.swap(swap[0], swap[1]);
          if (!visited.containsKey(adjacent)) {
            fringe.add(adjacent);
            visited.put(adjacent, visited.get(current) + 1);
          }
        }
      }
    }
  }
}

class Board {
  public int[] matrix;

  public Board(int[] matrix) {
    this.matrix = matrix;
  }

  public int hashCode() {
    int code = 0;
    for (int i = 0; i < 9; i++) {
      code = code * 10 + matrix[i];
    }

    return code;
  }

  public Board clone() {
    int[] matrixCopy = new int[9];
    for (int i = 0; i < 9; i++) {
      matrixCopy[i] = matrix[i];
    }
    return new Board(matrixCopy);
  }

  public boolean equals(Object o) {
    Board b = (Board) o;
    for (int i = 0; i < 9; i++) {
      if (matrix[i] != b.matrix[i]) {
        return false;
      }
    }

    return true;
  }

  public Board swap(int i, int j) {
    Board b = clone();
    int temp = b.matrix[i];
    b.matrix[i] =  b.matrix[j];
    b.matrix[j] = temp;
    return b;
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

