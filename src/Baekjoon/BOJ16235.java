package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
하나의 칸에 여러 개의 나무가 있다 나이가 어린 나무부터 양분을 먹는다.
땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없다 양분을 먹지 못하고 즉시 죽는다.

여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.

가을에는 나무가 번식한다. 번식하는 나무의 나이는 5배수여야 하며, 인접한 칸 8칸에 나이가 1인 나무가 생긴다.

겨울에는 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이며, 입력으로 주어진다.

 */
public class BOJ16235 {

    static int N,M,K;
    static int[][] map;
    static int[][] currentFoodMap;
    static PriorityQueue<Integer>[][] q;
    static List<Integer>[][] dieTree;
    static int[] xDirection = {-1,-1,0,1,1,1,0,-1};
    static int[] yDirection = {0,1,1,1,0,-1,-1,-1};

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ans += q[i][j].size();
            }
        }

        return ans;
    }

    private static void winter() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                currentFoodMap[i][j] += map[i][j];
        }
    }

    private static void fall() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (q[i][j].isEmpty()) continue;

                reproduce(i,j);
            }
        }
    }

    private static void reproduce(int x, int y) {

        Iterator iterator = q[x][y].iterator();

        int age = 0;
        while (iterator.hasNext()) {

            age = (int) iterator.next();

            if (age % 5 == 0 && age != 0) {

                for (int i = 0; i < 8; i++) {

                    int nx = x + xDirection[i];
                    int ny = y + yDirection[i];

                    if (isInvalid(nx,ny)) continue;
                    q[nx][ny].add(1);
                }
            }
        }
    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 1 || nx >= N + 1 || ny < 1 || ny >= N + 1;
    }

    private static void summer() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dieTree[i][j].isEmpty()) continue;

                give(i,j);
            }
        }
    }

    private static void give(int x, int y) {

        for (int num : dieTree[x][y]) {
            currentFoodMap[x][y] += (num / 2);
        }

        dieTree[x][y].clear();
    }

    private static void spring() {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if(q[i][j].isEmpty()) continue;

                int food = currentFoodMap[i][j];
                eat(food,i,j);
            }
        }
    }

    private static void eat(int food, int x, int y) {

        List<Integer> temp = new ArrayList<>();

        int tree = 0;
        while (!q[x][y].isEmpty()) {

            tree = q[x][y].poll();

            if (food >= tree) {
                food -= tree;
                temp.add(tree + 1);
            }
            else dieTree[x][y].add(tree);
        }

        currentFoodMap[x][y] = food;


        for (int liveTree : temp) {
            q[x][y].add(liveTree);
        }

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        K = Integer.parseInt(input[2]);

        map = new int[N + 1][N + 1];
        currentFoodMap = new int[N + 1][N + 1];
        q = new PriorityQueue[N + 1][N + 1];
        dieTree = new ArrayList[N + 1][N + 1];

        for (int i = 1; i<= N; i++) {
            input = br.readLine().split(" ");

            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(input[j - 1]);
                currentFoodMap[i][j] = 5;
            }
        }

        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++) {
                q[i][j] = new PriorityQueue<>();
                dieTree[i][j] = new ArrayList<>();
            }


        int r, c, age;
        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            r = Integer.parseInt(input[0]);
            c = Integer.parseInt(input[1]);
            age = Integer.parseInt(input[2]);

            q[r][c].add(age);
        }


    }
}
