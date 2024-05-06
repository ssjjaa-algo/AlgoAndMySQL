package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ9019 {

    static class Node {
        int num;
        String command;

        public Node(int num, String command) {
            this.num = num;
            this.command = command;
        }

        public int D() {
            return (num * 2) % 10000;
        }

        public int S() {
            return num != 0 ? num - 1 : 9999;
        }

        public int L() {
            return (num / 1000) + ((num % 1000) * 10);
        }

        public int R() {
            return ((num % 10) * 1000) + (num / 10);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);

           sb.append(makeAtoB(A,B)).append("\n");
        }

        System.out.print(sb);

    }

    private static String makeAtoB(int A, int B) {

        boolean[] visited = new boolean[10000];

        Queue<Node> q = new ArrayDeque<>();

        visited[A] = true;
        q.add(new Node(A,""));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.num == B) return cur.command;
            int next = 0;

            next = cur.D();
            if (!visited[next]) {
                visited[next] = true;
                q.add(new Node(next,cur.command + "D"));
            }

            next = cur.S();
            if (!visited[next]) {
                visited[next] = true;
                q.add(new Node(next,cur.command + "S"));
            }

            next = cur.L();
            if (!visited[next]) {
                visited[next] = true;
                q.add(new Node(next,cur.command + "L"));
            }

            next = cur.R();
            if (!visited[next]) {
                visited[next] = true;
                q.add(new Node(next,cur.command + "R"));
            }

        }

        return "";
    }

        /*

    D: D 는 n * 2, if (n * 2 > 9999) number = (n * 2) % 10000
    S: S 는 if (n == 0) number = 9999 / else n--
    L: L 은 n의 각 자릿수를 왼편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d2, d3, d4, d1이 된다.
    R: R 은 n의 각 자릿수를 오른편으로 회전시켜 그 결과를 레지스터에 저장한다. 이 연산이 끝나면 레지스터에 저장된 네 자릿수는 왼편부터 d4, d1, d2, d3이 된다.
     */
}
