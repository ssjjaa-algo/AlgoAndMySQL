package Baekjoon;

import java.io.*;

public class BOJ14620 {

    static class Flower {
        int x,y;

        public Flower(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[][] map;
    static int N;
    static boolean[][] visited;
    static Flower[] flowers;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        init();

        String[] input;
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        // 꽃의 씨앗 3개를 배치하러 가자.
        placeThreeFlower(1,0);

        System.out.println(ans);

    }

    private static void init() {
        map = new int[N][N]; // 화단의 사이즈
        visited = new boolean[N][N];
        flowers = new Flower[3];
    }

    private static void placeThreeFlower(int x, int cnt) {
        if (cnt == 3) {
            ans = Math.min(ans,search());
            return;
        }

        for (int i=x; i<N - 1; i++) {
            for (int j=1; j<N - 1; j++) {
                if(!visited[i][j]) {
                    visited[i][j] = true;
                    flowers[cnt] = new Flower(i,j);
                    placeThreeFlower(i,cnt + 1);
                    visited[i][j] = false;
                }
            }
        }
    }

    private static int search() {

        boolean[][] check = new boolean[N][N];

        int sum = 0;

        for (int i=0; i< 3; i++) { // 처음 3개의 꽃을 심는데 필요한 땅의 가격을 미리 더해둔다.
            check[flowers[i].x][flowers[i].y] = true;
            sum += map[flowers[i].x][flowers[i].y];
        }

        for (int i=0; i<3; i++) {
            Flower flower = flowers[i];
            for (int j=0; j<4; j++) {

                int nx = flower.x + xDirection[j];
                int ny = flower.y + yDirection[j];

                if (check[nx][ny]) return Integer.MAX_VALUE;

                sum += map[nx][ny];
                check[nx][ny] = true;
            }
        }

        return sum;

    }

}
