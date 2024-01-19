package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
열쇠 a, b, c, d, e, f를 가질 수 있는 경우의 수 : 64개
visited[64][N][M] 필요

1. 현재 키를 저장하는 방법
    현재 가지고 있는 key와 등장한 key를 OR 연산하면 등장한 키들이 key에 누적

2. 현재 키를 가지고 있는지 확인하는 방법
    현재 가지고 있는 key와 등장한 key를 AND 연산

3. 계산 로직
    - bfs의 큐에 담을 Node는 x,y와 현재 key의 상태
        - 1. 범위 안에 들어와있지 않거나 이미 방문지점이거나 벽('#')인 경우 continue
        - 2. 빈칸인 경우 큐에 대입 ( key 변화 x )
        - 3. 해당 위치에 키가 위치하는 경우( a ~ f )
            1. 현재 가지고 있는 key와 OR 연산하여 큐에 대입
        - 4. 해당 위치에 문이 존재하는 경우( A ~ F )
            1. 현재 가지고 있는 key와 AND 연산
                1. 값이 1이라면 ok
                2. 값이 0이라면 continue


 */
public class BOJ1194 {

    static int N,M;
    static char[][] map;
    static boolean[][][] visited;
    static int startX, startY;
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};

    static class Node {
        int x, y, key;

        public Node(int x, int y, int key) {
            this.x=x;
            this.y=y;
            this.key=key;
        }
    }
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(bfs());

    }

    private static int bfs() {

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(startX, startY,0));
        visited[0][startX][startY] = true;

        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Node cur = q.poll();

                for (int j = 0; j < 4; j++) {
                    int nx = cur.x + xDirection[j];
                    int ny = cur.y + yDirection[j];
                    int key = cur.key;

                    if (isInvalid(nx,ny) || visited[key][nx][ny] || map[nx][ny] == '#') continue;

                    if (map[nx][ny] == '1') return cnt + 1;

                    if (map[nx][ny] == '.') {
                        visited[key][nx][ny] = true;
                        q.add(new Node(nx,ny,key));
                    }

                    else if (map[nx][ny] >= 'a' && map[nx][ny] <='f') { // 키인 경우
                        int nextKey = key | (1 << map[nx][ny] - 'a');
                        visited[key][nx][ny] = true;
                        q.add(new Node(nx,ny,nextKey));
                    }

                    else if (map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {

                        int hasKey = key & (1 << map[nx][ny] -'A');

                        if (hasKey != 0) {
                            visited[key][nx][ny] = true;
                            q.add(new Node(nx,ny,key));
                        }
                    }

                }
            }

            cnt++;
        }

        return -1;
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
        visited = new boolean[64][N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') {
                    startX = i;
                    startY = j;
                    map[i][j] = '.';
                }
            }
        }
    }
}
