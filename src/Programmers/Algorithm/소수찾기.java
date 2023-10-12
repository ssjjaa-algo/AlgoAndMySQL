package Programmers.Algorithm;

import java.util.*;
/*

1. prime은 7자리까지이기 때문에, 10000000까지의 에라토스테네스의 체를 구한다.

2. 뽑아온 수들을 전체 dfs한다. 최대 7자리이기 때문에 충분하다
*/


class 소수찾기 {

    static boolean[] isPrime = new boolean[10000001];
    static StringBuilder sb = new StringBuilder();
    static String standard;
    static int answer = 0;
    static boolean[] visited;
    static Set<Integer> set = new HashSet<>();

    public int solution(String numbers) {

        int len = numbers.length();
        visited = new boolean[len];
        Prime();

        standard = numbers;

        for (int i = 1; i <= len; i++) {
            dfs(0,i,len);
        }

        return answer;
    }

    public void dfs(int cnt, int limit, int len) {

        if (cnt == limit) {

            int num = Integer.parseInt(sb.toString());

            if (!isPrime[num] && !set.contains(num)) {
                set.add(num);
                answer++;
            }
            return;
        }

        for (int i = 0; i < len; i++) {
            if (sb.length() == 0 && standard.charAt(i) == '0') continue;

            if (visited[i]) continue;

            sb.append(standard.charAt(i));
            visited[i] = true;

            dfs(cnt + 1, limit, len);

            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void Prime() {

        isPrime[0] = true;
        isPrime[1] = true;

        for (int i = 2; i < 10000000; i++) {

            if (!isPrime[i]) {
                for (int j = 2; i * j < 10000000; j++ ) {
                    isPrime[i * j] = true;
                }
            }

        }
    }
}
