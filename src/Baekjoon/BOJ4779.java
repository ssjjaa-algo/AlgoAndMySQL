package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ4779 {

    static char[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String str = br.readLine();
            if (str == null || str.isEmpty()) break;

            int number = Integer.parseInt(str);
            int size = (int)Math.pow(3, number);
            arr = new char[size];
            Arrays.fill(arr, '-');

            divide(0,  size);

            for (char c : arr) {
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void divide(int start, int size) {

        if (size <= 1) return;

        int left = start + (size / 3);
        int right = start + (size / 3) * 2;

        for (int i = left; i < right; i++) arr[i] = ' ';

        divide(start,size / 3);
        divide(right,size / 3);
    }
}
