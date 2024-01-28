package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1062 {

    static int N,K;
    static String[] words;
    static boolean[] isSelected = new boolean[26];
    static int ans = 0;
    public static void main(String[] args) throws IOException {

        init();
        if (K < 5) {
            System.out.println(0);
            return;
        }
        K -= 5;

        isSelected[0] = true;
        isSelected[2] = true;
        isSelected[8] = true;
        isSelected[13] = true;
        isSelected[19] = true;

        select(0,0);
        System.out.println(ans);
    }

    private static void select(int start, int cnt) {
        if (cnt == K) {
            ans = Math.max(ans,calculate());
            return;
        }

        for (int i = start; i < 26; i++) {

            if (!isSelected[i]) {
                isSelected[i] = true;
                select(i + 1, cnt + 1);
                isSelected[i] = false;
            }
        }
    }

    private static int calculate() {

        int cnt = 0;
        for (String word : words) {
            cnt += isValidWord(word);
        }

        return cnt;
    }

    private static int isValidWord(String word) {

        int len = word.length();
        for (int i = 0; i < len; i++) {
            if (!isSelected[word.charAt(i) - 'a']) return 0;
        }

        return 1;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }


    }
}
