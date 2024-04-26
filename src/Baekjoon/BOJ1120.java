package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * koder topcoder
 * koder topco (4)
 * koder opcod (5)
 * koder pcode (5)
 * koder coder (1)
 */
public class BOJ1120 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String a = input[0];
        String b = input[1];
        int ans = 50;

        int aLen = a.length();
        int bLen = b.length();

        for (int i = 0; i <= bLen - aLen; i++) {
            int cnt = 0;
            for (int j = 0; j < aLen; j++) {
                if (a.charAt(j) != b.charAt(j + i)) cnt++;
            }
            ans = Math.min(ans, cnt);
        }

        System.out.println(ans);
    }

}
