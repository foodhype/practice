import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P248B {
  public static void main(String args[]) throws Exception {
    // Input
    InputReader ir = new InputReader(System.in);
    int n = ir.nextInt();

    // Run
    ChillyWilly cw = new ChillyWilly();
    char[] result = cw.compute(n);

    // Output
    OutputWriter ow = new OutputWriter(System.out, n);
    ow.println(result);
    ow.close();
  }
}

class ChillyWilly {
  /* Determine minimum integer of length n that is divisible by 2, 3, 5, and 7. Return -1 if no
     such number exists.

     I optimized the hell out of this function only to realize the bottleneck was I/O, so it's
     fast as hell but could probably be cleaner. */
  public char[] compute(int n) {
    char[] result;
    if (n < 3) {
      result = new char[2];
      result[0] = '-';
      result[1] = '1';
      return result;
    }

    if (n == 3) {
      result = new char[3];
      result[0] = '2';
      result[1] = '1';
      result[2] = '0';
      return result;
    }

    result = new char[n];
    Arrays.fill(result, '0');
    result[0] = '1';

    int rem = (n - 3) % 6;

    switch (rem) {
      case 0: // Last three digits are 110
        result[n - 3] = '1';
        result[n - 2] = '1';
        result[n - 1] = '0';
        return result;
      case 1: // Last two digits are 50
        result[n - 2] = '5';
        result[n - 1] = '0';
        return result;
      case 2: // Last two digits are 80
        result[n - 2] = '8';
        result[n - 1] = '0';
        return result;
      case 3: // Last three digits 170
        result[n - 3] = '1';
        result[n - 2] = '7';
        result[n - 1] = '0';
        return result;
      case 4: // Last two digits are 20
        result[n - 2] = '2';
        result[n - 1] = '0';
        return result;
      case 5: // Last three digits are 200
        result[n - 3] = '2';
        result[n - 2] = '0';
        result[n - 1] = '0';
        return result;
    }

    return new char[0];
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

