package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ3190 {

    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class DirectionInfo {
        int time;
        int dir;

        public DirectionInfo(int time, int dir) {
            this.time=time;
            this.dir=dir;
        }
    }
    static int n;
    static int l;
    static int[][] map;
    static DirectionInfo[] infos;
    static String[] input;

    static int[] xDircetion = {0,1,0,-1};
    static int[] yDirection = {1,0,-1,0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        init();
        System.out.println(calculate());

    }

    private static int calculate() {

        Queue<Coordination> q = new ArrayDeque<>();
        q.add(new Coordination(1,1));

        int tailX = 1;
        int tailY = 1;
        int headX = 1;
        int headY = 1;

        int curDir = 0;

        int cnt = 0;
        int idx = 0;
        map[tailX][tailY] = 1;

        while(true) {

            headX += xDircetion[curDir];
            headY += yDirection[curDir];

            if (!isIn(headX,headY) || map[headX][headY] == 1) {
                break;
            }

            q.add(new Coordination(headX,headY));

            if (map[headX][headY] == Integer.MAX_VALUE) { // 1. 사과인 경우
                map[headX][headY] = 1;
            }

            else if (map[headX][headY] == 0) { // 2. 사과가 없는 경우
                map[tailX][tailY] = 0; // 꼬리를 빼주고
                q.poll(); // 왔던 흔적을 지워주고
                tailX = q.peek().x;
                tailY = q.peek().y;
                map[headX][headY] = 1;
            }

            cnt++;


            if (idx < l && cnt == infos[idx].time) {
                curDir = changeDir(curDir,infos[idx].dir);
                idx++;
            }

        }

        return cnt + 1;
    }

    private static boolean isIn(int nx, int ny) {
        if (nx < 1 || nx >= n + 1 || ny < 1 || ny >= n + 1) return false;

        return true;
    }

    private static int changeDir(int curDir, int dir) {

        curDir = curDir + dir;

        if (curDir == -1) return 3;

        else return (curDir % 4);
    }

    private static void init() throws IOException {
        int k;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];

        int x,y;
        for (int i = 0; i < k; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);

            map[x][y] = Integer.MAX_VALUE; // 사과는 Integer.MAX_VALUE로 표시
        }

        l = Integer.parseInt(br.readLine());
        infos = new DirectionInfo[l];

        int time;
        char direction;
        int dir;
        for (int i = 0; i < l; i++) {
            input = br.readLine().split(" ");
            time = Integer.parseInt(input[0]);

            direction = input[1].charAt(0);

            if (direction == 'L') {
                dir = -1;
            }
            else {
                dir = 1;
            }
            infos[i] = new DirectionInfo(time,dir);

        }
    }
}
