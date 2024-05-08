package Programmers.Algorithm;

class 단어변환 {

    static int ans = 100;
    static boolean[] visited;

    public int solution(String begin, String target, String[] words) {

        boolean canFind = false;
        visited = new boolean[words.length];
        for (String word : words) {
            if (word.equals(target)) {
                canFind = true;
                break;
            }
        }
        if (!canFind) return 0;
        dfs(0, begin, target, words);

        return ans == 100 ? 0 : ans;
    }

    public void dfs(int cnt, String begin, String target, String[] words) {

        if (begin.equals(target)) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (cnt >= ans) return;

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && check(begin, words[i])) {
                visited[i] = true;
                dfs(cnt + 1, words[i], target, words);
                visited[i] = false;
            }
        }
    }

    public boolean check(String begin, String target) {

        int len = begin.length();

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if (begin.charAt(i) != target.charAt(i)) cnt++;
        }

        return cnt == 1 ? true : false;
    }
}
