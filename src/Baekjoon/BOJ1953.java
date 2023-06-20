package Baekjoon;

import java.util.*;
import java.io.*;
public class BOJ1953 {

    static int n;
    static List<Integer> students[];
    static int[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input;

        n = Integer.parseInt(br.readLine());
        students = new ArrayList[n + 1];
        visited = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            students[i] = new ArrayList<>();
        }

        int numOfHate = 0;
        int hatePeople1 = 0;
        int hatePeople2 = 0;

        for (int i = 0; i < n; i++) { // 서로 싫어하는 관계를 입력받는다.
            input = br.readLine().split(" ");
            numOfHate = Integer.parseInt(input[0]);
            hatePeople1 = i + 1;
            for (int j = 0; j < numOfHate; j++) {
                hatePeople2 = Integer.parseInt(input[j + 1]);
                students[hatePeople1].add(hatePeople2);
                students[hatePeople2].add(hatePeople1);
            }
        }

        bfs();

        wr.write(sb.toString());
        wr.close();
        br.close();

    }

    private static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> blue = new ArrayList<>();
        List<Integer> white = new ArrayList<>();

        for (int i=1; i<=n; i++) { // 모든 학생들에 대하여

            if(visited[i] != 0 )continue; // 이미 배치된 학생은 넘아가고

            q.add(i);
            visited[i] = 1; // 처음에 청팀에 넣어본다.

            while(!q.isEmpty()) {
                int temp = q.poll();
                int size = students[temp].size();
                for (int j = 0; j < size; j++) { // i번째 학생이 싫어하는 학생들의 목록에 대해서, 이 과정에서 청팀에 속하지 못하는 애들 다 집어넣어진다.

                    int next = students[temp].get(j);
                    if (visited[next] != 0) continue; // 이미 배치된 학생이라면 넘어가고

                    visited[next] = visited[temp] * -1; // 싫어하는 학생을 반대편 집단에 넣어준다.
                    q.add(next);
                }
            }
        }

        for (int i=1; i<=n; i++) {
            if (visited[i] == 1) blue.add(i);

            else if (visited[i] == -1) white.add(i);
        }

        sb.append(blue.size()).append("\n");
        for (int i=0; i < blue.size(); i++) {
            sb.append(blue.get(i)).append(" ");
        }
        sb.append("\n").append(white.size()).append("\n");
        for (int i=0; i<white.size(); i++) {
            sb.append(white.get(i)).append(" ");
        }
    }
}