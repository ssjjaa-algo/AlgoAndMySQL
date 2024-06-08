package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 술래잡기 {

    static int n,m,k,h;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};

    static class Node {
        int r, c, dir;

        public Node(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }
    static Set<Integer>[][] liveMap;
    static boolean[] live;
    static Node[] runaway;
    static int[][] treeMap;

    static Node[] sequence;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine() .split(" ");

        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        h = Integer.parseInt(input[2]);
        k = Integer.parseInt(input[3]);

        treeMap = new int[n][n];
        liveMap = new HashSet[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) liveMap[i][j] = new HashSet<>();
        }
        live = new boolean[m];
        Arrays.fill(live, true);
        runaway = new Node[m];
        sequence = getSequence();

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]) - 1;
            int c = Integer.parseInt(input[1]) - 1;
            int d = Integer.parseInt(input[2]);
            runaway[i] = new Node(r, c, d);
            liveMap[r][c].add(i);
        }

        for (int i = 0; i < h; i++) {
            input = br.readLine().split(" ");
            int r = Integer.parseInt(input[0]) - 1;
            int c = Integer.parseInt(input[1]) - 1;
            treeMap[r][c] = 1;
        }

        calculate();

    }

    private static void calculate() {

        int time = 0;
        int ans = 0;
        while (time < k) {
            move(time % ((2 * n * n) - 2));
            time++;
            ans += killRunaway(time % ((2 * n * n) - 2), time);
        }

        System.out.println(ans);
    }

    private static int killRunaway(int position, int time) {

        Node boss = sequence[position];

        int r = boss.r - rDir[boss.dir];
        int c = boss.c - cDir[boss.dir];

        int score = 0;
        for (int i = 0; i < 3; i++) {

            r += rDir[boss.dir];
            c += cDir[boss.dir];

            if (isInvalid(r,c)) break;
            if (treeMap[r][c] == 1) continue;

            score += liveMap[r][c].size();

            for (int num : liveMap[r][c]) {
                live[num] = false;
            }

            liveMap[r][c].clear();
        }

        return time * score;
    }

    private static void move(int time) {

        Node boss = sequence[time];

        for (int i = 0; i < m; i++) {

            if (!live[i]) continue;

            int distance = getDistance(boss.r, boss.c, runaway[i].r, runaway[i].c);
            if (distance > 3) continue;

            int nr = runaway[i].r + rDir[runaway[i].dir];
            int nc = runaway[i].c + cDir[runaway[i].dir];

            if (isInvalid(nr, nc)) {
                runaway[i].dir = (runaway[i].dir + 2) % 4;
                nr = runaway[i].r + rDir[runaway[i].dir];
                nc = runaway[i].c + cDir[runaway[i].dir];
            }

            if (nr == boss.r && nc == boss.c) continue;

            liveMap[runaway[i].r][runaway[i].c].remove(i);
            liveMap[nr][nc].add(i);
            runaway[i].r = nr;
            runaway[i].c = nc;

        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }

    private static int getDistance(int r, int c, int r1, int c1) {

        return Math.abs(r1 - r) + Math.abs(c1 - c);
    }


    private static Node[] getSequence() {

        List<Node> list = new ArrayList<>();
        int r = n / 2;
        int c = n / 2;

        int step = 1;
        int count = 0;
        int dir = 0;
        list.add(new Node(r, c,0));
        while(!(r == 0 && c == 0)) {

            r += rDir[dir];
            c += cDir[dir];
            count++;

            if (step == count) {
                count = 0;
                dir = (dir + 1) % 4;

                if (dir == 0 || dir == 2) step++;
            }
            list.add(new Node(r, c, dir));
        }
        list.remove(list.size() -1);

        step = n;
        count = 1;
        dir = 2;
        list.add(new Node(0, 0, 2));
        while(!(r == n / 2 && c == n / 2)) {

            r += rDir[dir];
            c += cDir[dir];
            count++;

            if (step == count) {
                count = 0;
                dir = dir - 1;
                if (dir < 0) dir = 3;

                if (dir == 1 || dir == 3) step--;
            }
            list.add(new Node(r, c, dir));
        }
        list.remove(list.size() - 1);


        return list.toArray(new Node[0]);
    }

}
