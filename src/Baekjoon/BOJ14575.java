package Baekjoon;

import java.io.*;

public class BOJ14575 {

    static class Participant {
        int left,right;
        public Participant(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    static int N,T;
    static Participant participants[];
    static String[] input;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int leftMax, rightMax;
    public static void main(String[] args) throws IOException{
        init();
        System.out.println(binarySearch());
    }

    private static boolean calculate(int mid) {

        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0 ; i < N; i++) {

            if (mid < participants[i].left) return false;
            leftSum += participants[i].left;
            rightSum += Math.min(mid,participants[i].right);
        }

        if (leftSum <= T && T <= rightSum) return true;

        return false;
    }

    private static int binarySearch() {

        int mid;
        int ans = Integer.MAX_VALUE;
        while (leftMax <= rightMax) {

            mid = (leftMax + rightMax) / 2;
            if(calculate(mid)) {
                ans = Math.min(ans,mid);
                rightMax = mid - 1;
            }
            else leftMax = mid + 1;
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        T = Integer.parseInt(input[1]);
        participants = new Participant[N];

        int left,right;
        for (int i = 0 ; i < N; i++) {
            input = br.readLine().split(" ");
            left = Integer.parseInt(input[0]);
            right = Integer.parseInt(input[1]);
            participants[i] = new Participant(left,right);
            leftMax = Math.max(leftMax,left);
            rightMax = Math.max(rightMax,right);
        }

    }


}
