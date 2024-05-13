package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2352 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        calculate(arr, dp, N);

    }

    private static void calculate(int[] arr, int[] dp, int N) {

        int dpIdx = 0;
        for (int i = 0; i < N; i++) {

            int idx = binarySearch(arr[i], dp,0, dpIdx);

            if (idx == -1) {
                dp[dpIdx++] = arr[i];
            }
            else {
                dp[idx] = arr[i];
            }
        }

        System.out.println(dpIdx);

    }

    private static int binarySearch(int target, int[] dp, int left, int right) {

        int ans = -1;
        while (left <= right) {

            int mid = (left + right) / 2;

            if (dp[mid] >= target) {
                ans = mid;
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return ans;
    }
}
