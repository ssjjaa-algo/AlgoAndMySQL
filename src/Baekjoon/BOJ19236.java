package Baekjoon;

import java.io.*;

public class BOJ19236 {

    static class Fish{
        int x, y, dir;
        boolean alive;

        public Fish(int x, int y, int dir, boolean alive) {
            this.x=x;
            this.y=y;
            this.dir=dir;
            this.alive=alive;
        }
    }

    static int[] xDirection = {-1,-1,0,1,1,1,0,-1};
    static int[] yDirection = {0,-1,-1,-1,0,1,1,1};
    static int[][] map = new int[4][4];
    static Fish[] fishes = new Fish[17];
    static int ans = 0;
    public static void main(String[] args) throws IOException{
        init();

        // (0,0) 위치의 물고기를 먹고 시작하며, sum을 기록한다.
        int sharkDir = fishes[map[0][0]].dir;
        int sum = map[0][0];
        fishes[map[0][0]].alive = false;
        map[0][0] = -1;
        dfs(0,0,sharkDir,sum);

        System.out.println(ans);
    }

    private static void dfs(int sharkX, int sharkY, int sharkDir, int sum) {
        ans = Math.max(ans,sum);

        int[][] copyMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        Fish[] copyFishes = new Fish[17];

        for (int i =1; i <= 16; i++) {
            copyFishes[i] = new Fish(fishes[i].x,fishes[i].y,fishes[i].dir,fishes[i].alive);
        }

        moveFish();

        for (int i = 1; i <= 3; i++) {

            int nx = sharkX + xDirection[sharkDir] * i;
            int ny = sharkY + yDirection[sharkDir] * i;

            if (!isIn(nx,ny)) break;

            if (map[nx][ny] == 0) continue;

            int eat = map[nx][ny];
            int nxDir = fishes[map[nx][ny]].dir;

            map[sharkX][sharkY] = 0;
            map[nx][ny] = -1;
            fishes[eat].alive=false;

            dfs(nx,ny,nxDir,sum + eat);

            map[sharkX][sharkY] = -1;
            map[nx][ny] = eat;
            fishes[eat].alive=true;
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = copyMap[i][j];
            }
        }

        for (int i =1; i <= 16; i++) {
            fishes[i] = new Fish(copyFishes[i].x,copyFishes[i].y,copyFishes[i].dir,copyFishes[i].alive);
        }

    }

    private static void moveFish() {
        for (int i = 1; i <= 16; i++) {
            if(!fishes[i].alive) continue;

            int curX = fishes[i].x;
            int curY = fishes[i].y;
            int curDir = fishes[i].dir;

            int nx, ny, nxDir;
            for (int j = 0; j < 8; j++) {
                nxDir = (curDir + j) % 8;
                nx = curX + xDirection[nxDir];
                ny = curY + yDirection[nxDir];
                fishes[i].dir = nxDir;

                if (!isIn(nx,ny) || map[nx][ny] == -1) continue;

                if (map[nx][ny] == 0) {
                    fishes[i].x=nx;
                    fishes[i].y=ny;

                    map[nx][ny]=i;
                    map[curX][curY] = 0;

                    break;
                }
                else {
                    int changeFish = map[nx][ny];
                    fishes[changeFish].x=curX;
                    fishes[changeFish].y=curY;
                    map[curX][curY] = changeFish;


                    fishes[i].x=nx;
                    fishes[i].y=ny;
                    map[nx][ny] = i;

                    break;
                }
            }
        }
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) return false;
        return true;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input;

        for (int i = 0 ; i < 4; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(input[j * 2]);
                int direction = Integer.parseInt(input[(j * 2) + 1]);
                map[i][j] = num;
                fishes[num] = new Fish(i,j,direction - 1,true);
            }
        }

    }
}
