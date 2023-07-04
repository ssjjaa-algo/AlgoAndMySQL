package Algorithm;

import java.io.*;
import java.util.*;

public class SegmentTree {

    static int[] arr = {1,9,3,8,4,5,5,9,10,3,4,5};
    static int[] tree =new int[48];
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        init(0, 11, 1);
    }

    private static int init(int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(start,mid,node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    private static int sum(int start, int end, int node, int left, int right) {

        if (left > end || right < start) return 0; // 범위 밖인 경우 호출 x

        if (left <= start && end <= right) return tree[node];

        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right) +
                sum(mid + 1, end, node * 2 + 1, left, right);

    }

    void update(int start, int end, int node, int index, int diff) {
        if (index < start || index > end) return;

        tree[node] += diff;
        if (start == end) return;
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

}
