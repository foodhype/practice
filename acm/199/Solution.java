import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Solution {
  public static void main(String args[]) {
    InputReader ir = new InputReader(System.in);
    int n = ir.nextInt();
    Snob[] snobs = new Snob[n];
    for (int i = 0; i < n; i++) {
      int strength = ir.nextInt();
      int beauty = ir.nextInt();
      snobs[i] = new Snob(strength, beauty, i + 1);
    }

    int[] result = BeautifulPeople.maxCompatibleSnobs(snobs);

    OutputWriter ow = new OutputWriter(System.out);
    ow.println(result.length);
    for (int r: result) {
      ow.print(r);
      ow.printSpace();
    }
    ow.close();
  }
}

class BeautifulPeople {
  public static int[] maxCompatibleSnobs(Snob[] snobs) {
    Arrays.sort(snobs);

    int dp[] = new int[snobs.length];
    int prev[] = new int[snobs.length];
    dp[0] = 1;
    prev[0] = -1;
    int max = 1;
    int bestEnd = 0;

    for (int i = 1; i < snobs.length; i++) {
      dp[i] = 1;
      prev[i] = -1;

      for (int j = i - 1; j >= 0; j--) {
        if (dp[j] + 1 > dp[i] &&
            snobs[j].strength < snobs[i].strength &&
            snobs[j].beauty < snobs[i].beauty) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
      }

      if (dp[i] > max) {
        bestEnd = i;
        max = dp[i];
      }
    }

    int[] result = new int[max];
    int currentBest = bestEnd;
    int k = max - 1;
    while (currentBest >= 0) {
      result[k] = snobs[currentBest].index;
      currentBest = prev[currentBest];
      k--;
    }

    return result;
  }
}

class Snob implements Comparable {
  public final int strength;
  public final int beauty;
  public final int index;

  public Snob(int strength, int beauty, int index) {
    this.strength = strength;
    this.beauty = beauty;
    this.index = index;
  }

  public int compareTo(Object o) {
    int c = this.strength - ((Snob) o).strength;
    if (c == 0) {
      return this.beauty - ((Snob) o).beauty;
    } else {
      return c;
    }
  }

  public boolean equals(Object o) {
    return this.strength == ((Snob) o).strength &&
        this.beauty == ((Snob) o).beauty &&
        this.index == ((Snob) o).index;
  }

  public String toString() {
    return "[s: " + strength + " b: " + beauty + " i: " + index + "]";
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

