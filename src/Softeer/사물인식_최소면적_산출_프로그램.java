package Softeer;

import java.io.*;
import java.util.*;
public class 사물인식_최소면적_산출_프로그램{
    static class Coordination {
        int x,y;

        public Coordination(int x, int y) {
            this.x=x;
            this.y=y;
        }
    }

    static List<Coordination> arr[];
    static int ans = Integer.MAX_VALUE;
    static boolean[] visitedColor;
    static int N,K;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        arr = new ArrayList[K + 1];


        for (int i=1; i<=K; i++)
            arr[i] = new ArrayList<>();

        int x,y,color;
        for (int i=0; i<N; i++) {
            input = br.readLine().split(" ");
            x = Integer.parseInt(input[0]);
            y = Integer.parseInt(input[1]);
            color = Integer.parseInt(input[2]);
            arr[color].add(new Coordination(x,y));
        }


        for (int i=0; i < arr[1].size(); i++) {
            dfs(arr[1].get(i).x,arr[1].get(i).x,arr[1].get(i).y,arr[1].get(i).y,2);
        }
        System.out.println(ans);
    }

    private static void dfs(int xMax, int xMin, int yMax, int yMin, int cnt) {

        if (cnt == K+1) {
            ans = Math.min(ans, calculate(xMax, xMin, yMax, yMin));
            return;
        }

        for (int i=0; i < arr[cnt].size(); i++) {

            int x1 = xMax;
            int x2 = xMin;
            int y1 = yMax;
            int y2 = yMin;
            Coordination temp = arr[cnt].get(i);
            x1 = Math.max(x1,temp.x);
            x2 = Math.min(x2,temp.x);
            y1 = Math.max(y1,temp.y);
            y2 = Math.min(y2,temp.y);

            if (calculate(x1,x2,y1,y2) >= ans) continue; // 시간초과 해
            // 갱신을 할 필요가 없는 경우를 가지치기 한다.

            dfs(x1,x2,y1,y2,cnt + 1);
        }
    }

    private static int calculate(int xMax, int xMin, int yMax, int yMin) {

        return Math.abs(xMax - xMin) * Math.abs(yMax - yMin);
    }


}
