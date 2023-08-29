package Baekjoon;

import java.io.*;
public class BOJ14719 {

    static int H,W;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        /*
        현재 나의 위치를 기준으로 왼쪽, 오른쪽에 나보다 큰 기둥이 있어야 한다.
        1) 없으면 continue

        2) 왼쪽, 오른쪽의 기둥들이 모두 크다면 두 기둥 중 더 작은 기둥(Math.min) - 현재의 기둥 값을 뺀다.
         */
        int res = 0;

        for (int i = 1; i < W - 1; i++) {

            // 왼쪽 기둥 중에서 제일 높은거 찾기
            int left = 0;
            for (int j = 0; j < i; j++) {
                left = Math.max(arr[j],left);
            }
            // 오른쪽 기동 중에서 제일 높은거 찾기

            int right = 0;
            for (int j = i + 1; j < W; j++) {
                right = Math.max(arr[j],right);
            }

            if (left > arr[i] && right > arr[i]) {
                res += Math.min(left,right) - arr[i];
            }
        }

        return res;
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        H = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);

        arr = new int[W];
        input = br.readLine().split(" ");
        for (int i = 0 ; i < W; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }


    }
}
