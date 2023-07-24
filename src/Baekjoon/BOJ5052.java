package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ5052 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class TrieNode { // step 1. TrieNode의 형태를 선언한다
        // TrieNode는 Character를 키로 갖고 Value로 TrieNode를 가지는 HashMap 형태로 선언 (배열도 무관)
        Map<Character,TrieNode> childNodes = new HashMap<>();
        boolean last; // 단어의 끝을 체킹해야 함.

        public TrieNode() {
            childNodes = new HashMap<>();
            last = false;
        }
    }

    static class Trie {

        TrieNode root; // root부터 시작

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode tempNode = this.root; // 매번 root에서 시작한다.
            int len = word.length();

            for (int i = 0; i < len; i++) { // 단어의 길이만큼
                // childnode가 없는 경우 만들고, 있으면 자식으로 변할 것임
                tempNode = tempNode.childNodes.computeIfAbsent(word.charAt(i),c -> new TrieNode());
            }

            tempNode.last=true;

        }

        public boolean contains(String word) {
            TrieNode tempNode = this.root;

            int len = word.length();
            for (int i = 0; i < len; i++) {

                tempNode = tempNode.childNodes.get(word.charAt(i));

                if (tempNode == null) return false;

            }

            if(tempNode.childNodes.size() > 0) return true;

            return false;
        }

    }
    public static void main(String[] args) throws IOException{

        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        while (tc > 0) {
            int n = Integer.parseInt(br.readLine());

            boolean flag = true;
            List<String> wordList = new ArrayList<>();
            String word;
            Trie trie = new Trie();
            for (int i = 0; i < n; i++) {
                word = br.readLine();
                trie.insert(word);
                wordList.add(word);
            }

            for (int i = 0; i < n; i++) {
                if (trie.contains(wordList.get(i))) {
                    flag = false;
                    break;
                }
            }


            sb.append(flag ? "YES\n" : "NO\n");

            tc--;
        }
        bw.write(sb.toString());
        bw.close();
        br.close();

    }
}
