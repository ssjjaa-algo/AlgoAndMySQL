package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ19238 {

    static int N, M, fuel;
    static int[][] map;
    static boolean[][] visited;
    static int taxiRow, taxiCol;
    static int[] xDirection = {-1, 0, 0, 1};
    static int[] yDirection = {0, -1, 1, 0};
    static class Customer {
        int startRow, startCol, arriveRow, arriveCol;

        public Customer(int startRow, int startCol, int arriveRow, int arriveCol) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.arriveRow = arriveRow;
            this.arriveCol = arriveCol;
        }
    }
    static class Node implements Comparable<Node> {
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Node o) {
            if (this.row == o.row) return Integer.compare(this.col, o.col);
            return Integer.compare(this.row, o.row);
        }
    }

    static Customer[] customers;

    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        for (int i = 0; i < M; i++) {

            int customer = findNearestCustomer();
            if (customer == -1) {
                fuel = -1;
                break;
            }


            taxiRow = customers[customer - 2].startRow;
            taxiCol = customers[customer - 2].startCol;
            map[taxiRow][taxiCol] = 0; // 해당 위치에는 더이상 승객이 없음을 표시

            int distance = goToArrivePosition(customer);

            if (distance == -1) {
                fuel = -1;
                break;
            }

            taxiRow = customers[customer - 2].arriveRow;
            taxiCol = customers[customer - 2].arriveCol;
            fuel += (distance) * 2;

        }

        System.out.println(fuel);

    }

    private static int goToArrivePosition(int customer) {

        int arriveRow = customers[customer - 2].arriveRow;
        int arriveCol = customers[customer - 2].arriveCol;

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(taxiRow, taxiCol));
        visited[taxiRow][taxiCol] = true;

        int cnt = 0;
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Node cur = q.poll();
                int row = cur.row;
                int col = cur.col;

                if (row == arriveRow && col == arriveCol) {
                    if (fuel - cnt < 0) return -1;

                    fuel -= cnt;
                    return cnt;
                }

                for (int j = 0; j < 4; j++) {
                    int nr = row + xDirection[j];
                    int nc = col + yDirection[j];

                    if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;

                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }


            }
            cnt++;
        }

        return -1;

    }

    private static int findNearestCustomer() {

        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        if (map[taxiRow][taxiCol] > 1) return map[taxiRow][taxiCol];

        Queue<Node> q = new ArrayDeque<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        q.add(new Node(taxiRow, taxiCol));
        visited[taxiRow][taxiCol] = true;

        int cnt = 0;
        while(!q.isEmpty()) {

            if (!pq.isEmpty()) {
                if (fuel - cnt < 0) return -1;

                fuel -= cnt;
                Node passenger = pq.poll();

                return map[passenger.row][passenger.col];
            }
            int size = q.size();

            for (int i = 0; i < size; i++) {

                Node cur = q.poll();
                int row = cur.row;
                int col = cur.col;

                for (int j = 0; j < 4; j++) {
                    int nr = row + xDirection[j];
                    int nc = col + yDirection[j];

                    if (isInvalid(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;

                    if (map[nr][nc] > 1) {
                        pq.add(new Node(nr, nc));
                    }

                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc));
                }


            }
            cnt++;
        }

        return -1;
    }

    private static boolean isInvalid(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= N;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        fuel = Integer.parseInt(input[2]);

        map = new int[N][N];
        visited = new boolean[N][N];
        customers = new Customer[M];

        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        input = br.readLine().split(" ");
        taxiRow = Integer.parseInt(input[0]) - 1;
        taxiCol = Integer.parseInt(input[1]) - 1;

        for (int i = 2; i < M + 2; i++) {
            input = br.readLine().split(" ");
            int startRow = Integer.parseInt(input[0]) - 1;
            int startCol = Integer.parseInt(input[1]) - 1;
            int arriveRow = Integer.parseInt(input[2]) - 1;
            int arriveCol = Integer.parseInt(input[3]) - 1;
            customers[i - 2] = new Customer(startRow, startCol, arriveRow, arriveCol);

            map[startRow][startCol] = i; // 해당 위치에 손님이 있음을 명시한다.
        }

    }
}
