package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 나무타이쿤 {
    static int n, m;
    static int[][] map;
    static boolean[][] medicine;

    static int[] rDir = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] cDir = {1, 1, 0, -1, -1, -1, 0, 1};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        map = new int[n][n];
        medicine = new boolean[n][n];

        medicine[n - 1][0] = true;
        medicine[n - 1][1] = true;
        medicine[n - 2][0] = true;
        medicine[n - 2][1] = true;

        for (int i = 0; i < n; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < n ;j ++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine().split(" ");
            int d = Integer.parseInt(input[0]) - 1;
            int p = Integer.parseInt(input[1]);
            simulate(d, p);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);

    }

    private static void simulate(int dir, int p) {

        moveMedicine(dir, p);
    }

    private static void moveMedicine(int dir, int p) {

        boolean[][] temp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (medicine[i][j]) { // 영양제 위치한 경우
                    int nr = (i + p * rDir[dir]) % n;
                    int nc = (j + p * cDir[dir]) % n;
                    if (nr < 0) nr += n;
                    if (nc < 0) nc += n;

                    temp[nr][nc] = true;
                    medicine[i][j] = false;
                }
            }
        }

        // 영양제 투입
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                medicine[i][j] = temp[i][j];
                if (medicine[i][j]) {
                    map[i][j]++;
                    medicine[i][j] = false;
                }
            }
        }

        growTree(temp);
        buyMedicine(temp);

    }

    private static void buyMedicine(boolean[][] temp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j]) continue;

                if (map[i][j] >= 2) {
                    map[i][j] -= 2;
                    medicine[i][j] = true;
                }
            }
        }
    }

    private static void growTree(boolean[][] temp) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (temp[i][j]) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + rDir[(2 * k) + 1];
                        int nc = j + cDir[(2 * k) + 1];

                        if (isInvalid(nr, nc)) continue;
                        if (map[nr][nc] >= 1) cnt++;
                    }

                    map[i][j] += cnt;
                }
            }
        }
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= n || c < 0 || c >= n;
    }
}

