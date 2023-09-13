package Programmers.Algorithm;
class 연속펄스부분수열의합 {
    public long solution(int[] sequence) {

        int[] arr1 = makeArr(sequence,1);
        int[] arr2 = makeArr(sequence,-1);

        long answer = Math.max(calculate(arr1),calculate(arr2));
        return answer;
    }

    public long calculate(int[] arr) {

        int length = arr.length;

        long [] dp = new long[length];
        dp[0] = arr[0];
        long max = dp[0];
        for (int i = 1; i < length; i++) {

            if (dp[i - 1] <= 0) dp[i] = arr[i];
            else dp[i] = dp[i-1] + arr[i];

            max = Math.max(max,dp[i]);
        }

        return max;
    }

    public int[] makeArr(int[] sequence, int num) {

        int[] arr = new int[sequence.length];
        int length = sequence.length;
        for (int i = 0 ; i < length; i++) {

            if (i % 2 == 0) arr[i] = sequence[i] * num;

            else arr[i] = sequence[i] * (num * -1);
        }

        return arr;
    }
}