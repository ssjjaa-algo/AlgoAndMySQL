package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ15810 {

    static int n,m;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(binarySearch());
    }

    public static long binarySearch() {

        long left = arr[0];
        long right = arr[0] * m;


        while (left <= right) {

            long mid = (left + right) / 2;

            if (calculate(mid) >= m) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static int calculate(long mid) {

        int ans = 0;
        int length = arr.length;

        for (int i = 0; i < length; i++) {
            ans += mid / arr[i];
        }

        return ans;
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        arr = new long[n];

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(input[i]);
        }

        Arrays.sort(arr);

    }
}
