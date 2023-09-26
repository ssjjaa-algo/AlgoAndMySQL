package Programmers.Algorithm;

class 연속된부분수열의합 {
    public int[] solution(int[] arr, int k) {

        int len = arr.length;

        int front = 0;
        int back = 0;

        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        int minFront = 0;
        int minBack = 0;
        while (back < len && front <= back) {

            if (sum + arr[back] < k) {
                sum += arr[back++];
            }

            else if (sum + arr[back] > k) {
                sum -= arr[front++];
            }

            else if (sum + arr[back] == k) {
                sum += arr[back];
                int temp = back - front;
                if (temp < minLen) {
                    minLen = temp;
                    minFront = front;
                    minBack = back;
                }
                back++;
            }
        }

        int[] answer = {minFront, minBack};
        return answer;
    }
}
