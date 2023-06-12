package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ8980 {

    static class Delivery implements Comparable<Delivery>{
        int from;
        int to;
        int cost;

        public Delivery(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Delivery o) { // 받는 마을을 기준으로 오름차순
            return Integer.compare(this.to, o.to);
        }

    }

    static Delivery[] deliveries;
    static int[] capcity;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int C =Integer.parseInt(input[1]);

        int M = Integer.parseInt(br.readLine());

        init(N,M,C);

        int from, to, cost;
        for (int i=0; i<M; i++) {
            input = br.readLine().split(" ");

            from = Integer.parseInt(input[0]);
            to = Integer.parseInt(input[1]);
            cost= Integer.parseInt(input[2]);
            deliveries[i] = new Delivery(from,to,cost);
        }

        Arrays.sort(deliveries); // 도착 지점을 기준을 오름차순 정렬


        int res = 0;
        for (int i=0; i<M; i++) {

            Delivery delivery = deliveries[i];
            cost = delivery.cost;
            from = delivery.from;
            to = delivery.to;
            if (capcity[delivery.from] == 0) continue;

            int addDeliveryCapacity = 0;

            for (int start = from; start < to; start++) { // 범위가 가장 작은 곳부터 시작하니
                //시작점으로부터 가져오기 시작한다면 가져올 수 있는 최대의 양을 알아서 가져오게 된다.
                if (capcity[start] - cost <= 0) { // 모든 택배를 가져오지 못하는 경우

                    addDeliveryCapacity = capcity[start]; // 가져올 수 있는 만큼 가져오기
                    cost = capcity[start]; // 다음 지점에서 가져올 수 있는 택배는 현재에서 보낸 만큼임.
                    capcity[start] = 0; // 이제 여기서 가져올 수 있는 택배상자는 없다.

                }

                else {
                    addDeliveryCapacity = cost; // 모든 택배를 가져올 수 있다면 가져간다.
                    capcity[start] = capcity[start] - cost;
                }

            }
            res += cost; // 누적
        }

        System.out.println(res);
    }

    private static void init(int N, int M,int C) {
        capcity = new int[N+1];
        deliveries = new Delivery[M];

        for (int i=1; i<=N; i++)
            capcity[i] = C;
    }
}
