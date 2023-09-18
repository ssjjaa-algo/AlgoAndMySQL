package CodeTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int ans = 0;
    static class Node {
        int day;
        int cost;

        public Node(int day, int cost) {
            this.day=day;
            this.cost=cost;
        }
    }

    static Node[] nodes;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(ans);
    }

    private static void init() throws IOException {
        n = Integer.parseInt(br.readLine());
        String[] input;
        nodes = new Node[n + 2];
        int t,p;
        for (int i = 1; i <= n; i++) {
            input = br.readLine().split(" ");
            t = Integer.parseInt(input[0]);
            p = Integer.parseInt(input[1]);

            nodes[i] = new Node(t,p);
        }
        nodes[n + 1] = new Node(0,0);
    }
}
