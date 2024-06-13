package CodeTree;

import java.io.*;
import java.util.*;

public class 색깔트리 {

    static int Q;
    static class Node {

        int p, color, maxDepth, curDepth;
        public Node(int p, int color, int maxDepth, int curDepth) {
            this.p = p;
            this.color = color;
            this.maxDepth = maxDepth;
            this.curDepth = curDepth;
        }
    }

    static List<Integer>[] adj = new ArrayList[100001];
    static Node[] nodes = new Node[100001];
    static int colorCnt = 0;
    static boolean[] diffrent = new boolean[6];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 1; i<= 100000; i++) adj[i] = new ArrayList<>();
        Q = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(Q-- > 0) {
            String[] input = br.readLine().split(" ");
            int command = Integer.parseInt(input[0]);

            // 노드 추가 100 mid pid color maxDepth
            if (command == 100) {
                insertNode(input);
            }

            // 색깔 변경 200 mid color
            else if (command == 200) {
                changeColor(Integer.parseInt(input[1]), Integer.parseInt(input[2]));
            }

            // 색깔 조회 300 mid
            else if (command == 300) {
                sb.append(nodes[Integer.parseInt(input[1])].color).append("\n");
            }

            // 점수 조회 400, 최대 100번이므로 약 천만번
            else {

                long ans = 0;
                for (int i = 1; i <= 100000; i++) {
                    if (nodes[i] == null) continue;
                    Arrays.fill(diffrent, false);
                    diffrent[nodes[i].color] = true;
                    colorCnt = 1;
                    findDifferentColor(i, nodes[i].color);
                    ans += (colorCnt * colorCnt);
                }
                sb.append(ans).append("\n");
            }
        }

        System.out.print(sb);

    }

    private static void dfs(int id) {
        Node n = nodes[id];
        for (int next : adj[id]) {
            dfs(next);
        }
    }

    private static void findDifferentColor(int id, int color) {

        if(colorCnt == 5) return;

        for (int next : adj[id]) {
            if (nodes[next].color != color && !diffrent[nodes[next].color]) {
                diffrent[nodes[next].color] = true;
                colorCnt++;
            }
            findDifferentColor(next, color);
        }

    }

    private static void changeColor(int id, int color) {

        nodes[id].color = color;
        for (int next : adj[id]) {
            changeColor(next, color);
        }
    }


    private static void insertNode(String[] input) {

        int mid = Integer.parseInt(input[1]);
        int pid = Integer.parseInt(input[2]);
        int color = Integer.parseInt(input[3]);
        int maxDepth = Integer.parseInt(input[4]);

        if (pid == -1) {// 최상단인 경우
            nodes[mid] = new Node(pid, color, maxDepth, 1); //
        }
        else {

            // 현재 삽입하려는 노드의 위치를 부모를 통해 확인한다.
            int cur = nodes[pid].curDepth + 1;
            if (cur > nodes[pid].maxDepth) {
                return; // 규칙에 위배 : maxDepth가 허용한 범위보다 큰 경우.
            }
            nodes[mid] = new Node(pid, color,
                    Math.min(nodes[pid].maxDepth, nodes[pid].curDepth + maxDepth),
                    nodes[pid].curDepth + 1);
            adj[pid].add(mid); // 서브트리를 탐색할 때, dfs로 이용하기 위함
        }
    }
}

