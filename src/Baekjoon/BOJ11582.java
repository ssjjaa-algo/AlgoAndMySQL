package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ11582 {

    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        int K = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i += N / K){
            divideAndSort(i, i + N / K, sb);
        }

        System.out.println(sb);
    }

    private static void divideAndSort(int start, int end, StringBuilder sb) {

        int[] temp = new int[end - start];
        for (int i = start; i < end; i++) {
            temp[i - start] = arr[i];
        }

        Arrays.sort(temp);
        for (int num : temp) {
            sb.append(num).append(" ");
        }
    }
}
