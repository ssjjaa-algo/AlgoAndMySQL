package Baekjoon;

import java.io.*;

public class BOJ1561 {

    static int N,M;
    static int[] arr;
    static long minPeople = 0;
    static long maxMinute = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[M];
        input = br.readLine().split(" ");
        for (int i=0; i<M; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        if (N <= M) { //
            System.out.println(N);
            return;
        }


        binarySearch();

        // 최악의 경우 : N이 20억이고 탈 수 있는 놀이기구 1개에 30분
        // 최대 600억 대기해야함.
        // 이진 탐색의 right는 600억.

        int find = 0;

        for (int i=0; i<M; i++) {

            if (minPeople == N) {
                break;
            }

            if (maxMinute % arr[i] == 0) {
                minPeople++;
                find = i;
            }

        }
        System.out.println(find + 1);

    }

    private static void binarySearch() {

        long left = 0;
        long right = 2000000000L * 30;

        while (left <= right) {

            long mid = (left + right) / 2;
            long people = countOfPeople(mid);

            if (people < N) {
                left = mid + 1; // 시간을 늘려본다.
                minPeople = people; // 이 값부터 하나하나씩 더해가며 확인할 것이므로.
            }

            else {
                right = mid - 1;
                maxMinute = mid; // 줄이고 줄이다 보면, 이 경우를 마주하게 되고 right가 더 작아지는 순간이 오는데
                //maxMinute 분 안에 답이 있다.즉, minPeople과 countOfPeople(maxMinute) 사이에 답이 있는 것.
                //그래서 maxMinute을 갱신해둔 후, 이진 탐색이 끝나면 maxMinute / arr[i]의 계산을 반복해서 몇 번째 놀이기구 탔는지 찾을 거다.
            }
        }
    }

    private static long countOfPeople(long mid) {

        long sum = 0;
        for (int i=0; i < M; i++) {
            sum = sum + (mid/arr[i]);
        }
        return sum + M;
    }
}
