package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ16934 {

    static class Trie {
        Node root = new Node();
        public String insert(String str) {

            Node cur = this.root;
            int length = str.length();
            StringBuilder sb = new StringBuilder();
            boolean flag = false;
            int slice = 0;
            for (int i = 0; i < length; i++) {
                char c = str.charAt(i);
                if (cur.child.get(c) != null) {
                    cur = cur.child.get(c);
                    sb.append(c);
                }
                else {
                    cur = cur.child.computeIfAbsent(c, key -> new Node());
                    if (!flag) {
                        sb.append(c);
                        flag = true;
                    }
                }
            }

            cur.count++;
            if (cur.count == 1) return sb.toString();
            else return sb.append(cur.count).toString();

        }
    }
    static class Node {
        Map<Character, Node> child = new HashMap<>();

        int count = 0;
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        calculate(N);
    }

    private static void calculate(int N) throws IOException {

        Trie trie = new Trie();
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            sb.append(trie.insert(br.readLine())).append("\n");
        }

        System.out.println(sb);
    }
}