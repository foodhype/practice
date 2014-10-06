import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int t = ir.nextInt();
        int r = ir.nextInt();
        int h = ir.nextInt();

        OutputWriter ow = new OutputWriter(System.out);
        while (true) {
            int[] travelAgencyPrice = new int[t];
            boolean[][] trGraph = new boolean[t][r];
            for (int i = 0; i < t; i++) {
                travelAgencyPrice[i] = ir.nextInt();
                for (int j = 0; j < r; j++) {
                    trGraph[i][j] = ir.nextInt() == 0 ? true : false;
                }
            }

            int[] restaurantPrice = new int[r];
            boolean[][] rhGraph = new boolean[r][h];
            for (int i = 0; i < r; i++) {
                restaurantPrice[i] = ir.nextInt();
                for (int j = 0; j < h; j++) {
                    rhGraph[i][j] = ir.nextInt() == 0 ? true : false;
                }
            }

            int[] hotelPrice = new int[h];
            boolean[][] htGraph = new boolean[h][t];
            for (int i = 0; i < h; i++) {
                hotelPrice[i] = ir.nextInt();
                for (int j = 0; j < t; j++) {
                    htGraph[i][j] = ir.nextInt() == 0 ? true : false;
                }
            }

            int minPrice = Integer.MAX_VALUE;
            int[] result = new int[]{-1, -1, -1};
            for (int i = 0; i < t; i++) {
                int tPrice = travelAgencyPrice[i];
                for (int j = 0; j < r; j++) {
                    if (trGraph[i][j]) {
                        int rPrice = restaurantPrice[j];
                        for (int k = 0; k < h; k++) {
                            if (rhGraph[j][k] && htGraph[k][i]) {
                                int hPrice = hotelPrice[k];
                                if (tPrice + rPrice + hPrice < minPrice) {
                                    minPrice = tPrice + rPrice + hPrice;
                                    result = new int[]{i, j, k};
                                }
                            }
                        }
                    }
                }
            }

            if (minPrice == Integer.MAX_VALUE) {
                ow.println("Don't get married!");
            } else {
                ow.print(result[0]);
                ow.printSpace();
                ow.print(result[1]);
                ow.printSpace();
                ow.print(result[2]);
                ow.print(":");
                ow.println(minPrice);
            }

            try {
                t = ir.nextInt();
                r = ir.nextInt();
                h = ir.nextInt();
            } catch (InputMismatchException e) {
                break;
            }
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

    public void print(int x) {
        writer.print(x);
    }

    public void print(String s) {
        writer.print(s);
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

    public void printSpace() {
        writer.print(" ");
    }

    public void close() {
        writer.close();
    }
}

