package Baekjoon;

import java.io.*;

public class BOJ20922 {

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static String[] input;
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        int []arr = new int[N];
        int []count = new int[100001];

        init(N,arr);

        System.out.println(calculate(N,K,arr,count));
    }
    private static int calculate(int N, int K, int[] arr, int[] count) {

        int startIdx = 0;
        int endIdx = 0;
        int length = 1;
        int max = 0;

        while (endIdx < N) {

            while(endIdx < N && count[arr[endIdx]] < K) {
                count[arr[endIdx]]++;
                endIdx++;
            }

            max = Math.max(endIdx - startIdx,max);
            count[arr[startIdx++]]--;
        }

        return max;

    }
    private static void init(int N, int[] arr) throws IOException {

        input = br.readLine().split(" ");
        for (int i = 0; i < N ; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }


    }
}
