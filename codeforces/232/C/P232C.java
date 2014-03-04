import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.InputMismatchException;

public class P232C {
  public static void main(String args[]) {
    stressTest();
/*    // Input
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
    DoeGraphSimulation doeGraphSim = new DoeGraphSimulation();
    BigInteger[] shortestPathLengths = doeGraphSim.getShortestPathLengths(
        numQueries, src, dst, order);

    // Output
    OutputWriter ow = new OutputWriter(System.out);
    for (BigInteger shortestPathLength: shortestPathLengths)
      ow.println(shortestPathLength.toString());
    ow.close();
    */
  }

  public static void stressTest() {
    BridgeCache b = new BridgeCache(1000);
    DoeGraphSimulation d = new DoeGraphSimulation();
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("1"), 7, new BigInteger("35")).equals(new BigInteger("0"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("2"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("3"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("4"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("5"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("6"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("7"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("8"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("9"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("10"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("11"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("12"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("13"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("14"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("15"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("16"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("17"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("18"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("19"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("20"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("21"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("22"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("23"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("24"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("25"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("26"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("27"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("28"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("29"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("30"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("31"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("32"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("33"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("1"), new BigInteger("34"), 7, new BigInteger("35")).equals(new BigInteger("4"));

    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("1"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("2"), 7, new BigInteger("35")).equals(new BigInteger("0"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("3"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("4"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("5"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("6"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("7"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("8"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("9"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("10"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("11"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("12"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("13"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("14"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("15"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("16"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("17"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("18"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("19"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("20"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("21"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("22"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("23"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("24"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("25"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("26"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("27"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("28"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("29"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("30"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("31"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("32"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("33"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("2"), new BigInteger("34"), 7, new BigInteger("35")).equals(new BigInteger("5"));

    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("1"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("2"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("3"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("4"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("5"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("6"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("7"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("8"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("9"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("10"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("11"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("12"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("13"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("14"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("15"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("16"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("17"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("18"), 7, new BigInteger("35")).equals(new BigInteger("6"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("19"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("20"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("21"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("22"), 7, new BigInteger("35")).equals(new BigInteger("3"));

    System.out.println("sp: " + d.getShortestPathLength(new BigInteger("34"), new BigInteger("23"), 7, new BigInteger("35")));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("23"), 7, new BigInteger("35")).equals(new BigInteger("4"));


    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("24"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("25"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("26"), 7, new BigInteger("35")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("27"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("28"), 7, new BigInteger("35")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("29"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("30"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("31"), 7, new BigInteger("35")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("32"), 7, new BigInteger("35")).equals(new BigInteger("2"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("33"), 7, new BigInteger("35")).equals(new BigInteger("1"));
    assert d.getShortestPathLength(new BigInteger("34"), new BigInteger("34"), 7, new BigInteger("35")).equals(new BigInteger("0"));

    assert d.getShortestPathLength(new BigInteger("5"), new BigInteger("42"), 8, new BigInteger("56")).equals(new BigInteger("5"));
    assert d.getShortestPathLength(new BigInteger("47"), new BigInteger("34"), 8, new BigInteger("56")).equals(new BigInteger("3"));
    assert d.getShortestPathLength(new BigInteger("13"), new BigInteger("34"), 8, new BigInteger("56")).equals(new BigInteger("4"));
    assert d.getShortestPathLength(new BigInteger("35"), new BigInteger("55"), 8, new BigInteger("56")).equals(new BigInteger("3"));


    assert d.getShortestPathLength(new BigInteger("6965972159099695"), new BigInteger("6844219427569504"), 1000, b.max(1000)).equals(new BigInteger("29"));

/*    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    assert d.getShortestPathLength(new BigInteger(""), new BigInteger(""), 8).equals(new BigInteger(""));
    */
  }
}

class DoeGraphSimulation {
  private static final int BRIDGE_CACHE_SIZE = 1000;
  private BridgeCache bridgeCache;
  private ShortestPathCache spCache;

  public DoeGraphSimulation(){
    bridgeCache = new BridgeCache(BRIDGE_CACHE_SIZE);
    spCache = new ShortestPathCache();
  }

  public BigInteger[] getShortestPathLengths(int numQueries, BigInteger[] src,
      BigInteger[] dst, int order) {
    BigInteger[] shortestPathLengths = new BigInteger[numQueries];
    for (int i = 0; i < numQueries; i++)
      shortestPathLengths[i] = getShortestPathLength(src[i], dst[i], order, bridgeCache.max(order));

    return shortestPathLengths;
  }

  public BigInteger getShortestPathLength(BigInteger src, BigInteger dst, int order, BigInteger max) {
    if (src.compareTo(max) >= 0 || dst.compareTo(max) >= 0)
      return max;
    // If src is dst, the distance is zero
    if (src.equals(dst))
        return BigInteger.ZERO;
    // Ensure src is less than or equal to dst
    if (dst.compareTo(src) < 0)
      return getShortestPathLength(dst, src, order, max);
    if (dst.subtract(src).equals(BigInteger.ONE))
      return BigInteger.ONE;

    System.out.println();
    System.out.println("src: " + src);
    System.out.println("dst: " + dst);
    System.out.println("order: " + order);

    // Find nearest numbers bridging to one from src and dst
    ArrayList<BigInteger> nearestBridgesFromSrc = bridgeCache.nearestBridges(src, order);
    ArrayList<BigInteger> nearestBridgesFromDst = bridgeCache.nearestBridges(dst, order);
    boolean srcIsBridge = nearestBridgesFromSrc.contains(src);
    boolean dstIsBridge = nearestBridgesFromDst.contains(dst);

    // If src is one, computations are simplified.
    if (src.equals(BigInteger.ONE)) {
      if (dstIsBridge) {
        return BigInteger.ONE;
      } else {
        BigInteger spDstToBridge = getShortestPathLength(
            nearestBridgesFromDst.get(0), dst, order, max);
        if (nearestBridgesFromDst.size() == 2)
          spDstToBridge = spDstToBridge.min(getShortestPathLength(
              nearestBridgesFromDst.get(1), dst, order, max));
        return spDstToBridge.add(BigInteger.ONE);
      }
    }

    // If src and dst are in different "subgraphs"
    if (nearestBridgesFromDst.size() > 0 && src.compareTo(nearestBridgesFromDst.get(0)) < 0) {
      // Sequential path in the worst case
      BigInteger sp = dst.subtract(src);

      // Find shortest path from src to dst that goes through one
      BigInteger spSrcToBridge = BigInteger.ZERO;
      if (!srcIsBridge && nearestBridgesFromSrc.size() > 0) {
        spSrcToBridge = getShortestPathLength(nearestBridgesFromSrc.get(0), src, order, max);
        if (nearestBridgesFromSrc.size() == 2) {
          spSrcToBridge = spSrcToBridge.min(getShortestPathLength(
              src, nearestBridgesFromSrc.get(1).subtract(BigInteger.ONE), order, max).add(BigInteger.ONE));
        }
      }

      BigInteger spDstToBridge = BigInteger.ZERO;
      if (!dstIsBridge && nearestBridgesFromDst.size() > 0) {
        spDstToBridge = getShortestPathLength(nearestBridgesFromDst.get(0), dst, order, max);
        if (nearestBridgesFromDst.size() == 2) {
          spDstToBridge = spDstToBridge.min(getShortestPathLength(
                dst, nearestBridgesFromDst.get(1).subtract(BigInteger.ONE), order, max).add(BigInteger.ONE));
        }
      }

      BigInteger spSrcToOne = spSrcToBridge.add(BigInteger.ONE);
      BigInteger spDstToOne = spDstToBridge.add(BigInteger.ONE);
      sp = sp.min(spSrcToOne.add(spDstToOne));

      // Find shortest path from src to dst that doesn't go through one if it might be shorter
      BigInteger spSrcToHigherBridge = getShortestPathLength(src, nearestBridgesFromSrc.get(
            nearestBridgesFromSrc.size() - 1).subtract(BigInteger.ONE), order, max).add(BigInteger.ONE);
      BigInteger spHigherBridgeToDst = getShortestPathLength(nearestBridgesFromSrc.get(
            nearestBridgesFromSrc.size() - 1), dst, order, max);
      sp = sp.min(spSrcToHigherBridge.add(spHigherBridgeToDst));
      
      return sp;
    }

    src = src.subtract(nearestBridgesFromDst.get(0).subtract(BigInteger.ONE));
    dst = dst.subtract(nearestBridgesFromDst.get(0).subtract(BigInteger.ONE));
    max = max.subtract(nearestBridgesFromDst.get(0).subtract(BigInteger.ONE));

    /* Reduce shortest path problem to subproblem if src and dst are in the same subgraph by 
       treating the subgraph as a graph (if subproblem result hasn't already been cached). */
    if (spCache.containsCall(src, dst, order)) {
      return spCache.get(src, dst, order);
    } else {
      BigInteger sp = getShortestPathLength(src, dst, order, max);
      spCache.put(src, dst, order, sp);
      return sp;
    }
  }
}

class BridgeCache {
  private int size;
  private BigInteger[] bridgeSeq;
  private HashSet<BigInteger> bridgeSet;

  public BridgeCache(int size) {
    this.size = size;
    bridgeSeq = new BigInteger[size];
    bridgeSet = new HashSet<BigInteger>(size);
    init(size);
  }

  private void init(int n) {
    bridgeSeq = new BigInteger[n];
    BigInteger a = new BigInteger("1");
    BigInteger b = new BigInteger("2");
    for (int i = 0; i < n; i++) {
      bridgeSeq[i] = a.add(BigInteger.ONE);
      bridgeSet.add(a.add(BigInteger.ONE));
      BigInteger temp = a.add(b);
      a = b;
      b = temp;
    }

  }

  public BigInteger max(int order) {
    BigInteger a = new BigInteger("1");
    BigInteger b = new BigInteger("2");
    for (int i = 0; i < order; i++) {
      BigInteger temp = a.add(b);
      a = b;
      b = temp;
    }

    return b;
  }

  private boolean inBounds(BigInteger n, int order) {
    return n.compareTo(BigInteger.ONE) >= 0 && n.compareTo(bridgeSeq[order - 1]) <= 0;
  }

  public boolean containsBridge(BigInteger n, int order) {
    if (inBounds(n, order))
      return bridgeSet.contains(n);
    else
      return false;
  }

  public BigInteger get(int index, int order) {
    if (inBounds(bridgeSeq[index], order))
      return bridgeSeq[index];
    else
      throw new ArrayIndexOutOfBoundsException("Bridge does not exist");
  }

  public ArrayList<BigInteger> nearestBridges(BigInteger target, int order) {
    ArrayList<BigInteger> range = new ArrayList<BigInteger>(3);
    int index = Arrays.binarySearch(bridgeSeq, target);
    if (index < 0) {
      if ((-index - 2) >= 0 && inBounds(bridgeSeq[-index - 2], order))
        range.add(bridgeSeq[-index - 2]);
      if ((-index - 1) >= 0 && inBounds(bridgeSeq[-index - 1], order))
        range.add(bridgeSeq[-index - 1]);
    } else {
      if ((index - 1) >= 0 && inBounds(bridgeSeq[index - 1], order))
        range.add(bridgeSeq[index - 1]);
      if (index >= 0 && inBounds(bridgeSeq[index], order))
        range.add(bridgeSeq[index]);
      if ((index + 1) >= 0 && inBounds(bridgeSeq[index + 1], order))
        range.add(bridgeSeq[index + 1]);
    }

    return range;
  }
}

class ShortestPathCache {
  private HashMap<List<BigInteger>, BigInteger> memo;

  public ShortestPathCache() {
    memo = new HashMap<List<BigInteger>, BigInteger>();
  }

  public boolean containsCall(BigInteger src, BigInteger dst, int order) {
    return memo.containsKey(Arrays.asList(src, dst, BigInteger.valueOf(order)));
  }

  public BigInteger get(BigInteger src, BigInteger dst, Integer order) {
    return memo.get(Arrays.asList(src, dst, BigInteger.valueOf(order)));
  }

  public void put(BigInteger src, BigInteger dst, Integer order, BigInteger sp) {
    memo.put(Arrays.asList(src, dst, BigInteger.valueOf(order)), sp);
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

