package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2632 {

    static int pizza;
    static int n,m;
    static int[] aArr;
    static int[] bArr;
    static int[] aSum = new int[2000001];
    static int[] bSum = new int[2000001];
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        makeSumArr(m, aArr, aSum);
        makeSumArr(n, bArr, bSum);

        int ans = 0;
        for (int i = 0; i <= pizza; i++) {
            ans += aSum[i] * bSum[pizza - i];
        }

        System.out.println(ans);
    }

    private static void makeSumArr(int size, int[] arr, int[] sum) {

        int maxSum = 0;
        for (int i = 0; i < size; i++) {
            maxSum += arr[i];
        }
        for (int i = 0; i < size; i++) {
            int res = 0;
            for (int j =i; j < i + size; j++) {
                res += arr[j % size];
                sum[res]++;
            }
        }
        sum[0] = 1;
        sum[maxSum] = 1;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pizza = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);

        aArr = new int[m];
        bArr = new int[n];

        for (int i = 0; i < m; i++) {
            aArr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < n; i++) {
            bArr[i] = Integer.parseInt(br.readLine());
        }
    }
}
