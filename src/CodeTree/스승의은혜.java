package CodeTree;

import java.io.*;
import java.util.*;

public class 스승의은혜 {

    static int N;
    static int cost;

    static class Info implements Comparable<Info> {

        int price;
        int delivery;

        public Info(int price, int delivery) {
            this.price=price;
            this.delivery=delivery;
        }

        @Override
        public int compareTo(Info o) {

            if (this.price + this.delivery == o.price + o.delivery) {
                return Integer.compare(this.delivery,o.delivery);
            }

            return Integer.compare(this.price + this.delivery, o.price + o.delivery);
        }

    }

    static Info[] infos;

    public static void main(String[] args) throws IOException{
        // 여기에 코드를 작성해주세요.

        init();
        System.out.println(calculate());
    }

    private static int calculate() {

        boolean check = true;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            if (infos[i].price + infos[i].delivery <= cost) {
                cost -= infos[i].price + infos[i].delivery;
            }

            else {
                if (check) {
                    check = false;
                    cost -= infos[i].price / 2 + infos[i].delivery;

                    if (cost < 0) {
                        break;
                    }
                }
                else {
                    break;
                }
            }
            cnt++;
        }

        return cnt;
    }


    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        cost = Integer.parseInt(input[1]);

        infos = new Info[N];
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");

            infos[i] = new Info(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
        }

        Arrays.sort(infos);
    }
}