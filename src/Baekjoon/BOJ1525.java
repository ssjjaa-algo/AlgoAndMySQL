package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BOJ1525 {

    static String ans = "";
    static String original = "";
    static Set<String> set = new HashSet<>();
    static int[] xDirection = {-1,0,1,0};
    static int[] yDirection = {0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(bfs());
    }

    private static int bfs() {

        Queue<String> q = new ArrayDeque<>();
        q.add(original);

        int cnt = 0;

        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; i++) {
                String temp = q.poll();

                if (temp.equals(ans)) return cnt;

                int idx = temp.indexOf("0");
                int x = idx / 3;
                int y = idx % 3;

                for (int j = 0; j < 4; j++) {

                    int nx = x + xDirection[j];
                    int ny = y + yDirection[j];

                    if (isInvalid(nx,ny)) continue;

                    char replace = temp.charAt(nx * 3 + ny);
                    StringBuilder sb = new StringBuilder();
                    sb.append(temp);

                    sb.setCharAt(idx, temp.charAt(nx * 3 + ny));
                    sb.setCharAt(nx * 3 + ny,'0');

                    if (!set.contains(sb.toString())) {
                        q.add(sb.toString());
                        set.add(sb.toString());
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    private static boolean isInvalid(int nx, int ny) {
        return nx < 0 || nx >= 3 || ny < 0 || ny >= 3;
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < 3; j++) {
                original += input[j];
                if(input[j].charAt(0) == '0') cnt++;
            }
        }

        int limit = 9 - cnt;
        int idx = 1;
        while (limit-- > 0) {
            ans += idx++;
        }

        while (cnt-- > 0) {
            ans += '0';
        }

        set.add(original);
    }
}

/*
1. 입력받는 배열을 String으로 변환한다.
    해당 String을 Set에 넣는다.
2. 정답 배열의 모습을 String ans로 둔다.

3. bfs
    1. q에 처음 String을 넣는다
    2. q에서 0의 위치를 찾아낸다 idx -> valueOf('0')
        1. 행은 idx / 3이 된다.
        2. 열은 idx % 3이 된다.
            - 4방 탐색을 시작한다
                예외) 범위 밖이면 continue

                1. String의 idx번째 원소와 ((nx * 3) + ny) 번째 원소와 바꾼다.
                예외) 이미 Set에 해당 String이 있다면 넣지 않는다.
                아니라면 Set에 String을 넣고 String 원상복구 시킨다.

    전체를 큐의 사이즈만큼 돌며 cnt++한다.
    String이 ans와 일치하는 경우 return cnt한다.


 */