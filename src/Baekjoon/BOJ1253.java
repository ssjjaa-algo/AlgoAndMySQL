package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1253 {

    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int cnt = 0;
        for (int i = 0; i < N; i++) {

            int front = 0;
            int back = N - 1;
            int ans = arr[i];

            while (front < back) {
                int res = arr[front] + arr[back];

                if (res > ans) back--;
                else if (res < ans) front++;
                else {
                    if (front != i && back != i) {
                        cnt++;
                        break;
                    }
                    else if (front == i) {
                        front++;
                    }
                    else if (back == i) {
                        back--;
                    }
                }
            }
        }

        System.out.println(cnt);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String[] input = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(arr);
    }
}
