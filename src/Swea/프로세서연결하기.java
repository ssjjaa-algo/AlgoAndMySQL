package Swea;


import java.io.*;
import java.util.*;

class 프로세서연결하기
{
    static int[][] map;
    static int[][] board;
    static int N;
    static int connectedCore, ans, core;
    static int[] rDir = {-1, 0, 1, 0};
    static int[] cDir = {0, 1, 0, -1};
    static int[] coreDir;
    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static List<Node> cores;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            board = new int[N][N];
            cores = new ArrayList<>();
            connectedCore = 0;
            ans = Integer.MAX_VALUE;
            core = 0;
            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(input[j]);
                    if (map[i][j] == 1) {
                        if (i == 0 || i == N - 1 || j == 0 || j == N -1) continue;
                        core++;
                        cores.add(new Node(i, j));
                    }
                }
            }
            coreDir = new int[core];
            dfs(0, core); // coreDir에 모든 방향 경우의 수 설정한다. 코어 최대 12개 * 4방향 = 4^12 = 약 3만.

            sb.append("#").append(test_case).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static boolean isInvalid(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static void dfs(int cnt, int core) {

        if (cnt == core) {
            copy();

            int coreCnt = 0;
            int length = 0;

            for (int i = 0; i < core; i++) {

                int remain = core - i;
                if (coreCnt + remain < connectedCore) return;
                Node node = cores.get(i);
                int tempCnt = 0;
                boolean isConnected = false;
                int nr = node.r;
                int nc = node.c;
                while(true) {
                    nr += rDir[coreDir[i]];
                    nc += cDir[coreDir[i]];
                    if (isInvalid(nr, nc)) {
                        isConnected = true;
                        break;
                    }

                    if (map[nr][nc] == 1 || board[nr][nc] == 1) break;
                    board[nr][nc] = 1;
                    tempCnt++;
                }

                if (isConnected) {
                    coreCnt++;
                    length += tempCnt;
                }
            }

            if (coreCnt > connectedCore) {
                connectedCore = coreCnt;
                ans = length;
            }
            else if (coreCnt == connectedCore && ans > length) {
                ans = length;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            coreDir[cnt] = i;
            dfs(cnt + 1, core);
        }
    }

    private static void copy() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = map[i][j];
            }
        }
    }
}

