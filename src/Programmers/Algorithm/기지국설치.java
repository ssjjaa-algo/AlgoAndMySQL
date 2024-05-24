package Programmers.Algorithm;

class 기지국설치 {
    public int solution(int n, int[] stations, int w) {

        int answer = 0;
        int idx = 0;
        int len = stations.length;
        int cur = 1;

        while (cur <= n) {

            if (idx < len) {

                if (cur < stations[idx] - w) {
                    answer++;
                    cur += (2 * w) + 1;
                }

                else if (cur >= stations[idx] - w && cur <= stations[idx] + w) {
                    cur = stations[idx++] + w + 1;
                }
            }
            else {
                answer++;
                cur += (2 * w) + 1;
            }

        }

        return answer;
    }
}
