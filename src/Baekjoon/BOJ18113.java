package Baekjoon;

import java.io.*;

public class BOJ18113 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    public static void main(String[] args) throws IOException{

        String[] input = br.readLine().split(" ");
        int N,K,M;
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);

        int len = init(N,K);

        if (len == 0) {
            System.out.println(-1);
            return;
        }

        if (M == 1) {
            System.out.println(len);
            return;
        }

        System.out.println(binarySearch(N,len,M)); // N개에 대하여 len으로 이분 탐색 시작

    }

    private static int binarySearch(int N, int len, int M) {

        int left = 1;
        int right = len;

        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            for (int i=0; i<N; i++) {
                cnt = cnt + (arr[i] / mid);
            }

            if (cnt >= M) {
                ans = Math.max(ans,mid);
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return ans;
    }

    private static int init(int N, int K) throws IOException {

        arr = new int[N];
        int len = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            if (arr[i] <= K) {
                arr[i] = 0;
                continue;
            }

            if (arr[i] < 2 * K) {
                arr[i] = arr[i] - K;
            }
            else {
                arr[i] = arr[i] - (2 * K);
            }

            len = Math.max(len, arr[i]); // 가장 긴 길이를 가져와서 걔부터 binary search 할거다.
        }

        return len;
    }
}
