package Baekjoon;

    /*
    각 사람들이 일을 할 수 있는지 체크한다.

    1. i번째 사람이 할 수 있는 일의 목록을 가져온다.

    2. 일의 목록 중에서
        아직 그 일이 할당되지 않은 일이거나
        그 일을 담당하고 있었던 사람이 다른 일을 할 수 있는 상황이라면
        다른 사람에게 할당을 해본다.
     */

import java.io.*;
import java.util.*;
public class BOJ11375 {

    static int N, M;
    static List<Integer> adj[];
    static int[] task;
    static boolean visited[];

    public static void main(String[] args) throws IOException{
        init();
        System.out.println(calculate());
    }
    private static boolean dfs(int num) {

        for (int next: adj[num]) {

            if (visited[next]) continue;

            visited[next] = true;

            if (task[next] == 0 || dfs(task[next])) {
                task[next] = num;
                return true;
            }
        }
        return false;
    }
    private static int calculate() {

        int cnt = 0 ;
        for (int i = 1; i <=N; i++) {
            Arrays.fill(visited,false);
            if (dfs(i)) cnt++;
            // 각각의 사람들이 일을 할 수 있다면 증가시킨다.
            // 재배치의 과정에서 일을 할 수 없는 사람이면 CNT를 증가시키지 않는다.
        }

        return cnt;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        task = new int[M + 1];
        visited = new boolean[M + 1];

        for (int i = 1; i <= N; i++) {
            input = br.readLine().split(" ");
            int num = Integer.parseInt(input[0]);

            for (int j = 1; j <= num; j++) {
                adj[i].add(Integer.parseInt(input[j]));
            }
        }

    }

}
