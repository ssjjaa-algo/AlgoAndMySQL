package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1351 {

    static Map<Long, Long> map = new HashMap<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long N = Long.parseLong(input[0]);
        long P = Long.parseLong(input[1]);
        long Q = Long.parseLong(input[2]);

        map.put(0L, 1L);

        calculate(N, P, Q);
        System.out.println(map.get(N));
    }

    static void calculate(long N, long P, long Q) {

        if (map.containsKey(N)) return;
        long num1 = N / P;
        long num2 = N / Q;

        if (!map.containsKey(num1)) {
            calculate(num1, P, Q);
        }
        if (!map.containsKey(num2)) {
            calculate(num2, P, Q);
        }
        map.put(N, map.get(num1) + map.get(num2));

    }
}
