package Baekjoon;

import java.io.*;
import java.util.Stack;

public class BOJ2812 {

    public static void main(String[] args) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        char[] arr = br.readLine().toCharArray();
        Stack<Character> s = new Stack<Character>();

        int len = arr.length;
        for (int i=0; i < len; i++) {

            while(!s.isEmpty() && s.peek() < arr[i] && K > 0) {
                s.pop();
                K--;
            }
            s.push(arr[i]);

        }


        while (K > 0) {
            s.pop();
            K--;
        }

        while (!s.isEmpty()) {
            sb.append(s.pop());
        }
        sb.reverse();

        System.out.println(sb.toString());
    }
}
