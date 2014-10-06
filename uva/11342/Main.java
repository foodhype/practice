import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        int precomputed[][] = Solution.precompute();

        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int results[][] = new int[n][3];
        for (int i = 0; i < n; i++) {
            int k = ir.nextInt();
            results[i] = precomputed[k];
        }

        OutputWriter ow = new OutputWriter(System.out);

        for (int i = 0; i < n; i++) {
            if (results[i] == null) {
                ow.println(-1);
            } else {
                ow.print(results[i][0]);
                ow.printSpace();
                ow.print(results[i][1]);
                ow.printSpace();
                ow.println(results[i][2]);
            }
        }

        ow.close();
    }
}


class Solution {
    public static int[][] precompute() {
        int precomputed[][] = new int[50001][3];
        int max = (int) Math.ceil(Math.sqrt(50000));
        int[] squareCache = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            squareCache[i] = i*i;
        }

        for (int i = 0; i < squareCache.length; i++) {
            int square1 = squareCache[i];
            for (int j = i; j < squareCache.length; j++) {
                int square2 = squareCache[j];
                for (int k = j; k < squareCache.length; k++) {
                    int square3 = squareCache[k];

                    int a = (int) Math.sqrt(square1);
                    int b = (int) Math.sqrt(square2);
                    int c = (int) Math.sqrt(square3);

                    if (square1 + square2 + square3 <= 50000 &&
                            precomputed[square1 + square2 + square3][0] == 0 &&
                            precomputed[square1 + square2 + square3][1] == 0 &&
                            precomputed[square1 + square2 + square3][2] == 0) {
                        precomputed[square1 + square2 + square3] = new int[]{a, b, c};

                    }
                }
            }
        }

        for (int i = 1; i < precomputed.length; i++) {
            if (precomputed[i][0] == 0 &&
                    precomputed[i][1] == 0 &&
                    precomputed[i][2] == 0) {
                precomputed[i] = null;
            }
        }

        return precomputed;
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

