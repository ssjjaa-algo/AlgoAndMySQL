package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935 {

    static char[] bomb;
    static int bombLength;
    static char[] str;
    static int strLength;
    static Stack<Character> result = new Stack<>();
    public static void main(String[] args) throws IOException {

        init();
        makeResult();
    }

    private static void makeResult() {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < strLength; i++) {

            result.push(str[i]);
            int size = result.size();

            if (result.peek() == bomb[bombLength - 1] && size >= bombLength) {

                int bombIdx = bombLength - 1;
                boolean flag = true;
                for (int j = size - 1; j >= size - bombLength; j--) {
                    if (result.get(j) != bomb[bombIdx--]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < bombLength; j++)
                        result.pop();
                }
            }
        }

        if (result.isEmpty()) sb.append("FRULA");
        else result.forEach(sb::append);

        System.out.println(sb);

    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        bomb = br.readLine().toCharArray();

        strLength = str.length;
        bombLength = bomb.length;

    }
}
