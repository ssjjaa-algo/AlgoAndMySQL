package Baekjoon;

import java.io.*;

public class BOJ2980 {

    static int N,L;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[] input;

    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() throws IOException {
        int next = 0;
        int prev = 0;
        int R,G;
        int time = 0;
        for (int i = 0 ; i < N ; i++) {

            input = br.readLine().split(" ");
            next = Integer.parseInt(input[0]);
            R = Integer.parseInt(input[1]);
            G = Integer.parseInt(input[2]);

            time += next - prev;

            int rest = time % ( R + G );

            if (R > rest) {
                time += R - rest;
            }

            prev = next;
        }

        time += L - next;

        return time;
    }

    private static void init() throws IOException {

        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        L = Integer.parseInt(input[1]);

    }
}
