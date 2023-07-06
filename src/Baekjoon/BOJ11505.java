package Baekjoon;

import java.io.*;

public class BOJ11505 {

    static long[] arr;
    static long[] tree;
    final static long mod = 1000000007;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        String[] input ;

        input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);

        init(N);
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        makeTree(0,N-1,1);

        int count = M + K;
        int a,b,c;
        while (count > 0) {
            input = br.readLine().split(" ");
            a = Integer.parseInt(input[0]);
            b = Integer.parseInt(input[1]);
            c = Integer.parseInt(input[2]);

            if (a == 1) { // update, b에 있는 수를 c로 바꾼다.
                change(b-1,c,N-1);
            }
            else { // multiplation
                sb.append(multiplation(b-1,c-1,0,N-1,1)).append("\n");
            }
            count--;
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void change(int index, int val, int end) {
        arr[index] = val;
        update(0,end,index,1,val);
    }

    private static long update(int start, int end, int index, int node, int val) {
        if (index < start || index > end) return tree[node];

        if (start == end) return tree[node] = val;

        int mid = (start + end) / 2;

        long l = update(start, mid, index, node * 2, val);
        long r = update(mid + 1, end, index, node * 2 + 1, val);
        return tree[node] = (l * r) % mod;
    }

    private static long makeTree(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];

        int mid = (start + end) / 2;

        return tree[node] = (makeTree(start, mid, node * 2) * makeTree(mid + 1, end, node * 2 +1)) % mod;
    }

    private static long multiplation(int left, int right, int start, int end, int node) {
        // 1. 현재 구간이 구하려는 범위 안에 들어와있지 않은 경우
        if (left > end || right < start) return 1;
        // 2. 현재 구간이 구하려는 범위 안에 들어와있는 경우

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;

        long l = multiplation(left,right,start,mid,node * 2);
        long r = multiplation(left,right,mid + 1, end, node * 2 + 1);

        return (l * r) % mod;
    }


    private static void init(int N) {
        arr = new long[N];
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }
}
