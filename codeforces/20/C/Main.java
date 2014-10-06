import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        ArrayList<HashMap<Integer, Integer>> graph = new ArrayList<HashMap<Integer, Integer>>(n);
        for (int i = 0; i <= n; i++) {
            graph.add(new HashMap<Integer, Integer>());
        }

        for (int i = 0; i < m; i++) {
            int a = ir.nextInt();
            int b = ir.nextInt();
            int w = ir.nextInt();
            graph.get(a).put(b, w);
            graph.get(b).put(a, w);
        }

        Solution.solve(graph, n);
    }
}

/*
class Vertex implements Comparable<Vertex> {
    public final int id;
    public final int minDistance;

    public Vertex(int id, int minDistance) {
        this.id = id;
        this.minDistance = minDistance;
    }

    public int compareTo(Vertex other) {
        return minDistance - other.minDistance;
    }
}
*/

class Solution {
    public static void solve(
            ArrayList<HashMap<Integer, Integer>> graph, int n) {
        final int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];
        TreeSet<Integer> queue = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (dist[a] < dist[b]) {
                    return -1;
                }
                if (dist[a] > dist[b]) {
                    return 1;
                }
                return a - b;
            }
        });

        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            if (i != 1) {
                dist[i] = Integer.MAX_VALUE;
            }
            prev[i] = -1;
            queue.add(i);
        }

        boolean[] visited = new boolean[n + 1];        

        while (queue.size() > 0) {
            int current = queue.pollFirst();
            visited[current] = true;

            HashMap<Integer, Integer> neighborEdges = graph.get(current);
            
            if (neighborEdges.keySet().size() > 0) {
                for (int neighbor: neighborEdges.keySet()) {
                    int weight = neighborEdges.get(neighbor);
                    if (!visited[neighbor]) {
                        int distance = dist[current] + weight;

                        if (distance < dist[neighbor]) {
                            queue.remove(neighbor);
                            dist[neighbor] = distance;
                            prev[neighbor] = current;
                            queue.add(neighbor);
                        }
                    }
                }
            }
        }

        if (dist[n] == Integer.MAX_VALUE) {
            System.out.println("" + -1);
        } else {
            ArrayList<Integer> result = new ArrayList<Integer>();
            int current = n;
            while (current != -1) {
                result.add(current);
                current = prev[current];
            }

            OutputWriter ow = new OutputWriter(System.out);
            int last = result.size() - 1;
            if (result.get(last) == 1) {
                if (last == 0) {
                    ow.println(result.get(0));
                } else {
                    ow.print(result.get(last));
                    for (int i = last - 1; i >= 0; i--) {
                        ow.printSpace();
                        ow.print(result.get(i));
                    }
                    ow.println("");
                }
            } else {
                ow.println(-1);
            }
            ow.close();
        }
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

