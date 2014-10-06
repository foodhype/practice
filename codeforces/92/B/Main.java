import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        String binString = ir.next();

        OutputWriter ow = new OutputWriter(System.out);

        ow.println(Solution.solve(binString.toCharArray()));

        ow.close();
    }
}


class Solution {
    public static int solve(char[] binString) {
        int actions = 0;
        for (int i = binString.length - 1; i >= 0; i--) {
            /*System.out.println("BEGIN LOOP");
            System.out.println("binString: " + new String(binString));
            System.out.println("i: " + i);
            System.out.println("actions: " + actions);
            System.out.println();*/
            if (i == 0 && binString[i] == '1') {
                break;
            } else if (binString[i] == '0') {
                actions++;
                //System.out.println("EVEN: DIVIDE BY TWO");
            } else {
                actions++;
                //System.out.println("ODD: ADD ONE");
                int j = i;
                i++;
                while (j >= 0 && binString[j] == '1') {
                    binString[j] = '0';
                    j--;
                }

                if (j >= 0) {
                    binString[j] = '1';
                }
            }
        }
/*
        System.out.println();
        System.out.println("binString: " + new String(binString));
        System.out.println("actions: " + actions);
        System.out.println();
*/
        return actions;
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

