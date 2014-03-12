import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
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
    if (snobs.length == 0) {
      return new int[0];
    }

    Arrays.sort(snobs);
    ArrayList<TreeSet<Snob>> buckets = new ArrayList<TreeSet<Snob>>(snobs.length);
    for (int i = 0; i < snobs.length; i++) {
      buckets.add(new TreeSet<Snob>());
    }

    buckets.get(0).add(snobs[0]);

    Snob last = null;
    int max = 0;
    for (int i = 0; i < snobs.length; i++) {
      int low = -1;
      int high = max;
      Snob current = snobs[i];
      Snob lower = null;
      while (high - low > 1) {
        int mid = (low + high) / 2;
        lower = lower(current, buckets.get(mid)); 
        if (lower != null) {
          low = mid;
        } else {
          high = mid;
        }
      }

      boolean inserted = insert(current, buckets.get(low + 1));
      if (inserted && lower != null) {
        current.prev(lower);
      }

      if (low + 2 > max) {
        last = current;
        max = low + 2;
      }
    }

    int result[] = new int[max];
    for (int i = max - 1; i >= 0; i--) {
      result[i] = last.index;
      last = last.prev();
    }

    return result;
  }

  public static Snob lower(Snob val, TreeSet<Snob> bucket) {
    Snob temp = bucket.ceiling(new Snob(val.strength, -1, -1));
    if (temp == null || temp.equals(bucket.first())) {
      return null;
    } else {
      Snob lower = bucket.lower(temp);
      if (lower.beauty < val.beauty) {
        return lower;
      } else {
        return null;
      }
    }
  }

  public static boolean insert(Snob snob, TreeSet<Snob> bucket) {
    Snob temp = bucket.ceiling(snob);

    if (temp != null && !temp.equals(bucket.first())) {
      Snob temp2 = bucket.lower(temp);
      if (temp2.strength == snob.strength && temp2.beauty <= snob.beauty) {
        return false;
      }
    }

    while (temp != null && !temp.equals(bucket.last()) && temp.beauty >= snob.beauty) {
      Snob temp3 = bucket.higher(temp);
      bucket.remove(temp);
      temp = temp3;
    }

    bucket.add(snob);
    return true;
  }

/*  public static int[] maxCompatibleSnobs(Snob[] snobs) {
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
  }*/
}

class Snob implements Comparable {
  public final int strength;
  public final int beauty;
  public final int index;
  private Snob prev;

  public Snob(int strength, int beauty, int index) {
    this.strength = strength;
    this.beauty = beauty;
    this.index = index;
  }

  public Snob prev() {
    return prev;
  }

  public void prev(Snob prev) {
    this.prev = prev;
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

