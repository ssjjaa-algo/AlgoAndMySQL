package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ8983 {

    static class Node {
        int x,y;

        public Node(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }
    static int M,N,L;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static String[] input;
    static int[] shotPosition;
    static Node[] animal;
    public static void main(String[] args) throws IOException{

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;
        int left,right;
        for (int i = 0; i < N; i++) { // 동물들의 개수에 대하여

            left = 0;
            right = M - 1; // M개의 사대를 binarySearch
            if(binarySearch(0, M - 1, animal[i])) cnt++;
        }

        return cnt;
    }
    private static boolean binarySearch(int left, int right, Node animal) {

        while (left <= right) {
            int mid = (left + right) / 2;

            int distance = Math.abs(shotPosition[mid] - animal.x) + animal.y;

            if (distance <= L) return true;

            if (shotPosition[mid] < animal.x) {
                left = mid + 1;
            }
            else {
               right = mid - 1;
            }
        }

        return false;
    }

    private static void init() throws IOException {
        input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);

        shotPosition = new int[M];
        animal = new Node[N];

        input = br.readLine().split(" ");

        for (int i = 0; i < M; i++) {
            shotPosition[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(shotPosition);

        int x, y;
        for (int i = 0 ; i < N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            animal[i] = new Node(x,y);
        }

    }
}
