import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int x = ir.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = ir.nextInt();
        }

        OutputWriter ow = new OutputWriter(System.out);

        ow.println(Solution.solve(n, x, arr));

        ow.close();
    }
}


class Solution {
    public static int solve(int n, int x, int[] arr) {
        ArrayList<Integer> li = new ArrayList<Integer>();
        Arrays.sort(arr);
        int count = 0;
        int medianIndex = ((n + 1) / 2) - 1;
        int insertionIndex = Arrays.binarySearch(arr, 0, n, x);
        if (insertionIndex >= 0) {
            while (insertionIndex < medianIndex &&
                    arr[insertionIndex] == arr[insertionIndex + 1]) {
                insertionIndex++;
            }
            while (insertionIndex > medianIndex &&
                    arr[insertionIndex] == arr[insertionIndex - 1]) {
                insertionIndex--;
            } 
        } else {
            insertionIndex = -(insertionIndex + 1);
            n++;
            count++;
            medianIndex = ((n + 1) / 2) - 1;
        }

        while (insertionIndex < medianIndex) {
            insertionIndex++;
            n++;
            count++;
            medianIndex = ((n + 1) / 2) - 1;
        }

        while (insertionIndex > medianIndex) {
            n++;
            count++;
            medianIndex = ((n + 1) / 2) - 1;
        }

        return count;
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

