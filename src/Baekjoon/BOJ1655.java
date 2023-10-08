package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ1655 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input;
    static int N;

    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2,o1);
        }
    });

    public static void main(String[] args) throws IOException {
        calculate();
    }

    private static void calculate() throws IOException {

        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());

            if (maxHeap.size() == minHeap.size()) {
                maxHeap.add(num);
            }
            else minHeap.add(num);

            if (!minHeap.isEmpty()) {

                if (maxHeap.peek() > minHeap.peek()) {
                    int a = maxHeap.poll();
                    int b = minHeap.poll();

                    minHeap.add(a);
                    maxHeap.add(b);
                }
            }
            sb.append(maxHeap.peek()).append("\n");

        }

        System.out.print(sb);
    }
}
