package Softeer;

import java.io.*;
import java.util.*;
public class 자동차_테스트 {

    static int N,Q;
    static int[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        init();
        calculate();
    }

    private static void calculate() throws IOException{

        StringBuilder sb = new StringBuilder();
        int num;
        for (int i = 0; i < Q; i++) {
            num = Integer.parseInt(br.readLine());

            int idx = binarySearch(num);
            int ans = idx * (N - (idx + 1));
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int binarySearch(int num) {

        int left = 0;
        int right = N - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr[mid] == num) return mid;

            else if (arr[mid] > num) {
                right = mid - 1;
            }
            else if (arr[mid] < num) {
                left = mid + 1;
            }
        }
        return 0;

    }

    private static void init() throws IOException {
        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        Q = Integer.parseInt(input[1]);

        arr = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(arr);

    }
}

