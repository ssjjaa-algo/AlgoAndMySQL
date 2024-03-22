package Programmers.Algorithm;

import java.util.*;

class 순위검색 {

    static HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {

        for (String s : info) {
            makeAllInfo(0,"", s.split(" "));
        }

        for (List<Integer> list : map.values()) {
            Collections.sort(list);
        }


        return calculate(query);
    }

    public int[] calculate(String[] query) {

        int[] res = new int[query.length];

        int idx = 0;
        for (String q : query) {

            String[] convertQueryToInfo = q.split(" and ");
            String[] lastQuery = convertQueryToInfo[3].split(" ");
            int score = Integer.parseInt(lastQuery[1]);
            convertQueryToInfo[3] = lastQuery[0];

            String makeQuery = String.join("",convertQueryToInfo);

            res[idx] = binarySearch(score, makeQuery);
            idx++;
        }

        return res;
    }

    public int binarySearch(int score, String makeQuery) {

        if(!map.containsKey(makeQuery)) return 0;

        List<Integer> scores = map.get(makeQuery);

        int left = 0;
        int right = scores.size() - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (scores.get(mid) >= score) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }

        }
        return scores.size() - left;
    }

    public void makeAllInfo(int depth, String query, String[] info) {

        if(depth == 4) {

            int score = Integer.parseInt(info[4]);
            if (!map.containsKey(query)) {
                map.put(query, new ArrayList<Integer>());
            }
            map.get(query).add(score);

            return;
        }

        makeAllInfo(depth + 1, query + "-", info);
        makeAllInfo(depth + 1, query + info[depth], info);

    }
}