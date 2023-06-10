package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ17135 {

    /*
     * originalArr는 그대로 있어야 하고
     * copyArr가 매번 필요함
     *
     * copyArr = originalArr의 모양을 그대로 받고 시작하고
     *
     * 한 행이 끝나면 궁수이 위치를 i행으로 올려 해당 위치를 모두 -1로 바꾸고 시작한다
     * copyArr가 모
     */

    static int[][] original; // 원본 배열
    static int n,m,d; // 행, 열, 궁수의 사격거리
    static int[] placeArcher = new int[3]; // 궁수 배치시키기 위한 배열
    static int ans = 0;

    static int[] Xarr = {0,-1,0};
    static int[] Yarr = {-1,0,1};

    static class Enemy{

        int x;
        int y;

        public Enemy(int x, int y) {

            this.x=x;
            this.y=y;
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        d = Integer.parseInt(input[2]);
        original = new int [n+1][m];

        for (int i=0; i< n; i++) {
            input = br.readLine().split(" ");
            for (int j=0; j<m; j++) {
                original[i][j] = Integer.parseInt(input[j]);
            }
        }

        Combination(0,0); // 궁수 배치시키는 방법을 조합으로 구한다

        sb.append(ans);
        wr.write(sb.toString());
        wr.close();
        br.close();
    }

    private static void Combination(int start, int cnt) {

        if (cnt == 3) {
            Attack();
            return;
        }

        for (int i= start; i < m; i++) {
            placeArcher[cnt] = i;
            Combination(i+1, cnt+1);
        }


    }
    private static void Attack() {

        // 1. 배열 복사
        int[][] copyArr = new int [n+1][m];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++)
                copyArr[i][j] = original[i][j];
        }

        PriorityQueue<Enemy> pq = new PriorityQueue<>();

        // 2. 계산 로직 시작
        int killCount = 0;

        List<Enemy> list= new ArrayList<>();

        for (int i=n-1; i>=0; i--) { // 맨 아래에서부터

            for (int archer = 0; archer < 3; archer++) { // 각각의 archer가
                Enemy temp = Dead(i,placeArcher[archer],copyArr); // 가장 가까우 적을 찾으러 가보는데
                if (temp == null) continue; // 없으면 상관없다.

                list.add(temp);
            }

            for (int j=0; j<list.size(); j++) {

                int x = list.get(j).x;
                int y = list.get(j).y;

                if (copyArr[x][y] == 1) { // 살아있는 적, 즉 최초만 killCount++
                    killCount++;
                    copyArr[x][y] = 0;
                }
            }

        }

        //System.out.println(killCount);
        ans = Math.max(ans, killCount);

    }
    private static Enemy Dead(int x, int y, int[][] copyArr) {

        Queue<Enemy> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        q.add(new Enemy(x,y));
        Enemy temp;
        int nx,ny;
        while(!q.isEmpty()) {

            temp = q.poll();

            if(visited[temp.x][temp.y]) continue;

            if(copyArr[temp.x][temp.y] == 1) {
                return temp;
            }

            visited[temp.x][temp.y] = true;

            for (int i=0; i<3; i++) {
                nx = temp.x + Xarr[i];
                ny = temp.y + Yarr[i];
                if (isIn(nx,ny) && CalculateDist(x+1, y, nx, ny)) {
                    q.add(new Enemy(nx,ny));
                }
            }
        }

        return null;
    }

    private static boolean isIn(int nx, int ny) {

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) return false;

        return true;
    }

    private static boolean CalculateDist(int archerRow, int archerCol, int enemyRow, int enemyCol) {

        int dist = Math.abs(archerRow - enemyRow) + Math.abs(archerCol - enemyCol);

        if (dist <= d) {
            return true;
        }

        return false;
    }



}