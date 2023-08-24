package Baekjoon;

import java.io.*;

public class BOJ1717 {

    static int[] parents;
    static int N,M;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] input;
    public static void main(String[] args) throws IOException{
        init();
        calculate();
    }
    private static void calculate() throws IOException{
        StringBuilder sb = new StringBuilder();

        int calculation, a, b;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            calculation = Integer.parseInt(input[0]);
            a = Integer.parseInt(input[1]);
            b = Integer.parseInt(input[2]);

            if (calculation == 0) { // 합집합 연산
                union(a,b);
            }

            else { // 같은 집합에 속해있는지
                sb.append(check(a,b) ? "YES" : "NO").append("\n");
            }

        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int Aroot = find(a);
        int Broot = find(b);

        if (Aroot == Broot) return;

        if (Aroot > Broot) {
            parents[Aroot] = Broot;
        }
        else parents[Broot] = Aroot;
    }
    private static int find(int num) {
        if (parents[num] == num) return num;

        else return parents[num] = find(parents[num]);
    }

    private static boolean check(int a, int b) {
        if (find(a) == find(b)) return true;

        return false;
    }
    private static void init() throws IOException {
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

    }
}
