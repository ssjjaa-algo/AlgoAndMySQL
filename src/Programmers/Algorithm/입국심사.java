package Programmers.Algorithm;

import java.util.*;

class 입국심사 {
    public long solution(int n, int[] times) {

        long left = 1;
        long right = 100000 * 1000000000L;
        long ans = Long.MAX_VALUE;
        Arrays.sort(times);

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canJudge(mid, times, n)) {
                ans = Math.min(ans, mid);
                right = mid - 1;
            }
            else left = mid + 1;
        }

        return ans;
    }

    public boolean canJudge(long mid, int[] times, int n) {

        long cnt = 0;
        for (int i = 0; i < times.length; i++) {

            cnt += (mid / times[i]);
            if (cnt >= n) return true;
        }

        return false;
    }
}
