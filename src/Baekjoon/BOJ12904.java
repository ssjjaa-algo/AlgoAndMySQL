package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ12904 {

    static StringBuilder S, T;
    static int ok;
    public static void main(String[] args) throws IOException {

        init();

        while (T.length() > S.length()) {

            if (T.toString().charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            }
            else {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        System.out.println(T.toString().equals(S.toString()) ? 1 : 0);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = new StringBuilder();
        T = new StringBuilder();
        S.append(br.readLine());
        T.append(br.readLine());


        br.close();
    }
}
