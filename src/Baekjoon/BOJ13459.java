
package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ13459 {

    static class Ball {
        int redX, redY, blueX, blueY;

        public Ball(int redX, int redY, int blueX, int blueY) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
        }
    }
    static char[][] map;
    static boolean[][][][] visited;
    static int N,M;
    static int redRow, redCol, blueRow, blueCol, endRow, endCol;
    static int[] xDirection ={-1,0,1,0};
    static int[] yDirection ={0,1,0,-1};

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(bfs(redRow,redCol,blueRow,blueCol));
    }

    private static boolean bfs(int redX, int redY, int blueX, int blueY) {

        Queue<Ball> q = new ArrayDeque<>();
        visited[redX][redY][blueX][blueY] = true;
        q.add(new Ball(redX,redY,blueX,blueY));
        map[redX][redY] = '.';
        map[blueX][blueY] = '.';
        int cnt = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            cnt++;
            if (cnt > 10) break;

            for (int i = 0; i < size; i++) {

                Ball temp = q.poll();

                for (int j = 0; j < 4; j++) {

                    int redNx = temp.redX;
                    int redNy = temp.redY;
                    int blueNx = temp.blueX;
                    int blueNy = temp.blueY;

                    boolean redFlag = false;
                    boolean blueFlag = false;

                    while(map[redNx + xDirection[j]][redNy + yDirection[j]] != '#')
                    {
                        redNx += xDirection[j];
                        redNy += yDirection[j];

                        if (map[redNx][redNy] == 'O') {
                            redFlag = true;
                            break;
                        }
                    }

                    while(map[blueNx + xDirection[j]][blueNy + yDirection[j]] != '#')
                    {
                        blueNx += xDirection[j];
                        blueNy += yDirection[j];

                        if (map[blueNx][blueNy] == 'O') {
                            blueFlag = true;
                            break;
                        }
                    }

                    if (redFlag && !blueFlag) return true;
                    else if (!redFlag && blueFlag) continue;

                    if (redFlag && blueFlag) {

                        int redAbs = Math.abs(endRow - redNx) + Math.abs(endCol - redNy);
                        int blueAbs = Math.abs(endRow - blueNx) + Math.abs(endCol - blueNy);

                        if (redAbs < blueAbs) {
                            return true;
                        }
                        else continue;
                    }

                    if (redNx == blueNx && redNy == blueNy) {

                        int redAbs = Math.abs(temp.redX - redNx) + Math.abs(temp.redY - redNy);
                        int blueAbs = Math.abs(temp.blueX - blueNx) + Math.abs(temp.blueY - blueNy);

                        if (redAbs < blueAbs) {
                            blueNx -= xDirection[j];
                            blueNy -= yDirection[j];
                        }
                        else {
                            redNx -= xDirection[j];
                            redNy -= yDirection[j];
                        }
                    }


                    if(!visited[redNx][redNy][blueNx][blueNy]) {
                        visited[redNx][redNy][blueNx][blueNy] = true;
                        q.add(new Ball(redNx, redNy, blueNx, blueNy));
                    }


                }
            }
        }

        return false;

    }

    private static boolean isInvalid(int nx, int ny) {

        return nx < 0 || nx >= N || ny < 0 || ny >= M;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        String str;

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            map[i] = str.toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    redRow = i;
                    redCol = j;
                }

                else if (map[i][j] == 'B') {
                    blueRow = i;
                    blueCol = j;
                }

                else if (map[i][j] == 'O') {
                    endRow = i;
                    endCol = j;
                }
            }
        }


    }
}
