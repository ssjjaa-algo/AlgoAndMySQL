package CodeTree;

import java.io.*;
import java.util.*;

public class 코드트리투어 {
    static int Q;
    static int n, m;
    static List<Node> adj[];
    static int[] dist;
    static boolean[] idMap = new boolean[30010];
    static PriorityQueue<Product> productQ = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();

    static class Node implements Comparable<Node>{
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static class Product implements Comparable<Product> {

        int id;
        int revenue;
        int dest;
        int profit;

        public Product(int id, int revenue, int dest, int profit) {
            this.id = id;
            this.revenue = revenue;
            this.dest = dest;
            this.profit = profit;
        }

        @Override
        public int compareTo(Product o) {

            if (this.profit == o.profit) {
                return Integer.compare(this.id, o.id);
            }
            return Integer.compare(o.profit, this.profit);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[1]);
        m = Integer.parseInt(input[2]);
        adj = new ArrayList[n];
        dist = new int[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        for (int i = 1; i < (input.length) / 3; i++) {
            int idx = 3 * i;
            int u = Integer.parseInt(input[idx]);
            int v = Integer.parseInt(input[idx + 1]);
            int w = Integer.parseInt(input[idx + 2]);
            adj[u].add(new Node(v, w));
            adj[v].add(new Node(u, w));
        }

        dijkstra(0); // 초기 시작점은 0
        Q--;

        while (Q-- > 0) {
            input = br.readLine().split(" ");
            command(input);
        }

        System.out.print(sb);
    }

    private static void dijkstra(int start) {

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        while (!q.isEmpty()) {

            Node node = q.poll();
            int cur = node.v;
            int cost = node.w;

            if (dist[cur] < cost) continue;

            for (Node n : adj[cur]) {
                int next = n.v;
                int next_cost = cost + n.w;

                if (dist[next] > next_cost) {
                    dist[next] = next_cost;
                    q.add(new Node(next, next_cost));
                }
            }
        }
    }

    private static void command(String[] input) {

        int command = Integer.parseInt(input[0]);
        // 여행 상품 생성 : 200 id revenue dest
        if (command == 200) {
            createProduct(input);
        }
        // 여행 상품 취소 : 300 id
        else if (command == 300) {
            cancleProduct(Integer.parseInt(input[1]));
        }
        // 여행 상품 판매 : 400
        else if (command == 400) {
            sb.append(sellProduct()).append("\n");
        }
        // 여행 상품 출발지 변경 : 500 s
        else {
            changeStartPosition(Integer.parseInt(input[1]));
        }
    }

    private static void changeStartPosition(int start) {

        List<Product> list = new ArrayList<>();
        while(!productQ.isEmpty()) { // 기존의 상품들을 기록해두고
            list.add(productQ.poll());
        }

        dijkstra(start); // 최단거리를 재갱신

        for (Product p : list) { // 변경된 정보들을 다시 ProductQ에 저장
            p.profit = (p.revenue - dist[p.dest]);
            productQ.add(p);
        }
    }

    private static void cancleProduct(int id) {
        idMap[id] = false;
    }

    private static int sellProduct() {

        while(!productQ.isEmpty()) {
            Product product = productQ.peek();
            if (product.profit < 0) break;
            productQ.poll();

            if (!idMap[product.id]) { // 판매 취소 상품이라면
                continue;
            }
            idMap[product.id] = false; // 해당 id의 상품을 판매하였으므로 상품 판매 처리
            return product.id;
        }

        return -1;
    }

    private static void createProduct(String[] input) {
        int id = Integer.parseInt(input[1]);
        int revenue = Integer.parseInt(input[2]);
        int dest = Integer.parseInt(input[3]);
        int profit = revenue - dist[dest];
        idMap[id] = true; // 여행 상품 존재 표시
        productQ.add(new Product(id, revenue, dest, profit));
    }

}