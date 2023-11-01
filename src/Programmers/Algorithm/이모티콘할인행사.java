package Programmers.Algorithm;

import java.util.*;

class 이모티콘할인행사 {

    static int[] discount = {10,20,30,40};
    static int[] choice;
    static int[] prices;
    static int countOfUser;
    static int[][] infoOfUser;
    static int[] ans = new int[2];

    public int[] solution(int[][] users, int[] emoticons) {

        init(users,emoticons);

        dfs(0,emoticons.length);

        return ans;
    }

    public void init(int[][] users, int[] emoticons) {
        int length = emoticons.length;
        countOfUser = users.length;

        choice = new int[length];
        prices = new int[length];
        infoOfUser = new int[countOfUser][2];

        for (int i = 0; i < countOfUser; i++) {
            infoOfUser[i][0] = users[i][0];
            infoOfUser[i][1] = users[i][1];
        }

        for (int i = 0; i < length; i++) {
            prices[i] = emoticons[i];
        }

    }

    public int[] calculate(int end) {

        int plus = 0;
        int price = 0;

        for (int i = 0; i < countOfUser; i++) {
            int sum  =0;
            for (int j = 0; j < end; j++) {
                if (choice[j] < infoOfUser[i][0]) continue;

                sum += (prices[j] / 100) * (100 - choice[j]);
            }

            if (sum >= infoOfUser[i][1]) { // 이모티콘 플러스 가입 대상
                plus++;
            }
            else {
                price += sum;
            }
        }

        return new int[] {plus,price};
    }

    public void dfs(int start,int end) {

        if (start == end) {

            int[] arr = calculate(end);

            if (arr[0] > ans[0]) {
                ans[0] = arr[0];
                ans[1] = arr[1];
            }

            else if (arr[0] == ans[0] && arr[1] > ans[1]) {
                ans[1] = arr[1];
            }

            return;
        }

        for (int i = start; i < end; i++) {

            for (int j = 0; j < 4; j++) {
                choice[i] = discount[j];
                dfs(i + 1, end);
            }
        }
    }


}
