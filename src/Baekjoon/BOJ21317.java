package Baekjoon;

import java.io.*;
public class BOJ21317 {

    static int[] notBigJump;
    static int[][] arr;
    static int N,K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        init();
        calculateNotBigJump();

        for (int i = 1; i <= N - 3; i++) {
            calculateBigJump(i);
        }

        System.out.println(ans);
    }

    private static void calculateBigJump(int start) {

        int[] bigJump = new int[N + 1];

        for (int i = 1; i < start + 3; i++) {
            bigJump[i] = notBigJump[i];
        }
        bigJump[start + 3] = notBigJump[start] + K;

        for (int i = start + 4; i <= N; i++) {
            bigJump[i] = Math.min(bigJump[i-1] + arr[i-1][0], bigJump[i-2] + arr[i-2][1]);
        }

        ans = Math.min(ans,bigJump[N]);
    }


    private static void calculateNotBigJump() {

        if (N == 1) {
            ans = 0;
            return;
        }

        notBigJump[1] = 0;
        notBigJump[2] = arr[1][0];


        for (int i = 3; i <= N; i++) {
            notBigJump[i] = Math.min(notBigJump[i-1] + arr[i-1][0], notBigJump[i-2] + arr[i-2][1]);
        }
        ans = notBigJump[N];

    }

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        String[] input;

        notBigJump = new int[N + 1];
        arr = new int[N][2];
        for (int i = 1; i< N; i++) {
            input = br.readLine().split(" ");

            arr[i][0] = Integer.parseInt(input[0]);
            arr[i][1] = Integer.parseInt(input[1]);
        }

        K = Integer.parseInt(br.readLine());
    }
}
