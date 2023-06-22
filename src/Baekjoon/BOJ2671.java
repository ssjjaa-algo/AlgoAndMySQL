package Baekjoon;

import java.io.*;

public class BOJ2671 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        if(check(str)) System.out.println("SUBMARINE");

        else System.out.println("NOISE");
    }

    private static boolean check(String str) {

        int length = str.length();
        int idx = 0;


        while (idx < length) {

            if (str.charAt(idx) == '0') {

                if (idx + 1 >= length) return false;
                if (str.charAt(idx + 1) != '1') return false;

                idx += 2;
            }

            else {
                if (idx + 3 >= length) return false; // 1001이 나올 수 없는 경우 fail
                if (str.charAt(idx + 1) != '0' || str.charAt(idx + 2) != '0') return false; // 첫 번째 1 뒤 0이 2개아닌 경우 fail

                //현재 idx 위치 : 첫 번째 1
                idx++; // 0이 나타나는 위치로 옮겨주고
                while(idx < length && str.charAt(idx) == '0') { // 0이 끝날 때까지 옮겨준다
                    idx++;
                }
                if (idx >= length) return false;// 두 번째 1이 출현하기 전에 스트링이 끝났으면 fail
                idx++;

                while (idx < length && str.charAt(idx) == '1') {
                    if (idx + 2 < length && str.charAt(idx + 1) == '0' && str.charAt(idx + 2) == '0') break;

                    idx++;
                }
            }
        }


        return true;

    }
}
