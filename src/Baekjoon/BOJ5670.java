package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ5670 {

    static class TrieNode {

        Map<Character,TrieNode> childeNodes = new HashMap<>();
        boolean isLast;
    }

    static class Trie {
        TrieNode rootNode;

        public Trie() {
            this.rootNode = new TrieNode();
        }

        public void insert(String word) {

            TrieNode tempNode = this.rootNode;
            int len = word.length();

            for (int i = 0; i < len; i++) {
                char alpha = word.charAt(i);
                tempNode.childeNodes.computeIfAbsent(alpha,c ->new TrieNode());
                tempNode = tempNode.childeNodes.get(alpha);
            }

            tempNode.isLast = true;
        }

        public int contain(String word) {
            TrieNode tempNode = this.rootNode;

            int len = word.length();
            if (len == 1) return 1;

            int count = 1;

            tempNode = tempNode.childeNodes.get(word.charAt(0));
            for (int i = 1; i < len; i++) {

                if (tempNode.childeNodes.size() >= 2) count++;

                else if (tempNode.childeNodes.size() == 1 && tempNode.isLast) count++;

                tempNode = tempNode.childeNodes.get(word.charAt(i));

            }

            return count;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        while (true) {
            String str = br.readLine();
            if(str == null || str.equals("")) break;
            solve(str);
        }

        System.out.println(sb);

    }

    private static void solve(String str) throws IOException {

        int tc = Integer.parseInt(str);

        List<String> wordList = new ArrayList<>();
        Trie trie = new Trie();
        for (int i = 0; i < tc; i++) {
            String word = br.readLine();
            wordList.add(word);
            trie.insert(word);
        }

        double sum = 0;
        for (int i = 0; i < tc; i++) {
            sum += trie.contain(wordList.get(i));
        }
        sb.append(String.format("%.2f",sum / tc)).append("\n");
    }
}
