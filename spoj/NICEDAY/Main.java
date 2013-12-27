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
    int testCases = ir.nextInt();
    int[] results = new int[testCases];
    for (int i = 0; i < testCases; i++) {
      int numCompetitors = ir.nextInt();
      Competitor[] competitors = new Competitor[numCompetitors];
      for (int j = 0; j < numCompetitors; j++)
        competitors[j] = new Competitor(ir.nextInt(), ir.nextInt(), ir.nextInt());
      results[i] = NiceDay.getNumExcellentCompetitors(competitors);
    }

    OutputWriter ow = new OutputWriter(System.out);
    for (int result: results)
      ow.println(result);
    ow.close();
  }
}

class NiceDay {
  public static int getNumExcellentCompetitors(Competitor[] competitors) {
    int result = 0;
    int numCompetitors = competitors.length;
    Arrays.sort(competitors);

    for (int i = 0; i < numCompetitors; i++) {
      /* At this point, all competitors that can possibly be better than the 
         ith competitor must have an index < i */
      boolean betterPairExists = false;
      for (int j = 0; j < i; j++) {
        if (competitors[j].contest2 < competitors[i].contest2 &&
            competitors[j].contest3 < competitors[i].contest3) {
          betterPairExists = true;
          break;
        }
      }

      if (!betterPairExists)
        result++;
    }

    return result;
  }
}

class Competitor implements Comparable {
  public final int contest1;
  public final int contest2;
  public final int contest3;

  public Competitor(int contest1, int contest2, int contest3) {
    this.contest1 = contest1;
    this.contest2 = contest2;
    this.contest3 = contest3;
  }

  public int compareTo(Object o) {
    return contest1 - ((Competitor) o).contest1;
  }

  public String toString() {
    return "" + contest1 + " " + contest2 + " " + contest3;
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

