# 剑指Offer 013.二维子矩阵的和

给定一个二维矩阵`matrix`，以下类型的多个请求：

- 计算其子矩阵范围内元素的总和，该子矩阵左上角为`(row1,col1)`，右下角为`(row2,col2)`。

实现`NumMatrix`类

- `NumMatix(int[][] matrix)`给定整数矩阵`matrix`进行初始化

- `int sumRegion(int row1, int col1, int row2, int col2)`返回左上角`(row1,col1)`、右下角`(row2,col2)`的子矩阵的元素总和。

2022/4/20 第一次刷

初见思路：存储二维矩阵的前缀和，然后根据前缀和返回子矩阵的元素总和

代码如下

```java
class NumMatrix {

    int[][] dp;

    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        dp = new int[n][m];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                if (i == 0 && j == 0){
                    dp[i][j] = matrix[i][j];
                } else if (i == 0){
                    dp[i][j] = matrix[i][j] + dp[i][j-1];
                } else if (j == 0){
                    dp[i][j] = matrix[i][j] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0){
            return dp[row2][col2];
        } else if (row1 == 0){
            return dp[row2][col2] - dp[row2][col1-1];
        } else if (col1 == 0){
            return dp[row2][col2] - dp[row1-1][col2];
        } else {
            return dp[row2][col2] - dp[row1-1][col2] - dp[row2][col1-1] + dp[row1-1][col1-1];
        }
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```

结果：通过！时间复杂度：初始化O(mn)，检索O(1)，空间复杂度O(mn)

代码有些复杂，对其进行优化，将dp数组行列均扩大1，然后循环时从1开始计数，就不需要考虑数组index<0的情况了。

优化后代码如下：

```java
class NumMatrix {

    int[][] dp;

    public NumMatrix(int[][] matrix) {
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

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
```

结果：通过！
