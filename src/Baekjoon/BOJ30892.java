package Baekjoon;

import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class BOJ30892 {

    static int N, K;
    static long T;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        init();
        calculate();
        System.out.println(T);
    }

    static void calculate() {

        Stack<Integer> stack = new Stack<>();

        int idx = 0;
        for (int i = 0; i < K; i++) {
            while (idx < N && arr[idx] < T) {
                stack.push(arr[idx++]);
            }

            if (stack.isEmpty()) return;
            T += stack.pop();
        }

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        T = Long.parseLong(input[2]);

        arr = new int[N];
        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(arr);
    }
}
