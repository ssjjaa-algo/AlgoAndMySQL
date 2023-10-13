package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1785 {

    static int n;
    static long[] arr;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static long calculate() {

        long sum = 0;
        long temp = 0;
        for (int i = n - 1; i >= 0; i--) {
            temp = arr[i] - ((n - 1 - i));
            if (temp <= 0) continue;
            sum += temp;
        }

        return sum;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);
    }
}
