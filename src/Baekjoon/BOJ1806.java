package Baekjoon;

import java.io.*;

public class BOJ1806 {

    /*
    10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
    1) 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중,
    2) 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
     */
    public static void main(String []args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 수열의 크기
        int S = Integer.parseInt(input[1]); // 찾아야 하는 합

        int[] arr = new int[N];

        input = br.readLine().split(" ");

        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(input[i]); // 원소 입력받기
        }


        sb.append(twoPointer(0,0, N,S, arr));
        System.out.println(sb);

        wr.close();
        br.close();
        /*
        투포인터 문제풀이
        1) front head 1개, back head 1개

        front head는 고정시킨 상태로 backhead를 계속해서 늘린다.
           1) 같은 경우 수열 길이 갱신 후 ok
           2) 더 큰 경우 수열 길이 갱신은 하지 않고 front head가 가리키는 값 뺀 후 front head 늘리기
         */


    }

    private static int twoPointer(int front, int back, int N, int S, int arr[]) {

        int sum = 0;
        int length = Integer.MAX_VALUE;
        for (int i=0; i < N; i++) {
            sum += arr[back++]; // back Pointer는 계속해서 증가하는 경우로 가야함.

            while (sum >= S) { // 이상의 값을 가질 때까지
                length = Math.min(length, (back - front)); // 길이를 갱신해본다
                sum = sum - arr[front++]; // front를 증가하여 줄여보며 길이를 최소화 시키는 과정임.

            }

        }

        return length == Integer.MAX_VALUE? 0 : length;
    }

}