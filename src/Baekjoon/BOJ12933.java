package Baekjoon;

import java.io.*;
import java.util.*;
public class BOJ12933 {

    static char[] str;
    static char[] alphas = {'q','u','a','c','k'};
    static boolean[] visited;
    static boolean check;
    static List<Integer> choice = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        init();
        System.out.println(calculate());
    }

    public static int calculate() {

        int cnt = 0;
        int length = str.length;

        for (int i = 0; i < length; i++) {
            if (str[i] == 'q' && !visited[i]) {
                if(dfs(i,0,length,false)) cnt++;


                for (int num : choice) {
                     visited[num] = false;
                 }
                choice.clear();

            }
        }

        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                if (!dfs(i,0,length,false)) return -1;
            }
        }

        return cnt == 0 ? -1 : cnt;
    }

    public static boolean dfs(int start, int endOfQuack, int end, boolean flag) {

        if (endOfQuack == 5) {
            choice.clear();
            flag = true;
            endOfQuack = 0;
        }

        for (int i = start; i < end; i++) {

            if (!visited[i] && str[i] == alphas[endOfQuack]) { // 방문하지 않았고 다음 원소와 동일하다면

                visited[i] = true;
                choice.add(i);
                if(dfs(i + 1, endOfQuack + 1, end, flag)) return true;
            }
        }

        if (flag) return true;

        return false;
    }

    public static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        str = s.toCharArray();
        visited = new boolean[s.length()];

    }
}
