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
    int t = ir.nextInt();
    int[] results = new int[t];

    for (int i = 0; i < t; i++) {
      int n = ir.nextInt();
      int k = ir.nextInt();
      Customer[] customers = new Customer[n];
      for (int j = 0; j < n; j++) {
        int s = ir.nextInt();
        int f = ir.nextInt();
        int p = ir.nextInt();
        customers[j] = new Customer(s, f, p);
      }

      results[i] = Solution.maxCustomers(n, k, customers);
    }

    OutputWriter ow = new OutputWriter(System.out);

    for (int result: results) {
      ow.println(result);
    }

    ow.close();
  }
}

class Solution {
  public static int maxCustomers(int customerCount, int compartmentCount, Customer[] customers) {
    Arrays.sort(customers);

    int[] finishTimes = new int[compartmentCount];
    customerCount = 0;

    for (Customer c: customers) {
      if (c.s >= finishTimes[c.p - 1]) {
        finishTimes[c.p - 1] = c.f;
        customerCount++;
      }
    }

    return customerCount;
  }
}

class Customer implements Comparable {
  public final int s;
  public final int f;
  public final int p;

  public Customer(int s, int f, int p) {
    this.s = s;
    this.f = f;
    this.p = p;
  }

  public int compareTo(Object o) {
    return f - ((Customer) o).f;
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

