package Programmers.Algorithm;

class 네트워크dfs {

    public int solution(int n, int[][] computers) {

        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) { // 방문하지 않은 컴퓨터라면
            if (!visited[i]) {
                answer++;
                dfs(i,n,computers,visited);
            }
        }

        return answer;
    }

    public void dfs(int v, int n, int[][] computers, boolean[] visited) {

        visited[v] = true;

        for (int i = 0; i < n; i++) {
            if (computers[v][i] == 1 && !visited[i]) {
                dfs(i,n,computers,visited);
            }
        }
    }

}
