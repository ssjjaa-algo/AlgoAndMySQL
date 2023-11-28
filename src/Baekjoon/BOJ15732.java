package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ15732 {

    static int N,K,D;
    static int[][] rules;
    public static void main(String[] args) throws IOException{

        init();
        System.out.println(binarySearch());
    }

    private static int binarySearch() {

        int left = 1;
        int right = N;

        while (left <= right) {

            int mid = (left + right) / 2;

            long corns = cntOfCorns(mid);

            if (corns >= D) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }

        return right + 1;
    }

    private static long cntOfCorns(int mid) {

        long sum = 0;

        int right = 0;
        int res = 0;
        for (int i = 0; i < K; i++) {

            if (rules[i][0] > mid) continue;

            right = Math.min(mid,rules[i][1]);
            res = (right - rules[i][0]);

            sum += res / rules[i][2] + 1;

        }

        return sum;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        D = Integer.parseInt(input[2]);

        rules = new int[K][3];

        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            rules[i][0] = Integer.parseInt(input[0]);
            rules[i][1] = Integer.parseInt(input[1]);
            rules[i][2] = Integer.parseInt(input[2]);
        }

        Arrays.sort(rules,(o1,o2) -> { return Integer.compare(o1[0],o2[0]);});

    }


}
