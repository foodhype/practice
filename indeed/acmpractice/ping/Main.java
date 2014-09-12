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
    ArrayList<ArrayList<Integer>> result[] = new ArrayList<ArrayList<Integer>>;
    for (int i = 0; i < n; i++) {
        String bs = ir.next();
        result.add(Solution.solve(bs.toCharArray()));
    }


    OutputWriter ow = new OutputWriter(System.out);
    for (int i = 0; i < result.size() - 1; i++) {
        ArrayList<Integer> r = result.get(i);
        for (int j = 0; j < r.size() - 1; j++) {
            ow.print(r.get(j) + " ");
        }
        ow.println(r.get(r.size() - 1));
    }

    ArrayList<Integer> r = result.get(result.size() - 1);
    for (int j = 0; j < r.size() - 1; j++) {
            ow.print(r.get(j) + " ");
    }
    ow.print(r.get(r.size() - 1));
    ow.close();
  }
}

class Solution {
    public static solve(char[] bs) {
        boolean even = (bs[0] == '0');
        ArrayList<Integer> result = new ArrayList<Integer>();
        int ping[] = new int[bs.length];
        for (int i = 1; i < bs.length; i++) {
            if (i == 1 || (bs[i] == '1' && even) || (bs[i] == '0' && !even)) {
                result.add(i);
                for (int j = i + i; j < bs.length; j += i) {
                    
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

