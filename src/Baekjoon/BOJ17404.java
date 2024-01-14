package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BOJ17404 {

    static int N;
    static int map[][];
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static int calculate() {

        int[][] copyMap = new int[N][N];
        int ans = 99999999;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[j] = map[j].clone();
            }

            ans = Math.min(ans,dp(copyMap,i));
        }

        return ans;
    }

    private static int dp(int[][] copyMap, int choice) {

        for (int i = 0; i < 3; i++) {
            if (i != choice) copyMap[0][i] = 99999999;
        }

        for (int i = 1; i < N; i++) {
            copyMap[i][0] = Math.min(copyMap[i-1][1], copyMap[i-1][2]) + copyMap[i][0];
            copyMap[i][1] = Math.min(copyMap[i-1][0], copyMap[i-1][2]) + copyMap[i][1];
            copyMap[i][2] = Math.min(copyMap[i-1][0], copyMap[i-1][1]) + copyMap[i][2];
        }

        return IntStream.range(0, 3)
                .filter(i -> i != choice)
                .map(i -> copyMap[N - 1][i])
                .min()
                .orElseThrow();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }


    }
}
