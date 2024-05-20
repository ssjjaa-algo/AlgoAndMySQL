package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 루돌프의반란 {
    static int N,M,P,C,D;
    static int[][] map;
    static int rudolR, rudolC;
    static Santa[] santas;
    static int[] rDirection = {-1, 0, 1, 0};
    static int[] cDirection = { 0, 1, 0, -1};

    static class Santa {

        int row, col, score, stun;
        boolean live;

        public Santa(int row, int col) {
            this.row = row;
            this.col = col;
            this.score = 0;
            this.stun = 0;
            this.live = true;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        P = Integer.parseInt(input[2]);
        C = Integer.parseInt(input[3]);
        D = Integer.parseInt(input[4]);
        map = new int[N + 1][N + 1];
        santas = new Santa[P + 1];

        input = br.readLine().split(" ");
        rudolR = Integer.parseInt(input[0]);
        rudolC = Integer.parseInt(input[1]);

        for (int i = 1; i <= P; i++) {
            input = br.readLine().split(" ");
            int number = Integer.parseInt(input[0]);
            int row = Integer.parseInt(input[1]);
            int col = Integer.parseInt(input[2]);
            santas[number] = new Santa(row, col);
            map[row][col] = number;
        }

        calculate();
    }

    private static void calculate() {

        while (M-- > 0) {

            if (allDead()) break;

            int distance = Integer.MAX_VALUE;
            int maxR = 0;
            int maxC = 0;
            for (int i = 1; i <= P; i++) {
                if (!santas[i].live) continue;

                int d = getDistance(santas[i].row, santas[i].col);
                if (d > distance) continue;
                else if (d == distance) {
                    if (santas[i].row > maxR) {
                        maxR = santas[i].row;
                        maxC = santas[i].col;
                    }

                    else if (santas[i].row == maxR && santas[i].col > maxC) {
                        maxR = santas[i].row;
                        maxC = santas[i].col;
                    }
                }
                else {
                    maxR = santas[i].row;
                    maxC = santas[i].col;
                    distance = d;
                }
            }

            int[] direction = getDirection(maxR, maxC);
            checkCrashed(C, direction);

            for (int i = 1; i <= P; i++) {
                if (!santas[i].live || santas[i].stun > 0) continue;

                int d = getDistance(santas[i].row, santas[i].col);
                int dir = 999;
                for (int j = 0; j < 4; j++) {

                    int nr = santas[i].row + rDirection[j];
                    int nc = santas[i].col + cDirection[j];

                    if (isInvalid(nr, nc) || map[nr][nc] != 0) continue;
                    int nd = getDistance(nr, nc);

                    if (d > nd) {
                        d = nd;
                        dir = j;

                    }
                }

                if (dir == 999) continue;
                map[santas[i].row][santas[i].col] = 0;
                santas[i].row += rDirection[dir];
                santas[i].col += cDirection[dir];
                map[santas[i].row][santas[i].col] = i;
                checkCrashed(D, new int[]{rDirection[dir] * -1, cDirection[dir] * -1});
            }

            for (int i = 1; i <= P; i++) {
                if (!santas[i].live) continue;
                if (santas[i].stun > 0) santas[i].stun--;
                santas[i].score++;
            }

        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= P; i++) {
            sb.append(santas[i].score).append(" ");
        }
        System.out.println(sb);
    }

    private static void checkCrashed(int score, int[] direction) {

        int target = map[rudolR][rudolC];
        if (target == 0) return;

        Santa s = santas[target];
        s.stun = 2;
        s.score += score;
        map[s.row][s.col] = 0;
        s.row += (direction[0] * score);
        s.col += (direction[1] * score);
        if (isInvalid(s.row, s.col)) {
            map[rudolR][rudolC] = 0;
            s.live = false;
            return;
        }

        interaction(s, target, direction);

    }

    private static void interaction(Santa s, int target, int[] direction) {

        int number = target;

        int row = s.row;
        int col = s.col;
        while (true) {

            if (isInvalid(row, col)) {
                santas[number].live = false;
                break;
            }

            if (map[row][col] == 0) {
                map[row][col] = number;
                santas[number].row = row;
                santas[number].col = col;
                break;
            }

            int temp = map[row][col];
            map[row][col] = number;
            row += direction[0];
            col += direction[1];
            santas[temp].row = row;
            santas[temp].col = col;
            number = temp;
        }

    }

    private static boolean allDead() {

        for (int i = 1; i <= P; i++) {
            if (santas[i].live) return false;
        }

        return true;
    }


    private static boolean isInvalid(int row, int col) {
        return row < 1 || row > N || col < 1 || col > N;
    }

    private static int[] getDirection(int row, int col) {

        int r = 0;
        int c = 0;
        if (row < rudolR) {
            r = -1;
            rudolR--;
        }
        else if (row > rudolR) {
            r = 1;
            rudolR++;
        }

        if (col < rudolC) {
            c = -1;
            rudolC--;
        }
        else if (col > rudolC) {
            c = 1;
            rudolC++;
        }

        return new int[]{r, c};
    }

    private static int getDistance(int row, int col) {

        return (row - rudolR) * (row - rudolR) + (col - rudolC) * (col - rudolC);
    }
}


/*

1. 산타의 좌표 기록

2. 루돌프는 매 번, 살아있는 산타들과 거리 비교
   - 거리를 기준으로 거리가 작거나 같은 산타를 만났을 때마다 row, col에 따라 갱신

3. 루돌프가 산타로 향했는데 충돌한 경우
   - 산타는 C의 점수를 얻는다.
   - 산타는 C칸 만큼 밀려난다. 루돌프가 움직인 방향 (x, y)만큼, 이동은 즉시 된다
   - 산타는 기절 상태가 된다. stun을 2로 준다.

   if (stun > 0) continue;

4. 산타가 밀려나서 상호작용이 일어난 경우
   - 산타는 해당 방향으로 1칸 이동, while문

*/
