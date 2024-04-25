package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1105 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String L = input[0];
        String R = input[1];

        if (L.length() != R.length()) {
            System.out.println(0);
        }
        else {
            int len = L.length();
            int cnt = 0;

            for (int i = 0; i < len; i++) {
                char l = L.charAt(i);
                char r = R.charAt(i);

                if (l == r) {
                    if (l == '8') cnt++;
                }

                else break;
            }
            System.out.println(cnt);
        }
    }
}
