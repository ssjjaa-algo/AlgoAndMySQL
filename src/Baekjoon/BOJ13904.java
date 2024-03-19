package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * 1. 6일차에 할 수 있는 과제 (6, 5)
 * 2. 5일차 없음
 * 3. 4일차에 할 수 있는 과제 (4, 60) (4, 40) (4, 10) -> (4, 60) 선택
 * 4. 3일차에 할 수 있는 과제 (4, 40) (4, 10) (3, 30) -> (4, 40) 선택
 * 5. 2일차에 할 수 있는 과제 (3, 30) (2, 50) -> (2, 50) 선택
 * 6. 1일차에 할 수 있는 과제 (3, 30) (1, 20) -> (3, 30) 선택
 *
 * n일차에 할 수 있는 과제 : day >= n 일차를 가진 과제 중에서 점수가 가장 큰 과제.
 * n은 최대 1000으로 n^2 가능
 */

public class BOJ13904 {

    static int N;

    static class Node {
        int day;
        int score;

        public Node(int day, int score) {
            this.day = day;
            this.score = score;
        }
    }

    static Node[] nodes;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int sum = 0;
        int last = nodes[0].day;

        for (int i = last; i > 0; i--) {

            int score = 0;
            int choice = 0;
            for (int j = 0; j < N; j++) {

                if (nodes[j].day < i) break;

                if (score < nodes[j].score) {
                    score = nodes[j].score;
                    choice = j;
                }
            }
            sum += score;
            nodes[choice].score = 0;
        }

        System.out.println(sum);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nodes = new Node[N];

        int day, score;
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            day = Integer.parseInt(input[0]);
            score = Integer.parseInt(input[1]);

            nodes[i] = new Node(day, score);
        }

        Arrays.sort(nodes, (o1, o2) -> {
            if (o2.day == o1.day) {
                return Integer.compare(o2.score, o1.score);
            }
            return Integer.compare(o2.day, o1.day);
        });
    }
}
