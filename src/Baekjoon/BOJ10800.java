package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 자기 공보다 크기가 작고 색이 다른 공을 사로잡는다. 점수는 해당 공의 크기만큼 얻게 된다.
 *
 * 1. 크기 순으로 정렬
 * 2. 해당 ball을 뽑아서 이전의 누적합을 기록해야 한다.
 *    - 첫 번째 볼(가장 작은 볼)의 누적합은 0
 *    - 이후 볼은 이전 볼의 누적합을 더하면서 온다.
 *       - 다 더하고 나서, 자기 자신의 색과 동일한 컬러들을 뺀다.
 *
 */
public class BOJ10800 {

    static class Ball {
        int idx, color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    static int N;
    static Ball[] balls;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int color = Integer.parseInt(input[0]);
            int size = Integer.parseInt(input[1]);
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls, Comparator.comparingInt(o -> o.size));

        calculate();
    }

    private static void calculate() {

        int[] colors = new int[N + 1];
        int[] ans = new int[N];

        int idx = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {

            while (balls[idx].size < balls[i].size) {
                sum += balls[idx].size;
                colors[balls[idx].color] += balls[idx].size;
                idx++;
            }
            ans[balls[i].idx] = sum - colors[balls[i].color];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb);
    }
}
