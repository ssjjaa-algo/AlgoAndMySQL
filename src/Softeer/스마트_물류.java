import java.io.*;
import java.util.*;

public class 스마트_물류 {

    static int N, K;
    static char[] arr;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
        
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        arr = br.readLine().toCharArray();
    }

    private static void calculate() {

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (arr[i] == 'P') {
                if (checkFront(i) || checkBack(i)) {
                    cnt++;
                }
             }
        }
        
        System.out.println(cnt);
    }

    private static boolean checkFront(int i) {
        for (int j = i - K; j < i; j++) {
            if (j < 0) continue;
            if (arr[j] == 'H') {
                arr[j] = 'X';
                return true;
            }
        }
        return false;
    }

    private static boolean checkBack(int i) {
        for (int j = i + 1; j < N && j <= i + K; j++) {
            if (j >= N) break;
            if (arr[j] == 'H') {
                arr[j] = 'X';
                return true;
            }
        }
        return false;
    }
}
