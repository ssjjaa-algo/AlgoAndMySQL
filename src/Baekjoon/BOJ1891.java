package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1891 {

    static int d;
    static char[] map;
    static long row, col;
    static long targetRow, targetCol;

    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        long length = 1L << d;
        findLocation(0, 0, 0, length, length);
        targetRow -= row;
        targetCol += col;

        if (isInvalid(targetRow, targetCol, length)) {
            System.out.println(-1);
            return;
        }

        System.out.print(findTargetLocation(targetRow, targetCol, new StringBuilder(), length / 2));
    }

    private static String findTargetLocation(long r, long c, StringBuilder sb, long length) {

        if (length == 0) {
            return sb.toString();
        }

        if (r < length && c >= length) {
            sb.append("1");
            findTargetLocation(r, c - length, sb, length / 2);
        }

        else if (r < length && c < length) {
            sb.append("2");
            findTargetLocation(r, c, sb, length / 2);
        }

        else if (r >= length && c < length) {
            sb.append("3");
            findTargetLocation(r - length, c, sb, length / 2);
        }

        else {
            sb.append("4");
            findTargetLocation(r - length, c - length, sb, length / 2);
        }

        return sb.toString();
    }


    private static boolean isInvalid(long r, long c, long length) {

        return r < 0 || r >= length || c < 0 || c >= length;
    }

    private static void findLocation(int idx, long r1, long c1, long r2, long c2) {

        if (idx == d) {
            targetRow = r1;
            targetCol = c1;
            return;
        }

        char c = map[idx];

        if (c == '1') {
            findLocation(idx + 1, r1, (c1 + c2) / 2, (r1 + r2) / 2, c2);
        }
        else if (c == '2') {
            findLocation(idx + 1,r1, c1, (r1 + r2) / 2, (c1 + c2) / 2);
        }
        else if (c == '3') {
            findLocation(idx + 1, (r1 + r2) / 2, c1, r2, (c1 + c2) / 2);
        }
        else {
            findLocation(idx + 1, (r1 + r2) / 2, (c1 + c2) / 2, r2, c2);
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        d = Integer.parseInt(input[0]);
        map = input[1].toCharArray();

        input = br.readLine().split(" ");
        col = Long.parseLong(input[0]);
        row = Long.parseLong(input[1]);
    }
}
