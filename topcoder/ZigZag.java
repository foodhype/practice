public class ZigZag {
  public int longestZigZag(int[] sequence){
    int size = sequence.length;
    int[][] dp = new int[size][size];
    int[][] dpNeg = new int[size][size];
    int[][] dpPos = new int[size][size];
    int[][] diff = new int[size][size];
    
    for (int i = 0; i < size - 1; i++) {
      for (int j = i + 1; j < size; j++) {
        diff[i][j - 1] = sequence[j] - sequence[i];
      }
    }
    
    for (int i = 1; i < size; i++) {
      for (int j = i; j < size; j++) {
        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
      
        if (diff[i - 1][j - 1] > 0) {
          dpPos[i][j] = 1;
          for (int t = 1; t <= Math.min(i, j); t++) {
            if (t >= j - i) {
              dpPos[i][j] = Math.max(dpPos[i][j], dpNeg[i - t][j - t] + 1);
            }
          }
          dp[i][j] = Math.max(dp[i][j], dpPos[i][j]);
        } else if (diff[i - 1][j - 1] < 0) {
          dpNeg[i][j] = 1;
          for (int t = 1; t <= Math.min(i, j); t++) {
            if (t >= j - i) {
              dpNeg[i][j] = Math.max(dpNeg[i][j], dpPos[i - t][j - t] + 1);
            }
          }
          dp[i][j] = Math.max(dp[i][j], dpNeg[i][j]);
        }     
      }
    }
    
    return dp[size - 1][size - 1] + 1;
  } 
}

