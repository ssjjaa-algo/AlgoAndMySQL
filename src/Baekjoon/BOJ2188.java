package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ2188 {
    static int N,M;
    static List<Integer> cows[];
    static boolean[] visited;
    static int[] temp;
    public static void main(String[] args) throws IOException {
        init();
        System.out.println(calculate());
    }
    private static int calculate() {

        int cnt = 0;
        for (int i =1; i<= N; i++) {

            Arrays.fill(visited,false);

            if(dfs(i)) cnt++;
        }

        return cnt;
    }

    private static boolean dfs(int num) {

        for (int next : cows[num]) {

            if (visited[next]) continue;

            visited[next] = true;

            if (temp[next] == 0 || dfs(temp[next])) {// 아직 배정되지 않았거나 교체가 가능한 경우
                temp[next] = num;
                return true;
            }
        }

        return false;
    }

    private static void init()throws IOException {
        String[] input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        cows = new ArrayList[N + 1];
        visited = new boolean[M + 1];
        temp = new int[M + 1];

        for (int i = 0; i <= N; i++) {
            cows[i] = new ArrayList<>();
        }

        int num;
        for (int i = 1 ; i <= N ; i++) {
            input = br.readLine().split(" ");
            num = Integer.parseInt(input[0]);

            for (int j = 1; j <= num; j++) {
                cows[i].add(Integer.parseInt(input[j]));
            }
        }

    }

}
