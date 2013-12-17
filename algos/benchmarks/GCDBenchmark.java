import java.util.Random;

public class GCDBenchmark {
  public static final int BENCHMARK_SIZE = 20000000;

  public static void main(String args[]) {
    System.out.println("Benchmark size is " + BENCHMARK_SIZE);
    System.out.println();

    int[] randIntA = new int[BENCHMARK_SIZE];
    int[] randIntB = new int[BENCHMARK_SIZE];
    Random rand = new Random();
    for (int i = 0; i < BENCHMARK_SIZE; i++) {
      randIntA[i] = Math.abs(rand.nextInt());
      randIntB[i] = Math.abs(rand.nextInt());
    }

    System.out.println("Running recursive Euclidean GCD...");
    long startTime1 = System.nanoTime();
    for (int i = 0; i < BENCHMARK_SIZE; i++)
      recursiveEuclideanGcd(randIntA[i], randIntB[i]);
    long endTime1 = System.nanoTime();

    System.out.println("Running iterative Euclidean GCD...");
    long startTime2 = System.nanoTime();
    for (int i = 0; i < BENCHMARK_SIZE; i++)
      iterativeEuclideanGcd(randIntA[i], randIntB[i]);
    long endTime2 = System.nanoTime();

    System.out.println("Running recursive binary GCD...");
    long startTime3 = System.nanoTime();
    for (int i = 0; i < BENCHMARK_SIZE; i++)
      recursiveBinaryGcd(randIntA[i], randIntB[i]);
    long endTime3 = System.nanoTime();

    System.out.println("Running iterative binary GCD...");
    long startTime4 = System.nanoTime();
    for (int i = 0; i < BENCHMARK_SIZE; i++)
      iterativeBinaryGcd(randIntA[i], randIntB[i]);
    long endTime4 = System.nanoTime();

    System.out.println();
    System.out.println("Elapsed time (over " + BENCHMARK_SIZE + " iterations):");
    System.out.println("Recursive Euclidean GCD took " + (endTime1 - startTime1) + " ns");
    System.out.println("Iterative Euclidean GCD took " + (endTime2 - startTime2) + " ns");
    System.out.println("Recursive binary GCD took    " + (endTime3 - startTime3) + " ns");
    System.out.println("Iterative binary GCD took    " + (endTime4 - startTime4) + " ns");
  }

  public static int recursiveEuclideanGcd(int a, int b) {
    return (b == 0) ? a : recursiveEuclideanGcd(b, a % b);
  }

  public static int iterativeEuclideanGcd(int a, int b) {
    while (b != 0)
      b = a % (a = b);
    return a;
  }

  public static int recursiveBinaryGcd(int a, int b) {
    if (b == 0)
      return a;
    if (a == 0)
      return b;

    // a and b even
    if ((a & 1) == 0 && (b & 1) == 0)
      return recursiveBinaryGcd(a >> 1, b >> 1) << 1;
    // a is even, b is odd
    else if ((a & 1) == 0)
      return recursiveBinaryGcd(a >> 1, b);
    // a is odd, b is even
    else if ((b & 1) == 0)
      return recursiveBinaryGcd(a, b >> 1);
    // a and b odd, a >= b
    else if (a >= b)
      return recursiveBinaryGcd((a-b) >> 1, b);
    // a and b odd, a < b
    else
      return recursiveBinaryGcd(a, (b-a) >> 1);
  }

  public static int iterativeBinaryGcd(int a, int b){
    int shift;
    if (a == 0)
      return b;
    if (b == 0)
      return a;

    for (shift = 0; ((a | b) & 1) == 0; ++shift) {
      a >>= 1;
      b >>= 1;
    }

    while ((a & 1) == 0)
      a >>= 1;

    do {
      while ((b & 1) == 0)  
        b >>= 1;

      if (a > b) {
        int t = b;
        b = a;
        a = t;
      }

      b = b - a;
    } while (b != 0);

    return a << shift; 
  }
}

