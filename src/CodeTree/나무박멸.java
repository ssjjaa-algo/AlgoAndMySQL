package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 나무박멸 {

    static int n, m, k, time;
    static int[][] map;
    static int[][] killer;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};
    static int[] rDiagonal = {-1, -1, 1, 1};
    static int[] cDiagonal = {-1, 1, 1, -1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        time = Integer.parseInt(input[3]);
        map = new int[n][n];
        killer = new int[n][n];

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        calculate();
    }

    private static void calculate() {

        int ans = 0;
        while (m-- > 0) {
            if (allDead()) break;
            decreaseSpray();
            growTree(); // 1. 나무의 성장
            breeding(); // 2. 번식
            ans += findKillerPosition();
        }

        System.out.println(ans);
    }

    private static void decreaseSpray() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (killer[i][j] == 0) continue;
                killer[i][j]--;
            }
        }
    }

    private static boolean allDead() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0) return false;
            }
        }
        return true;
    }

    private static int findKillerPosition() {

        int max = 0;
        int r = -1;
        int c = -1;
        for (int i = 0; i < n; i++) { // 제초제를 가장 많이 뿌리는 칸을 찾는다.
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue; // 나무가 없는 칸에 제초제를 뿌리면 박멸되는 나무가 없다.
                int kill = spread(i, j);
                if (kill > max) {
                    max = kill;
                    r = i;
                    c = j;
                }
            }
        }

        fillSpray(r, c); // 제초제 뿌리기
        return max;

    }

    private static void fillSpray(int r, int c) {

        map[r][c] = 0;
        killer[r][c] = time + 1;

        for (int i = 0; i < 4; i++) { // 4번의 방향에 대해서
            for (int j = 1; j <= k; j++) { // k번 이동해야 한다.
                int nr = r + j * rDiagonal[i];
                int nc = c + j * cDiagonal[i];
                if (isInvalid(nr, nc)) break;

                killer[nr][nc] = time + 1;
                if (map[nr][nc] == 0 || map[nr][nc] == -1) break; // 나무가 없는 칸이거나 벽인 경우 여기까지.
                map[nr][nc] = 0;
            }
        }
    }

    private static int spread(int r, int c) {

        int score = map[r][c];
        for (int i = 0; i < 4; i++) {
            score += kill(r, c, i);
        }

        return score;
    }

    private static int kill(int r, int c, int dir) {

        int score = 0;
        for (int i = 0; i < k; i++) {
            r += rDiagonal[dir];
            c += cDiagonal[dir];
            if (isInvalid(r, c) || map[r][c] == -1 || map[r][c] == 0) return score;

            score += map[r][c];
        }

        return score;
    }

    private static void breeding() {

        int[][] temp = new int[n][n]; // 번식을 동시에 하기 위해 임시 배열 선언

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || map[i][j] == -1 ) continue; // 나무가 있는 칸에 대해서

                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + rDir[k];
                    int nc = j + cDir[k];
                    if (isInvalid(nr, nc) || map[nr][nc] != 0 || killer[nr][nc] != 0) continue; // 빈칸이고 제초제 없는 칸에 대해서

                    cnt++; // 그러한 칸이 몇개인지 세주고
                }

                if (cnt == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int nr = i + rDir[k];
                    int nc = j + cDir[k];
                    if (isInvalid(nr, nc) || map[nr][nc] != 0 || killer[nr][nc] != 0) continue; // 빈칸이고 제초제 없는 칸에 대해서

                    temp[nr][nc] += (map[i][j] / cnt); // 각 칸의 나무 그루수 / 번식이 가능한 칸의 개수
                }
            }
        }

        // 번식한 나무들의 결과를 모두 원본에 +
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == -1) continue;
                map[i][j] += temp[i][j];
            }
        }

    }

    private static void growTree() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0 || map[i][j] == -1) continue;
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + rDir[k];
                    int nc = j + cDir[k];
                    if (isInvalid(nr, nc) || map[nr][nc] == 0 || map[nr][nc] == -1) continue;
                    cnt++;
                }
                map[i][j] += cnt; // 인접한 나무의 수만큼 성장한다.
            }
        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}

/*

제초제 : k 범위만큼 대각선으로 퍼진다.
   - 벽이 있는 경우 가로막혀서 전파되지 않는다.

1. 나무의 성장
   - 상하좌우로 인접한 칸 중에서, 나무가 있는 칸만큼 수가 증가한다 (4방향에 나무가 다 있다면 4 증가)

2. 기존에 있었던 나무들은 인접한 4개의 칸에서 번식을 진행
   - 벽이 아니고, 다른 나무가 없고, 제초제가 없는 칸에 번식을 진행한다.

   - 자신의 나무 그루 수 / 자신의 위치에서 번식 가능한 칸의 개수만큼 빈 칸에 나무++

   - 번식의 과정은 모든 나무에서 동시에 일어나기 때문에, 여기에 temp 배열을 사용해두고
   - 모든 번식이 끝나면 나무 배열에 temp배열 값을 더해준다.


3. 제초제를 뿌린다
   - 나무가 가장 많이 박멸되는 칸에 제초제를 뿌린다.
   - 나무가 없는 칸에 뿌리면 박멸되는 것이 아무것도 없다.

   - 나무가 있는 칸에 제초제를 뿌리면 4방향의 대각선으로 k칸만큼 전파한다.
      - 도중에 벽이 있거나 나무가 없는 칸이 있는 경우, 해당 칸 까지만 제초제가 뿌려진다.
      - 제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가, c + 1년째가 될 때 사라진다.
      - 제초제가 뿌려진 곳에 다시 제초제가 뿌려지는 경우 새로 뿌려진 해로부터 다시 c년동안 제초제가 유지된다.

   - 박멸시키는 나무의 수가 동일한 칸이 있는 경우 행이 작은 순서, 다음은 열이 작은 순서(순서대로 하면 된다.)


*/