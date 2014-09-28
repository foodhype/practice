import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();

        HashMap<Integer, HashMap<Integer, Integer>> graph =
                new HashMap<Integer, HashMap<Integer, Integer>>();
        for (int i = 0; i < m; i++) {
            int a = ir.nextInt();
            int b = ir.nextInt();
            int w = ir.nextInt();

            if (graph.containsKey(a)) {
                graph.get(a).put(b, w);
            } else {
                HashMap<Integer, Integer> adjList =
                        new HashMap<Integer, Integer>();
                adjList.put(b, w);
                graph.put(a, adjList);
            }

            if (graph.containsKey(b)) {
                graph.get(b).put(a, w);
            } else {
                HashMap<Integer, Integer> adjList =
                        new HashMap<Integer, Integer>();
                adjList.put(a, w);
                graph.put(b, adjList);
            }
        }

        int[] result = Solution.solve(graph, n);
        OutputWriter ow = new OutputWriter(System.out);
        if (result == null) {
            ow.println(-1);
        } else {
            if (result.length == 1) {
                ow.println(result[0]);
            } else {
                ow.print(result[0]);
                for (int i = 1; i < result.length; i++) {
                    ow.printSpace();
                    ow.print(result[i]);
                }
                ow.println("");
            }
        }

        ow.close();
    }
}


class Vertex implements Comparable<Vertex> {
    public final int id;
    public int minDistance;

    public Vertex(int id, int minDistance) {
        this.id = id;
        this.minDistance = minDistance;
    }

    public int compareTo(Vertex other) {
        return minDistance - other.minDistance;
    }
}


class Solution {
    public static int[] solve(
            HashMap<Integer, HashMap<Integer, Integer>> graph, int n) {
        Integer[] dist = new Integer[n + 1];
        Integer[] prev = new Integer[n + 1];
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
        dist[1] = 0;
        for (int i = 1; i <= n; i++) {
            if (i != 1) {
                dist[i] = Integer.MAX_VALUE;
                prev[i] = null;
            }
            queue.add(new Vertex(i, dist[i]));
        }

        HashSet<Integer> visited = new HashSet<Integer>();
        
        while (queue.size() > 0) {
            Vertex current = queue.poll();
            visited.add(current.id);

            HashMap<Integer, Integer> neighborEdges = graph.get(current.id);
            if (neighborEdges != null) {
                for (int neighbor: neighborEdges.keySet()) {
                    int weight = neighborEdges.get(neighbor);

                    if (!visited.contains(neighbor)) {
                        int distance = dist[current.id] + weight;

                        if (distance < dist[neighbor]) {
                            queue.remove(new Vertex(neighbor, dist[neighbor]));
                            dist[neighbor] = distance;
                            prev[neighbor] = current.id;
                            queue.add(new Vertex(neighbor, dist[neighbor]));
                        }
                    }
                }
            }
        }

        if (dist[n] == Integer.MAX_VALUE) {
            return null;
        } else {
            Deque<Integer> stack = new ArrayDeque<Integer>();
            Integer current = n;
            while (current != null) {
                stack.push(current);
                current = prev[current];
            }
            int[] result = new int[stack.size()];
            int index = 0;
            while (stack.size() > 0) {
                result[index] = stack.pop();
                index++;
            }

            if (result[0] == 1) {
                return result;
            } else {
                return null;
            }
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

