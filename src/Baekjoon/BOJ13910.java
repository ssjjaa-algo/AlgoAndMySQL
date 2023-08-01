package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ13910 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] input;
    static int[] dp;
    static List<Integer> possible = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int[] wok = new int[M];
        dp = new int[10010];

        init(wok,M,N);
        calculateTwoHand(wok,M,N);
        fillDp(M,N);


        if (dp[N] >= 99999) System.out.println(-1);
        else System.out.println(dp[N]);


    }

    private static void fillDp(int M, int N) {

        int size = possible.size();

        int value = 0;
        for (int i = 0 ; i < size; i++) {
            value = possible.get(i);

            for (int j = value + 1; j <= N; j++) {
                if (dp[j - value] != 99999) dp[j] = Math.min(dp[j],dp[j-value] + 1);
            }
        }


    }

    private static void calculateTwoHand(int[] wok, int M, int N) {

        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                if (wok[i] + wok[j] > 10000) continue;
                dp[wok[i] + wok[j]] = 1;
                possible.add(wok[i] + wok[j]);
            }
        }

    }

    private static void init(int []wok, int M, int N) throws IOException{
        input = br.readLine().split(" ");

        for (int i = 0; i <= N; i++) {
            dp[i] = 99999;
        }
        for (int i = 0 ; i < M ; i ++) {
            wok[i] = Integer.parseInt(input[i]);
            dp[wok[i]] = 1;
            possible.add(wok[i]);
        }

    }
}
