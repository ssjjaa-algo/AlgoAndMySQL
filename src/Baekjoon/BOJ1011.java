package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1011 {

    static List<Long> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        int tc = Integer.parseInt(br.readLine());

        list.add(0L);
        list.add(1L);
        list.add(2L);
        list.add(3L);

        init(2,4);

        String[] input;
        int len = list.size();
        int x,y,left,right;
        long distance = 0;
        for (int i=0; i<tc; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            distance = y - x;
            binarySearch(0,len - 1,distance);
        }

        wr.write(sb.toString());
        wr.close();
        br.close();

        /*
        1 - 1 ( 1 )
        2 - 1 1 ( 2 )
        3 - 1 1 1 ( 3 )
        5 - 1 2 1 1 ( 4)
        7 - 1 2 2 1 1 ( 5)
        10 - 1 2 3 2 1 1 ( 6)
        13 - 1 2 3 3 2 1 1 ( 7 )
        17 - 1 2 3 4 3 2 1 1 ( 8)
        21 - 1 2 3 4 4 3 2 1 1 ( 9)
        26 - 1 2 3 4 5 4 3 2 1 1 (10)
        31 - 1 2 3 4 5 5 4 3 2 1 1 (11)
        37 - 1 2 3 4 5 6 5 4 3 2 1 1 (12)
        43 - 1 2 3 4 5 6 6 5 4 3 2 1 1 (13)

        1, 2, 3, 5, 7, 10, 13, 17, 21,
        dp[1] = 1
        dp[2] = 2
        dp[3] = 3
        dp[4] = 5
        dp[5] = 7
        dp[6] = 10
        dp[7] = 13


         */
    }

    private static void binarySearch(int left, int right, long distance) {

        long data = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            data = list.get(mid);
            if (data == distance) {
                sb.append(mid).append("\n");
                return;
            }

            else if (data > distance ) {
                if (distance >= list.get(mid - 1)) {
                    sb.append(mid - 1).append("\n");
                    return;
                }
                right = mid - 1;
            }
            else {
                if (distance < list.get(mid + 1)) {
                    sb.append(mid).append("\n");
                    return;
                }
                left = mid + 1;
            }
        }
    }

    private static void init(int plus, int idx) {
        while(true){

            if (list.get(idx-1) >= Math.pow(2,31)) break;
            list.add(list.get(idx-1) + plus);
            list.add(list.get(idx) + plus);

            idx += 2;
            plus++;

        }
    }
}
