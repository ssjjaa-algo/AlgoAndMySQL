package Baekjoon;


import java.io.*;
import java.util.*;

public class BOJ11568 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] length = new int[N];
        Arrays.fill(length,1);

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        System.out.println(LIS(N,arr,length));
    }

    private static int LIS(int N, int[] arr, int[] length) {

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {

            for (int j = 0; j <= i; j++) {

                if (arr[j] < arr[i]) {
                    length[i] = Math.max(length[j] + 1, length[i]);
                }
            }
            max = Math.max(max,length[i]);
        }
        return max;
    }
}
