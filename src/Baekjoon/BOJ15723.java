package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ15723 {

    /*
    그래프를 인접리스트의 형태로 나타내어 dfs로 결론을 낸다.
     */
    static List<Integer> adj[] = new ArrayList[27];
    static boolean[] visited = new boolean[27];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i=0; i<27; i++) {
            adj[i] = new ArrayList<>();
        }

        int n = Integer.parseInt(br.readLine());
        String[] input;

        int comp1, comp2;
        for (int i=0; i< n; i++) {
            input = br.readLine().split(" ");
            comp1 = input[0].charAt(0) - 'a';
            comp2 = input[2].charAt(0) - 'a';

            adj[comp1].add(comp2);
        }

        // a와 b는 서로 다른 임의의 '알파벳' 소문자이므로 최대 26개 가능.


        int m = Integer.parseInt(br.readLine());
        for (int i=0; i<m; i++) {
            input = br.readLine().split(" ");
            comp1 = input[0].charAt(0) - 'a';
            comp2 = input[2].charAt(0) - 'a';

            if(dfs(comp1,comp2)) sb.append("T").append("\n");

            else sb.append("F").append("\n");

            Arrays.fill(visited,false);
        }

        wr.write(sb.toString());
        wr.close();
        br.close();
    }

    private static boolean dfs(int comp1, int comp2) {

        int number;
        for (int i=0; i < adj[comp1].size(); i++) {

            number = adj[comp1].get(i);

            if (number == comp2) {
                return true;
            }

            if(!visited[number]) {
                visited[number] = true;
                if(dfs(number,comp2)){
                    return true;
                }
            }

        }

        return false;

    }
}
