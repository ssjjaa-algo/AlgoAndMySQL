package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ14725 {

    static StringBuilder sb = new StringBuilder();
    static class TrieNode {
        Map<String, TrieNode> childNodes = new HashMap<>();
    }

    static class Trie {
        TrieNode root;

        public Trie () {
            this.root = new TrieNode();
        }

        public void insert(List<String> strList) {

            TrieNode tempNode = root;

            for (String s : strList) { // 자식에 계속해서 추가
                tempNode.childNodes.putIfAbsent(s,new TrieNode());
                tempNode = tempNode.childNodes.get(s);
            }
        }

        public void print(TrieNode curNode, int depth) {

            if (curNode.childNodes != null) {

                List<String> sameDepthList = new ArrayList<>(curNode.childNodes.keySet());
                Collections.sort(sameDepthList);

                for (String s : sameDepthList) {

                    for (int i = 0; i < depth; i++) {
                        sb.append("--");
                    }
                    sb.append(s).append("\n");
                    print(curNode.childNodes.get(s),depth + 1);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {

        Trie trie = init();
        trie.print(trie.root,0);

        System.out.println(sb);
    }

    private static Trie init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input;
        int num;
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            num = Integer.parseInt(input[0]);

            List<String> list = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                list.add(input[j + 1]);
            }
            trie.insert(list);
        }

        return trie;
    }
}
