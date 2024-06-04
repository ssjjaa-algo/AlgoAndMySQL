package Programmers.Algorithm;

class 징검다리건너기 {
    public int solution(int[] stones, int k) {

        int left = 0;
        int right = 200000000; // 최대값

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canGo(mid, k, stones)) {
                left = mid + 1;
            }
            else right = mid - 1;
        }

        return left - 1;
    }

    public boolean canGo(int mid, int people, int[] stones) {

        int len = stones.length;
        int count = 0;
        for (int i = 0; i < len; i++) {

            if (stones[i] < mid) count++;
            else count = 0;

            if (count >= people) return false;
        }
        return true;
    }
}
