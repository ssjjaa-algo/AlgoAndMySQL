package Programmers.Algorithm;

import java.util.*;

class 셔틀버스 {

    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public String solution(int n, int t, int m, String[] timetable) {

        makeMinute(timetable);

        int cnt = 0;
        int start = 540;
        int arrive = 540;
        for (int i = 0; i < n; i++) {
            cnt = 0;
            start = (540 + (t * i)) % 1440;

            while(!pq.isEmpty()) {
                int people = pq.peek();
                if (people <= start) {
                    cnt++;
                    arrive = pq.poll() - 1;
                }
                else break;

                if (cnt == m) break;
            }
        }

        if (cnt < m) arrive = start;

        String hour = Integer.toString(arrive / 60);
        String min = Integer.toString(arrive % 60);

        if (hour.length() == 1) hour = "0" + hour;
        if (min.length() == 1) min = "0" + min;

        return hour + ":" + min;
    }

    public void makeMinute(String[] timetable) {

        for (String s : timetable) {

            String[] res = s.split(":");
            int hour = Integer.parseInt(res[0]) * 60;
            int minute = Integer.parseInt(res[1]);
            pq.add(hour + minute);
        }
    }
}

