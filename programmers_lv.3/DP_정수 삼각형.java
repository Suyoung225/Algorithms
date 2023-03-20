import java.util.Arrays;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] DP = new int[n][n];
        DP[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if(j == 0)
                    DP[i][j] = DP[i-1][j] + triangle[i][j];
                else if(j == i)
                    DP[i][j] = DP[i-1][j-1] + triangle[i][j];
                else
                    DP[i][j] = Math.max(DP[i-1][j], DP[i-1][j-1]) + triangle[i][j];
            }
        }
        return Arrays.stream(DP[n-1]).max().getAsInt();
    }
}
