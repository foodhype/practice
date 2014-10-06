import java.io.*;
import java.util.*;


public class Main {
    // Sudoku solver
    public static void main(String args[]) {
        InputReader ir = new InputReader(System.in);
        int n = 0;


        OutputWriter ow = new OutputWriter(System.out);

        int testCase = 0;
        while (true) {
            try {
                n = ir.nextInt();
            } catch (InputMismatchException e) {
                break;
            }

            int[][] grid = new int[n*n][n*n];
            for (int i = 0; i < n*n; i++) {
                for (int j = 0; j < n*n; j++) {
                    grid[i][j] = ir.nextInt();
                }
            }

            if (testCase != 0) {
                ow.println("");
            }
            if (Solution.solve(grid, 0, 0, n)) {
                for (int i = 0; i < n*n; i++) {
                    for (int j = 0; j < n*n; j++) {
                        if (j == 0) {
                            ow.print(grid[i][j]);
                        } else {
                            ow.printSpace();
                            ow.print(grid[i][j]);
                        }
                    }
                    ow.println("");
                }
            } else {
                ow.println("NO SOLUTION");
            }

            testCase++;
        }

        ow.close();
    }
}


class Solution {
    public static boolean solve(int[][] grid, int r, int c, int n) {
        if (r >= n*n) {
            return true;
        } else if (grid[r][c] == 0) {
            boolean[] visited_in_row = new boolean[n*n+1];
            boolean[] visited_in_column = new boolean[n*n+1];
            boolean[] visited_in_subgrid = new boolean[n*n+1];

            for (int i = 0; i < n*n; i++) {
                if (grid[r][i] != 0) {
                    visited_in_row[grid[r][i]] = true;
                }
                if (grid[i][c] != 0) {
                    visited_in_row[grid[i][c]] = true;
                }
            }

            int subgridStartRow = r - (r % n);
            int subgridStartCol = c - (c % n);
            for (int i = subgridStartRow; i < subgridStartRow + n; i++) {
                for (int j = subgridStartCol; j < subgridStartCol + n; j++) {
                    if (grid[i][j] != 0) {
                        visited_in_subgrid[grid[i][j]] = true;
                    }
                }
            }

            for (int k = 1; k <= n*n; k++) {
                if (!visited_in_row[k] &&
                        !visited_in_column[k] &&
                        !visited_in_subgrid[k]) {

                    int nextRow = r;
                    int nextCol = c + 1;
                    if (nextCol == n*n) {
                        nextRow++;
                        nextCol = 0;
                    }

                    grid[r][c] = k;
                    if (solve(grid, nextRow, nextCol, n)) {
                        return true;
                    } else {
                        grid[r][c] = 0;
                    }
                }
            }

        } else {
            int nextRow = r;
            int nextCol = c + 1;
            if (nextCol == n*n) {
                nextRow++;
                nextCol = 0;
            }

            return solve(grid, nextRow, nextCol, n);
        }


        return false;
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

