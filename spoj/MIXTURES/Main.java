import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

public class Main {
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    OutputWriter ow = new OutputWriter(System.out);

    String line = br.readLine();
    while (line != null && !line.equals("")) {
      int n = Integer.parseInt(line);
      String[] input = br.readLine().split(" ");
      int[] mixtures = new int[n];
      for (int i = 0; i < n; i++)
        mixtures[i] = Integer.parseInt(input[i]);
      int result = MIXTURES.minimumSmoke(mixtures);
      ow.println(result);
      line = br.readLine();
    }

    br.close();
    ow.close();
  }
}

class MIXTURES {
  /* The key is that this problem is very similar to finding the minimum cost
     of performing matrix-chain multiplication. */
  public static int minimumSmoke(int[] mixtures) {
    int size = mixtures.length;
    // dp[i][j] is minimum smoke in range i..j
    int dp[][] = new int[size][size];
    // compound[i][j] is the compound resulting from mixing compunds i..j
    int[][] compound = new int[size][size];
    for (int i = 0; i < size; i++) {
      // Base case: range of length 1 has no smoke
      dp[i][i] = 0;
      // Base case: compound with one mixture is simply that mixture
      compound[i][i] = mixtures[i];
    }

    for (int i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < size; j++) {
        compound[i][j] = (compound[i][j - 1] + mixtures[j]) % 100;
      }
    }

    // l is range length
    for (int l = 2; l <= size; l++) {
      // i is start of range
      for (int i = 0; i < size - l + 1; i++) {
        // j is end of range
        int j = i + l - 1;
        dp[i][j] = Integer.MAX_VALUE;
        // k is dividing point
        for (int k = i; k < j; k++) {
          int q = dp[i][k] + dp[k + 1][j] + compound[i][k] * compound[k + 1][j];
          if (q < dp[i][j])
            dp[i][j] = q;
        }
      }
    }

    return dp[0][size - 1];
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

