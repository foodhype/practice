import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.InputMismatchException;

/* This may be the easiest greedy problem I've ever solved. */
public class P58B {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int largestDenomination = ir.nextInt();

    ArrayList<Integer> result = Coins.getDenominations(largestDenomination);

    OutputWriter ow = new OutputWriter(System.out);
    for (int i = 0; i < result.size(); i++) {
      ow.print(result.get(i));
      if (i == result.size() - 1)
        ow.println();
      else
        ow.printSpace();
    }
    ow.close();
  }
}

class Coins {
  /* Find the maximum number of denominations of coins that can exist, such 
     that all smaller denominations are divisible by all larger denominations,
     where the largest denomination is given. */
  public static ArrayList<Integer> getDenominations(int largestDenomination) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    result.add(largestDenomination);
    int numCoins = 1;
    for (int i = largestDenomination - 1; i >= 1; i--) {
      if (result.get(numCoins - 1) % i == 0) {
        result.add(i);
        numCoins++;
      }
    }

    return result;
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

  public void print(String s) {
    writer.println(s);
  }

  public void print(char[] c) {
    writer.println(c);
  }

  public void printSpace() {
    writer.print(" ");
  }

  public void println() {
    writer.println();
  }

  public void close() {
    writer.close();
  }
}

