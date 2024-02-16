package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ16637 {

    static List<Character> operations = new ArrayList<>();
    static List<Integer> numbers = new ArrayList<>();
    static int N;
    static char[] str;
    static int ans = -999999999;
    public static void main(String[] args) throws IOException {

        init();
        dfs(0, numbers.get(0));
        System.out.println(ans);
    }

    private static void dfs(int idx, int result) {

        if (idx >= operations.size()) {
            ans = Math.max(ans, result);
            return;
        }

        dfs(idx + 1, calculate(result, numbers.get(idx +1), operations.get(idx)));

        if (operations.size() > idx + 1) {
            int num2 = calculate(numbers.get(idx + 1), numbers.get(idx + 2),operations.get(idx + 1));
            dfs(idx + 2, calculate(result, num2, operations.get(idx)));
        }


    }

    private static int calculate(int num1, int num2, char operation) {

        if (operation == '+') {
            return num1 + num2;
        }
        else if (operation == '-') {
            return num1 - num2;
        }
        else {
            return num1 * num2;
        }

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        str = br.readLine().toCharArray();
        for (char c : str) {
            if (c >= '0' && c <= '9') {
                numbers.add(c - '0');
            }
            else operations.add(c);
        }
    }
}
