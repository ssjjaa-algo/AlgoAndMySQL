package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 시작점을 기준으로 정렬, 끝나는 지점과 다음 시작점을 비교
 * 다음 시작점이 끝나는 지점보다 작은 경우 강의실 증가가 필요하다. pq에 삽입
 * 다음 시작점이 끝나는 지점보다 작은 경우 해당 끝나는 지점을 poll하고 새로운 끝나는 지점으로 갱신한다.
 * pq는 끝나는 값을 기준으로 오름차순 정렬되있으므로 다음 강의실이 이용 가능하면 change, 이용 못하면 삽입
 * pq의 size가 정답이 된다.
 */
public class BOJ11000 {

    static int N;

    static class Lecture {
        int start,end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static Lecture[] lectures;
    public static void main(String[] args) throws IOException {

        init();
        calculate();
    }

    private static void calculate() {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);

        for (Lecture lecture : lectures) {

            int start = lecture.start;
            int end = pq.peek();

            if (start >= end) {
                pq.poll();
            }

            pq.add(lecture.end);

        }

        System.out.println(pq.size());
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lectures = new Lecture[N];

        String[] input;
        int start, end;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            start = Integer.parseInt(input[0]);
            end = Integer.parseInt(input[1]);

            lectures[i] = new Lecture(start,end);
        }

        Arrays.sort(lectures,(o1,o2) -> {return Integer.compare(o1.start,o2.start);});
    }
}
