import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        ArrayList<ArrayList<Integer>> instances =
                new ArrayList<ArrayList<Integer>>();

        while (!(n == 0 && m == 0)) {
            ArrayList<Integer> results = new ArrayList<Integer>();
            instances.add(results);
            ArrayList<ArrayList<Integer>> graph =
                    new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<Integer>()); 
            }

            int[] indegree = new int[n + 1];
            for (int i = 0; i < m; i++) {
                int taskA = ir.nextInt();
                int taskB = ir.nextInt();
                graph.get(taskA).add(taskB);
                indegree[taskB]++;
            }

            Queue<Integer> queue = new LinkedList<Integer>();
            for (int i = 1; i <= n; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                results.add(current);

                for (int neighbor: graph.get(current)) {
                    indegree[neighbor]--;

                    if (indegree[neighbor] == 0) {
                        queue.add(neighbor);
                    }
                } 
            }

            n = ir.nextInt();
            m = ir.nextInt();
        }

        OutputWriter ow = new OutputWriter(System.out);

        for (ArrayList<Integer> results: instances) {
            for (int i = 0; i < results.size(); i++) {
                if (i == 0) {
                    ow.print(results.get(i));
                } else {
                    ow.printSpace();
                    ow.print(results.get(i));
                }
            }
            ow.println("");
        }

        ow.close();
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

