package Programmers.Algorithm;

import java.util.*;

class 요격시스템 {
    public int solution(int[][] targets) {

        Arrays.sort(targets, (o1, o2) -> {
            return Integer.compare(o1[1],o2[1]);
        });

        int cnt = 1;
        int end = targets[0][1];

        for (int i = 1; i < targets.length; i++) {

            if (targets[i][0] < end && targets[i][1] >= end) continue;

            end = targets[i][1];
            cnt++;
        }

        return cnt;
    }
}
