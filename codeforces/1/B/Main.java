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

    int n = ir.nextInt();
    String[] results = new String[n];
    for (int i = 0; i < n; i++) {
      String input = ir.next();
      if (input.matches("R[0-9]+C[0-9]+")) {
        results[i] = Solution.rxcyToExcel(input);
      } else {
        results[i] = Solution.excelToRxcy(input);
      }
    }

    OutputWriter ow = new OutputWriter(System.out);
    for (String s: results) {
      ow.println(s);
    }
    ow.close();
  }
}

class Solution {
  public static String rxcyToExcel(String input) {
    int c = input.indexOf("C");
    int row = Integer.parseInt(input.substring(1, c));
    int col = Integer.parseInt(input.substring(c + 1));

    String result = "";
    int radix = 26;
    while (col != 0) {
      int n = col % radix;
      col /= radix;
      char next;
      if (n == 0) {
        col -= 1;
        next = 'Z';
      } else {
        next = (char) (n + 'A' - 1);
      }
      result = next + result;
    }

    return result + String.valueOf(row);
  }

  public static String excelToRxcy(String input) {
    int index = 0;
    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if ('0' <= c && c <= '9') {
        break;
      }
      index++;
    }

    int row = Integer.parseInt(input.substring(index));
    
    int col = 0;
    int radix = 26;
    for (int i = 0; i < index; i++) {
      char c = input.charAt(i);
      col += ((int) input.charAt(i)) - 'A' + 1;
      col *= radix;
    }
    col /= radix;

    return "R" + row + "C" + col;
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

