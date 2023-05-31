package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ5639 {
    static StringBuilder sb = new StringBuilder();
    static List<Integer> tree =new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        String data;
        while (true) {
            data = br.readLine();

            if (data == null || data.equals("")) break;
            tree.add(Integer.parseInt(data));
        }

        postOrder(0,tree.size()-1);

        wr.write(sb.toString());
        wr.close();
        br.close();
    }

    private static void postOrder(int start, int end) { // 이진 검색 트리니 binarySearch 이용하면 된다.

        if (start > end) return;

        int idx = start + 1; // root의 다음 지점부터 비교해서

        while( idx <= end && tree.get(start) > tree.get(idx)) idx++; // idx가 범위 안에 들어와있고 루트보다 큰 값이 나오기 전까지

        postOrder(start + 1,idx-1); // 왼쪽 자식들만 찾기
        postOrder(idx,end); // 오른쪽 자식들만 찾기
        sb.append(tree.get(start)).append("\n");

    }


}

