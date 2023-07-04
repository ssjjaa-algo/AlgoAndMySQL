package Baekjoon;

import java.io.*;


public class BOJ2042 {

    static long[] arr;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        // 1. N의 개수에 대하여 트리의 높이를 구하고 필요한 트리의 공간을 구성한다.
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);

        tree = new long[treeSize];
        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        init(0,N-1,1); // segement tree 만들러 가자.

        int count = M + K; // M + K번의 명령어를 수행한다.

        long a,b,c;
        while (count > 0 ) {
            input = br.readLine().split(" ");
            a = Long.parseLong(input[0]);
            b = Long.parseLong(input[1]);
            c = Long.parseLong(input[2]);

            if (a == 1) { // update
                update((int)b - 1,c,N-1);
            }

            else { // calcaulate
                sb.append(sum((int)b -1,(int)c -1,0,N-1,1)).append("\n");
            }
            count--;
        }

        wr.write(sb.toString());
        wr.close();
        br.close();
    }

    /*
    세그먼트 트리 구성
    N개의 원소에 대하여 구간합을 표현하기 위해 통상 2^(|log2N|+ 1)개의 공간을 가진 트리 필요
    N이 2의 제곱꼴 형태로 떨어진다면 2^|log2N|
     */

    private static long init(int start, int end, int node) {
        if (start == end) { // leaf
            return tree[node] = arr[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static long sum(int left, int right, int start, int end, int node) {
        // left, right 구간에 해당하는 합을 구하는 메서드

        // 1. left와 right 안에 없는 합을 구하는 경우
        if (left > end || right < start) return 0;

        // 2. left와 right 안에 있는 합을 구하는 경우
        if (left <= start && end <= right) return tree[node];

        // 3. 그 외의 경우 재귀로 합을 구한다.
        int mid = (start + end) / 2;
        return sum(left, right, start, mid, node * 2) + sum(left, right, mid + 1, end, node * 2 + 1);
    }

    private static void update(int index, long val, int end) {

        long diff = val - arr[index]; // diff만큼의 변경이 있을 것.
        arr[(int) index] = val;
        update(0, end,1,index,diff); // 해당하는 구간들에 대하여 diff만큼의 변화를 갱신한다.
    }

    private static void update(int start, int end, int node, int index, long diff) {

        if (index < start || index > end) return; // 해당 인덱스를 포함하고 있지 않은 범위는 갱신 필요 없음.
        tree[node] += diff;

        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

}
