package Programmers.Algorithm;

import java.util.*;

class 숫자게임 {
    public int solution(int[] A, int[] B) {
        int answer = -1;
        Arrays.sort(A);
        Arrays.sort(B);

        return calculate(A, B);
    }

    public int calculate(int[] A, int[] B) {

        int len = B.length;
        int aIdx = 0;
        int bIdx = 0;

        int score = 0;
        while (bIdx < len) {

            if (A[aIdx] < B[bIdx]) {
                score++;
                aIdx++;
            }

            bIdx++;
        }

        return score;
    }
}

