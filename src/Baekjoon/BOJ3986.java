package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BOJ3986 {

    /*
    AABB

    ABAB
    AAABB

    A cnt가 2인데 B cnt가 1인 경우
    B cnt가 2인데 A cnt가 1인 경우

    내 이전의 녀석과 나와 같은 값이 아니면 fail


     */

    static int N;
    static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            String str = list.get(i);

            Stack<Character> s = new Stack<>();
            for (int j = 0; j < str.length(); j++) {

                char temp = str.charAt(j);

                if (!s.isEmpty() && s.peek() == temp) s.pop();

                else s.push(temp);
            }
            if (s.isEmpty()) cnt++;
        }

        return cnt;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(br.readLine());
        }
    }
}
