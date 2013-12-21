import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.InputMismatchException;

public class P232C {
  private static final int NUM_FIBS = 102;
  private static BigInteger[] fibs = new BigInteger[NUM_FIBS];

  public static void main(String args[]) {
    // Input
    InputReader ir = new InputReader(System.in);
    int numQueries = ir.nextInt();
    int order = ir.nextInt();
    BigInteger[] src = new BigInteger[numQueries];
    BigInteger[] dst = new BigInteger[numQueries];
    for (int i = 0; i < numQueries; i++) {
      src[i] = new BigInteger(ir.next());
      dst[i] = new BigInteger(ir.next());
    }

    // Run
    fibs = fibs(NUM_FIBS);
    BigInteger[] shortestPathLengths = getShortestPathLengths(numQueries, src, dst, order);

    // Output
    OutputWriter ow = new OutputWriter(System.out);
    for (BigInteger shortestPathLength: shortestPathLengths)
      ow.println(shortestPathLength.toString());
    ow.close();
  }

  public static BigInteger[] getShortestPathLengths(int numQueries, BigInteger[] src,
      BigInteger[] dst, int order) {
    BigInteger[] shortestPathLengths = new BigInteger[numQueries];
    for (int i = 0; i < numQueries; i++)
      shortestPathLengths[i] = getShortestPathLength(src[i], dst[i], order);

    return shortestPathLengths;
  }

  public static BigInteger getShortestPathLength(BigInteger src, BigInteger dst, int order) {
    // If src is dst, the distance is zero
    if (src.equals(dst))
      return BigInteger.ZERO;
    // Ensure src is less than or equal to dst
    if (dst.compareTo(src) < 0)
      return getShortestPathLength(dst, src, order);

    // Find nearest fibonacci numbers to src and dst to find nearby references to one
    BigInteger[] nearestFibsFromSrc = nearestFibs(src);
    BigInteger[] nearestRefsToOneFromSrc = new BigInteger[2];
    nearestRefsToOneFromSrc[0] = nearestFibsFromSrc[0].add(BigInteger.ONE);
    if (nearestFibsFromSrc.length == 3)
      nearestRefsToOneFromSrc[1] = src.add(BigInteger.ONE);
    else
      nearestRefsToOneFromSrc[1] = nearestFibsFromSrc[1].add(BigInteger.ONE);
    BigInteger[] nearestFibsFromDst = nearestFibs(dst);
    BigInteger[] nearestRefsToOneFromDst = new BigInteger[2];
    nearestRefsToOneFromDst[0] = nearestFibsFromDst[0].add(BigInteger.ONE);
    if (nearestFibsFromDst.length == 3)
      nearestRefsToOneFromDst[1] = dst.add(BigInteger.ONE);
    else
      nearestRefsToOneFromDst[1] = nearestFibsFromDst[1].add(BigInteger.ONE);
    boolean srcIsRefToOne = !src.equals(BigInteger.ONE) &&
        src.equals(nearestFibsFromSrc[0].add(BigInteger.ONE));
    boolean dstIsRefToOne = !dst.equals(BigInteger.ONE) &&
        dst.equals(nearestFibsFromDst[0].add(BigInteger.ONE));

    // If src is one, computations are simplified.
    if (src.equals(BigInteger.ONE)) {
      if (dstIsRefToOne) {
        return BigInteger.ONE;
      } else {
        BigInteger spDstToLowerRefToOne = getShortestPathLength(
            nearestRefsToOneFromDst[0], dst, order);
        if (!dst.equals(fibs[order + 2])) {
          BigInteger spDstToHigherRefToOne = getShortestPathLength(
              dst, nearestRefsToOneFromDst[1], order);
          return spDstToLowerRefToOne.min(spDstToHigherRefToOne).add(BigInteger.ONE);
        } else {
          return spDstToLowerRefToOne.add(BigInteger.ONE);
        }
      }
    }

    // If src and dst are in different "subgraphs"
    if (src.compareTo(nearestRefsToOneFromDst[0]) < 0) {
      // Sequential path in the worst case
      BigInteger sp = dst.subtract(src);
      if (!dst.equals(fibs[order + 2])) {
        // Find shortest path from src to dst that goes through one
        BigInteger spSrcToOne;
        if (srcIsRefToOne)
          spSrcToOne = BigInteger.ONE;
        else {
          BigInteger spSrcToLowerRefToOne = getShortestPathLength(nearestRefsToOneFromSrc[0], src, order);
          BigInteger spSrcToHigherRefToOne = getShortestPathLength(
              src, nearestRefsToOneFromSrc[1].subtract(BigInteger.ONE), order).add(BigInteger.ONE);
          spSrcToOne = spSrcToLowerRefToOne.min(spSrcToHigherRefToOne).add(BigInteger.ONE);
        }

        BigInteger spDstToOne;
        if (dstIsRefToOne)
          spDstToOne = BigInteger.ONE;
        else {
          BigInteger spDstToLowerRefToOne = getShortestPathLength(nearestRefsToOneFromDst[0], dst, order);
          if (!dst.equals(fibs[order + 2])) {
            BigInteger spDstToHigherRefToOne = getShortestPathLength(
                dst, nearestRefsToOneFromDst[1].subtract(BigInteger.ONE), order).add(BigInteger.ONE);
            spDstToOne = spDstToLowerRefToOne.min(spDstToHigherRefToOne).add(BigInteger.ONE);
          } else {
            spDstToOne = spDstToLowerRefToOne;
          }
        }

        sp = spSrcToOne.add(spDstToOne);
      }

      // Find shortest path from src to dst that doesn't go through one if it might be shorter
      BigInteger spSrcToHigherRef = getShortestPathLength(
          src, nearestRefsToOneFromSrc[1].subtract(BigInteger.ONE), order).add(BigInteger.ONE);
      BigInteger spHigherRefToDst = getShortestPathLength(nearestRefsToOneFromSrc[1], dst, order);
      sp = sp.min(spSrcToHigherRef.add(spHigherRefToDst));
      
      return sp;
    }
   
    // Reduce graph if src and dst are in same graph
    return getShortestPathLength(src.subtract(nearestFibsFromDst[0]),
        dst.subtract(nearestFibsFromDst[0]), order - 2);
  }

  private static BigInteger[] nearestFibs(BigInteger target) {
    BigInteger[] range;
    int index = Arrays.binarySearch(fibs, target);
    if (index < 0) {
      range = new BigInteger[2];
      range[0] = fibs[-index - 2];
      range[1] = fibs[-index - 1];
    } else {
      range = new BigInteger[3];
      range[0] = fibs[index - 1];
      range[1] = fibs[index];
      range[2] = fibs[index + 1];
    }

    return range;
  }

  public static boolean isFib(BigInteger n) {
    BigInteger fiveNSquare = n.shiftLeft(1).multiply(new BigInteger("5"));
    BigInteger four = new BigInteger("4");
    BigInteger fiveNSquarePlusFour = fiveNSquare.add(four);
    BigInteger fiveNSquareMinusFour = fiveNSquare.subtract(four);

    return sqrt(fiveNSquarePlusFour).shiftLeft(1).equals(fiveNSquarePlusFour) ||
        sqrt(fiveNSquareMinusFour).shiftLeft(1).equals(fiveNSquareMinusFour);
  }

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

  public OutputWriter(Writer writer) {
    this.writer = new PrintWriter(writer);
  }

  public void println(String s) {
    writer.println(s);
  }

  public void println(int x) {
    writer.println(x);
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

