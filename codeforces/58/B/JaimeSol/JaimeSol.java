import java.util.*;
import java.io.*;

class JaimeSol {
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    int[] factors = new int[n + 1];
    factors[0] = factors[1] = 1;
    for (int i = 2; i <= n; i++) {
      if (factors[i] != 0) {
        continue;
      }

      for (int j = i; j <= n; j+=i) {
        if (factors[j] != 0) {
          continue;
        }
        factors[j] = i;
      }
    }

    while (n != 1) {
      System.out.print(n + " ");
      n /= factors[n];
    }

    System.out.println(1);
    br.close();
  }
}
