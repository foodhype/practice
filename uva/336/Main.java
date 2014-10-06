import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);
        OutputWriter ow = new OutputWriter(System.out);
        int t = 1;
        int nc = ir.nextInt();
        while (nc != 0) {
            HashMap<Integer, HashSet<Integer>> network =
                    new HashMap<Integer, HashSet<Integer>>();
            for (int i = 0; i < nc; i++) {
                int node1 = ir.nextInt();
                int node2 = ir.nextInt();
                if (network.containsKey(node1)) {
                    network.get(node1).add(node2);
                } else {
                    HashSet<Integer> adjList = new HashSet<Integer>();
                    adjList.add(node2);
                    network.put(node1, adjList);
                }

                if (network.containsKey(node2)) {
                    network.get(node2).add(node1);
                } else {
                    HashSet<Integer> adjList = new HashSet<Integer>();
                    adjList.add(node1);
                    network.put(node2, adjList);
                }
            }

            int start = ir.nextInt();
            int ttl = ir.nextInt();
            while (!(start == 0 && ttl == 0)) {

                int result = Solution.solve(network, start, ttl, nc);

                ow.println("Case " + t + ": " + result + " nodes not reachable from node " + start + " with TTL = " + ttl + ".");

                start = ir.nextInt();
                ttl = ir.nextInt();
                t++;
            }

            nc = ir.nextInt();
        }

        ow.close();
    }
}


class Solution {
    public static int solve(HashMap<Integer, HashSet<Integer>> network,
            int start,
            int ttl,
            int nc) {
        HashMap<Integer, Integer> distances = new HashMap<Integer, Integer>();
        for (int key: network.keySet()) {
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> visited = new HashSet<Integer>();
        visited.add(start);
        queue.add(start);

        while (queue.size() > 0) {
            int current = queue.poll();

            for (int neighbor: network.get(current)) {
                if (!visited.contains(neighbor)) {
                    distances.put(neighbor, distances.get(current) + 1);
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        int reachCount = 0;
        for (int node: distances.keySet()) {
            if (distances.get(node) <= ttl) {
                reachCount += 1;
            }
        }

        return network.keySet().size() - reachCount;
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
        if (numChars == -1) {
            throw new InputMismatchException();
        }
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0) {
                return -1;
            }
        }

        return buf[curChar++];
    }

    public String readLine() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (c != '\n' && c != '\r' && c != -1);

        return res.toString();
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9') {
                throw new InputMismatchException();
            }
            res *= 10;
            res += c & 15;
            c = read();
        } while (!isSpaceChar(c));

        return res * sgn;
    }

    public String next() {
        int c = read();
        while (isSpaceChar(c)) {
            c = read();
        }
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

