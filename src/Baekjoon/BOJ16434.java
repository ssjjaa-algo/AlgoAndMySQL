package Baekjoon;

import java.io.*;

public class BOJ16434 {

    static class Info {
        int num;
        long damage;
        long hp;

        public Info() {}

        public Info(int num, long damage, long hp) {
            this.num=num;
            this.damage=damage;
            this.hp=hp;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        long soldierDamage = Long.parseLong(input[1]);

        Info[] arr = new Info[N];
        int a;
        long b;
        long c;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            a= Integer.parseInt(input[0]);
            b= Long.parseLong(input[1]);
            c= Long.parseLong(input[2]);

            arr[i] = new Info(a,b,c);
        }

        System.out.println(binarySearch(1,Long.MAX_VALUE, N, soldierDamage,arr));


    }
    static private long binarySearch(long left, long right, int N, long soldierWeapon, Info[] arr) {

        long min = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;
            long soldierDamage = soldierWeapon;
            long curHp = mid;
            boolean flag = true;
            for (int i = 0; i < N; i++) {

                if (arr[i].num == 2) {
                    soldierDamage += arr[i].damage;
                    curHp = Math.min(curHp + arr[i].hp, mid);
                } else {
                    long attackCount = calculateAttackCount(soldierDamage, arr[i].hp);

                    curHp = curHp - ((attackCount - 1) * arr[i].damage);
                }

                if (curHp <= 0) {
                    left = mid + 1;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                right = mid - 1;
                min = Math.min(min, mid);
            }
        }
        return min;
    }
    private static long calculateAttackCount(long damage, long hp) {

        if (hp % damage == 0) return hp / damage;

        return (hp / damage) + 1;
    }
}