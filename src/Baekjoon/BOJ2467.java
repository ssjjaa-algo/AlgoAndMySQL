package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2467 {

    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(arr);

       int[] ans = calculate();

        for (int num : ans) {
            System.out.print(num + " ");
        }
    }

    static int[] calculate() {

        int res = Integer.MAX_VALUE;

        int leftAns = 0;
        int rightAns = 0;

        /**
         * 배열의 크기만큼 이진탐색
         * left를 1씩 올려가며
         * 모든 경우에 대해 탐색
         * 100000 * log100000
         */

        for (int i = 0; i < n - 1; i++) {

            int left = i + 1;
            int right = n - 1;

            while (left <= right) {

                int mid = (left + right) / 2;

                int sum = arr[i] + arr[mid];

                if (res > Math.abs(sum)) {
                    res = Math.abs(sum);
                    leftAns = arr[i];
                    rightAns = arr[mid];
                }

                if (sum > 0) {
                    right = mid - 1;
                }
                else if (sum < 0) {
                    left = mid + 1;
                }
                else return new int[]{leftAns, rightAns};
            }
        }

        return new int[]{leftAns, rightAns};
    }
}
