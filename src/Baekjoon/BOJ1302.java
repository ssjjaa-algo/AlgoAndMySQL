package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ1302 {

    static Map<String,Integer> map = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        System.out.println(calculate(N));

    }

    private static String calculate(int N) throws IOException{

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map.put(str,map.getOrDefault(str,0) + 1);
        }

        int max = map.values().stream().max(Integer::compareTo).orElseThrow();

        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        for (String s : list) {
            if (map.get(s) == max) return s;
        }

        return null;
    }
}
