package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BOJ1700 {

    static int N,K;
    static int[] command;
    static int[] sequenceOfCommand;
    static Set<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            set.add(command[i]);
        }

        // print();

        int currentCommand = 0;
        int change = 0;
        for (int i = N; i < K; i++) {
            currentCommand = command[i];

            if (set.size() < N || set.contains(currentCommand)) {
                set.add(currentCommand);
                continue;
            }
            change = changePlug(i);

            set.remove(change);
            set.add(currentCommand);
            cnt++;
        }

        return cnt;
    }

    private static int changePlug(int idx) {

        for (int i = idx; (i < K); i++) {

            if (set.contains(command[i]) && sequenceOfCommand[command[i]] == 0) {
                sequenceOfCommand[command[i]] = i;
            }
        }

        Iterator<Integer> iter = set.iterator();

        int num = 0;
        int maxNum = 0;
        int change = 0;
        while (iter.hasNext()) {
            num = iter.next();
            if (sequenceOfCommand[num] == 0 ) {
                change = num;
                break;
            }

            if (maxNum < sequenceOfCommand[num]) {
                maxNum = sequenceOfCommand[num];
                change = num;
            }
        }

        Arrays.fill(sequenceOfCommand,0);

        // System.out.println("변화 대상 : " + change);
        return change;
    }

    private static void print() {

        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        command = new int[K + 1];
        sequenceOfCommand = new int[K + 1];

        input = br.readLine().split(" ");

        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(input[i]);
        }
    }
}
