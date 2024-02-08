package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ19237 {

    /**
     * 1. 맨 처음에 모든 상어가 자신의 위치에 냄새를 뿌린다.
     * 2. 아래의 과정이 한 메서드에서 일어나게 설정한다. (우선순위 방향 기준으로)
     *      1. 인접한 칸 중 아무 냄새가 없는 칸을 찾아서 이동한다.
     *      2. 1.에 해당하는 경우가 없다면 인접한 칸 중 자신의 냄새가 있는 방향으로 이동한다.
     *          - 이 경우를 판단하기 위해 flag 변수가 필요하다. 1번 패스하면 2번 과정이 일어나지 않도록.
     *      3. 이동한 상어를 temp (임시 배열)에 집어넣어둔다.
     *          1. 번호가 작은 상어부터 이동하게 하여 후에 들어오는 상어가 같은 위치를 점유하는 경우 그 상어는 죽게 된다.
     *
     * 상어가 이동하는 시점은 동시에 이동한다. 즉, 이동 후의 냄새 반영은 모든 상어가 이동하고 난 후 확인해야 한다.
     * --> 임시 배열에 어떤 지점에 상어가 위치했음을 나타내는 배열이 매 반복마다 필요하다.
     *
     * 상어의 냄새와 시간을 가지고 있는 배열이 필요하다.
     */

    static int N, M, k;
    static int[] xDirection = {-1,1,0,0};
    static int[] yDirection = {0,0,-1,1};
    static int currentSharkCnt;
    static class Shark {
        int x, y, direction;
        int[][] priority;

        public Shark(int x, int y, int direction, int[][] priority) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.priority = priority;
        }
    }

    static class Smell {
        int shark, time;

        public Smell(int shark, int time) {
            this.shark = shark;
            this.time = time;
        }
    }

    static Smell[][] smells;
    static Shark[] sharks;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static int calculate() {

        int cnt = 0;

        while (cnt <= 1000) {

            if (currentSharkCnt == 1) return cnt;
            cnt++;

            int[][] temp = new int[N][N];

            for (int i = 1; i <= M; i++) { // 1. 살아있는 상어 이동
                if (sharks[i] != null) moveShark(i, sharks[i], temp);
            }

            for (int i = 0; i < N; i++) { // 2. 이동 전의 상어 냄새 시간 줄이기
                for (int j = 0; j < N; j++) {
                    if (smells[i][j].shark == 0) continue;

                    smells[i][j].time--;

                    if (smells[i][j].time == 0) {
                        smells[i][j].shark = 0;
                        smells[i][j].time = 0;
                    }
                }
            }

            for (int i = 0; i < N; i++) { // 3. 이동한 상어 냄새 갱신해주기
                for (int j = 0; j < N; j++) {
                    if (temp[i][j] != 0) {
                        smells[i][j].shark = temp[i][j];
                        smells[i][j].time = k;
                    }
                }
            }
        }

        return -1;
    }

    private static void moveShark(int num, Shark shark, int[][] temp) {

        if (isValidToMove(0, shark, temp)) return; // 냄새가 없는 지점으로 이동
        isValidToMove(num, shark, temp); // 자신의 냄새로 이동

    }

    private static boolean isValidToMove(int num, Shark shark, int[][] temp) {

        for (int i = 0; i < 4; i++) {
            int direction = shark.priority[shark.direction][i];

            int nx = shark.x + xDirection[direction];
            int ny = shark.y + yDirection[direction];

            if (isInvalid(nx,ny) || smells[nx][ny].shark != num) continue;

            check(smells[shark.x][shark.y].shark, nx, ny, direction, temp);
            return true;
        }

        return false;
    }


    private static void check(int num, int nx, int ny, int direction, int[][] temp) {

        if (temp[nx][ny] != 0) { // 번호가 작은 순서대로 상어가 들어오기 때문에 이미 누가 들어왔다면 해당 상어는 쫓겨난다.
            sharks[num] = null;
            currentSharkCnt--;
            return;
        }

        // 바뀐 값으로 세팅
        temp[nx][ny] = num;
        sharks[num].x = nx;
        sharks[num].y = ny;
        sharks[num].direction = direction;

    }


    private static boolean isInvalid(int nx, int ny) {

        return nx < 0 || nx >= N || ny < 0 || ny >= N;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);

        sharks = new Shark[M + 1];
        smells = new Smell[N][N];
        currentSharkCnt = M;

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int shark = Integer.parseInt(input[j]);

                if (shark != 0) {
                    sharks[shark] = new Shark(i,j,0,null);
                    smells[i][j] = new Smell(shark, k); // k만큼의 냄새 초기화
                }
                else {
                    smells[i][j] = new Smell(0,0);
                }
            }
        }

        input = br.readLine().split(" ");
        for (int i = 1; i <= M; i++) {
            sharks[i].direction = Integer.parseInt(input[i - 1]) - 1;
        }

        for (int i = 1; i <= M; i++) {

            int[][] priority = new int[4][4]; // 4방향의 우선순위를 가지는 배열.
            for (int j = 0; j < 4; j++) {
                input = br.readLine().split(" ");
                for (int k = 0; k < 4; k++) {
                    priority[j][k] = Integer.parseInt(input[k]) - 1;
                }
            }
            sharks[i].priority = priority;
        }
    }
}