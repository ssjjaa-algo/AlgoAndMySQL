package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1644 {

    static int N;
    static int[] primes;
    static List<Integer> realPrimes = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        int front = 0;
        int back = 0;
        int sum = 0;

        int size = realPrimes.size();

        while (front < size && realPrimes.get(front) <= N) {

            while (back < size && sum < N) {
                sum += realPrimes.get(back++);
            }

            if (sum == N) cnt++;
            sum -= realPrimes.get(front);
            front++;
        }

        return cnt;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        primes = new int[4000001];


        for (int i = 2; i <= 4000000; i++) {
            if (primes[i] == 0) {
                primes[i] = 2;
                realPrimes.add(i);

                for (int j = 2 * i; j <= 4000000; j = j + i) {
                    primes[j] = 1;
                }
            }
        }

    }

}
