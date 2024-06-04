package Programmers.Algorithm;

import java.util.*;

public class 보석쇼핑 {

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>(Arrays.asList(gems));

        int start = 0;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < gems.length; i++) {

            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);

            while (map.get(gems[start]) > 1) {
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }

            if (map.size() == set.size()) {

                if (max > (i - start)) {
                    max = i - start;
                    answer[0] = start + 1;
                    answer[1] = i + 1;
                }
            }
        }
        return answer;
    }
}
