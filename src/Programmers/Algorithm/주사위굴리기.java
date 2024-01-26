package Programmers.Algorithm;

import java.util.*;

class 주사위굴리기 {

    static boolean[] visited;
    static int ans = 0;
    static int[] answer;
    public int[] solution(int[][] dice) {

        init(dice);
        diceCombination(0,0,dice);

        return answer;
    }

    private void diceCombination(int cnt, int start, int[][] dice) {

        if (cnt == (dice.length / 2)) {
            int res = calculate(dice);
            if (res > ans) {
                ans = res;
                record(dice.length);
            }
            return;
        }

        for (int i = start; i < dice.length; i++) {
            visited[i] = true;
            diceCombination(cnt + 1, i + 1, dice);
            visited[i] = false;
        }
    }
    private void record(int len) {
        int idx = 0;
        for (int i = 0; i < len; i++)
        {
            if (visited[i]) answer[idx++] = i + 1;
        }

    }

    private int calculate(int[][] dice) {

        int len = dice.length;

        int[] aIdx = new int[len / 2];
        int[] bIdx = new int[len / 2];
        List<Integer> aResult = new ArrayList<>();
        List<Integer> bResult = new ArrayList<>();

        int a = 0;
        int b = 0;
        for (int i = 0; i < dice.length; i++) {

            if (visited[i]) {
                aIdx[a++] = i;
            }
            else {
                bIdx[b++] = i;
            }
        }

        makeResultArr(aIdx, bIdx, aResult, bResult, dice);
        Collections.sort(aResult);
        Collections.sort(bResult);
        return binarySearch(aResult, bResult);

    }

    private int binarySearch(List<Integer> aResult, List<Integer> bResult) {
        int win = 0;
        for (Integer target : aResult) {

            int left = 0;
            int right = bResult.size() - 1;

            while (left <= right) {

                int mid = (left + right) / 2;

                if (target > bResult.get(mid)) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
            win += left;
        }

        return win;
    }

    private void makeResultArr(int[] aIdx, int[] bIdx, List<Integer> aResult, List<Integer> bResult, int[][] dice) {

        sumCombination(0,0,aIdx,aResult, dice);
        sumCombination(0,0,bIdx,bResult, dice);
    }

    private void sumCombination(int cnt,int sum, int[] idx, List<Integer> result, int[][] dice) {

        if (cnt == dice.length / 2) {
            result.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            sumCombination(cnt + 1, sum + dice[idx[cnt]][i], idx, result, dice);
        }

    }

    private void init(int[][] dice) {

        int len = dice.length;
        visited = new boolean[len];
        answer = new int[len / 2];
    }

}