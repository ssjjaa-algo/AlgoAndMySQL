package Programmers.Algorithm;

import java.util.*;

class 디스크컨트롤러 {
    public int solution(int[][] jobs) {

        Arrays.sort(jobs, (o1, o2) -> {return Integer.compare(o1[0], o2[0]);});
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {return Integer.compare(o1[1], o2[1]);});

        int len = jobs.length;
        int start = 0;
        int res = 0;
        int finish = 0;

        while (!pq.isEmpty() || start < len) {

            while(start < len && jobs[start][0] <= finish) {
                pq.add(jobs[start++]);
            }

            if (pq.isEmpty()) finish = jobs[start][0];

            else { // 1개 씩 처리
                int[] job = pq.poll();
                res += (job[1] + finish - job[0]);
                finish += job[1];
            }


        }

        return (res / len);
    }
}
