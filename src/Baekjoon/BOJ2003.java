import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static int N, M;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {

        init();
        int res = calculate();
        System.out.println(res);
    }

    static int calculate() {
        int left = 0;
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            sum += arr[i];
            while (sum >= M) {
                if (sum == M) {
                    cnt++;
                }
                sum -= arr[left++];           
            }
        }
        
        return cnt;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        arr = new int[N];

        input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }
        
    }
}
