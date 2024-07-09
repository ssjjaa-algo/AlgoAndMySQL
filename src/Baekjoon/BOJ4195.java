package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BOJ4195 {

    static Map<String, Integer> map = new HashMap<>();
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {

            int f = Integer.parseInt(br.readLine());

            parent = new int[f * 2 + 1];
            rank = new int[f * 2 + 1];
            for (int j = 1; j <= f * 2; j++) parent[j] = j;
            Arrays.fill(rank, 1);

            int idx = 1;
            for (int j = 0; j < f; j++) {
                String[] input = br.readLine().split(" ");
                if(!map.containsKey(input[0])) {
                    map.put(input[0], idx++);
                }

                if(!map.containsKey(input[1])) {
                    map.put(input[1], idx++);
                }

                int a = map.get(input[0]);
                int b = map.get(input[1]);
                int ans = union(a,b);
                sb.append(ans).append("\n");
            }
            map.clear();
        }
        System.out.print(sb);

    }

    static int find(int num) {
        if (parent[num] == num) return num;
        return parent[num] = find(parent[num]);
    }

    static int union(int a, int b) {

        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return rank[aRoot];

        if (aRoot > bRoot) {
            parent[bRoot] = aRoot;
            rank[aRoot] += rank[bRoot];
            return rank[aRoot];
        }
        else {
            parent[aRoot] = bRoot;
            rank[bRoot] += rank[aRoot];
            return rank[bRoot];
        }
    }
}
