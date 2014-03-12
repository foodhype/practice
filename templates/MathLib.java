import java.math.BigInteger;

public class MathLib {
  public static BigInteger sqrt(BigInteger n) {
    BigInteger a = BigInteger.ONE;
    BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
    while (b.compareTo(a) >= 0) {
      BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
      if (mid.multiply(mid).compareTo(n) > 0) {
        b = mid.subtract(BigInteger.ONE);
      } else {
        a = mid.add(BigInteger.ONE);
      }
    }

    return a.subtract(BigInteger.ONE);
  }

  public static BigInteger[] fibs(int n) {
    BigInteger[] result = new BigInteger[n];
    BigInteger a = new BigInteger("0");
    BigInteger b = new BigInteger("1");
    for (int i = 0; i < n; i++) {
      result[i] = a;
      BigInteger temp = a.add(b);
      a = b;
      b = temp;
    }

    return result;
  }

  public static log2(double a) {
    return (int)(Math.log(x) / Math.log(2)+1e-10);
  }

  public static boolean isFib(BigInteger n) {
    BigInteger fiveNSquare = n.shiftLeft(1).multiply(new BigInteger("5"));
    BigInteger four = new BigInteger("4");
    BigInteger fiveNSquarePlusFour = fiveNSquare.add(four);
    BigInteger fiveNSquareMinusFour = fiveNSquare.subtract(four);

    return sqrt(fiveNSquarePlusFour).shiftLeft(1).equals(fiveNSquarePlusFour) ||
        sqrt(fiveNSquareMinusFour).shiftLeft(1).equals(fiveNSquareMinusFour);
  }
}

