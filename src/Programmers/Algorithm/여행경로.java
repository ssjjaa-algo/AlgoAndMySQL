package Programmers.Algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class 여행경로 {

    boolean[] visited;
    List<String> list = new ArrayList<>();
    public String[] solution(String[][] tickets) {

        visited = new boolean[tickets.length];

        dfs("ICN", "ICN", 0, tickets);
        Collections.sort(list);
        return list.get(0).split(" ");
    }

    public void dfs(String from, String path, int depth, String[][] tickets) {

        if (depth == tickets.length) {
            list.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (visited[i]) continue;

            if (tickets[i][0].matches(from)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth + 1, tickets);
                visited[i] = false;
            }
        }

    }
}
