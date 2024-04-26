package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ1138 {

    static int[] arr;
    static LinkedList<Integer> list = new LinkedList<>();
    static int N;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        list.add(N);
        for (int i = N - 2; i >= 0; i--) {
            findPosition(i + 1, arr[i]);
        }

        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    private static void findPosition(int num, int people) {

        if (people == 0) {
            list.addFirst(num);
            return;
        }

        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {

            if (list.get(i) > num) {
                cnt++;
            }
            if (cnt == people) {
                list.add(i + 1, num);
                break;
            }

        }

    }


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
    }
}
