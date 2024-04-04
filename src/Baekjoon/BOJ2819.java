package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ2819 {

    static int N, M;
    static char[] commands;
    static long sum;
    static int robotX, robotY;
    static List<Integer> allX = new ArrayList<>();
    static List<Integer> allY = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        StringBuilder sb = new StringBuilder();
        long res = sum;
        for (char command : commands) {

            if (command == 'S') {
                robotY++;
                int cnt = binarySearch(allY, robotY - 1);
                sum += cnt - (N - cnt);
            }
            else if (command == 'J') {
                robotY--;
                int cnt = binarySearch(allY, robotY);
                sum += (cnt * -1) + (N - cnt);
            }
            else if (command == 'I') {
                robotX++;
                int cnt = binarySearch(allX, robotX - 1);
                sum += cnt - (N - cnt);
            }
            else {
                robotX--;
                int cnt = binarySearch(allX, robotX);
                sum += (cnt * -1) + (N - cnt);
            }

            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }

    private static int binarySearch(List<Integer> list, int num) {

        int left = 0;
        int right = list.size() - 1;
        int res = -1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (list.get(mid) <= num) {
                res = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return res + 1;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        int x, y;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            sum += Math.abs(x) + Math.abs(y);
            allX.add(x);
            allY.add(y);
        }

        Collections.sort(allX);
        Collections.sort(allY);

        commands = br.readLine().toCharArray();
    }
}
