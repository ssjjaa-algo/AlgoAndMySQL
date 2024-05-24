package Programmers.Algorithm;

import java.util.Arrays;

class 최고의집합 {
    public int[] solution(int n, int s) {

        if (s < n) return new int[]{-1};

        int[] answer = new int[n];
        int standard = s / n;
        int rest = s % n;

        for (int i = 0; i < n; i++) {
            answer[i] = standard;

            if (rest > 0) {
                answer[i]++;
                rest--;
            }
        }

        Arrays.sort(answer);
        return answer;
    }
}

