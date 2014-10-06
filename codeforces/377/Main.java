import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int k = ir.nextInt();

        char[][] maze = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] row = ir.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                maze[i][j] = row[j];
            }
        }

        Solution.solve(maze, n, m, k);
        OutputWriter ow = new OutputWriter(System.out);
        for (int i = 0; i < n; i++) {
            ow.println(new String(maze[i]));
        }
        ow.close();
    }
}


class Solution {
    private static int N = 0;
    private static int M = 0;
    private static int K = 0;
    private static boolean[][] used;

    public static void solve(char[][] maze, int n, int m, int k) {
        used = new boolean[n][m];
        N = n;
        M = m;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == '.') {
                    count++;
                }
            }
        }

        K = count - k;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (K > 0 && maze[i][j] == '.') {
                    dfs(maze, i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (count > 0 && !used[i][j] && maze[i][j] == '.') {
                    maze[i][j] = 'X';
                    count--;
                }
            }
        }
    }

    public static boolean inBounds(int i, int j) {
        return i >= 0 && j >= 0 && i < N && j < M;
    }

    public static void dfs(char[][] maze, int i, int j) {
        used[i][j] = true;
        K--;
        
        if (K > 0 && inBounds(i - 1, j) && maze[i - 1][j] == '.' && !used[i - 1][j]) {
            dfs(maze, i - 1, j);
        }
        if (K > 0 && inBounds(i, j - 1) && maze[i][j - 1] == '.' && !used[i][j - 1]) {
            dfs(maze, i, j - 1);
        }
        if (K > 0 && inBounds(i + 1, j) && maze[i + 1][j] == '.' && !used[i + 1][j]) {
            dfs(maze, i + 1, j);
        }
        if (K > 0 && inBounds(i, j + 1) && maze[i][j + 1] == '.' && !used[i][j + 1]) {
            dfs(maze, i, j + 1);
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

