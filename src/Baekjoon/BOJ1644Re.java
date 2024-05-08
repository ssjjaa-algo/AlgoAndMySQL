package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ1644Re {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> primes = makePrimeList(N);
        br.close();

        calculate(primes, N);
    }

    private static void calculate(List<Integer> primes, int N) {

        int front = 0;
        int back = 0;
        int size = primes.size();
        long sum = 0;
        int ans = 0;

        while (front < size && primes.get(front) <= N) {

            while (sum < N && back < size) {
                sum += primes.get(back++);
            }
            if (sum == N) ans++;

            sum -= primes.get(front++);

        }

        System.out.println(ans);

    }

    private static List<Integer> makePrimeList(int N) {

        boolean[] primes = new boolean[N + 1];
        List<Integer> list = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            if(!primes[i]) {
                list.add(i);
                for (int j = 2; j * i <= N; j++) {
                    primes[j * i] = true;
                }
            }
        }
        return list;
    }
}

/**
 * 1. 4000000만까지의 소수들을 구해서 list에 넣어둔다. -> 에라토스테네스
 * 2. 2부터 시작하여 소수들의 합을 구하기
 *    - 합이 n보다 큰 경우 -> 앞에거 자르기
 *    - 합이 n인 경우 ++
 *    - 합이 n보다 작은 경우 -> 뒤에거 추가
 *
 *
 */
