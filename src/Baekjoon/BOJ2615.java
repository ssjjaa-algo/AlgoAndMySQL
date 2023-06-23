package Baekjoon;

import java.io.*;

public class BOJ2615 {

    static int[] xDirection = {0,1,1,1,0,-1,-1,-1};// 동, 동남, 남, 남서
    static int[] yDirection = {1,1,0,-1,-1,-1,0,1};// 동, 동남, 남, 남서
    static int ansRow, ansCol;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] map = new int[20][20];

        String[] input;
        for (int i=1; i<20; i++) {
            input = br.readLine().split(" ");
            for (int j=1; j<20; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
            }
        }

        int winner = check(map);
        sb.append(winner).append("\n");

        if (winner != 0) sb.append(ansRow).append(" ").append(ansCol);

        System.out.println(sb.toString());
        wr.close();
        br.close();

    }
    private static int check(int[][] map) {

        for (int i=1; i<20; i++) {
            for (int j=1; j<20; j++) {
                if (map[i][j] == 1) { // 검은 바둑알

                    if (findCanAns(map,i,j,1)) return 1;
                }

                else if (map[i][j] == 2) { // 흰 바둑알

                    if (findCanAns(map,i,j,2)) return 2;
                }
            }
        }
        return 0;
    }

    private static boolean findCanAns(int[][] map, int x, int y, int color) {

        for (int i=0; i<4; i++) { // 4가지 방향에 대해서
            if (isIn(x + xDirection[i + 4],y + yDirection[i + 4])) {
                if (map[x + xDirection[i + 4]][y + yDirection[i + 4]] == color) continue;
            }
            int nx = x;
            int ny = y;
            int cnt = 1;
            while(true) {

                nx = nx + xDirection[i];
                ny = ny + yDirection[i];

                if (cnt == 5) { // 5개의 오목이 연속선상에 있었다면

                    if(verify(map,nx, ny,color)) {
                        // 다음 값도 동일한지 확인하고, 범위도 체크해보는 마지막 관문.
                        ansRow = x;
                        ansCol = y;

                        if (i == 3) {
                            ansRow = nx - xDirection[i];
                            ansCol = ny - yDirection[i];
                        }
                        return true;
                    }

                    break;
                }

                if (!isIn(nx,ny) || map[nx][ny] != color) break;

                cnt++;
            }

        }

        return false;
    }

    private static boolean verify(int[][] map, int nx, int ny, int color) {

        if (!isIn(nx,ny) || map[nx][ny] != color) return true;

        return false;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 1 || nx >= 20 || ny < 1 || ny >= 20) return false;

        return true;
    }
}
