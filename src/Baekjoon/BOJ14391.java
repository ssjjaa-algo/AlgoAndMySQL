package Baekjoon;

import java.io.*;

public class BOJ14391 {

    static boolean[][] visited;
    static int[][] arr;
    static int N,M;
    static int ans = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        visited = new boolean[N][M];
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0,0);

        System.out.println(ans);
    }

    private static void dfs(int row, int col) {

        if(row == N) { // 모든 행을 다 탐색했을 경우
            ans = Math.max(ans,calculate());
            return;
        }

        if (col == M) { // 모든 열을 다 탐색했을 경우
            dfs(row + 1, 0);
            return;
        }

        // 가로와 세로의 부분집합의 개수를 구한다.

        visited[row][col] = true; // 방문처리가 되있는 행이 가로로 선택된 숫자들이고
        dfs(row,col + 1);
        visited[row][col] = false; // 방문처리가 되있지 않은 행들이 세로로 선택된 숫자들이다.
        dfs(row,col + 1);

    }

    private static int calculate() {

        return calculateRow() + calculateCol();
    }

    private static int calculateCol() {

        // 모든 가로들의 합을 구한다.
        int sum = 0;
        int segment = 0;
        for (int i=0; i<N; i++) {
            segment = 0;
            for (int j=0; j<M; j++) {
                if (visited[i][j]) {
                    segment = segment * 10;
                    segment = segment + arr[i][j];
                }
                else {
                    sum += segment;
                    segment = 0;
                }
            }
            sum += segment;
        }

        return sum;
    }

    private static int calculateRow() {

        int sum = 0;
        int segment = 0;

        for (int i=0; i<M; i++) {
            segment=0;
            for (int j=0; j<N; j++) {
                if (!visited[j][i]) {
                    segment = segment * 10;
                    segment = segment + arr[j][i];
                }
                else {
                    sum += segment;
                    segment = 0;
                }
            }
            sum += segment;
        }

        return sum;
    }
}
