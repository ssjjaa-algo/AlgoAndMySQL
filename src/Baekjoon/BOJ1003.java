package Baekjoon;

import java.io.*;

public class BOJ1003 {

    static int[] countOfZero = new int[41];
    static int[] countOfOne = new int[41];

    /**
     * countOfZero[0] = 입력이 0으로 들어왔을 때 등장하는 0의 개수
     * countOfZero[1] = 입력이 1로 들어왔을 때 등장하는 0의 개수
     * countOfZero[2] = 입력이 2로 들어왔을 때 등장하는 0의 개수
     *
     * 초기화
     * counOfZero[0] = 0
     * countOfZero[1] = 0
     * countOfZero[2] = 1
     * countOfZero[3] = 1
     * countOfZero[4] = 2
     * countOfZero[5] = 3
     * countOfZero[6] = 5
     * 6 -> (5, 4), 5 - > (4, 3), 4 -> (3, 2), 3 -> (2, 1), 2 -> (1,0)
     * 5 -> (4, 3), 4 -> (3, 2), 3 -> (2, 1), 2 -> (1, 0)
     * 4 -> (3, 2), 3 - > (2, 1) -> 2 -> (1,0)
     * 3 -> (2, 1), 2 - > (1, 0)

     *
     *
     * countOfOne[0] = 0
     * countOfOne[1] = 1
     * countOfOne[2] = 1
     * countOfOne[3] = 2
     * countOfOne[4] = 3
     * countOfOne[5] = 5
     * countOfOne[6] = 8
     */
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void init() {

        countOfZero[0] = 1;
        countOfZero[1] = 0;
        countOfZero[2] = 1;

        countOfOne[0] = 0;
        countOfOne[1] = 1;
        countOfOne[2] = 1;

        for (int i = 3; i <= 40; i++) {
            countOfZero[i] = countOfZero[i-1] + countOfZero[i-2];
            countOfOne[i] = countOfOne[i-1] + countOfOne[i-2];
        }
    }

    private static void calculate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int num = 0;
        for (int i = 0; i < T; i++) {
            num = Integer.parseInt(br.readLine());
            sb.append(countOfZero[num]).append(" ").append(countOfOne[num]).append("\n");
        }

        System.out.println(sb);
    }
}
