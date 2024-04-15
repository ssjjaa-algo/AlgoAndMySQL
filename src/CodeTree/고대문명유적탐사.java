import java.io.*;
import java.util.*;

public class 고대문명유적탐사 {

    static int K, M;
    static int[][] map = new int[5][5];
    static int[] relics;
    static int[] rDirection = {-1, 0, 1, 0};
    static int[] cDirection = {0, 1, 0, -1};
    static int relicsIdx = 0;

    static class Node implements Comparable<Node>{
        int row, col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int compareTo(Node o) {
            if (this.col == o.col) {
                return Integer.compare(o.row, this.row);
            }
            return Integer.compare(this.col, o.col);
        }
    }

    static class Info implements Comparable<Info>{

        int row, col, rotateCount, score;
        List<Node> list = new ArrayList<>();

        public Info(int row, int col, int rotateCount, int score, List<Node> list) {
            this.row = row;
            this.col = col;
            this.rotateCount = rotateCount;
            this.score = score;
            this.list = list;
        }

        @Override
        public int compareTo(Info o) {

            if (this.score == o.score) {

                if (this.rotateCount == o.rotateCount) {

                    if (this.col == o.col) {

                        return Integer.compare(this.row, o.row);
                    }
                    return Integer.compare(this.col, o.col);
                }
                return Integer.compare(this.rotateCount, o.rotateCount);
            }
            return Integer.compare(o.score, this.score);
        }

    }

    public static void main(String[] args) throws IOException {
        
        init();
        calculate();
    }

    private static void calculate() {

        StringBuilder sb = new StringBuilder();
        while(K-- > 0) {

            List<Info> infos = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                for (int j = 1; j <= 3; j++) {

                    for (int k = 0; k < 3; k++) {

                        int[][] temp = copy();
                        for (int l = 0; l <= k; l++) {
                            rotate(temp, i, j);
                        }
                        Info info = countMaxRelics(i, j, k, temp);
                        infos.add(info);
                    }
                }
            }

            Collections.sort(infos);
            int score = chaining(infos.get(0));
            if (score == 0) break;
            
            sb.append(score).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static int chaining(Info target) {

            if (target.score == 0) return 0;
            Collections.sort(target.list);
            int score = target.score;

            for (int i = 0; i <= target.rotateCount; i++) {
                rotate(map, target.row, target.col);
            }

            for (Node node : target.list) {
                map[node.row][node.col] = 0;
                map[node.row][node.col] = relics[relicsIdx++];
            }
            
            while(true) {
                boolean[][] visited = new boolean[5][5];
                List<Node> countNode = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {

                        if(map[i][j] == 0 || visited[i][j]) continue;
                        bfs(i, j, visited, map, countNode);
                        
                    }
                }

                if (countNode.size() >= 3) {
                    Collections.sort(countNode);
                    for (Node node : countNode) {
                        map[node.row][node.col] = 0;
                        map[node.row][node.col] = relics[relicsIdx++];
                    }
                    score += countNode.size();

                }

                else break;
            }
            return score;
    }

    private static void print() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static Info countMaxRelics(int row, int col, int rotateCount, int[][] temp) {

        boolean[][] visited = new boolean[5][5];
        
        int relicsCount = 0;
        List<Node> countNode = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 0 || visited[i][j]) continue;
                bfs(i, j, visited, temp, countNode);
            }
        }

        if (countNode.size() < 3) return new Info(row, col, 0, 0, null);
        return new Info(row, col, rotateCount, countNode.size(), countNode);

    }

    private static void bfs(int row, int col, boolean[][] visited, int[][] temp, List<Node> countNode) {

        visited[row][col] = true;
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(row,col));
        int num = temp[row][col];

        List<Node> choice = new ArrayList<>();
        choice.add(new Node(row,col));
        int count = 1;
        while(!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = node.row + rDirection[i];
                int nc = node.col + cDirection[i];

                if (isInvalid(nr,nc) || visited[nr][nc] || temp[nr][nc] != num) continue;

                visited[nr][nc] = true;
                Node newNode = new Node(nr, nc);
                q.add(newNode);
                choice.add(newNode);
            }
        }

        if (choice.size() >= 3) {
            for (Node node : choice) {
                countNode.add(node);
            }
        }
        
        return;
    }

    private static boolean isInvalid(int nr, int nc) {
        return nr < 0 || nr >= 5 || nc < 0 || nc >= 5;
    }


    private static int[][] copy() {

        int[][] temp = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        K = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        relics = new int[M];

        for (int i = 0; i < 5; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        input = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            relics[i] = Integer.parseInt(input[i]);
        }

    }

    private static void rotate(int[][] tempMap, int row, int col) {

       int temp1 = tempMap[row + 1][col - 1];
       tempMap[row + 1][col - 1] = tempMap[row + 1][col + 1];
       tempMap[row + 1][col + 1] = tempMap[row - 1][col + 1];
       tempMap[row - 1][col + 1] = tempMap[row - 1][col - 1];
       tempMap[row - 1][col - 1] = temp1;

       int temp2 = tempMap[row + 1][col];
       tempMap[row + 1][col] = tempMap[row][col + 1];
       tempMap[row][col + 1] = tempMap[row - 1][col];
       tempMap[row - 1][col] = tempMap[row][col - 1];
       tempMap[row][col - 1] = temp2;
    }

}
