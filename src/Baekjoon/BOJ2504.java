package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504 {

    static String str;
    static Stack<Character> s = new Stack<>();
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int ans = 0;
        int multiple = 1;
        for (int i = 0; i < str.length(); i++) {

            char temp = str.charAt(i);

            if (temp == '(' || temp == '[') {
                s.add(temp);
                multiple *= (temp == '(' ? 2 : 3);
            }

            else if (temp == ')') {

                if (s.isEmpty() || s.peek() != '(') return 0;
                if (str.charAt(i - 1) == '(') ans += multiple;
                multiple /= 2;
                s.pop();
            }

            else if (temp == ']') {
                if (s.isEmpty() || s.peek() != '[') return 0;
                if (str.charAt(i - 1) == '[') ans += multiple;
                multiple /= 3;
                s.pop();
            }

        }

        if (!s.isEmpty()) return 0;

        return ans;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
    }
}
