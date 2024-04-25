package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1059 {

    static int L;
    static int[] arr;
    static int n;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int min = 0;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < L; i++) {
            if (arr[i] < n) {
                min = Math.max(min, arr[i]);
            }
            else if (arr[i] > n) {
                max = Math.min(max, arr[i]);
            }
            else {
                System.out.println(0);
                return;
            }
        }

        System.out.println( (max - n) * (n - min) - 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        L = Integer.parseInt(br.readLine());

        arr = new int[L];
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < L; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        n = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
    }
}