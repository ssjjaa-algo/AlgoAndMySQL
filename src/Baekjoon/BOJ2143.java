package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2143 {

    static int T, n, m;
    static int[] a;
    static int[] b;

    static HashMap<Integer, Integer> aMap = new HashMap<>();
    static HashMap<Integer, Integer> bMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        long ans = 0;
        for (Map.Entry<Integer, Integer> b : bMap.entrySet()) {
            ans += ((long) aMap.getOrDefault(T - b.getKey(), 0) * b.getValue());
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(input[i]);
        }

        m = Integer.parseInt(br.readLine());
        b = new int[m];
        input = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(input[i]);
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                aMap.put(sum, aMap.getOrDefault(sum, 0) + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += b[j];
                bMap.put(sum, bMap.getOrDefault(sum, 0) + 1);
            }
        }

    }
}
