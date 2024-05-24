package Programmers.Algorithm;

class 등굣길 {

    static int[][] map;
    static class Node {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int solution(int m, int n, int[][] puddles) {

        map = new int[m + 1][n + 1];
        for (int[] puddle : puddles) {
            map[puddle[0]][puddle[1]] = -1;
        }

        return calculate(m, n);
    }

    public int calculate(int m, int n) {

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            if (map[1][i] == -1) break;
            dp[1][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            if (map[i][1] == -1) break;
            dp[i][1] = 1;
        }

        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                if (map[i][j] == -1) continue;
                dp[i][j] += (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }

        return dp[m][n];
    }

}
