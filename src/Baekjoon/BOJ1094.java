package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1094 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        int res = 0;

        for (int i = 0; i < 7; i++) {
            if ((X & (1 << i)) > 0) res++;
        }

        System.out.println(res);

    }
}
