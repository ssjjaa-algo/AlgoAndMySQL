package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2343 {

    static int n;
    static int blueCnt;
    static int[] arr;
    static int sum;
    static int min = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        n = Integer.parseInt(input[0]);
        blueCnt = Integer.parseInt(input[1]);
        arr = new int[n];

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
            sum += arr[i];
            min = Math.max(min, arr[i]);
        }

        System.out.println(binarySearch());
    }

    private static int binarySearch() {

        int left = min;
        int right = sum;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 1;
            int add = 0;
            for (int i = 0; i < n; i++) {
                add += arr[i];
                if (add > mid) {
                    cnt++;
                    add = arr[i];
                }
            }

            if (cnt > blueCnt) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
