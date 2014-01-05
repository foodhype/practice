public class AvoidRoads {
  public long numWays(int width, int height, String[] bad) {
    int rows = height;
    int cols = width;
  
    long[][] dp = new long[rows + 2][cols + 2];
    boolean[][] downBlocked = new boolean[rows + 1][cols + 1];
    boolean[][] rightBlocked = new boolean[rows + 1][cols + 1];
    
    for (String b: bad) {
      String[] p = b.split(" ");
      int[] untraverseable = {Integer.parseInt(p[0]), Integer.parseInt(p[1]),
          Integer.parseInt(p[2]), Integer.parseInt(p[3])};
      int row1 = untraverseable[1];
      int col1 = untraverseable[0];
      int row2 = untraverseable[3];
      int col2 = untraverseable[2];
      if (row1 == row2) {
        rightBlocked[row1][Math.min(col1, col2)] = true;
      } else {
        downBlocked[Math.min(row1, row2)][col1] = true;
      }
    }
    
    for (int i = 1; i <= rows + 1; i++) {
      dp[i][1] = 1;
      if (downBlocked[i - 1][0]) {
        break;
      }
    }
    
    for (int j = 1; j <= cols + 1; j++) {
      dp[1][j] = 1;
      if (rightBlocked[0][j - 1]) {
        break;
      }
    }
    
    for (int i = 2; i <= rows + 1; i++) {
      for (int j = 2; j <= cols + 1; j++) {
        if (!downBlocked[i - 2][j - 1]) {
          dp[i][j] += dp[i - 1][j];
        }
        if (!rightBlocked[i - 1][j - 2]) {
          dp[i][j] += dp[i][j - 1];
        }
      }
    }
    
    return dp[rows + 1][cols + 1];
  }
}
