package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9935_Re {

    static char[] str;
    static char[] bombStr;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        int strLength = str.length;
        int bombStrLength = bombStr.length;

        Stack<Character> result = new Stack<>();

        for (int i = 0; i < strLength; i++) {

            result.add(str[i]);

            if (result.peek() == bombStr[bombStrLength - 1] && result.size() >= bombStrLength) {

                int idx = bombStrLength - 1;
                int size = result.size() - 1;
                boolean flag = true;
                for (int j = size; j > size - bombStrLength; j--) {
                    if (result.get(j) != bombStr[idx--]) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    for (int j = 0; j < bombStrLength; j++) {
                        result.pop();
                    }
                }
            }
        }

        if (result.isEmpty()) {
            System.out.println("FRULA");
        }
        else {

            StringBuilder sb = new StringBuilder();
            while (!result.isEmpty()) {
                sb.append(result.pop());
            }

            System.out.println(sb.reverse());
        }

    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine().toCharArray();
        bombStr = br.readLine().toCharArray();


    }
}
