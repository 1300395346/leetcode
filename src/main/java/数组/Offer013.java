package 数组;

public class Offer013 {
    int[][] dp;
    public static void main(String[] args) {
        int[][]  matrix = {{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        Offer013 offer013 = new Offer013(matrix);
        int ans = offer013.sumRegion(2,1,4,3);
        System.out.println(ans);
    }

    public Offer013(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        dp = new int[n+1][m+1];
        for (int i = 1; i <= n; ++i){
            for (int j = 1; j <= m; ++j){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] - dp[row2+1][col1] - dp[row1][col2+1] + dp[row1][col1];
    }
}
