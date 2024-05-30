package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ17609 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            int front = 0;
            int back = input.length - 1;

            if (palindrome(front, back, input)) {
                sb.append(0).append("\n");
            }
            else if (similarPalindrome(front, back, input, 1)) {
                sb.append(1).append("\n");
            }
            else sb.append(2).append("\n");
        }

        System.out.println(sb);

    }

    private static boolean similarPalindrome(int front, int back, char[] input, int cnt) {

        while (front < back) {

            if (input[front] != input[back]) {

                if (cnt == 1) {
                    boolean frontChange = similarPalindrome(front + 1, back, input, 0);
                    boolean backChange = similarPalindrome(front, back - 1, input, 0);
                    return frontChange || backChange;
                }
                else return false;
            }

            front++;
            back--;
        }
        return true;
    }

    private static boolean palindrome(int front, int back, char[] input) {

        while (front < back) {
            if (input[front++] != input[back--]) return false;
        }
        return true;
    }
}

/*
front + 1과 back이 다른 경우
back - 1과 front가 다른 경우

if (arr[front + 1] != arr[back] && arr[front] != arr[back - 1]) // 일반회문

else if (arr[front + 1] == arr[back])
    if (check(front + 1, back)) ok
else if (arr[front] == arr[back - 1]{
    if (check(front, back -1)) ok
}

else break;


 */
