package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2230 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int[] arr = new int[N];
        int M = Integer.parseInt(input[1]);

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;

        int front = 0;
        int back = 1;
        while (front < N && back < N) {

            int difference = Math.abs(arr[front] - arr[back]);
            if (difference < M) back++;

            else {
                min = Math.min(min, difference);
                front++;
            }
        }
        System.out.println(min);


    }
}
