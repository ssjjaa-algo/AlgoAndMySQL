package Baekjoon;

import java.io.*;

public class BOJ10713 {

    static class Info {

        long useTicket;
        long useCard;
        long priceOfCard;

        public Info(long useTicket, long useCard, long priceOfCard) {
            this.useTicket = useTicket;
            this.useCard = useCard;
            this.priceOfCard = priceOfCard;
        }

    }
    static int N,M;
    static int[] sequence;
    static Info[] infos;
    static int[] countOfTrain;
    static long ans;
    public static void main(String[] args) throws IOException {

        init();
        countTrain();
        calculate();
        System.out.println(ans);
    }

    private static void calculate() {

        int count = 0 ;
        for (int i = 1; i <= N - 1; i++) {

            count += countOfTrain[i];

            long useTicket = infos[i].useTicket * count;
            long useCard = infos[i].useCard * count + infos[i].priceOfCard;

            ans += Math.min(useTicket,useCard);
        }
    }

    private static void countTrain() {

        for (int i = 1; i < M; i++) { // 이동하는 만큼

            if (sequence[i] < sequence[i + 1]) {
                countOfTrain[sequence[i]]++;
                countOfTrain[sequence[i+1]]--;
            }

            else if (sequence[i] > sequence[i + 1]) {
                countOfTrain[sequence[i+1]]++;
                countOfTrain[sequence[i]]--;
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        sequence = new int[M + 1];
        infos = new Info[N + 1];
        countOfTrain = new int[N + 1];

        input = br.readLine().split(" ");
        for (int i = 1; i <= M; i++) {
            sequence[i] = Integer.parseInt(input[i - 1]);
        }

        long a,b,c;
        for (int i = 1; i <= N - 1; i++) {
            input = br.readLine().split(" ");
            a = Long.parseLong(input[0]);
            b = Long.parseLong(input[1]);
            c = Long.parseLong(input[2]);

            infos[i] = new Info(a,b,c);
        }
    }
}
