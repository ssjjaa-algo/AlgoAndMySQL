package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ1034 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        int K = Integer.parseInt(br.readLine());
        br.close();

        System.out.println(calculate(map, M, K));
    }

    private static int calculate(Map<String, Integer> map, int M, int K) {

        List<String> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> Integer.compare(map.get(o2), map.get(o1)));

        for (String s : list) {

            int zeroCount = 0;
            for (int i = 0; i < M; i++) {
                if (s.charAt(i) == '0') zeroCount++;
            }

            if (zeroCount > K) continue;

            if (K % 2 == zeroCount % 2) return map.get(s);

        }

        return 0;
    }
}
