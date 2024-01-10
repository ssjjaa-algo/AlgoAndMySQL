package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ16496 {

    static int N;
    static String[] str;
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());

    }

    private static String calculate() {

        StringBuilder sb = new StringBuilder();

        for (String s : str) {
            sb.append(s);
        }

        if (sb.charAt(0) == '0') {
            return "0";
        }

        return sb.toString();
    }

    private static void init() throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        str = new String[N];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            str[i] = input[i];
        }

        Arrays.sort(str,(o1,o2) -> (o2 + o1).compareTo(o1 + o2));
    }
}
