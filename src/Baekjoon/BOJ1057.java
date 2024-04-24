package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1057 {

    static int N, a, b;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        a = Integer.parseInt(input[1]);
        b = Integer.parseInt(input[2]);

        int cnt = 1;
        while(true) {

            if ( a % 2 == 1) {
                a = a / 2 + 1;
            }
            else a /= 2;

            if (b % 2 == 1) {
                b = b / 2 + 1;
            }
            else b /= 2;

            if (a == b) {
                System.out.println(cnt);
                break;
            }
            cnt++;
        }

    }
}
