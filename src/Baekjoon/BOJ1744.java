package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1744 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;
    static boolean[] selected;
    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        init();
        System.out.println(calculate());
    }

    private static int calculate() {
        int choice;
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            if(selected[i]) continue; // i번째 숫자가 이미 선택된 숫자라면 넘어간다.
            choice = arr[i];
            selected[i] = true;
            // choice가 음수인 경우

            if (choice < 0) {
                if (arr[i + 1] <= 0) {
                    sum = sum + selectedNext(choice,i+1);
                }

                else {
                    sum = sum + choice;
                }

                continue;
            }

            // choice가 0인 경우

            else if (choice == 0) {
                continue;
            }

            // choice가 1인 경우

            else if (choice == 1) {
                sum = sum + choice;
                continue;
            }

            // choice가 양수인 경우

            else if (choice > 1) {

                if ((N - i + 1) % 2 == 1) { //
                    sum = sum + selectedNext(choice, i + 1);
                }
                else {
                    sum = sum + choice;
                }

                continue;
            }

        }

        if (!selected[N - 1]) sum += arr[N-1];
        return sum;
    }

    private static int selectedNext(int choice, int nextIdx) {
        selected[nextIdx] = true;
        return choice * arr[nextIdx];
    }

    private static void init() throws IOException{
        arr = new int[N];
        selected = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
    }
}
