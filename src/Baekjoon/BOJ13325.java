package Baekjoon;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13325 {

    static int N;
    static int[] tree;
    static int size;
    static int ans = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        size = (int) Math.pow(2, N + 1);
        tree = new int[size];

        String[] input = br.readLine().split(" ");
        for (int i = 2; i < size; i++) {
            tree[i] = Integer.parseInt(input[i - 2]);
        }
        dfs(1);
        System.out.println(ans);
    }

    private static int dfs(int number) {

        if (number  * 2 >= size) {
            ans += tree[number];
            return tree[number];
        }

        int leftSum = dfs(number * 2);
        int rightSum = dfs(number * 2 + 1);
        ans += tree[number] + Math.abs(leftSum - rightSum); // 자기 자신의 가중치를 더하고, 자식의 최댓값 만큼 추가해주어야 한다.
        return tree[number] + Math.max(leftSum, rightSum); // 큰 값을 부모에게 알려준다.
    }
}
