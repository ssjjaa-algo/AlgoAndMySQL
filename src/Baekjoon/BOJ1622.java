package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1622 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Character, Integer> aMap = new HashMap<>();
        Map<Character, Integer> bMap = new HashMap<>();
        List<Character> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        String str = "";
        while (true) {

            str = br.readLine();
            if (str == null) break;
            char[] a = str.toCharArray();
            char[] b = br.readLine().toCharArray();

            for (char c : a) {
                aMap.put(c, aMap.getOrDefault(c, 0) + 1);
            }
            for (char c : b) {
                bMap.put(c, bMap.getOrDefault(c, 0) + 1);
            }

            boolean[] visited = new boolean[27];
            for(char c : b) {
                int count = aMap.getOrDefault(c, 0);
                if (visited[c - 'a']) continue;
                if (count != 0) {
                    visited[c - 'a'] = true;
                    int bNumber = bMap.get(c);

                    int min = Math.min(count, bNumber);

                    for (int i = 0; i < min; i++) {
                        list.add(c);
                    }
                }
            }
            Collections.sort(list);
            for (char c : list) {
                sb.append(c);
            }
            sb.append("\n");
            list.clear();
            aMap.clear();
            bMap.clear();
        }

        System.out.print(sb);
    }

}
