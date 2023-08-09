package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ13913 {

    static int[] sequence = new int[100010];
    static int N,K;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{

        init();
        bfs();
        calculate();

        if (N == K) {
            System.out.println(0);
            System.out.println(N);
            return;
        }
        System.out.print(sb);
    }

    private static void calculate() {
        int num = K;
        Stack<Integer> stack = new Stack<Integer>();

        stack.add(K);
        while (true) {
            num = sequence[num];

            stack.add(num);
            if (num == N) break;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
    }

    private static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[100010];

        q.add(N);
        visited[N] = true;
        sequence[N] = N;

        int cnt = 0;
        while (!q.isEmpty()) {


            int size = q.size();
            for (int i = 0; i < size; i++) {

                int temp = q.poll();

                if (temp == K) {
                    sb.append(cnt).append("\n");
                    return;
                }

                if (isIn(temp * 2) && !visited[temp * 2]) {
                    visited[temp * 2] = true;
                    sequence[temp * 2] = temp;
                    q.add(temp * 2);
                }

                if (isIn(temp + 1) && !visited[temp + 1]) {
                    visited[temp + 1] = true;
                    sequence[temp + 1] = temp;
                    q.add(temp + 1);
                }

                if (isIn(temp - 1) && !visited[temp - 1]) {
                    visited[temp - 1] = true;
                    sequence[temp - 1] = temp;
                    q.add(temp - 1);
                }
            }
            cnt++;
        }
    }

    private static boolean isIn(int temp) {
        if (temp < 0 || temp > 100000) return false;

        return true;
    }

    private static void init() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

    }
}
