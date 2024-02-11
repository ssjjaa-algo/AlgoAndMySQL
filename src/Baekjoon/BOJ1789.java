package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1789 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());

        calculate(S);
    }

    private static void calculate(long S) {

        long num = 1;

        while ((num * (num + 1)) / 2 <= S) {
            num++;
        }


        System.out.println(num - 1);
    }
}
