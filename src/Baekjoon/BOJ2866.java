package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BOJ2866 {

    static Set<String> set = new HashSet<>();
    static char[][] map;
    static List<String> words = new ArrayList<>();
    static int R,C;
    public static void main(String[] args) throws IOException {

        init();
        calculate();

    }

    private static void calculate() {

        int left = 0;
        int right = R - 1;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if(isNotDuplicated(mid)) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean isNotDuplicated(int mid) {

        for (int i = 0; i < C; i++) {
            String str = words.get(i).substring(mid);
            if (set.contains(str)) return false;

            set.add(str);
        }

        set.clear();
        return true;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < C; i++) {
            for (int j = 0; j < R; j++) {
                sb.append(map[j][i]);
            }
            words.add(sb.toString());
            sb.setLength(0);
        }
    }
}