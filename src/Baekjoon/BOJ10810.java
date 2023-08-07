package Baekjoon;

import java.io.*;

public class BOJ10810 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static String[] input;
    static int[] bucket;
    public static void main(String[] args) throws IOException {
        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        init(N);
        calculate(M);

        for (int i = 1; i <= N ; i++) {
            sb.append(bucket[i]).append(" ");
        }

        System.out.println(sb);
    }

    private static void init(int N) {
        bucket = new int[N + 1];
    }
    private static void calculate(int M) throws IOException{

        int from, to, number;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            number = Integer.parseInt(input[2]);

            for (int j = from; j <= to; j++) {
                bucket[j] = number;
            }
        }

    }
}
