package Baekjoon;

import java.io.*;
public class BOJ1956 {

    static int V,E;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;

    static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {

        init();
        floyd();
        calculate();
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static void calculate() {

        for (int i = 1; i <= V; i++) {
            for(int j = 1; j <= V; j++) {

                if (i == j) continue;

                if (map[i][j] != INF && map[j][i] != INF) {
                    ans = Math.min(ans,map[i][j] + map[j][i]);
                }
            }
        }
    }

    private static void floyd() {

        for (int k = 1; k <= V; k++) { // 거치고
            for (int i = 1; i <=V; i++) { // 출발하고
                for (int j =1; j <= V; j++) { // 도착하고

                    if (i == j || i == k) continue;

                    if (map[i][j] > map[i][k] + map[k][j] ) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        V = Integer.parseInt(input[0]);
        E = Integer.parseInt(input[1]);

        map = new int[V+1][V+1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                map[i][j] = INF;
            }
        }
        int a,b,c;
        for (int i = 0; i < E; i++) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            c = Integer.parseInt(input[2]);

            map[a][b] = c;
        }

    }
}
