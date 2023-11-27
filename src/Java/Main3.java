package Java;


import java.io.*;
import java.util.*;
public class Main3 {

    static int[][] dice = {{1,2,3,4,5,6},{3,3,3,3,4,4},{1,3,3,4,4,4},{1,1,4,4,5,5}};
    static int[] choices;
    static int[] notChoices;
    static int[] res;
    static int[] idx;
    static boolean[] visited;
    static int max = 0;
    static int a = 0;
    static int b = 0;
    public static void main(String[] args) {

        init();
        choiceDice(0,0, dice.length);

        System.out.println(max);
        for (int i = 0; i < dice.length / 2; i++) {
            System.out.print((res[i] + 1) + " ");
        }
    }

    private static void choiceDice(int start, int cnt, int len) {

        if (cnt == len / 2) {
            calculate();

            if (a > max) {
                res = Arrays.stream(choices).toArray();
                max = a;
            }
            a = 0;
            b = 0;
            return;
        }

        for (int i = start; i < len; i++) {
            choices[cnt] = i;
            visited[i] = true;
            choiceDice(i + 1, cnt + 1, len);
            visited[i] = false;
        }
    }

    private static void calculate() {

        int cnt = 0;
        for (int i = 0; i < dice.length; i++) {
            if(!visited[i]) {
                notChoices[cnt] = i;
                cnt++;
            }
        }

        choiceDfs(dice.length / 2, 0, 0);
    }

    private static boolean notChoiceDfs(int len, int cnt, int sum, int compare) {
        if (cnt == len) {
            if (compare > sum) {

                int temp = 1;

                for (int i = 0; i < len; i++) {
                    temp = temp * idx[i];
                }

                a += temp;
                return true;

            }

            return false;
        }

        for (int i = 5; i >= 0; i--) {
            idx[cnt] = i + 1;
            if (notChoiceDfs(len, cnt + 1, sum + dice[notChoices[cnt]][i],compare))
                return true;

        }
        return false;
    }

    private static void choiceDfs(int len, int cnt, int sum) {

        if (cnt == len) {
            notChoiceDfs(len,0,0,sum);
            return;
        }

        for (int i = 5; i >= 0; i--) {

            choiceDfs(len, cnt + 1, sum + dice[choices[cnt]][i]);
        }
    }


    private static void init() {

        int len =dice.length;

        choices = new int[len / 2];
        notChoices = new int[len / 2];
        res = new int[len / 2];
        visited = new boolean[len];
        idx = new int[len / 2];

    }
}
