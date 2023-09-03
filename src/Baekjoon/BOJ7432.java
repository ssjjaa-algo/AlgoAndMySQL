package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ7432 {

    static StringBuilder sb = new StringBuilder();
    static class TrieNode {
        Map<String,TrieNode> childNodes = new HashMap<String, TrieNode>();
    }

    static class Trie {
        TrieNode root;
        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(List<String> stringList) {

            TrieNode tempNode = this.root;

            for (String s : stringList) {
                tempNode.childNodes.computeIfAbsent(s,c->new TrieNode());
                tempNode = tempNode.childNodes.get(s);
            }
        }

        public void print(TrieNode cur, int depth) {

            if (cur.childNodes != null) {

                List<String> sameDepthList = new ArrayList<>(cur.childNodes.keySet());
                Collections.sort(sameDepthList);

                for (String s : sameDepthList) {

                    for (int i = 0; i < depth; i++) {
                        sb.append(" ");
                    }
                    sb.append(s).append("\n");
                    print(cur.childNodes.get(s),depth + 1);
                }
            }

        }
    }
    public static void main(String[] args) throws IOException{

        Trie trie = init();
        trie.print(trie.root,0);

        System.out.println(sb);
    }

    private static Trie init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input;
        Trie trie = new Trie();
        for (int i =0; i < N; i++) {
            input = br.readLine().split("\\\\");
            List<String> stringList = new ArrayList<>();
            for (int j = 0; j <input.length; j++) {
                stringList.add(input[j]);
            }
            trie.insert(stringList);
        }

        return trie;
    }
}
