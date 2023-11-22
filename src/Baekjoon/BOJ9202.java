package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ9202 {

    static class Trie {

        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        public void insert(String word) {

            TrieNode node = this.root;

            int len = word.length();

            for (int i = 0 ; i < len; i++) {
                char alpha = word.charAt(i);
                node.childNodes.computeIfAbsent(alpha, c -> new TrieNode());
                node = node.childNodes.get(alpha);
            }

            node.isLast = true;
        }

        public boolean contains(String word) {

            TrieNode node = this.root;

            int len = word.length();

            for (int i = 0; i < len; i++) {
                char alpha = word.charAt(i);
                node = node.childNodes.get(alpha);

                if (node == null) {
                    return false;
                }
            }

            return true;
        }

    }

    static class TrieNode {
        Map<Character, TrieNode> childNodes = new HashMap<>();
        boolean isLast;
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<String> findWords;
    static Set<String> wordsList;
    static Trie trie;
    static boolean[][] visited;
    static char[][] map;
    static int[] scores = {0,0,0,1,1,2,3,5,11};
    static int score;
    static int cnt;
    static int[] xDirection = { -1,-1,-1,0,1,1,1,0};
    static int[] yDirection = { -1,0,1,1,1,0,-1,-1};
    static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        calculate();
        System.out.print(res);
    }

    private static void calculate() throws IOException {

        br.readLine();
        int b = Integer.parseInt(br.readLine());

        for (int a = 0; a < b - 1; a++) {
            for (int i = 0; i < 4; i++) {

                String str = br.readLine();
                map[i] = str.toCharArray();
            }

            findWords();

            br.readLine();
        }

        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        findWords();
    }

    private static void findWords() {

        findWords = new HashSet<>();
        visited = new boolean[4][4];
        score = 0;
        cnt = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                visited[i][j] = true;
                findWord(i,j, String.valueOf(map[i][j]));
                visited[i][j] = false;

            }
        }

        getRes();
    }

    private static void getRes() {

        String max = "";
        for (String s : findWords) {

            if (s.length() > max.length()) {
                max = s;
            }

            else if (s.length() == max.length()) {

                int compare = max.compareTo(s);

                if (compare <= 0) continue;
                else max = s;
            }
        }
        res.append(score).append(" ").append(max).append(" ").append(cnt).append("\n");

    }

    private static void findWord(int row, int col, String str) {

        if (!trie.contains(str)) {
            return;
        }

        if (wordsList.contains(str)) {
            score += getScore(str);
        }

        for (int i = 0; i < 8; i++) {

            int nx = row + xDirection[i];
            int ny = col + yDirection[i];

            if (!isIn(nx,ny) || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            findWord(nx,ny,str + map[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    private static int getScore(String str) {

        if (findWords.contains(str)) return 0;

        findWords.add(str);
        cnt++;

        return scores[str.length()];
    }

    private static boolean isIn(int nx, int ny) {

        if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) return false;

        return true;
    }

    private static void init() throws IOException {

        trie = new Trie();
        map = new char[4][4];
        wordsList = new HashSet<>();

        int w = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < w; i++) {
            String str = br.readLine();
            trie.insert(str);
            wordsList.add(str);
        }

    }
}
